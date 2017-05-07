package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.BetterSpinner;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Vendors.ResVendors;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.SideMenuAdb;
import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.R;

public class Home extends AppCompatActivity  {

    private BetterSpinner vendors;
    private BetterSpinner categoriesSp;
    private static List<String> vendorsNames;
    private static List<String> vendorsIds;
    private static List<String>products;
    private static List<String>pids;
    public static android.support.v4.app.FragmentManager fragmentManager;
    public static android.support.v4.app.FragmentTransaction fragmentTransaction;



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

        //Receive vendorsIds from countries screen
        String countryId=getIntent().getStringExtra("country_id");
        String stateId=getIntent().getStringExtra("stateId");

        Toast.makeText(getApplicationContext(),countryId+"   "+stateId,Toast.LENGTH_LONG).show();

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
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
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
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
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
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
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
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, plants);
                fragmentTransaction.commit();

            }
        });




        Products products2 = new Products();
        Bundle bundle = new Bundle();
        //put here id to send to fragment
        products2.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontener, products2);
        fragmentTransaction.commit();









        categoriesSp = (BetterSpinner) findViewById(R.id.countrydown);
        vendors =(BetterSpinner)findViewById(R.id.citydown);
        categoriesSp.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        vendors.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        categoriesSp.setHint(getResources().getString(R.string.selectcategorie));
        Generator.createService(SedraApi.class).getVendors(countryId,stateId).enqueue(new Callback<ResVendors>() {
            @Override
            public void onResponse(Call<ResVendors> call, Response<ResVendors> response) {
                if (response.isSuccessful()) {
                    //fill names and vendorsIds to spinner list from response
                    vendorsNames = new ArrayList<>();
                    vendorsIds = new ArrayList<>();
                    for (int i = 0; i < response.body().getVendors().size(); i++) {
                        vendorsNames.add(response.body().getVendors().get(i).getName());
                        vendorsIds.add(response.body().getVendors().get(i).getId());
                    }
                    //add names to spinner list
                    ArrayAdapter<String> vendorsAdabtor=new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, vendorsNames);
                    vendorsAdabtor.notifyDataSetChanged();
                    vendors.setAdapter(vendorsAdabtor);
                    vendors.setHint(getResources().getString(R.string.selectvendor));
                    vendors.setHintTextColor(Color.WHITE);
                }else {
                    Toast.makeText(getApplication(), "Response not success from vendors Home ", Toast.LENGTH_LONG).show();
                }

                }

            @Override
            public void onFailure(Call<ResVendors> call, Throwable t) {
                Toast.makeText(getApplication(), t.getMessage()+" : connection error from vendors", Toast.LENGTH_LONG).show();
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
        if (id == R.id.action_filtericon) {
           startActivity(new Intent(this,Filter.class));
            return true;
        }else

        return super.onOptionsItemSelected(item);
    }















}
