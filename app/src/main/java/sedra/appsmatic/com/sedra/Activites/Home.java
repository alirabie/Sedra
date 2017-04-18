package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Categories.ResCategories;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.R;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BetterSpinner citiesList;
    private BetterSpinner countrieList;

    private final String clientId = "70a96d7c-247c-4cd0-9737-937859e059a9";
    private final String clientSecret = "your-client-secret";
    private final String redirectUri = "http://sedragift.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Test web service categories
     Generator.createService(SedraApi.class).getCategories().enqueue(new Callback<ResCategories>() {
         @Override
         public void onResponse(Call<ResCategories> call, Response<ResCategories> response) {

             if (response.isSuccessful()) {
                 Log.e("True", response.body().getCategories().get(1).getId() + " "
                         + response.body().getCategories().get(1).getName() + " "
                         + response.body().getCategories().get(1).getDescription()
                         + " " + response.body().getCategories().get(1).getImage().getSrc());
             } else {

                 Log.e("notsucsess", "response");

             }
         }

         @Override
         public void onFailure(Call<ResCategories> call, Throwable t) {
             Log.e("Erorr", t.getMessage().toString());
         }
     });


        //Test web service categories products
        Generator.createService(SedraApi.class).getCategoryProducts(4).enqueue(new Callback<ResProducts>() {
            @Override
            public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                if(response.isSuccessful()){

                    Log.e("product",response.body().getProducts().get(1).getName()+ "\n"+
                            response.body().getProducts().get(1).getFullDescription()+ "\n"+
                            response.body().getProducts().get(1).getShortDescription()+ "\n"+
                            response.body().getProducts().get(1).getImages().get(0).getSrc()+ "\n"+
                            response.body().getProducts().get(1).getId());

                }else {

                    Log.e("Erorr","not sucsess");
                }
            }

            @Override
            public void onFailure(Call<ResProducts> call, Throwable t) {
                Log.e("Erorr",t.getMessage());
            }
        });




        citiesList = (BetterSpinner) findViewById(R.id.citydown);
        countrieList=(BetterSpinner) findViewById(R.id.countrydown);

        String[] items = new String[]{"مصر", "السعودية"};

        //Country List
        ArrayAdapter<String> cityadapter = new ArrayAdapter<String>(this,R.layout.drop_down_list_custome, items);
        citiesList.setAdapter(cityadapter);
        citiesList.setHint("Select City");




        //City List
        ArrayAdapter<String> cuntryadapter = new ArrayAdapter<String>(this,R.layout.drop_down_list_custome, items);
        countrieList.setAdapter(cuntryadapter);
        countrieList.setHint("Select Country");
















        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
