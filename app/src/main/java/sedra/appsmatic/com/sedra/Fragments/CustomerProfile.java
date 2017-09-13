package sedra.appsmatic.com.sedra.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import sedra.appsmatic.com.sedra.API.Models.Customers.UpdateCustomer;
import sedra.appsmatic.com.sedra.API.Models.District.Districts;
import sedra.appsmatic.com.sedra.API.Models.Registration.PostNewCustomer;
import sedra.appsmatic.com.sedra.API.Models.Registration.RCustomer;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.Models.verifications.VerificationCode;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Activites.SignInScreen;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;


public class CustomerProfile extends Fragment {

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
    private ImageView signUpBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpBtn=(ImageView)view.findViewById(R.id.signup_btn);
        emailInput=(EditText)view.findViewById(R.id.signup_email_input);
        passwordInput=(EditText)view.findViewById(R.id.signup_password_input);
        fNameInput=(EditText)view.findViewById(R.id.signup_username_input);
        lNameInput=(EditText)view.findViewById(R.id.signup_repassword_input);
        phoneInput=(EditText)view.findViewById(R.id.signup_phone_input);
        repass=(EditText)view.findViewById(R.id.re_password);
        address1=(EditText)view.findViewById(R.id.signup_address_input);
        verificationCodeInput=(EditText)view.findViewById(R.id.verificationcodeinput);
        verifyMobile=(TextView)view.findViewById(R.id.phone_ver_link_btn);
        verificationCodeInput.setVisibility(View.INVISIBLE);

        //Set images languages
        if(SaveSharedPreference.getLangId(getContext()).equals("ar")){
            signUpBtn.setImageResource(R.drawable.savebtn);
        }else{
            signUpBtn.setImageResource(R.drawable.save_btn_en);
        }

