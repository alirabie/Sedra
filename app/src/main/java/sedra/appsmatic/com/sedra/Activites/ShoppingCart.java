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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
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
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import sedra.appsmatic.com.sedra.API.Models.PaymentRes.ResPaymentAction;
import sedra.appsmatic.com.sedra.Adabters.PaymentTypeAdapter;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;
import sedra.appsmatic.com.sedra.RequestPayment;

public class ShoppingCart extends AppCompatActivity  {

    ImageView payBtn,activeDis,emptycart;
    private BetterSpinner cridetCards;
    private String[] contentArray ={"VISA","MasterCard"};
    private int cardTypeFlag=-1;
    String price ="100.0";
    private ResPaymentAction requestPayment;
    private IProviderBinder binder;
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
        requestPayment=new ResPaymentAction();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
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
            activeDis.setImageResource(R.drawable.activationbtn_ar);
            emptycart.setImageResource(R.drawable.emptybasketicon_ar);

        } else {
            payBtn.setImageResource(R.drawable.paybtn_en);
            activeDis.setImageResource(R.drawable.activation_btn_en);
            emptycart.setImageResource(R.drawable.emptybasketicon_en);
        }


        //pay action
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> paymentBrands = new LinkedHashSet<String>();
                paymentBrands.add("VISA");
                paymentBrands.add("MASTER");
                CheckoutSettings checkoutSettings = new CheckoutSettings(requestPayment.getId(), paymentBrands);
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                intent.putExtra(CheckoutActivity.CHECKOUT_SETTINGS, checkoutSettings);
                startActivityForResult(intent, CheckoutActivity.CHECKOUT_ACTIVITY);

                Log.e("hhhhh",requestPayment.getId());



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
                Toast.makeText(getApplication(),"transaction successful",Toast.LENGTH_LONG).show();
                break;
            case CheckoutActivity.RESULT_CANCELED:
            /* shopper canceled the checkout process */
                Toast.makeText(getApplication(),"shopper canceled the checkout process",Toast.LENGTH_LONG).show();
                break;
            case CheckoutActivity.RESULT_ERROR:
            /* error occurred */
                PaymentError error = data.getExtras().getParcelable(CheckoutActivity.CHECKOUT_RESULT_ERROR);
                Toast.makeText(getApplication(),"No checkout Id",Toast.LENGTH_LONG).show();
        }
    }







}
