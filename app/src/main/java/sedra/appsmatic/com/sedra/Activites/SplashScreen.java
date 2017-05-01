package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SplashScreen extends AppCompatActivity {

   private ImageView langBtn,loginBtn,signUpbtn,gustBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //set app lang
        setLang(R.layout.activity_splash_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //setup items
        langBtn=(ImageView)findViewById(R.id.lang_btn);
        loginBtn=(ImageView)findViewById(R.id.login_btn);
        signUpbtn=(ImageView)findViewById(R.id.signup_btn);
        gustBtn=(ImageView)findViewById(R.id.gust_btn);


        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
           langBtn.setImageResource(R.drawable.englishbtn);
        }else{
            langBtn.setImageResource(R.drawable.ic_menu_camera);
        }

        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            langBtn.setBackgroundResource(R.drawable.ripple);
            loginBtn.setBackgroundResource(R.drawable.ripple);
            signUpbtn.setBackgroundResource(R.drawable.ripple);
            gustBtn.setBackgroundResource(R.drawable.ripple);
        }

        //Lang switch button action
        langBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( SaveSharedPreference.getLangId(getApplicationContext()).equals("ar")) {
                    SaveSharedPreference.setLangId(getApplicationContext(), "en");
                    langBtn.setImageResource(R.drawable.ic_menu_camera);
                    SplashScreen.this.finish();
                    startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                }else {
                    SaveSharedPreference.setLangId(getApplicationContext(), "ar");
                    langBtn.setImageResource(R.drawable.englishbtn);
                    SplashScreen.this.finish();
                    startActivity(new Intent(getApplicationContext(), SplashScreen.class));
                }
            }
        });







        //Login action button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(getApplicationContext(),SignInScreen.class));
            }
        });



        //Sign up button action
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpScreen.class));
            }
        });




        //Gust button action
        gustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CountriesScreen.class));
            }
        });


    }




    // Change language method
    public  void setLang(int layout){
        String languageToLoad = SaveSharedPreference.getLangId(this);
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(layout);
    }
}
