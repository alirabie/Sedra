package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Locale;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SplashScreen extends AppCompatActivity {

   private ImageView langBtn,loginBtn,signUpbtn,gustBtn;
    private RelativeLayout bg;
    private LinearLayout btnsBox;
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_splash_screen);

        //Check login status
        if(!SaveSharedPreference.getCustomerId(SplashScreen.this).isEmpty()){
            startActivity(new Intent(SplashScreen.this,CountriesScreen.class));
            SplashScreen.this.finish();
        }





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
        bg=(RelativeLayout)findViewById(R.id.splash_bg);
        btnsBox=(LinearLayout)findViewById(R.id.buttons_container);


        //Animate buttons box
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.toptodown);
        btnsBox.clearAnimation();
        btnsBox.setAnimation(anim);


        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            langBtn.setImageResource(R.drawable.englishbtn);
            loginBtn.setImageResource(R.drawable.signin_arabic);
            signUpbtn.setImageResource(R.drawable.signup_arabic);
            gustBtn.setImageResource(R.drawable.guestbtn_arabic);
            bg.setBackground(getResources().getDrawable(R.drawable.splashscreen_arabic));

        }else{
            langBtn.setImageResource(R.drawable.arabicbtn);
            loginBtn.setImageResource(R.drawable.signin_btn_en);
            signUpbtn.setImageResource(R.drawable.signup_btn_en);
            gustBtn.setImageResource(R.drawable.guest_btn_en);
            bg.setBackground(getResources().getDrawable(R.drawable.backg_en));
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
                    langBtn.setImageResource(R.drawable.arabicbtn);
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
                Animation anim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.alpha);
                loginBtn.clearAnimation();
                loginBtn.setAnimation(anim);
                startActivity(new Intent(getApplicationContext(),SignInScreen.class));
                SplashScreen.this.finish();
            }
        });



        //Sign up button action
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.alpha);
                signUpbtn.clearAnimation();
                signUpbtn.setAnimation(anim);
                startActivity(new Intent(getApplicationContext(),SignUpScreen.class));
                SplashScreen.this.finish();
            }
        });




        //Gust button action
        gustBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.alpha);
                gustBtn.clearAnimation();
                gustBtn.setAnimation(anim);
                startActivity(new Intent(getApplicationContext(), CountriesScreen.class));
                SplashScreen.this.finish();
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.pressagain, Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
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
