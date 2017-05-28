package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

import com.payfort.fort.android.sdk.base.FortSdk;
import com.payfort.fort.android.sdk.base.callbacks.FortCallBackManager;
import com.payfort.fort.android.sdk.base.callbacks.FortCallback;
import com.payfort.sdk.android.dependancies.base.FortInterfaces;
import com.payfort.sdk.android.dependancies.models.FortRequest;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.Map;

import sedra.appsmatic.com.sedra.Adabters.PaymentTypeAdapter;
import sedra.appsmatic.com.sedra.PayFort.IPaymentRequestCallBack;
import sedra.appsmatic.com.sedra.PayFort.PayFortData;
import sedra.appsmatic.com.sedra.PayFort.PayFortPayment;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class ShoppingCart extends AppCompatActivity implements IPaymentRequestCallBack {

    ImageView payBtn,activeDis,emptycart;
    private BetterSpinner cridetCards;
    private String[] contentArray ={"VISA","MasterCard"};
    private int cardTypeFlag=-1;
    private FortCallBackManager fortCallback= null;
    final FortRequest fortRequest=new FortRequest();
    String price ="100.0";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_screen);
        fortCallback = FortCallback.Factory.create();
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


        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestForPayfortPayment();;
            }
        });


    }


        private void requestForPayfortPayment() {
            PayFortData payFortData = new PayFortData();
            if (!TextUtils.isEmpty(price) ){
                payFortData.amount = String.valueOf((int) (Float.parseFloat(price) * 100));// Multiplying with 100, bcz amount should not be in decimal format
                payFortData.command = PayFortPayment.PURCHASE;
                payFortData.currency = PayFortPayment.CURRENCY_TYPE;
                payFortData.customerEmail = "readyandroid@gmail.com";
                payFortData.language = PayFortPayment.LANGUAGE_TYPE;
                payFortData.merchantReference = String.valueOf(System.currentTimeMillis());
                PayFortPayment payFortPayment = new PayFortPayment(this, this.fortCallback, this);
                payFortPayment.requestForPayment(payFortData);
            }
        }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PayFortPayment.RESPONSE_PURCHASE) {
            fortCallback.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onPaymentRequestResponse(int responseType, PayFortData responseData) {

        if (responseType == PayFortPayment.RESPONSE_GET_TOKEN) {
            Toast.makeText(this, "Token not generated", Toast.LENGTH_SHORT).show();
            Log.e("onPaymentResponse", "Token not generated");
        } else if (responseType == PayFortPayment.RESPONSE_PURCHASE_CANCEL) {
            Toast.makeText(this, "Payment cancelled", Toast.LENGTH_SHORT).show();
            Log.e("onPaymentResponse", "Payment cancelled");
        } else if (responseType == PayFortPayment.RESPONSE_PURCHASE_FAILURE) {
            Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
            Log.e("onPaymentResponse", "Payment failed");
        } else {
            Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show();
            Log.e("onPaymentResponse", "Payment successful");
        }

    }
}
