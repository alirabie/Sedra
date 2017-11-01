package sedra.appsmatic.com.sedra.Activites;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.District.Districts;
import sedra.appsmatic.com.sedra.API.Models.LocalShoppingCartProductId;
import sedra.appsmatic.com.sedra.API.Models.Orders.NewOrder;
import sedra.appsmatic.com.sedra.API.Models.Orders.Order;
import sedra.appsmatic.com.sedra.API.Models.Orders.OrderItem;
import sedra.appsmatic.com.sedra.API.Models.Orders.OrderResponse;
import sedra.appsmatic.com.sedra.API.Models.Orders.ResDeleteOrderItem;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ResCartItems;
import sedra.appsmatic.com.sedra.API.Models.UpdateOrder.ReqNewOrderItem;
import sedra.appsmatic.com.sedra.API.Models.UpdateOrder.ResUpdateOrder;
import sedra.appsmatic.com.sedra.API.Models.Vendors.ResVendors;
import sedra.appsmatic.com.sedra.API.Models.WishListItems.ResAddingWishList;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.SideMenuAdb;
import sedra.appsmatic.com.sedra.CountBadge.BadgeDrawable;
import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class Home extends AppCompatActivity  {

    private BetterSpinner vendors;
    private BetterSpinner districtsSp;
    private static List<String> districtsNames;
    private static List<String> districtsIds;
    private static List<String> vendorsNames;
    private static List<String> vendorsIds;
    private static List<String>products;
    private static List<String>pids;
    public static android.support.v4.app.FragmentManager fragmentManager;
    public static android.support.v4.app.FragmentTransaction fragmentTransaction;
    private boolean doubleBackToExitPressedOnce = false;
    public static MenuItem itemCart;
    public static  LayerDrawable icon;
    public static SideMenuAdb sideMenuAdb;
    public static List<String>wishListProductsIds=new ArrayList<>();
    public static HashMap <String,String> itemsIds=new HashMap();
    public static ImageView flwerBtn,giftBtn,cookiesBtn,plantsBtn;
    public static RecyclerView sideMenu;
    public static Currency currency;



    public static List<LocalShoppingCartProductId>cardItemsIds=new ArrayList<>();
    public static int currentCount=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);





















        //ar-sa ar-kw

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(getIntent().getStringExtra("country_id").equals("69")){
                currency = Currency.getInstance(Locale.forLanguageTag("ar-sa"));
            } else if (getIntent().getStringExtra("country_id").equals("52")){
                currency =Currency.getInstance(Locale.forLanguageTag("ar-kw"));
            }
        }else {
            currency=Currency.getInstance(Locale.US);
        }


        cardItemsIds=SaveSharedPreference.getCartIds(Home.this);
        currentCount=SaveSharedPreference.getCurrentCount(Home.this);

        if(!SaveSharedPreference.getCustomerId(Home.this).isEmpty()){
            fillWishListFromServer(Home.this);
        }



        sideMenuAdb=new SideMenuAdb(Home.this);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Check location permissions for Marshmallow
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        }


        //Receive vendorsIds from countries screen
        final String countryId=getIntent().getStringExtra("country_id");
        final String stateId=getIntent().getStringExtra("stateId");
        final String countryName=getIntent().getStringExtra("countryname");
        final String stateName=getIntent().getStringExtra("statename");


        //Toast.makeText(getApplicationContext(),countryId+"   "+stateId,Toast.LENGTH_LONG).show();

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
                bundle.putString("id","1");
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
                bundle.putString("id","2");
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
                bundle.putString("id","3");
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
                bundle.putString("id","10");
                plants.setArguments(bundle);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, plants);
                fragmentTransaction.commit();

            }
        });





        //Default Product list
        Products products2 = new Products();
        Bundle bundle = new Bundle();
        //put here id to send to fragment
        products2.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontener, products2);
        fragmentTransaction.commit();











        districtsSp = (BetterSpinner) findViewById(R.id.countrydown);
        vendors =(BetterSpinner)findViewById(R.id.citydown);
        districtsSp.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        vendors.setAdapter(new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome));
        districtsSp.setHint(getResources().getString(R.string.distrects));

        //get vendors
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
                    ArrayAdapter<String> vendorsAdabtor = new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, vendorsNames);
                    vendorsAdabtor.notifyDataSetChanged();
                    vendors.setAdapter(vendorsAdabtor);
                    vendors.setHint(getResources().getString(R.string.selectvendor));
                    vendors.setHintTextColor(Color.WHITE);

                    //request filter by vendor
                    vendors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Products products2 = new Products();
                            Bundle bundle = new Bundle();
                            bundle.putString("flag","filter");
                            bundle.putString("categoryKey","");
                            bundle.putString("countryKey","");
                            bundle.putString("stateKey","");
                            bundle.putString("districtkey", "");
                            bundle.putString("vendorKey", vendorsNames.get(position)+"");
                            bundle.putString("priceKey", "0");
                            bundle.putString("serachKeyword","");

                            products2.setArguments(bundle);
                            //put here id to send to fragment
                            Home.fragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragmentcontener,
                                            products2)
                                    .addToBackStack(null).commitAllowingStateLoss();
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





        //get districts
        Generator.createService(SedraApi.class).getDestrics(getIntent().getStringExtra("countryname"),getIntent().getStringExtra("statename")).enqueue(new Callback<Districts>() {
            @Override
            public void onResponse(Call<Districts> call, Response<Districts> response) {

                if (response.isSuccessful()) {
                    //fill names to spinner list from response
                    districtsNames = new ArrayList<>();
                    districtsIds = new ArrayList<>();
                    for (int i = 0; i < response.body().getDistricts().size(); i++) {
                        districtsNames.add(response.body().getDistricts().get(i).getName());
                        districtsIds.add(response.body().getDistricts().get(i).getId());
                    }
                    //add names to spinner list
                    ArrayAdapter<String> districsAdabtor = new ArrayAdapter<>(Home.this, R.layout.drop_down_list_custome, districtsNames);
                    districsAdabtor.notifyDataSetChanged();
                    districtsSp.setAdapter(districsAdabtor);
                    districtsSp.setHint(getResources().getString(R.string.distrects));
                    districtsSp.setHintTextColor(Color.WHITE);

                    //request products by districts
                districtsSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Products products2 = new Products();
                        Bundle bundle = new Bundle();
                        bundle.putString("flag","filter");
                        bundle.putString("categoryKey","");
                        bundle.putString("countryKey",countryName);
                        bundle.putString("stateKey",stateName);
                        bundle.putString("districtkey", districtsNames.get(position));
                        bundle.putString("vendorKey", "");
                        bundle.putString("priceKey", "0");
                        bundle.putString("serachKeyword", "");
                        products2.setArguments(bundle);
                        //put here id to send to fragment
                        Home.fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentcontener,
                                        products2)
                                .addToBackStack(null).commitAllowingStateLoss();
                    }
                });

                } else {

                    Toast.makeText(getApplication(), "response not success from districts home spinner", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<Districts> call, Throwable t) {

                Toast.makeText(getApplication(), "connection error from districts home spinner"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




        //setup side menuR
        sideMenu=(RecyclerView)findViewById(R.id.sidemenulist);
        sideMenu.setAdapter(sideMenuAdb);
        sideMenu.setLayoutManager(new LinearLayoutManager(Home.this));
        //Toolbar setup
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
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;


        if(SaveSharedPreference.getCustomerId(Home.this).isEmpty()){

        }else {
            getCartItemsCount(Home.this,SaveSharedPreference.getCustomerId(Home.this));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SaveSharedPreference.getCustomerId(Home.this).isEmpty()){

        }else {
            getCartItemsCount(Home.this, SaveSharedPreference.getCustomerId(Home.this));
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {


            Products products2 = new Products();
            Bundle bundle = new Bundle();
            //put here id to send to fragment
            products2.setArguments(bundle);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentcontener, products2);
            fragmentTransaction.commit();
            districtsSp.clearListSelection();
            districtsSp.setText(getResources().getString(R.string.distrects));
            vendors.clearListSelection();
            vendors.setText(getResources().getString(R.string.selectvendor));
            flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
            giftBtn.setImageResource(R.drawable.giftbtnunactive);
            cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
            plantsBtn.setImageResource(R.drawable.plantsbtnunactive);

            Toast.makeText(this, R.string.pressagain, Toast.LENGTH_SHORT).show();

            if (doubleBackToExitPressedOnce) {
                final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(Home.this);
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
                                Home.this.finish();
                            }
                        })
                        .setButton2Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {dialogBuilder.dismiss();
                            }
                        })
                        .show();
                return;
            }



            this.doubleBackToExitPressedOnce = true;
            // Toast.makeText(this, R.string.pressagain, Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);




        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

       //set badge count
        itemCart = menu.findItem(R.id.action_carticon);
        icon = (LayerDrawable) itemCart.getIcon();
        //update Cart Counter
        if(SaveSharedPreference.getCustomerId(Home.this).isEmpty()){

        }else {
            getCartItemsCount(Home.this, SaveSharedPreference.getCustomerId(Home.this));
        }


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
            flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
            giftBtn.setImageResource(R.drawable.giftbtnunactive);
            cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
            plantsBtn.setImageResource(R.drawable.plantsbtnunactive);
           startActivity(new Intent(this,Filter.class));
            return true;
        }else if(id==R.id.action_carticon) {

            if(SaveSharedPreference.getCustomerId(Home.this).isEmpty()) {

                //start login dialog code >>>

                  FloatingLoginDialog.startShow(Home.this);

                ///end login dialog code


            }else{startActivity(new Intent(this, ShoppingCart.class));}
            return true;
        }
            return super.onOptionsItemSelected(item);

    }






    //******************************************* Utils Methods *****************************************************


    //Turn GPS ON Method
    public static void turnLocationOn(final Context ctx){

        isGooglePlayServicesAvailable((Activity) ctx);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(ctx)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i("TurnLocationOn", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i("TurnLocationOn", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult((Activity) ctx, 0x1);
                        } catch (IntentSender.SendIntentException e) {
                            Log.i("TurnLocationOn", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("TurnLocationOn", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });


    }



    //Check Google Service
    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }


    //set cart number badge
    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {
        BadgeDrawable badge;
        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }



    //Get Shopping Cart Count and fill shopping cart ids local
    public static void getCartItemsCount (final Context context,String customerId){
        Generator.createService(SedraApi.class).getCartItems(customerId).enqueue(new Callback<ResCartItems>() {
            @Override
            public void onResponse(Call<ResCartItems> call, Response<ResCartItems> response) {
                if (response.isSuccessful()) {

                    cardItemsIds.clear();
                    SaveSharedPreference.setCartIds(context, Home.cardItemsIds);

                    setBadgeCount(context, icon, response.body().getShoppingCarts().size() + "");

                    for (int i = 0; i < response.body().getShoppingCarts().size(); i++) {
                        //fill local cart ids by local id object
                        LocalShoppingCartProductId localShoppingCartProductId = new LocalShoppingCartProductId();
                        localShoppingCartProductId.setId(response.body().getShoppingCarts().get(i).getProductId());
                        localShoppingCartProductId.setCount(response.body().getShoppingCarts().get(i).getQuantity());
                        Home.cardItemsIds.add(localShoppingCartProductId);
                        Log.e("cart Id : ", Home.cardItemsIds.get(i).getId() + " " + Home.cardItemsIds.get(i).getCount());
                    }

                    SaveSharedPreference.setCartIds(context, Home.cardItemsIds);


                } else {
                    Log.e("erorr", "cart not set num");
                }
            }

            @Override
            public void onFailure(Call<ResCartItems> call, Throwable t) {
                Log.e("cartNum fail", t.getMessage());
            }
        });
    }



    //Mesh 3aref bt3mel aeh de ^_^
    public static void saveWishListToPrefs (Context context){
        SaveSharedPreference.setWishListOrders(context, wishListProductsIds);
    }




    //Place New Order from Shopping Cart
    public static void placeNewOrder(List<OrderItem>orderItems, final Context context){
        if(SaveSharedPreference.getOrderId(context).equals("")) {
            if (!orderItems.isEmpty()) {
                NewOrder newOrder = new NewOrder();
                final Order order = new Order();
                order.setCustomerId(Integer.parseInt(SaveSharedPreference.getCustomerId(context)));
                order.setOrderItems(orderItems);
                order.setBillingAddress(SaveSharedPreference.getCustomerInfo(context).getCustomers().get(0).getBillingAddress());
                order.setPaymentMethodSystemName("Payments.Manual");
                newOrder.setOrder(order);
                Gson gson = new Gson();
                //log request body
                Log.e("New Order request ", gson.toJson(newOrder));
                //place order on server
                Generator.createService(SedraApi.class).placeNewOrder(newOrder).enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.body().getOrders() != null) {
                                Toast.makeText(context, context.getResources().getString(R.string.orderplace), Toast.LENGTH_LONG).show();

                                //Set order id
                                SaveSharedPreference.setOrderId(context,response.body().getOrders().get(0).getId());
                            }

                            //Gson gson1=new Gson();
                            //Log.e("New Order response ", gson1.toJson(response.body()));

                        } else {

                            try {
                                Toast.makeText(context, "Response not success from order placement : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderResponse> call, Throwable t) {

                        Toast.makeText(context, "Connection failed from order placement" + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("errrr", t.getMessage());
                    }
                });
            }
        }else {
            Toast.makeText(context,SaveSharedPreference.getOrderId(context)+"",Toast.LENGTH_LONG).show();
        }

    }


    //fill wish list from server
    public static void fillWishListFromServer(final Context context){

        Generator.createService(SedraApi.class).getAllWishList(SaveSharedPreference.getCustomerId(context)).enqueue(new Callback<ResAddingWishList>() {
            @Override
            public void onResponse(Call<ResAddingWishList> call, Response<ResAddingWishList> response) {
                if (response.isSuccessful()) {
                    wishListProductsIds.clear();
                    itemsIds.clear();
                    for (int i = 0; i < response.body().getItems().size(); i++) {
                        wishListProductsIds.add(response.body().getItems().get(i).getProductId()+"");
                        itemsIds.put(response.body().getItems().get(i).getProductId()+"",response.body().getItems().get(i).getId()+"");

                    }
                    //   saveWishListToPrefs(context);
                    Log.e("sucsess wish", wishListProductsIds.size() + "");
                } else {

                    Log.e("Not sucsess", "from wishlist save ");
                }
            }

            @Override
            public void onFailure(Call<ResAddingWishList> call, Throwable t) {
                Log.e("falier", "from wishlist save ");
            }
        });
    }


    //Delete item from shopping cart
    public static void deleteItemFromShoppingCart(final Context context, final String id){

        //Loading Dialog
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(context.getResources().getString(R.string.pleasew));
        mProgressDialog.show();

        Generator.createService(SedraApi.class).deleteCartItems(id).enqueue(new Callback<ResCartItems>() {

            @Override
            public void onResponse(Call<ResCartItems> call, Response<ResCartItems> response) {


                if (response.isSuccessful()) {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();

                    ((Activity) context).finish();
                    context.startActivity(new Intent(context, ShoppingCart.class));
                } else {
                    if (mProgressDialog.isShowing())
                        mProgressDialog.dismiss();
                    Log.e("Delete Nooo", id);

                }
            }

            @Override
            public void onFailure(Call<ResCartItems> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Log.e("Delete Nooo", t.getMessage());
            }

            });

        }


    //Delete item from current order by order id
    public static void deleteItemFromOrder(final Context context,String orderId,String itemId){

        Generator.createService(SedraApi.class).deleteOrderItem(orderId, itemId).enqueue(new Callback<ResDeleteOrderItem>() {
            @Override
            public void onResponse(Call<ResDeleteOrderItem> call, Response<ResDeleteOrderItem> response) {
                if(response.isSuccessful()){
                    if(response.body().getError().isEmpty()){
                        Toast.makeText(context,response.body().getStatus()+" "+context.getResources().getString(R.string.removeitem),Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Error from deleting item from order",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    try {
                        Toast.makeText(context, "Response not success from delete item from order : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResDeleteOrderItem> call, Throwable t) {

                Toast.makeText(context,"Connection Failure from delete item from order : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




    }


    //Add item to Order
    public static void addItemToOrder(final Context context,String orderId,ReqNewOrderItem newOrderItem){


        Generator.createService(SedraApi.class).addItemToOrder(orderId, newOrderItem).enqueue(new Callback<ResUpdateOrder>() {
            @Override
            public void onResponse(Call<ResUpdateOrder> call, Response<ResUpdateOrder> response) {
                if(response.isSuccessful()){
                    if(!response.body().getOrderItems().isEmpty()){
                        Toast.makeText(context,context.getResources().getString(R.string.additem),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    try {
                        Toast.makeText(context, "Response not success from add item to order : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResUpdateOrder> call, Throwable t) {
                Toast.makeText(context,"Connection failed from add item to order : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }


    //Update current Order
    public static void updateOrder(final Context context,String orderId,String itemId,ReqNewOrderItem newOrderItem){

        Generator.createService(SedraApi.class).updateOrder(newOrderItem, orderId, itemId).enqueue(new Callback<ResUpdateOrder>() {
            @Override
            public void onResponse(Call<ResUpdateOrder> call, Response<ResUpdateOrder> response) {
                if(response.isSuccessful()){
                    if(!response.body().getOrderItems().isEmpty()){
                        Toast.makeText(context,context.getResources().getString(R.string.updateorder),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    try {
                        Toast.makeText(context, "Response not success from update item to order : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResUpdateOrder> call, Throwable t) {
                Toast.makeText(context,"Connection failed from update item to order : "+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




    }



    //check if id exist in local shopping cart ids and pick current count of this product that hold this id
    public static boolean IsIdExistInShoppingCart(Context context,int id){
        boolean result=new Boolean(null);
        for(int i=0;i<Home.cardItemsIds.size();i++){
            if(Home.cardItemsIds.get(i).getId()==id){
                result=true;
                currentCount=cardItemsIds.get(i).getCount();
                SaveSharedPreference.setCurrentCount(context,currentCount);
                break;

            }else {
                result=false;
            }
        }
        return result;
    }




}
