package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Countries.ResCountry;
import sedra.appsmatic.com.sedra.API.Models.Orders.NewOrder;
import sedra.appsmatic.com.sedra.API.Models.Orders.Order;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class CountriesScreen extends AppCompatActivity {
    private BetterSpinner countriesspinner;
    private BetterSpinner statesspinner;
    private ProgressBar progressBar;
    private RelativeLayout bg;
    private static List<String> countriesNames= new ArrayList<>();
    private static List<String> countriesIds= new ArrayList<>();
    private static List<String>statesNames= new ArrayList<>();
    private static List<String>statesIds= new ArrayList<>();
    private static LinearLayout statesBox,contriesBox;
    private static final String SAUDI_ID="52";
    private static final String KUWAIT_ID="69";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_countries_screen);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }








        //setup items 123
        progressBar = (ProgressBar)findViewById(R.id.countries_progressbar);
        countriesspinner = (BetterSpinner) findViewById(R.id.countryspinner);
        contriesBox=(LinearLayout)findViewById(R.id.countries_contenr);
        statesspinner=(BetterSpinner)findViewById(R.id.statesspinner);
        statesBox=(LinearLayout)findViewById(R.id.states_countener);
        bg=(RelativeLayout)findViewById(R.id.location_bg);

        //spanners default adaptors
        countriesspinner.setAdapter(new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome));
        statesspinner.setAdapter(new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome));

        //items visibility
        contriesBox.setVisibility(View.INVISIBLE);
        statesBox.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);


        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            bg.setBackground(getResources().getDrawable(R.drawable.backg));
        }else{
            bg.setBackground(getResources().getDrawable(R.drawable.location_bg_en));
        }


        //Hide states spinner when click on countries spinner
        countriesspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statesBox.setVisibility(View.INVISIBLE);
                statesNames.clear();
                statesIds.clear();
            }
        });


        //request countries by id from server
        Generator.createService(SedraApi.class).getCountries(KUWAIT_ID + "," + SAUDI_ID).enqueue(new Callback<ResCountry>() {
            @Override
            public void onResponse(Call<ResCountry> call, Response<ResCountry> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    contriesBox.setVisibility(View.VISIBLE);

                    //Animate Spinner box
                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                    contriesBox.clearAnimation();
                    contriesBox.setAnimation(anim);

                    //fill names and ids to spinner list from response
                    for (int i = 0; i < response.body().getCountries().size(); i++) {
                        countriesNames.add(response.body().getCountries().get(i).getName());
                        countriesIds.add(response.body().getCountries().get(i).getId());
                    }

                    //add names to spinner list
                    ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome, countriesNames);
                    cuntryadapter.notifyDataSetChanged();
                    countriesspinner.setAdapter(cuntryadapter);
                    countriesspinner.setHint(getResources().getString(R.string.selectcountry));
                    countriesspinner.setHintTextColor(Color.WHITE);

                    //counters list selection action
                    countriesspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                            //request states by id from server
                            Generator.createService(SedraApi.class).getStates(countriesIds.get(position)).enqueue(new Callback<ResStates>() {
                                int currentPosition = position;
                                @Override
                                public void onResponse(Call<ResStates> call, Response<ResStates> response) {
                                    if (response.isSuccessful()) {
                                        statesBox.setVisibility(View.VISIBLE);
                                        //Animate Spinner box
                                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
                                        statesBox.clearAnimation();
                                        statesBox.setAnimation(anim);
                                        //fill names and ids to spinner list from response
                                        for (int i = 0; i < response.body().getStates().size(); i++) {
                                            statesNames.add(response.body().getStates().get(i).getName());
                                            statesIds.add(response.body().getStates().get(i).getId());
                                        }

                                        //add names to spinner list
                                        final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome, statesNames);
                                        statesadabter.notifyDataSetChanged();
                                        statesspinner.setAdapter(statesadabter);
                                        statesspinner.setHint(getResources().getString(R.string.selectstate));
                                        statesspinner.setHintTextColor(Color.WHITE);
                                        //states list selection item action start home activity and send state id
                                        statesspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                startActivity(new Intent(CountriesScreen.this, Home.class)
                                                        .putExtra("country_id", countriesIds.get(currentPosition))
                                                        .putExtra("stateId", statesIds.get(position))
                                                        .putExtra("countryname",countriesNames.get(currentPosition))
                                                        .putExtra("statename",statesNames.get(position)));


                                                CountriesScreen.this.finish();
                                                countriesNames.clear();
                                                countriesIds.clear();
                                                statesNames.clear();
                                                countriesIds.clear();
                                            }
                                        });

                                    } else {
                                        Toast.makeText(getApplication(), "Response not success from states ", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResStates> call, Throwable t) {
                                    NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(CountriesScreen.this);
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
                    Toast.makeText(getApplication(), "Response not success from countries ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResCountry> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(CountriesScreen.this);
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        countriesNames.clear();
        countriesIds.clear();
        statesNames.clear();
        countriesIds.clear();
    }



    @Override
    public void onBackPressed() {
        final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(CountriesScreen.this);
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
                        dialogBuilder.dismiss();
                        CountriesScreen.this.finish();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {dialogBuilder.dismiss();
                    }
                })
                .show();

    }
}
