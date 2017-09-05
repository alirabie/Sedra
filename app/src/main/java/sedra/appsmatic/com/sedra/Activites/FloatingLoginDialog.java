package sedra.appsmatic.com.sedra.Activites;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 7/3/2017.
 */
public class FloatingLoginDialog {

    private static NiftyDialogBuilder dialogBuildercard;
    private static TextView forgetPassBtn,createNewAccount;
    private static ImageView signInBtn,home;
    private static EditText user,pass;

public static void startShow(final Context context){

    dialogBuildercard = NiftyDialogBuilder.getInstance(context);
    dialogBuildercard
            .withDuration(700)//def
            .withDialogColor(context.getResources().getColor(R.color.colorPrimary))
            .withEffect(Effectstype.Fall)
            .withTitleColor(context.getResources().getColor(R.color.colorPrimary))
            .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
            .setCustomView(R.layout.activity_sign_in_screen,context)
            .show();
    dialogBuildercard.setOnKeyListener(new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            return keyCode == KeyEvent.KEYCODE_BACK;
        }
    });
    //Setup items
    forgetPassBtn=(TextView)dialogBuildercard.findViewById(R.id.forgetpassword_txt_btn);
    createNewAccount=(TextView)dialogBuildercard.findViewById(R.id.newaccount_txt_btn);
    signInBtn=(ImageView)dialogBuildercard.findViewById(R.id.login_btn);
    home=(ImageView)dialogBuildercard.findViewById(R.id.home_btn_login);
    user=(EditText)dialogBuildercard.findViewById(R.id.email_input);
    pass=(EditText)dialogBuildercard.findViewById(R.id.password_input);

    //Set images languages
    if(SaveSharedPreference.getLangId(context).equals("ar")){
        signInBtn.setImageResource(R.drawable.signin_btn);

    }else{
        signInBtn.setImageResource(R.drawable.signinbtn_en);
    }
    //forget password button
    forgetPassBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
            forgetPassBtn.clearAnimation();
            forgetPassBtn.setAnimation(anim);
            context.startActivity(new Intent(context,ForgetPasswordScreen.class));
        }
    });
    //new account button
    createNewAccount.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
            createNewAccount.clearAnimation();
            createNewAccount.setAnimation(anim);
            context.startActivity(new Intent(context,SignUpScreen.class));
        }
    });
    //Home button action
    home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
            home.clearAnimation();
            home.setAnimation(anim);
            dialogBuildercard.dismiss();
        }
    });
    //login btn action
    signInBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
            signInBtn.clearAnimation();
            signInBtn.setAnimation(anim);

            if( user.getText().toString().length() == 0 ){
                user.setError(context.getResources().getString(R.string.loginvalemail));

            }else if (pass.getText().toString().length()==0) {
                pass.setError(context.getResources().getString(R.string.loginvalpassword));

            }else {
                //Loading Dialog
                final ProgressDialog mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage(context.getApplicationContext().getResources().getString(R.string.pleasew));
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
                                SaveSharedPreference.setCustomerId(context,response.body().getCustomers().get(0).getId()+"");
                                SaveSharedPreference.setCustomerInfo(context, response.body());
                                Home.getCartItemsCount(context, SaveSharedPreference.getCustomerId(context));
                                Home.sideMenuAdb.notifyDataSetChanged();
                                Home.sideMenu.setAdapter(Home.sideMenuAdb);
                                Home.sideMenu.setLayoutManager(new LinearLayoutManager(context));
                                Home.fillWishListFromServer(context);
                                dialogBuildercard.dismiss();
                                Log.e("Done : ", response.body().getCustomers().get(0).getId() + "");
                            }else if(response.body().getErrors().getAccount()!=null) {
                                //Show Error
                                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
                                dialogBuilder
                                        .withTitle(context.getResources().getString(R.string.sedra))
                                        .withDialogColor(R.color.colorPrimary)
                                        .withTitleColor("#FFFFFF")
                                        .withIcon(context.getResources().getDrawable(R.drawable.icon))
                                        .withDuration(700)                                          //def
                                        .withEffect(Effectstype.RotateBottom)
                                        .withMessage(response.body().getErrors().getAccount()+ "")
                                        .show();
                            }
                        }else {

                            if (mProgressDialog.isShowing())
                                mProgressDialog.dismiss();

                            Toast.makeText(context, "not success from sign in", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegResponse> call, Throwable t) {

                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();

                        Toast.makeText(context,t.getMessage()+" from sign in",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    });



























}































}
