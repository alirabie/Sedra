package sedra.appsmatic.com.sedra.Activites;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.BetterSpinner;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Categories.ResCategories;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.SideMenuAdb;
import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.R;

public class Home extends AppCompatActivity  {

    private BetterSpinner citiesList;
    private BetterSpinner countrieList;
    private static List<String> cats;
    private static List<String> ids;
    private static List<String>products;
    private static List<String>pids;
    private final String clientId = "70a96d7c-247c-4cd0-9737-937859e059a9";
    private final String clientSecret = "your-client-secret";
    private final String redirectUri = "http://sedragift.com";

    private ImageView flwerBtn,giftBtn,cookiesBtn,plantsBtn;
    private RecyclerView sideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //setup top buttons
        flwerBtn=(ImageView)findViewById(R.id.flower_btn);
        giftBtn=(ImageView)findViewById(R.id.gift_btn);
        cookiesBtn=(ImageView)findViewById(R.id.cookies_btn);
        plantsBtn=(ImageView)findViewById(R.id.plants_btn);

        flwerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Switch activation button
                flwerBtn.setImageResource(R.drawable.flowerbtnactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

                //start flowers fragment
                Products flowers = new Products();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","13");
                flowers.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, flowers);
                fragmentTransaction.commit();



            }
        });

        giftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Switch activation button
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

                //start gifts fragment
                Products gifts = new Products();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","14");
                gifts.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, gifts);
                fragmentTransaction.commit();

            }
        });

        cookiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Switch activation button
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

                //start cookies fragment
                Products cookies = new Products();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","15");
                cookies.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, cookies);
                fragmentTransaction.commit();
            }
        });

        plantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                giftBtn.setImageResource(R.drawable.giftbtnunactive);
                cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                plantsBtn.setImageResource(R.drawable.plantsbtnactive);

                //start plants fragment
                Products plants = new Products();
                Bundle bundle = new Bundle();
                //put here id to send to fragment
                bundle.putString("id","16");
                plants.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, plants);
                fragmentTransaction.commit();

            }
        });




        Products products2 = new Products();
        Bundle bundle = new Bundle();
        //put here id to send to fragment
        products2.setArguments(bundle);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontener, products2);
        fragmentTransaction.commit();









        countrieList = (BetterSpinner) findViewById(R.id.countrydown);
        citiesList=(BetterSpinner)findViewById(R.id.citydown);
        countrieList.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        citiesList.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        citiesList.setHint("Select Product");
        Generator.createService(SedraApi.class).getCategories().enqueue(new Callback<ResCategories>() {
            @Override
            public void onResponse(Call<ResCategories> call, Response<ResCategories> response) {

                if (response.isSuccessful()) {

                    //fill names and ids to spinner list from response
                    cats = new ArrayList<>();
                    ids = new ArrayList<>();
                    for (int i = 0; i < response.body().getCategories().size(); i++) {
                        cats.add(response.body().getCategories().get(i).getName());
                        ids.add(response.body().getCategories().get(i).getId());
                    }

                    //add names to spinner list
                    ArrayAdapter<String> cuntryadapter = new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, cats);
                    cuntryadapter.notifyDataSetChanged();
                    countrieList.setAdapter(cuntryadapter);
                    countrieList.setHint("Select Category");
                    //Action when select item from list
                    countrieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //get sub items by id from list of ids from selected item
                            Generator.createService(SedraApi.class).getCategoryProducts(ids.get(position)).enqueue(new Callback<ResProducts>() {
                                @Override
                                public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                                    if (response.isSuccessful()) {
                                        //fill names and ids from response
                                        products = new ArrayList<>();
                                        pids = new ArrayList<>();
                                        for (int i = 0; i < response.body().getProducts().size(); i++) {
                                            products.add(response.body().getProducts().get(i).getName());
                                            pids.add(response.body().getProducts().get(i).getId());
                                        }

                                        ArrayAdapter<String> padapter = new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, products);
                                        padapter.notifyDataSetChanged();
                                        citiesList.setAdapter(padapter);
                                        citiesList.setHint("Select Product");
                                        //action when select item
                                        citiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                //remove and add ( update ) products fragment and send item id with his bundle
                                                Products products = new Products();
                                                Bundle bundle = new Bundle();
                                                //put here id to send to fragment
                                                bundle.putString("Placeid", pids.get(position).toString());
                                                products.setArguments(bundle);
                                                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                                                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                fragmentTransaction.replace(R.id.fragmentcontener, products);
                                                fragmentTransaction.commit();


                                                Toast.makeText(getApplicationContext(), pids.get(position).toString(), Toast.LENGTH_LONG).show();
                                            }
                                        });


                                    } else {

                                        Log.e("Erorr", "not sucsess");
                                    }

                                }

                                @Override
                                public void onFailure(Call<ResProducts> call, Throwable t) {
                                    Log.e("Erorr", t.getMessage());
                                }
                            });

                            Toast.makeText(getApplicationContext(), ids.get(position).toString(), Toast.LENGTH_LONG).show();
                        }
                    });


                    Log.e("Categories : ", response.body().getCategories().get(0).getId() + " "
                            + response.body().getCategories().get(0).getName() + " "
                            + response.body().getCategories().get(0).getDescription()
                            + " " + response.body().getCategories().get(0).getImage().getSrc());
                } else {

                    Log.e("notsucsess", "response");

                }
            }

            @Override
            public void onFailure(Call<ResCategories> call, Throwable t) {
                Log.e("Erorr", t.getMessage().toString());
            }
        });










        //setup side menuR
        sideMenu=(RecyclerView)findViewById(R.id.sidemenulist);
        sideMenu.setAdapter(new SideMenuAdb(Home.this));
        sideMenu.setLayoutManager(new LinearLayoutManager(Home.this));



































































        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.sidemenuhome_icon);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_carticon) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }















}
