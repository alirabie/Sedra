package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sedra.appsmatic.com.sedra.Fragments.DiscountedProducts;
import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class DiscountedScreen extends AppCompatActivity {

    public static android.support.v4.app.FragmentManager fragmentManager;
    public static android.support.v4.app.FragmentTransaction fragmentTransaction;
    private ImageView flwerBtn,giftBtn,cookiesBtn,plantsBtn,home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discounted_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //setup top buttons
        flwerBtn=(ImageView)findViewById(R.id.dis_flower_btn);
        giftBtn=(ImageView)findViewById(R.id.dis_gift_btn);
        cookiesBtn=(ImageView)findViewById(R.id.dis_cookies_btn);
        plantsBtn=(ImageView)findViewById(R.id.dis_plants_btn);
        home=(ImageView)findViewById(R.id.dis_home_btn);



        DiscountedProducts products2 = new DiscountedProducts();
        Bundle bundle = new Bundle();
        //put here id to send to fragment
        products2.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.dis_fragmentcontener, products2);
        fragmentTransaction.commit();


        flwerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Switch activation button
                flwerBtn.setImageResource(R.drawable.flowerbtnactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

                //start flowers fragment
                DiscountedProducts flowers = new DiscountedProducts();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id", "13");
                flowers.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dis_fragmentcontener, flowers);
                fragmentTransaction.commit();


            }
        });

        giftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Switch activation button
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

                //start gifts fragment
                DiscountedProducts gifts = new DiscountedProducts();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","14");
                gifts.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dis_fragmentcontener, gifts);
                fragmentTransaction.commit();

            }
        });

        cookiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Switch activation button
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

                //start cookies fragment
                DiscountedProducts cookies = new DiscountedProducts();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","15");
                cookies.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dis_fragmentcontener, cookies);
                fragmentTransaction.commit();
            }
        });

        plantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnactive);

                //start plants fragment
                DiscountedProducts plants = new DiscountedProducts();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","16");
                plants.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dis_fragmentcontener, plants);
                fragmentTransaction.commit();

            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //un active all buttons
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);

                DiscountedProducts products2 = new DiscountedProducts();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                products2.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.dis_fragmentcontener, products2);
                fragmentTransaction.commit();
            }
        });


    }


}
