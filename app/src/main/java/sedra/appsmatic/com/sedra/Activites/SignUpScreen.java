package sedra.appsmatic.com.sedra.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Countries.ResCountry;
import sedra.appsmatic.com.sedra.API.Models.Customers.BillingAddress;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.Models.District.Districts;
import sedra.appsmatic.com.sedra.API.Models.Registration.PostNewCustomer;
import sedra.appsmatic.com.sedra.API.Models.Registration.RCustomer;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.Models.Vendors.ResVendors;
import sedra.appsmatic.com.sedra.API.Models.verifications.VerificationCode;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class SignUpScreen extends AppCompatActivity {

    private ImageView signUpBtn,home;
    private EditText emailInput,passwordInput,fNameInput,lNameInput,phoneInput,repass,verificationCodeInput,address1;
    private TextView verifyMobile;
    private BetterSpinner filterCountries;
    private BetterSpinner filterStates;
    private BetterSpinner filterdistructs;
    private static List<String> countriesIds= new ArrayList<>();
    private static List<String> countriesNames= new ArrayList<>();
    private static List<String>statesIds= new ArrayList<>();
    private static List<String>statesNames= new ArrayList<>();
    private static List<String> districtsIds=new ArrayList<>();
    private static List<String> districtsNames=new ArrayList<>();
    private static final String SAUDI_ID="52";
    private static final String KUWAIT_ID="69";
    private static String countryKey,stateKey,districtkey,statusid,countryid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        address1=(EditText)findViewById(R.id.signup_address_input);
        verificationCodeInput=(EditText)findViewById(R.id.verificationcodeinput);
        verifyMobile=(TextView)findViewById(R.id.phone_ver_link_btn);
        verificationCodeInput.setVisibility(View.INVISIBLE);


        filterCountries = (BetterSpinner) findViewById(R.id.signup_countery_spinner);
        filterStates =(BetterSpinner)findViewById(R.id.signup_state_spinner);
        filterdistructs=(BetterSpinner)findViewById(R.id.signup_district_spinner);
        filterCountries.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item));
        filterStates.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item));
        filterdistructs.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item));
        filterCountries.setHint(getResources().getString(R.string.selectcountry));
        filterCountries.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterStates.setHint(getResources().getString(R.string.selectstate));
        filterStates.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHint(getResources().getString(R.string.distrects));


        //get countries by id
        Generator.createService(SedraApi.class).getCountries(KUWAIT_ID + "," + SAUDI_ID).enqueue(new Callback<ResCountry>() {
            @Override
            public void onResponse(Call<ResCountry> call, Response<ResCountry> response) {
                if (response.isSuccessful()) {

                    //fill names and ids to spinner list from response
                    for (int i = 0; i < response.body().getCountries().size(); i++) {
                        countriesNames.add(response.body().getCountries().get(i).getName());
                        countriesIds.add(response.body().getCountries().get(i).getId());
                    }
                    //add names to spinner list
                    ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(SignUpScreen.this, android.R.layout.simple_spinner_dropdown_item, countriesNames);
                    cuntryadapter.notifyDataSetChanged();
                    filterCountries.setAdapter(cuntryadapter);
                    filterCountries.setHint(getResources().getString(R.string.selectcountry));
                    filterCountries.setHintTextColor(Color.GRAY);
                    //counters list selection action
                    filterCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                            if (!statesNames.isEmpty()) {
                                statesNames.clear();
                                statesIds.clear();
                            }

                            countryKey = countriesNames.get(position);
                            countryid=countriesIds.get(position);




                            //get states by id
                            Generator.createService(SedraApi.class).getStates(countriesIds.get(position)).enqueue(new Callback<ResStates>() {

                                int currentPosition = position;


                                @Override
                                public void onResponse(Call<ResStates> call, Response<ResStates> response) {
                                    if (response.isSuccessful()) {

                                        //fill names and ids to spinner list from response
                                        for (int i = 0; i < response.body().getStates().size(); i++) {
                                            statesNames.add(response.body().getStates().get(i).getName());
                                            statesIds.add(response.body().getStates().get(i).getId());
                                        }

                                        //add names to spinner list
                                        final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(SignUpScreen.this, android.R.layout.simple_spinner_dropdown_item, statesNames);
                                        statesadabter.notifyDataSetChanged();
                                        filterStates.setAdapter(statesadabter);
                                        filterStates.setHint(getResources().getString(R.string.selectstate));
                                        filterStates.setHintTextColor(Color.GRAY);
                                        //states list selection item action start home activity and send state id
                                        filterStates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                stateKey = statesNames.get(position);
                                                statusid=statesIds.get(position);

                                                //Get districts
                                                Generator.createService(SedraApi.class).getDestrics(countryKey, stateKey).enqueue(new Callback<Districts>() {
                                                    @Override
                                                    public void onResponse(Call<Districts> call, final Response<Districts> response) {

                                                        if (response.isSuccessful()) {


                                                            //fill names and ids to spinner list from response
                                                            for (int i = 0; i < response.body().getDistricts().size(); i++) {
                                                                districtsNames.add(response.body().getDistricts().get(i).getName());
                                                                districtsIds.add(response.body().getDistricts().get(i).getId());
                                                            }


                                                            //add names to spinner list
                                                            final ArrayAdapter<String> districtadapter = new ArrayAdapter<>(SignUpScreen.this, android.R.layout.simple_spinner_dropdown_item, districtsNames);
                                                            districtadapter.notifyDataSetChanged();
                                                            filterdistructs.setAdapter(districtadapter);
                                                            filterdistructs.setHint(getResources().getString(R.string.distrects));
                                                            filterdistructs.setHintTextColor(Color.GRAY);
                                                            filterdistructs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                @Override
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                                    if (!districtsNames.isEmpty()) {
                                                                        districtsNames.clear();
                                                                        districtsIds.clear();
                                                                    }

                                                                    districtkey = response.body().getDistricts().get(position).getName();

                                                                    Log.e("gooood","Country Id : "+countryid+"status id : "+statusid+" district name : "+districtkey);

                                                                }
                                                            });

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "response from filter districts not success", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Districts> call, Throwable t) {
                                                        Toast.makeText(getApplicationContext(), "response from filter districts failed" + " " + t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                });


                                            }
                                        });

                                    } else {
                                        Toast.makeText(getApplication(), "Response not sucsess from states ", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResStates> call, Throwable t) {
                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(SignUpScreen.this);
                                    dialogBuilder
                                            .withTitle(getResources().getString(R.string.conectionerrorr))
                                            .withDialogColor(R.color.colorPrimary)
                                            .withTitleColor("#FFFFFF")
                                            .withIcon(getResources().getDrawable(R.drawable.icon))
                                            .withDuration(700)                                          //def
                                            .withEffect(Effectstype.RotateBottom)
                                            .withMessage(t.getMessage() + " : From states ")
                                            .show();
                                }
                            });


                        }
                    });

                } else {
                    Toast.makeText(getApplication(), "Response not sucsess from countries ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResCountry> call, Throwable t) {

                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(SignUpScreen.this);
                dialogBuilder
                        .withTitle(getResources().getString(R.string.conectionerrorr))
                        .withDialogColor(R.color.colorPrimary)
                        .withTitleColor("#FFFFFF")
                        .withIcon(getResources().getDrawable(R.drawable.icon))
                        .withDuration(700)                                          //def
                        .withEffect(Effectstype.RotateBottom)
                        .withMessage(t.getMessage() + ":From countries ")
                        .show();

            }
        });












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




        //Verify phone number
        verifyMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(SignUpScreen.this, R.anim.alpha);
                verifyMobile.clearAnimation();
                verifyMobile.setAnimation(anim);

                Pattern pPhone=Pattern.compile("\\(?([0-9]{4})\\)?([ .-]?)([0-9]{4})\\2([0-9]{4})");
                Matcher mPhone=pPhone.matcher(phoneInput.getText().toString());
                if(phoneInput.getText().length()==0){
                    phoneInput.setError(getResources().getString(R.string.phoneerror));
                }else if(!mPhone.matches()){
                    phoneInput.setError(getResources().getString(R.string.phonevalid));
                } else {

                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(SignUpScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasew));
                    mProgressDialog.show();

                    Generator.createService(SedraApi.class).verifyMoblieNum(phoneInput.getText().toString()).enqueue(new Callback<VerificationCode>() {
                        @Override
                        public void onResponse(Call<VerificationCode> call, Response<VerificationCode> response) {

                            if (response.isSuccessful()) {
                                if (response.body().getErrorMessage().equals("") || response.body().getErrorMessage() == null) {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.codewillsend) + " ", Toast.LENGTH_LONG).show();
                                    verificationCodeInput.setVisibility(View.VISIBLE);
                                    //Animate verify box
                                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                                    verificationCodeInput.clearAnimation();
                                    verificationCodeInput.setAnimation(anim);
                                } else {
                                    Toast.makeText(getApplicationContext(), response.body().getErrorMessage() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Not success from mobile verification", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<VerificationCode> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failure from mobile verification" + t.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }







            }
        });






        //SignUp button Action
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pattern p = Pattern.compile("^(.+)@(.+)$");
                Matcher m = p.matcher(emailInput.getText().toString());
                Pattern pPhone=Pattern.compile("(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|\n" +
                        "2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|\n" +
                        "4[987654310]|3[9643210]|2[70]|7|1)\\d{1,14}$");
                Matcher mPhone=pPhone.matcher(phoneInput.getText().toString());

                //Inputs validations
                if( emailInput.getText().toString().length() == 0 ){
                   emailInput.setError(getResources().getString(R.string.loginvalemail));

                }else if (passwordInput.getText().toString().length()==0) {
                    passwordInput.setError(getResources().getString(R.string.loginvalpassword));

                }else if(repass.getText().toString().length()==0) {
                    repass.setError(getResources().getString(R.string.loginvalpassword));

                }else if(fNameInput.getText().toString().length()==0){
                    fNameInput.setError(getResources().getString(R.string.fnameerrorr));

                }else if(lNameInput.getText().toString().length()==0) {
                    lNameInput.setError(getResources().getString(R.string.lnameerrorr));

                }else if(phoneInput.getText().toString().length()==0) {
                    phoneInput.setError(getResources().getString(R.string.phoneerror));

                } else if (!mPhone.matches()){
                    phoneInput.setError(getResources().getString(R.string.phonevalid));

                } else if(!passwordInput.getText().toString().equals(repass.getText().toString())) {
                    passwordInput.setError(getResources().getString(R.string.passnotmatch));
                    repass.setError(getResources().getString(R.string.passnotmatch));

                }else if(!m.matches()) {
                    emailInput.setError(getResources().getString(R.string.notvalidemail));

                }else if(passwordInput.getText().length()<6) {
                    passwordInput.setError(getResources().getString(R.string.passwordleanght));


                }else if(verificationCodeInput.getText().length()==0) {

                    verificationCodeInput.setVisibility(View.VISIBLE);
                    verificationCodeInput.setError(getResources().getString(R.string.insertvercode));

                }else if(address1.getText().length()==0){

                    address1.setError(getResources().getString(R.string.addreesserorr));
                }else if(filterCountries.getText().length()==0){

                    filterCountries.setError(getResources().getString(R.string.selectcountry));
                }else if(filterStates.getText().length()==0){

                    filterStates.setError(getResources().getString(R.string.selectstate));
                }else if(filterdistructs.getText().length()==0){
                    filterdistructs.setError(getResources().getString(R.string.distrects));
                } else {

                    //Registration request >>

                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(SignUpScreen.this);
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getApplicationContext().getResources().getString(R.string.pleasew));
                    mProgressDialog.show();

                        //create data
                    PostNewCustomer postNewCustomer=new PostNewCustomer();
                    RCustomer customer=new RCustomer();


                    //fill billing address
                    BillingAddress billingAddress=new BillingAddress();
                    billingAddress.setCountryId(countryid);
                    billingAddress.setEmail(emailInput.getText().toString() + "");
                    billingAddress.setFirstName(fNameInput.getText().toString() + "");
                    billingAddress.setLastName(lNameInput.getText().toString() + "");
                    billingAddress.setStateProvinceId(statusid);
                    billingAddress.setCity(districtkey);
                    billingAddress.setPhoneNumber(phoneInput.getText().toString() + "");
                    billingAddress.setAddress1(address1.getText()+"");
                    billingAddress.setZipPostalCode("00");

                    List<Integer> rollIds=new ArrayList<Integer>();
                    rollIds.add(3);
                    customer.setRoleIds(rollIds);
                    customer.setEmail(emailInput.getText().toString() + "");
                    customer.setPassword(passwordInput.getText().toString() + "");
                    customer.setFirstName(fNameInput.getText().toString() + "");
                    customer.setLastName(lNameInput.getText().toString() + "");
                    customer.setPhone(phoneInput.getText().toString() + "");
                    customer.setVerificationcode(verificationCodeInput.getText().toString() + "");
                    customer.setBillingAddress(billingAddress);
                    postNewCustomer.setCustomer(customer);


                    Gson gson =new Gson();
                    String json = gson.toJson(postNewCustomer);

                    Log.e("Json : ",json);

                    Generator.createService(SedraApi.class).regesterNewCustomer(postNewCustomer).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if (response.body().getCustomers() != null) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.regsuccsess), Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUpScreen.this, SignInScreen.class));
                                    SignUpScreen.this.finish();
                                } else if (response.body().getErrors() != null) {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.faild) + " " + response.body().getErrors().getAccount() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "Not success from SignUp", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.faild) + " " + t.getMessage(), Toast.LENGTH_LONG).show();
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


    @Override
    protected void onResume() {
        super.onResume();

        countriesNames.clear();
        countriesIds.clear();
        statesNames.clear();
        countriesIds.clear();
        districtsNames.clear();
        districtsIds.clear();








    }
}
