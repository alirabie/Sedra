package sedra.appsmatic.com.sedra.Activites;

import android.content.ComponentName;
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
import com.mobile.connect.PWConnect;
import com.mobile.connect.exception.PWException;
import com.mobile.connect.exception.PWProviderNotInitializedException;
import com.mobile.connect.payment.PWCurrency;
import com.mobile.connect.payment.PWPaymentParams;
import com.mobile.connect.payment.credit.PWCreditCardType;
import com.mobile.connect.service.PWProviderBinder;

import java.util.Locale;

import sedra.appsmatic.com.sedra.BuildConfig;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class PaymentScreen extends AppCompatActivity {

    CardForm cardForm;
    TextView txtDes;
    Button pay;


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

        //setup items
        cardForm=(CardForm)findViewById(R.id.cardform);
        txtDes=(TextView)findViewById(R.id.payment_amount);
        pay=(Button)findViewById(R.id.btn_pay);


        txtDes.setText("0.0 SR");
        pay.setText(String.format("Payer %s",txtDes.getText()));












        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {

                /*
                try {
                    paymentParams = _binder
                            .getPaymentParamsFactory()
                            .createCreditCardPaymentParams(5.0,
                                    PWCurrency.SAUDI_ARABIA_RIYAL,
                                    "A test charge",
                                    card.getName(),
                                    PWCreditCardType.VISA,
                                    card.getNumber(),
                                    card.getExpYear().toString(),
                                    card.getExpMonth().toString(),
                                    card.getCVC());

                } catch (PWProviderNotInitializedException e) {
                    e.printStackTrace();
                    return;
                } catch (PWException e) {
                    e.printStackTrace();
                    return;
                }



             */   Toast.makeText(PaymentScreen.this,"Name : "+card.getName()+" | Last 4 digits : "+card.getLast4(),Toast.LENGTH_LONG).show();
            }

        });







    }
}
