package sedra.appsmatic.com.sedra.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class Filter extends AppCompatActivity {

    private ImageView closeBtn,filterBtnGo;
    private EditText searcInput,priceInput;
    private BetterSpinner filterCategories;
    private BetterSpinner filterPrice;
    private BetterSpinner filterTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        closeBtn=(ImageView)findViewById(R.id.close_filter_btn);
        filterBtnGo=(ImageView)findViewById(R.id.filter_go_btn);
        searcInput=(EditText)findViewById(R.id.search_input);
        priceInput=(EditText)findViewById(R.id.filter_price_input);

        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            filterBtnGo.setImageResource(R.drawable.filter_btn_ar);

        }else{
            filterBtnGo.setImageResource(R.drawable.filter_btn_en);
        }
        filterCategories = (BetterSpinner) findViewById(R.id.filter_category_spinner);
        filterPrice =(BetterSpinner)findViewById(R.id.filter_price_arr_spinner);
        filterTime =(BetterSpinner)findViewById(R.id.filter_time_arr_spinner);

        filterCategories.setAdapter(new ArrayAdapter<>(this, R.layout.drop_down_list_custome));
        filterPrice.setAdapter(new ArrayAdapter<>(this, R.layout.drop_down_list_custome));
        filterTime.setAdapter(new ArrayAdapter<>(this, R.layout.drop_down_list_custome));
        filterCategories.setHint(getResources().getString(R.string.filtercat));
        filterCategories.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterPrice.setHint(getResources().getString(R.string.priceselect));
        filterPrice.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterTime.setHint(getResources().getString(R.string.delivertime));
        filterTime.setHintTextColor(getResources().getColor(R.color.colorPrimary));








        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter.this.finish();
            }
        });


        filterBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Products products2 = new Products();
                Bundle bundle = new Bundle();
                products2.setArguments(bundle);
                //put here id to send to fragment
               Home.fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentcontener,
                                products2)
                        .addToBackStack(null).commitAllowingStateLoss();
                Filter.this.finish();
            }
        });







    }
}
