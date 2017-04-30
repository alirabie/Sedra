package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Countries.ResCountry;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.R;

public class CountriesScreen extends AppCompatActivity {
    private BetterSpinner countriesspinner;
    private BetterSpinner statesspinner;
    private static List<String> countriesNames= new ArrayList<>();
    private static List<String> countriesIds= new ArrayList<>();
    private static List<String>statesNames= new ArrayList<>();
    private static List<String>statesIds= new ArrayList<>();
    private static LinearLayout statesBox,contriesBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        countriesspinner = (BetterSpinner) findViewById(R.id.countryspinner);
        contriesBox=(LinearLayout)findViewById(R.id.countries_contenr);
        statesspinner=(BetterSpinner)findViewById(R.id.statesspinner);
        statesBox=(LinearLayout)findViewById(R.id.states_countener);
        countriesspinner.setAdapter(new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome));
        statesspinner.setAdapter(new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome));
        contriesBox.setVisibility(View.INVISIBLE);
        statesBox.setVisibility(View.INVISIBLE);


        //Hide states spinner when click on countries spinner
        countriesspinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statesBox.setVisibility(View.INVISIBLE);
                statesNames.clear();
                statesIds.clear();
            }
        });

        //get countries by id
        Generator.createService(SedraApi.class).getCountries("52,69").enqueue(new Callback<ResCountry>() {
            @Override
            public void onResponse(Call<ResCountry> call, Response<ResCountry> response) {
                if(response.isSuccessful()){
                    contriesBox.setVisibility(View.VISIBLE);
                    //fill names and ids to spinner list from response
                    for (int i = 0; i < response.body().getCountries().size(); i++) {
                        countriesNames.add(response.body().getCountries().get(i).getName());
                        countriesIds.add(response.body().getCountries().get(i).getId());
                    }
                        //add names to spinner list
                        ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome,countriesNames);
                        cuntryadapter.notifyDataSetChanged();
                        countriesspinner.setAdapter(cuntryadapter);
                        countriesspinner.setHint(getResources().getString(R.string.selectcountry));
                        countriesspinner.setHintTextColor(Color.WHITE);
                    //counters list selection action
                        countriesspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //get states by id
                                Generator.createService(SedraApi.class).getStates(countriesIds.get(position)).enqueue(new Callback<ResStates>() {
                                    @Override
                                    public void onResponse(Call<ResStates> call, Response<ResStates> response) {
                                        if(response.isSuccessful()){
                                            statesBox.setVisibility(View.VISIBLE);
                                            //fill names and ids to spinner list from response
                                            for (int i = 0; i < response.body().getStates().size(); i++) {
                                                statesNames.add(response.body().getStates().get(i).getName());
                                                statesIds.add(response.body().getStates().get(i).getId());
                                            }

                                            //add names to spinner list
                                            final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(CountriesScreen.this, R.layout.drop_down_list_custome,statesNames);
                                            statesadabter.notifyDataSetChanged();
                                            statesspinner.setAdapter(statesadabter);
                                            statesspinner.setHint(getResources().getString(R.string.selectstate));
                                            statesspinner.setHintTextColor(Color.WHITE);
                                            //states list selection item action start home activity and send state id
                                            statesspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    startActivity(new Intent(CountriesScreen.this,Home.class).putExtra("stateId",statesIds.get(position)));
                                                    CountriesScreen.this.finish();
                                                    countriesNames.clear();
                                                    countriesIds.clear();
                                                    statesNames.clear();
                                                    countriesIds.clear();
                                                }
                                            });

                                        }else {
                                            Toast.makeText(getApplication(),"Response not sucsess from states ",Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResStates> call, Throwable t) {
                                        Toast.makeText(getApplication(),t.getMessage()+":From states ",Toast.LENGTH_LONG).show();
                                    }
                                });










                            }
                        });

                }else {
                    Toast.makeText(getApplication(),"Response not sucsess from countries ",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResCountry> call, Throwable t) {

                Toast.makeText(getApplication(),t.getMessage()+":From countries ",Toast.LENGTH_LONG).show();
            }
        });









    }
}
