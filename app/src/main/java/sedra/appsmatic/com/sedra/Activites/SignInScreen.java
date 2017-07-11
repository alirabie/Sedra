package sedra.appsmatic.com.sedra.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.Models.Error.ResErrors;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SignInScreen extends AppCompatActivity {

    private TextView forgetPassBtn,createNewAccount;
    private ImageView signInBtn,home;
    private EditText user,pass;


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
        home=(ImageView)findViewById(R.id.home_btn_login);
        user=(EditText)findViewById(R.id.email_input);
        pass=(EditText)findViewById(R.id.password_input);




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
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                forgetPassBtn.clearAnimation();
                forgetPassBtn.setAnimation(anim);
                startActivity(new Intent(SignInScreen.this,ForgetPasswordScreen.class));
            }
        });


        //new account button
        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                createNewAccount.clearAnimation();
                createNewAccount.setAnimation(anim);
                startActivity(new Intent(SignInScreen.this,SignUpScreen.class));
            }
        });



        //Home button action
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                home.clearAnimation();
                home.setAnimation(anim);
                SignInScreen.this.finish();
                startActivity(new Intent(SignInScreen.this,SplashScreen.class));
            }
        });



        //login btn action
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SignInScreen.this, R.anim.alpha);
                signInBtn.clearAnimation();
                signInBtn.setAnimation(anim);

                if( user.getText().toString().length() == 0 ){
                    user.setError(getResources().getString(R.string.loginvalemail));

                }else if (pass.getText().toString().length()==0) {
                    pass.setError(getResources().getString(R.string.loginvalpassword));

                }else {


                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(SignInScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasew));
                    mProgressDialog.show();

                    HashMap loginData = new HashMap();
                    loginData.put("email", user.getText().toString() + "");
                    loginData.put("password", pass.getText().toString() + "");

                    //request login from server
                  Generator.createService(SedraApi.class).login(loginData).enqueue(new Callback<RegResponse>() {
                      @Override
                      public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                          if(response.isSuccessful()){

                              if (mProgressDialog.isShowing())
                                  mProgressDialog.dismiss();

                              if(response.body().getCustomers()!=null){
                                  SaveSharedPreference.setCustomerId(SignInScreen.this,response.body().getCustomers().get(0).getId()+"");
                                  Home.fillWishListFromServer(SignInScreen.this);
                                  startActivity(new Intent(SignInScreen.this, CountriesScreen.class));
                                  SignInScreen.this.finish();
                                  Log.e("Done : ",response.body().getCustomers().get(0).getId()+"");
                              }else if(response.body().getErrors().getAccount()!=null) {
                                  //Show Error
                                  NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(SignInScreen.this);
                                  dialogBuilder
                                          .withTitle(getResources().getString(R.string.sedra))
                                          .withDialogColor(R.color.colorPrimary)
                                          .withTitleColor("#FFFFFF")
                                          .withIcon(getResources().getDrawable(R.drawable.icon))
                                          .withDuration(700)                                          //def
                                          .withEffect(Effectstype.RotateBottom)
                                          .withMessage(response.body().getErrors().getAccount()+ "")
                                          .show();
                              }


                          }else {

                              if (mProgressDialog.isShowing())
                                  mProgressDialog.dismiss();

                              Toast.makeText(SignInScreen.this,"not success from sign in",Toast.LENGTH_LONG).show();
                          }
                      }

                      @Override
                      public void onFailure(Call<RegResponse> call, Throwable t) {

                          if (mProgressDialog.isShowing())
                              mProgressDialog.dismiss();

                          Toast.makeText(SignInScreen.this,t.getMessage()+" from sign in",Toast.LENGTH_LONG).show();
                      }
                  });











                }
            }
        });

    }





    @Override
    public void onBackPressed() {
        SignInScreen.this.finish();
        startActivity(new Intent(SignInScreen.this,SplashScreen.class));
    }
}
