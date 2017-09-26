package sedra.appsmatic.com.sedra.Activites;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
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
import com.oppwa.mobile.connect.checkout.meta.CheckoutSettings;
import com.oppwa.mobile.connect.exception.PaymentError;
import com.oppwa.mobile.connect.exception.PaymentException;
import com.oppwa.mobile.connect.provider.Connect;
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
    private String[] contentArray ={"VISA","MasterCard"};
    private int cardTypeFlag=-1;
    String price ="100.0";
    private RecyclerView itemsList;
    private ResPaymentAction requestPayment;
    private IProviderBinder binder;
    private ProgressBar progressBar;
    private TextView emptyFlag,totalPrice,finalTotalprice;
    private Boolean isReadyToPay;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (IProviderBinder) service;
        /* we have a connection to the service */
            try {
                binder.initializeProvider(Connect.ProviderMode.TEST);
            } catch (PaymentException ee) {
	    /* error occurred */
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
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
                cardTypeFlag = position;
            }
        });


        //Request Payment Checkout id
        try {
            String json=RequestPayment.request(Double.parseDouble(price),"SAR");
            Log.e("jjjjjj",json);
            Type type = new TypeToken<ResPaymentAction>() {}.getType();
            Gson gson = new Gson();
            requestPayment= gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }


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


                //Create New Order .
                //Check if order items is empty or not
                if(!orderItems.isEmpty()) {
                    NewOrder newOrder = new NewOrder();
                    Order order = new Order();
                    order.setCustomerId(Integer.parseInt(SaveSharedPreference.getCustomerId(ShoppingCart.this)));
                    order.setOrderItems(orderItems);
                    order.setBillingAddress(SaveSharedPreference.getCustomerInfo(ShoppingCart.this).getCustomers().get(0).getBillingAddress());
                    order.setPaymentMethodSystemName("Payments.Manual");
                    newOrder.setOrder(order);
                    Gson gson = new Gson();
                    //log request body
                    Log.e("New Order request ", gson.toJson(newOrder));

                    //place order on server
                    Generator.createService(SedraApi.class).placeNewOrder(newOrder).enqueue(new Callback<OrderResponse>() {
                        @Override
                        public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                            if(response.isSuccessful()){

                                if(response.body().getOrders()!=null){
                                    Toast.makeText(ShoppingCart.this,getResources().getString(R.string.orderplace),Toast.LENGTH_LONG).show();

                                }else {
                                    Toast.makeText(ShoppingCart.this,"there is an error in order placement response body",Toast.LENGTH_LONG).show();
                                }
                                //Gson gson1=new Gson();
                                //Log.e("New Order response ", gson1.toJson(response.body()));

                            }else {

                                try {
                                    Toast.makeText(ShoppingCart.this,"Response not success from order placement : "+response.errorBody().string(),Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<OrderResponse> call, Throwable t) {
                            Toast.makeText(ShoppingCart.this,"Connection failed from order placement"+t.getMessage(),Toast.LENGTH_LONG).show();
                            Log.e("errrr",t.getMessage());
                        }
                    });

                }else {

                    //empty order items


                }










               //Process Payment
                if(isReadyToPay){
                    Set<String> paymentBrands = new LinkedHashSet<String>();
                    paymentBrands.add("VISA");
                    paymentBrands.add("MASTER");
                    CheckoutSettings checkoutSettings = new CheckoutSettings(requestPayment.getId(), paymentBrands);
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    intent.putExtra(CheckoutActivity.CHECKOUT_SETTINGS, checkoutSettings);
                    startActivityForResult(intent, CheckoutActivity.CHECKOUT_ACTIVITY);
                    Log.e("hhhhh",requestPayment.getId());
                }else {
                    NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ShoppingCart.this);
                    dialogBuilder
                            .withTitle(getResources().getString(R.string.sedra))
                            .withDialogColor(R.color.colorPrimary)
                            .withTitleColor("#FFFFFF")
                            .withIcon(getResources().getDrawable(R.drawable.icon))
                            .withDuration(700)                                          //def
                            .withEffect(Effectstype.RotateBottom)
                            .withMessage(getResources().getString(R.string.paynotallaw))
                            .show();
                }




            }
        });









    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ConnectService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onPause() {
        super.onPause();
        orderItems.clear();
    }

    @Override
    protected void onStop() {
        super.onStop();

        unbindService(serviceConnection);
        stopService(new Intent(this, ConnectService.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case CheckoutActivity.RESULT_OK:
            /* transaction successful */
                Toast.makeText(getApplication(),"transaction successful"+" Checkout Id : "+requestPayment.getId(),Toast.LENGTH_LONG).show();
                ShoppingCart.this.finish();
                break;
            case CheckoutActivity.RESULT_CANCELED:
            /* shopper canceled the checkout process */
                Toast.makeText(getApplication(),"shopper canceled the checkout process"+" Checkout Id : "+requestPayment.getId(),Toast.LENGTH_LONG).show();
                ShoppingCart.this.finish();
                break;
            case CheckoutActivity.RESULT_ERROR:
            /* error occurred */
                PaymentError error = data.getExtras().getParcelable(CheckoutActivity.CHECKOUT_RESULT_ERROR);
                Toast.makeText(getApplication(),"No checkout Id"+" Checkout Id : "+requestPayment.getId(),Toast.LENGTH_LONG).show();
                ShoppingCart.this.finish();
        }
    }







}
