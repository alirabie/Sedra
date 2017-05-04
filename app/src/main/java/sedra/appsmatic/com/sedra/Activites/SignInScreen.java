package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SignInScreen extends AppCompatActivity {

    private TextView forgetPassBtn,createNewAccount;
    private ImageView signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Setup items
        forgetPassBtn=(TextView)findViewById(R.id.forgetpassword_txt_btn);
        createNewAccount=(TextView)findViewById(R.id.newaccount_txt_btn);
        signInBtn=(ImageView)findViewById(R.id.login_btn);

        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            signInBtn.setImageResource(R.drawable.signin_btn);

        }else{
            signInBtn.setImageResource(R.drawable.signinbtn_en);
        }


        //Check Os Ver
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            forgetPassBtn.setBackgroundResource(R.drawable.ripple);
            createNewAccount.setBackgroundResource(R.drawable.ripple);
        }



        //forget password button
        forgetPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInScreen.this,ForgetPasswordScreen.class));
            }
        });


        //new account button
        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInScreen.this,SignUpScreen.class));
            }
        });




    }
}
