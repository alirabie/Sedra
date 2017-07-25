package sedra.appsmatic.com.sedra.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.Models.Registration.PostNewCustomer;
import sedra.appsmatic.com.sedra.API.Models.Registration.RCustomer;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SignUpScreen extends AppCompatActivity {

    private ImageView signUpBtn,home;
    private EditText emailInput,passwordInput,fNameInput,lNameInput,phoneInput,repass;

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
        emailInput=(EditText)findViewById(R.id.signup_email_input);
        passwordInput=(EditText)findViewById(R.id.signup_password_input);
        fNameInput=(EditText)findViewById(R.id.signup_username_input);
        lNameInput=(EditText)findViewById(R.id.signup_repassword_input);
        phoneInput=(EditText)findViewById(R.id.signup_phone_input);
        repass=(EditText)findViewById(R.id.re_password);


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




        //SignUp button Action
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pattern p = Pattern.compile("^(.+)@(.+)$");
                Matcher m = p.matcher(emailInput.getText().toString());

                //Inputs validations
                if( emailInput.getText().toString().length() == 0 ){
                   emailInput.setError(getResources().getString(R.string.loginvalemail));

                }else if (passwordInput.getText().toString().length()==0) {
                    passwordInput.setError(getResources().getString(R.string.loginvalpassword));

                }else if(repass.getText().toString().length()==0) {
                    repass.setError(getResources().getString(R.string.loginvalpassword));

                }else if(fNameInput.getText().toString().length()==0){
                    fNameInput.setError(getResources().getString(R.string.fnameerrorr));

                }else if(lNameInput.getText().toString().length()==0){
                    lNameInput.setError(getResources().getString(R.string.lnameerrorr));

                }else if(phoneInput.getText().toString().length()==0) {
                    phoneInput.setError(getResources().getString(R.string.phoneerror));

                }else if(!passwordInput.getText().toString().equals(repass.getText().toString())) {
                    passwordInput.setError(getResources().getString(R.string.passnotmatch));
                    repass.setError(getResources().getString(R.string.passnotmatch));

                }else if(!m.matches()) {
                    emailInput.setError(getResources().getString(R.string.notvalidemail));

                }else if(passwordInput.getText().length()<6) {
                    passwordInput.setError(getResources().getString(R.string.passwordleanght));

                }else {

                    //Registration request >>

                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(SignUpScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasew));
                    mProgressDialog.show();

                        //create data
                    PostNewCustomer postNewCustomer=new PostNewCustomer();
                    RCustomer customer=new RCustomer();
                    List<Integer> rollIds=new ArrayList<Integer>();
                    rollIds.add(3);
                    customer.setRoleIds(rollIds);
                    customer.setEmail(emailInput.getText().toString()+"");
                    customer.setPassword(passwordInput.getText().toString()+"");
                    customer.setFirstName(fNameInput.getText().toString()+"");
                    customer.setLastName(lNameInput.getText().toString()+"");
                    customer.setPhone(phoneInput.getText().toString()+"");
                    postNewCustomer.setCustomer(customer);

                    Generator.createService(SedraApi.class).regesterNewCustomer(postNewCustomer).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                            if(response.isSuccessful()){

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if(response.body()!=null){
                                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.regsuccsess),Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUpScreen.this,SignInScreen.class));
                                    SignUpScreen.this.finish();
                                }else {
                                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.faild),Toast.LENGTH_LONG).show();
                                }

                            }else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                Toast.makeText(getApplicationContext(),"Not success from SignUp",Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(getApplicationContext(),getResources().getString(R.string.faild)+" "+t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });























                }













            }
        });



    }

    @Override
    public void onBackPressed() {
       SignUpScreen.this.finish();
    }
}
