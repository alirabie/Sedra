package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Categories.ResCategories;
import sedra.appsmatic.com.sedra.API.Models.Countries.ResCountry;
import sedra.appsmatic.com.sedra.API.Models.District.Districts;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.Models.Vendors.ResVendors;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class Filter extends AppCompatActivity {

    private ImageView closeBtn,filterBtnGo;
    private EditText searcInput,priceInput;
    private BetterSpinner filterCountries;
    private BetterSpinner filterStates;
    private BetterSpinner filterdistructs;
    private BetterSpinner filterVendors;
    private BetterSpinner filterCategoriy;
    private BetterSpinner filterSameday;
    private static final String SAUDI_ID="52";
    private static final String KUWAIT_ID="69";
    private static List<String>categoriesNames=new ArrayList<>();
    private static List<String>categoriesIds=new ArrayList<>();
    private static List<String> countriesIds= new ArrayList<>();
    private static List<String>statesIds= new ArrayList<>();
    private static List<String>statesNames= new ArrayList<>();
    private static List<String> countriesNames= new ArrayList<>();
    private static List<String> vendorsNames= new ArrayList<>();
    private static List<String> vendorsIds= new ArrayList<>();
    private static List<String> districtsNames=new ArrayList<>();
    private static List<String> districtsIds=new ArrayList<>();
    private static String categoryKey,countryKey,stateKey,districtkey,vendorKey,minPriceKey;
    private boolean deliveratsamedate=false;
    private static List<String> options;
    private static List<String>distructsNames=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        countryKey="";
        categoryKey="";
        stateKey="";
        districtkey="";
        vendorKey="";
        minPriceKey="0";
        options=new ArrayList<>();
        options.add(0,getResources().getString(R.string.yes));
        options.add(1,getResources().getString(R.string.no));

        closeBtn=(ImageView)findViewById(R.id.close_filter_btn);
        filterBtnGo=(ImageView)findViewById(R.id.filter_go_btn);
        searcInput=(EditText)findViewById(R.id.search_input);
        priceInput=(EditText)findViewById(R.id.filter_price_input);

        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            filterBtnGo.setImageResource(R.drawable.filter_btn_ar);

        }else{
            filterBtnGo.setImageResource(R.drawable.filter_btn_en);
        }

        filterCountries = (BetterSpinner) findViewById(R.id.filter_countery_spinner);
        filterStates =(BetterSpinner)findViewById(R.id.filter_state_spinner);
        filterVendors =(BetterSpinner)findViewById(R.id.filter_vendor_spinner);
        filterCategoriy=(BetterSpinner)findViewById(R.id.filter_category_spinner);
        filterSameday=(BetterSpinner)findViewById(R.id.filter_time);
        filterdistructs=(BetterSpinner)findViewById(R.id.filter_district_spinner);



        filterCategoriy.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item));
        filterCountries.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item));
        filterStates.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item));
        filterdistructs.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item));
        filterVendors.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item));
        filterSameday.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options));
        filterSameday.setHint(getResources().getString(R.string.sameday));
        filterSameday.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterCategoriy.setHint(getResources().getString(R.string.selectcategorie));
        filterCategoriy.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterCountries.setHint(getResources().getString(R.string.selectcountry));
        filterCountries.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterStates.setHint(getResources().getString(R.string.selectstate));
        filterdistructs.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterdistructs.setHint(getResources().getString(R.string.distrects));
        filterStates.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        filterVendors.setHint(getResources().getString(R.string.selectvendor));
        filterVendors.setHintTextColor(getResources().getColor(R.color.colorPrimary));


        //get categories
        Generator.createService(SedraApi.class).getCategories().enqueue(new Callback<ResCategories>() {
            @Override
            public void onResponse(Call<ResCategories> call, Response<ResCategories> response) {
                if (response.isSuccessful()) {
                    //fill names and ids to spinner list from response
                    for (int i = 0; i < response.body().getCategories().size(); i++) {
                        categoriesNames.add(response.body().getCategories().get(i).getName());
                        categoriesIds.add(response.body().getCategories().get(i).getId());
                    }

                    //add names to spinner list
                    ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(Filter.this, android.R.layout.simple_spinner_dropdown_item, categoriesNames);
                    cuntryadapter.notifyDataSetChanged();
                    filterCategoriy.setAdapter(cuntryadapter);
                    filterCategoriy.setHint(getResources().getString(R.string.selectcategorie));
                    filterCategoriy.setHintTextColor(Color.GRAY);
                    filterCategoriy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            categoryKey = categoriesIds.get(position);
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Response from filter categores not working !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResCategories> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage() + "Response from filter categores not working !", Toast.LENGTH_LONG).show();
            }
        });


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
                    ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(Filter.this, android.R.layout.simple_spinner_dropdown_item, countriesNames);
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
                                        final ArrayAdapter<String> statesadabter = new ArrayAdapter<>(Filter.this, android.R.layout.simple_spinner_dropdown_item, statesNames);
                                        statesadabter.notifyDataSetChanged();
                                        filterStates.setAdapter(statesadabter);
                                        filterStates.setHint(getResources().getString(R.string.selectstate));
                                        filterStates.setHintTextColor(Color.GRAY);
                                        //states list selection item action start home activity and send state id
                                        filterStates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                                                stateKey = statesNames.get(position);

                                                if (!vendorsNames.isEmpty()) {
                                                    vendorsNames.clear();
                                                    vendorsIds.clear();
                                                }



                                                //Get districts
                                                Generator.createService(SedraApi.class).getDestrics(countryKey,stateKey).enqueue(new Callback<Districts>() {
                                                    @Override
                                                    public void onResponse(Call<Districts> call, final Response<Districts> response) {

                                                        if(response.isSuccessful()){


                                                            //fill names and ids to spinner list from response
                                                            for (int i = 0; i < response.body().getDistricts().size(); i++) {
                                                                districtsNames.add(response.body().getDistricts().get(i).getName());
                                                                districtsIds.add(response.body().getDistricts().get(i).getId());
                                                            }


                                                            //add names to spinner list
                                                            final ArrayAdapter<String> districtadapter = new ArrayAdapter<>(Filter.this, android.R.layout.simple_spinner_dropdown_item, districtsNames);
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

                                                                    districtkey=response.body().getDistricts().get(position).getName();

                                                                }
                                                            });

                                                        }else {
                                                           Toast.makeText(getApplicationContext(),"response from filter districts not success",Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<Districts> call, Throwable t) {
                                                        Toast.makeText(getApplicationContext(),"response from filter districts failed"+" "+t.getMessage(),Toast.LENGTH_LONG).show();
                                                    }
                                                });














                                                //get vendors
                                                Generator.createService(SedraApi.class).getVendors(countriesIds.get(currentPosition), statesIds.get(position)).enqueue(new Callback<ResVendors>() {
                                                    @Override
                                                    public void onResponse(Call<ResVendors> call, Response<ResVendors> response) {
                                                        if (response.isSuccessful()) {
                                                            //fill names and vendorsIds to spinner list from response
                                                            for (int i = 0; i < response.body().getVendors().size(); i++) {
                                                                vendorsNames.add(response.body().getVendors().get(i).getName());
                                                                vendorsIds.add(response.body().getVendors().get(i).getId());
                                                            }
                                                            //add names to spinner list
                                                            ArrayAdapter<String> vendorsAdabtor = new ArrayAdapter<>(Filter.this, android.R.layout.simple_spinner_dropdown_item, vendorsNames);
                                                            vendorsAdabtor.notifyDataSetChanged();
                                                            filterVendors.setAdapter(vendorsAdabtor);
                                                            filterVendors.setHint(getResources().getString(R.string.selectvendor));
                                                            filterVendors.setHintTextColor(Color.GRAY);
                                                            filterVendors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                @Override
                                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                    vendorKey = vendorsNames.get(position);
                                                                }
                                                            });
                                                        } else {
                                                            Toast.makeText(getApplication(), "Response not success from vendors Home ", Toast.LENGTH_LONG).show();
                                                        }

                                                    }

                                                    @Override
                                                    public void onFailure(Call<ResVendors> call, Throwable t) {
                                                        Toast.makeText(getApplication(), t.getMessage() + " : connection error from vendors", Toast.LENGTH_LONG).show();
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
                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(Filter.this);
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

                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(Filter.this);
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



        //filter by dliver at the same day
        filterSameday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        deliveratsamedate=true;
                        break;
                    case 1:
                        deliveratsamedate=false;
                        break;
                }

            }
        });



        //close button
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter.this.finish();
            }
        });





        filterBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                filterBtnGo.clearAnimation();
                filterBtnGo.setAnimation(anim);

                if(!priceInput.getText().toString().isEmpty()){
                    minPriceKey=priceInput.getText().toString();
                }

                Log.e("gooooood",districtkey+vendorKey+countryKey+stateKey+minPriceKey+searcInput.getText().toString()+categoryKey);

                Products products2 = new Products();
                Bundle bundle = new Bundle();
                bundle.putString("flag","filter");
                bundle.putString("categoryKey",categoryKey);
                bundle.putString("countryKey",countryKey);
                bundle.putString("stateKey",stateKey);
                bundle.putString("districtkey", districtkey);
                bundle.putString("vendorKey", vendorKey);
                bundle.putString("priceKey", minPriceKey);
                bundle.putBoolean("deliverbythesameday", deliveratsamedate);
                bundle.putString("serachKeyword", searcInput.getText().toString() + "");

                products2.setArguments(bundle);
                //put here id to send to fragment
                Home.fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentcontener,
                                products2)
                        .addToBackStack(null).commitAllowingStateLoss();
                countriesNames.clear();
                countriesIds.clear();
                statesNames.clear();
                countriesIds.clear();
                categoriesIds.clear();
                categoriesNames.clear();
                districtsNames.clear();
                districtsIds.clear();
                countryKey="";
                categoryKey="";
                stateKey="";
                districtkey="";
                vendorKey="";
                minPriceKey="0";
                deliveratsamedate=false;
                Filter.this.finish();
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
        categoriesIds.clear();
        categoriesNames.clear();
        districtsNames.clear();
        districtsIds.clear();
        countryKey="";
        categoryKey="";
        stateKey="";
        districtkey="";
        vendorKey="";
        minPriceKey="0";
        deliveratsamedate=false;

    }
}