        filterCountries = (BetterSpinner)view.findViewById(R.id.signup_countery_spinner);
        filterStates =(BetterSpinner)view.findViewById(R.id.signup_state_spinner);
        filterdistructs=(BetterSpinner)view.findViewById(R.id.signup_district_spinner);
        filterCountries.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item));
        filterStates.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item));
        filterdistructs.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item));
        filterCountries.setHint(getResources().getString(R.string.selectcountry));
        filterCountries.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterStates.setHint(getResources().getString(R.string.selectstate));
        filterStates.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHint(getResources().getString(R.string.distrects));



        //fill inputs from saved data
        emailInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getEmail() + "");
        fNameInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getFirstName()+"");
        lNameInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getLastName() + "");
        //filterCountries.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getCountry().toString()+"");
        //filterStates.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getProvince().toString()+"");
        //filterdistructs.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getCity().toString()+"");
        address1.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getAddress1().toString()+"");
        phoneInput.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getPhoneNumber().toString() + "");



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
                    ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, countriesNames);
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
                            countryid = countriesIds.get(position);


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
                                        final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, statesNames);
                                        statesadabter.notifyDataSetChanged();
                                        filterStates.setAdapter(statesadabter);
                                        filterStates.setHint(getResources().getString(R.string.selectstate));
                                        filterStates.setHintTextColor(Color.GRAY);
                                        //states list selection item action start home activity and send state id
                                        filterStates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                stateKey = statesNames.get(position);
                                                statusid = statesIds.get(position);

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
                                                            final ArrayAdapter<String> districtadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, districtsNames);
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

                                                                    Log.e("gooood", "Country Id : " + countryid + "status id : " + statusid + " district name : " + districtkey);

                                                                }
                                                            });

                                                        } else {
                                                            Toast.makeText(getContext(), "response from filter districts not success", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Districts> call, Throwable t) {
                                                        Toast.makeText(getContext(), "response from filter districts failed" + " " + t.getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                });


                                            }
                                        });

                                    } else {
                                        Toast.makeText(getContext(), "Response not sucsess from states ", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResStates> call, Throwable t) {
                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());
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
                    Toast.makeText(getContext(), "Response not sucsess from countries ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResCountry> call, Throwable t) {

                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());
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



        //Verify phone number
        verifyMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                verifyMobile.clearAnimation();
                verifyMobile.setAnimation(anim);

                Pattern pPhone = Pattern.compile("\\(?([0-9]{4})\\)?([ .-]?)([0-9]{4})\\2([0-9]{4})");
                Matcher mPhone = pPhone.matcher(phoneInput.getText().toString());
                if (phoneInput.getText().length() == 0) {
                    phoneInput.setError(getResources().getString(R.string.phoneerror));
                } else if (!mPhone.matches()) {
                    phoneInput.setError(getResources().getString(R.string.phonevalid));
                } else {

                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getContext().getResources().getString(R.string.pleasew));
                    mProgressDialog.show();

                    Generator.createService(SedraApi.class).verifyMoblieNum(phoneInput.getText().toString()).enqueue(new Callback<VerificationCode>() {
                        @Override
                        public void onResponse(Call<VerificationCode> call, Response<VerificationCode> response) {

                            if (response.isSuccessful()) {
                                if (response.body().getErrorMessage().equals("") || response.body().getErrorMessage() == null) {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    Toast.makeText(getContext(), getResources().getString(R.string.codewillsend) + " ", Toast.LENGTH_LONG).show();
                                    verificationCodeInput.setVisibility(View.VISIBLE);
                                    //Animate verify box
                                    Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fadein);
                                    verificationCodeInput.clearAnimation();
                                    verificationCodeInput.setAnimation(anim);
                                } else {
                                    if (mProgressDialog.isShowing())
                                        mProgressDialog.dismiss();
                                    Toast.makeText(getContext(), response.body().getErrorMessage() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();
                                Toast.makeText(getContext(), "Not success from mobile verification", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<VerificationCode> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();
                            Toast.makeText(getContext(), "Failure from mobile verification" + t.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }
        });


        //Save button
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


                    //Update request >>

                    //Loading Dialog
                    final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
                    mProgressDialog.setIndeterminate(true);
                    mProgressDialog.setMessage(getContext().getResources().getString(R.string.pleasew));
                    mProgressDialog.show();

                    //create data

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
                    billingAddress.setAddress1(address1.getText() + "");
                    billingAddress.setZipPostalCode("00");
                    List<Integer> rollIds=new ArrayList<Integer>();
                    rollIds.add(3);
                    customer.setRoleIds(rollIds);
                    customer.setId(SaveSharedPreference.getCustomerId(getContext()));
                    customer.setEmail(emailInput.getText().toString() + "");
                    customer.setPassword(passwordInput.getText().toString() + "");
                    customer.setFirstName(fNameInput.getText().toString() + "");
                    customer.setLastName(lNameInput.getText().toString() + "");
                    customer.setPhone(phoneInput.getText().toString() + "");
                    customer.setVerificationcode(verificationCodeInput.getText().toString() + "");
                    customer.setBillingAddress(billingAddress);

                    UpdateCustomer updateCustomer=new UpdateCustomer();
                    updateCustomer.setCustomer(customer);

                    Gson gson =new Gson();
                    String json = gson.toJson(updateCustomer);

                    Log.e("new Customer Data :", json);

                    Generator.createService(SedraApi.class).updateCustomer(updateCustomer,SaveSharedPreference.getCustomerId(getContext())).enqueue(new Callback<RegResponse>() {
                        @Override
                        public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {

                            if (response.isSuccessful()) {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                if (response.body().getCustomers() != null) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.update), Toast.LENGTH_LONG).show();
                                    //Save new Data locally
                                    SaveSharedPreference.setCustomerInfo(getContext(),response.body());
                                    countriesNames.clear();
                                    countriesIds.clear();
                                    statesNames.clear();
                                    countriesIds.clear();
                                    districtsNames.clear();
                                    districtsIds.clear();

                                    //Change Fragment
                                    Products products = new Products();
                                    Bundle bundle = new Bundle();
                                    products.setArguments(bundle);
                                    android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity)getContext()).getSupportFragmentManager();
                                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragmentcontener, products);
                                    fragmentTransaction.commit();



                                } else if (response.body().getErrors() != null) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.faild) + " " + response.body().getErrors().getAccount() + "", Toast.LENGTH_LONG).show();
                                }

                            } else {

                                if (mProgressDialog.isShowing())
                                    mProgressDialog.dismiss();

                                Toast.makeText(getContext(), "Not success from update user", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onFailure(Call<RegResponse> call, Throwable t) {
                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(getContext(), getResources().getString(R.string.faild) + " update customer " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });





















                }






            }
        });






















    }

    @Override
    public void onResume() {
        super.onResume();
            countriesNames.clear();
            countriesIds.clear();
            statesNames.clear();
            countriesIds.clear();
            districtsNames.clear();
            districtsIds.clear();
    }
}
