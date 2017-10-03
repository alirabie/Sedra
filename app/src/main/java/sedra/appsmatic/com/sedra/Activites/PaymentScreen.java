package sedra.appsmatic.com.sedra.Activites;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobile.connect.PWConnect;
import com.mobile.connect.exception.PWException;
import com.mobile.connect.exception.PWProviderNotInitializedException;
import com.mobile.connect.payment.PWCurrency;
import com.mobile.connect.payment.PWPaymentParams;
import com.mobile.connect.payment.credit.PWCreditCardType;
import com.mobile.connect.service.PWProviderBinder;
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

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Locale;

import sedra.appsmatic.com.sedra.API.Models.PaymentRes.ResPaymentAction;
import sedra.appsmatic.com.sedra.BuildConfig;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;
import sedra.appsmatic.com.sedra.RequestPayment;

public class PaymentScreen extends AppCompatActivity  implements ITransactionListener {

    CardForm cardForm;
    TextView txtDes;
    Button pay;
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
        setContentView(R.layout.activity_payment_screen);



        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



        //Request Payment Checkout id
        try {
            String json= RequestPayment.request(100.0, "SAR");
            Log.e("jjjjjj", json);
            Type type = new TypeToken<ResPaymentAction>() {}.getType();
            Gson gson = new Gson();
            requestPayment= gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        //setup items
        cardForm=(CardForm)findViewById(R.id.cardform);
        txtDes=(TextView)findViewById(R.id.payment_amount);
        pay=(Button)findViewById(R.id.btn_pay);
        txtDes.setText(getIntent().getStringExtra("totalPrice"));
        pay.setText(String.format("Payer %s",txtDes.getText()));




















        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {

                try {

                    //Adapt month format when one char add 0 on left
                    String expMonth="";
                    if(card.getExpMonth().toString().length()==1){
                        expMonth="0"+card.getExpMonth();
                    }else {
                        expMonth=card.getExpMonth().toString();
                    }


                    //collect card data
                    PaymentParams paymentParams = new CardPaymentParams(
                            requestPayment.getId(),
                            getIntent().getStringExtra("cardBrand"),
                            card.getNumber(),
                            card.getName(),
                            expMonth,
                            card.getExpYear()+"",
                            card.getCVC()
                    );


                    //mack transaction
                    Transaction transaction = null;
                    transaction = new Transaction(paymentParams);
                    binder.submitTransaction(transaction);


                } catch (PaymentException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

                binder.addTransactionListener(PaymentScreen.this);

                Toast.makeText(PaymentScreen.this,"Name : "+card.getExpYear()+" | Last 4 digits : "+card.getExpMonth(),Toast.LENGTH_LONG).show();
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
    public void paymentConfigRequestSucceeded(CheckoutInfo checkoutInfo) {
        Toast.makeText(getApplication(),"config success"+" Checkout Id : "+requestPayment.getId()+checkoutInfo.getAmount()+"",Toast.LENGTH_LONG).show();
    }

    @Override
    public void paymentConfigRequestFailed(PaymentError paymentError) {
        Toast.makeText(getApplication(),"config error"+" Checkout Id : "+requestPayment.getId()+paymentError.getErrorMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void transactionCompleted(Transaction transaction) {
        Toast.makeText(getApplication(),"Sucsess"+" Checkout Id : "+requestPayment.getId()+transaction.getAlipaySignedOrderInfo(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void transactionFailed(Transaction transaction, PaymentError paymentError) {
        Log.e("Not sucsess"," Checkout Id : "+requestPayment.getId()+paymentError.getErrorInfo());
    }
}
