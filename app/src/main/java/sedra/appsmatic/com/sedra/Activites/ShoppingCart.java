package sedra.appsmatic.com.sedra.Activites;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import sedra.appsmatic.com.sedra.Adabters.PaymentTypeAdapter;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class ShoppingCart extends AppCompatActivity {

    ImageView payBtn,activeDis,emptycart;
    private BetterSpinner cridetCards;
    private String[] contentArray ={"VISA","MasterCard"};
    private int cardTypeFlag=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_screen);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }




        payBtn=(ImageView)findViewById(R.id.pay_btn);
        activeDis=(ImageView)findViewById(R.id.active_desc_code_activ_btn);
        emptycart=(ImageView)findViewById(R.id.imageView3);
        cridetCards=(BetterSpinner)findViewById(R.id.cridet_card_list);
        cridetCards.setAdapter(new PaymentTypeAdapter(ShoppingCart.this,R.layout.spinner_value_layout,contentArray));
        cridetCards.setHint(getResources().getString(R.string.cridet));
        cridetCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cardTypeFlag=position;
            }
        });





        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            payBtn.setImageResource(R.drawable.paybtn_ar);
            activeDis.setImageResource(R.drawable.activationbtn_ar);
            emptycart.setImageResource(R.drawable.emptybasketicon_ar);

        }else{
            payBtn.setImageResource(R.drawable.paybtn_en);
            activeDis.setImageResource(R.drawable.activation_btn_en);
            emptycart.setImageResource(R.drawable.emptybasketicon_en);
        }









    }
}
