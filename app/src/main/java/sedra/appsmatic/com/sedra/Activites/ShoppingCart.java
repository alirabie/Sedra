package sedra.appsmatic.com.sedra.Activites;

import android.animation.LayoutTransition;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oppwa.mobile.connect.checkout.dialog.CheckoutActivity;
import com.oppwa.mobile.connect.checkout.dialog.PaymentButtonFragment;
import com.oppwa.mobile.connect.checkout.meta.CheckoutSecurityPolicyMode;
import com.oppwa.mobile.connect.checkout.meta.CheckoutSettings;
import com.oppwa.mobile.connect.exception.PaymentError;
import com.oppwa.mobile.connect.exception.PaymentException;
import com.oppwa.mobile.connect.payment.CheckoutInfo;
import com.oppwa.mobile.connect.payment.PaymentParams;
import com.oppwa.mobile.connect.payment.card.CardPaymentParams;
import com.oppwa.mobile.connect.provider.Connect;
import com.oppwa.mobile.connect.provider.ITransactionListener;
import com.oppwa.mobile.connect.provider.Transaction;
import com.oppwa.mobile.connect.service.ConnectService;
import com.oppwa.mobile.connect.service.IProviderBinder;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Orders.NewOrder;
import sedra.appsmatic.com.sedra.API.Models.Orders.Order;
import sedra.appsmatic.com.sedra.API.Models.Orders.OrderItem;
import sedra.appsmatic.com.sedra.API.Models.Orders.OrderResponse;
import sedra.appsmatic.com.sedra.API.Models.PaymentRes.ResPaymentAction;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ResCartItems;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.CartAdb;
import sedra.appsmatic.com.sedra.Adabters.PaymentTypeAdapter;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;
import sedra.appsmatic.com.sedra.RequestPayment;

public class ShoppingCart extends AppCompatActivity  {

    private List<OrderItem>orderItems=new ArrayList<>();
    ImageView payBtn,activeDis,emptycart;
    private BetterSpinner cridetCards;
    private String[] contentArray ={"VISA","MASTER"};
    private String cardBrand="";
    String price ="100.0";
    private RecyclerView itemsList;
    private ResPaymentAction requestPayment;
    private ProgressBar progressBar;
    private TextView emptyFlag,totalPrice,finalTotalprice;
    private Boolean isReadyToPay;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_shopping_cart_screen);
        totalPrice=(TextView)findViewById(R.id.total_price);
        finalTotalprice=(TextView)findViewById(R.id.final_total_price);
        progressBar = (ProgressBar)findViewById(R.id.progressbar_cart);
        requestPayment=new ResPaymentAction();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        isReadyToPay=false;

        emptyFlag=(TextView)findViewById(R.id.cartimptyflag);
        emptyFlag.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);







        if(SaveSharedPreference.getCustomerId(ShoppingCart.this).isEmpty()){
            emptyFlag.setVisibility(View.VISIBLE);
            emptyFlag.setText(getResources().getString(R.string.loginplease));
        }else{

            //get list of customer cart items
            Generator.createService(SedraApi.class).getCartItems(SaveSharedPreference.getCustomerId(ShoppingCart.this)).enqueue(new Callback<ResCartItems>() {
                @Override
                public void onResponse(Call<ResCartItems> call, Response<ResCartItems> response) {

                    if(response.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        if(response.body().getShoppingCarts().isEmpty()){
                            emptyFlag.setVisibility(View.VISIBLE);
                            isReadyToPay=false;
                        }else {
                            emptyFlag.setVisibility(View.INVISIBLE);
                            itemsList=(RecyclerView)findViewById(R.id.shopping_cart_list);
                            itemsList.setLayoutManager(new LinearLayoutManager(ShoppingCart.this));
                            itemsList.setAdapter(new CartAdb(response.body(), ShoppingCart.this));
                            isReadyToPay=true;

                            //fill order items
                            if(!response.body().getShoppingCarts().isEmpty()) {

                                for (int i = 0; i < response.body().getShoppingCarts().size(); i++) {
                                    OrderItem orderItem = new OrderItem();
                                    orderItem.setProductId(response.body().getShoppingCarts().get(i).getProductId());
                                    orderItem.setQuantity(response.body().getShoppingCarts().get(i).getQuantity());
                                    orderItems.add(orderItem);
                                }

                                //Place new Order to server
                                Home.placeNewOrder(orderItems,ShoppingCart.this);

                            }else {
                                orderItems.clear();
                            }


                            //Calc total price for cart
                            Double total=0.0;
                            if(!response.body().getShoppingCarts().isEmpty()) {
                                for (int i = 0; i < response.body().getShoppingCarts().size(); i++) {
                                    total = total + response.body().getShoppingCarts().get(i).getQuantity() * response.body().getShoppingCarts().get(i).getProduct().getPrice();
                                }
                                totalPrice.setText(total+" "+getResources().getString(R.string.sr));
                                finalTotalprice.setText(total+" "+getResources().getString(R.string.sr));
                            }







                        }

                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Response not Success from Shopping cart List  ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResCartItems> call, Throwable t) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Response Failure from Shopping cart List  ", Toast.LENGTH_LONG).show();



                }
            });

        }

        payBtn = (ImageView) findViewById(R.id.pay_btn);
        activeDis = (ImageView) findViewById(R.id.active_desc_code_activ_btn);
        emptycart = (ImageView) findViewById(R.id.imageView3);
        cridetCards = (BetterSpinner) findViewById(R.id.cridet_card_list);
        cridetCards.setAdapter(new PaymentTypeAdapter(ShoppingCart.this, R.layout.spinner_value_layout, contentArray));
        cridetCards.setHint(getResources().getString(R.string.cridet));
        cridetCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardBrand=contentArray[position];
            }
        });




        //Set images languages
        if (SaveSharedPreference.getLangId(this).equals("ar")) {
            payBtn.setImageResource(R.drawable.paybtn_ar);
            activeDis.setImageResource(R.drawable.active_btn_ar);
            emptycart.setImageResource(R.drawable.emptybasketicon_ar);

        } else {
            payBtn.setImageResource(R.drawable.paybtn_en);
            activeDis.setImageResource(R.drawable.activecodeshoppingcartbtn_en);
            emptycart.setImageResource(R.drawable.emptybasketicon_en);
        }























        //pay action
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                payBtn.clearAnimation();
                payBtn.setAnimation(anim);

                //Check if card brand selected
                if(cridetCards.getText().toString().isEmpty()){
                    cridetCards.setError(getResources().getString(R.string.selectcard));
                }else {
                    startActivity(new Intent(ShoppingCart.this, PaymentScreen.class)
                            .putExtra("cardBrand", cardBrand)
                            .putExtra("totalPrice", totalPrice.getText()));
                    ShoppingCart.this.finish();
                }



            }


        });









    }









    @Override
    protected void onPause() {
        super.onPause();
        orderItems.clear();
    }

}
