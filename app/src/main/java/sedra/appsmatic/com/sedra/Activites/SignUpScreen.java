package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SignUpScreen extends AppCompatActivity {

    private ImageView signUpBtn,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        signUpBtn=(ImageView)findViewById(R.id.signup_btn);
        home=(ImageView)findViewById(R.id.home_btn_login);


        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            signUpBtn.setImageResource(R.drawable.signup_btn_arabic);
        }else{
            signUpBtn.setImageResource(R.drawable.signupsc_btn_en);
        }




        //Home btn action
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpScreen.this.finish();
                startActivity(new Intent(SignUpScreen.this,SplashScreen.class));
            }
        });






    }

    @Override
    public void onBackPressed() {
        final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(SignUpScreen.this);
        dialogBuilder
                .withTitle(getResources().getString(R.string.sedra))
                .withDialogColor(R.color.colorPrimary)
                .withTitleColor("#FFFFFF")
                .withIcon(getResources().getDrawable(R.drawable.icon))
                .withDuration(700)                                          //def
                .withEffect(Effectstype.RotateBottom)
                .withMessage(getResources().getString(R.string.exitfromapp))
                .withButton1Text(getResources().getString(R.string.yes))
                .withButton2Text(getResources().getString(R.string.no))
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SignUpScreen.this.finish();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }
}
