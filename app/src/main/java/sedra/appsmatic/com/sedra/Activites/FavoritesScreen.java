package sedra.appsmatic.com.sedra.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.ProductsAdb;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class FavoritesScreen extends AppCompatActivity {


    private ImageView home;
    private RecyclerView proudctsList;
    private ProgressBar progressBar;
    private static TextView emptySign;
    private String ids;
    ProductsAdb productsAdb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_favorite_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Setup items
        home=(ImageView)findViewById(R.id.fav_home_btn_login);
        progressBar = (ProgressBar)findViewById(R.id.fav_progressbar);
        emptySign=(TextView)findViewById(R.id.fav_empty_tv);
        emptySign.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        ids="";


        if(!Home.wishListProductsIds.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0;i<Home.wishListProductsIds.size();i++){
                stringBuilder.append(Home.wishListProductsIds.get(i)+",");
            }
            ids=stringBuilder.toString();
            Generator.createService(SedraApi.class).getSpecificProducts(ids).enqueue(new Callback<ResProducts>() {
                @Override
                public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                    if (response.isSuccessful()) {
                        if(progressBar.isShown())
                            progressBar.setVisibility(View.GONE);
                        if(response.body().getProducts().isEmpty()){
                            emptySign.setVisibility(View.VISIBLE);
                        }else {
                            emptySign.setVisibility(View.INVISIBLE);
                        }

                        try {
                            proudctsList = (RecyclerView)findViewById(R.id.fav_prouductslist);
                            productsAdb=new ProductsAdb(response.body(), FavoritesScreen.this);
                            proudctsList.setAdapter(productsAdb);
                            productsAdb.notifyDataSetChanged();
                            Display display = FavoritesScreen.this.getWindowManager().getDefaultDisplay();
                            DisplayMetrics outMetrics = new DisplayMetrics();
                            display.getMetrics(outMetrics);
                            float density = getResources().getDisplayMetrics().density;
                            float dpWidth = outMetrics.widthPixels / density;
                            int columns = Math.round(dpWidth / 130);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(FavoritesScreen.this, columns);
                            proudctsList.setLayoutManager(gridLayoutManager);
                        } catch (Exception e) {

                        }

                    } else {
                        Toast.makeText(FavoritesScreen.this, "No response from favorite products ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResProducts> call, Throwable t) {

                    NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(FavoritesScreen.this);
                    dialogBuilder
                            .withTitle(getResources().getString(R.string.conectionerrorr))
                            .withDialogColor(R.color.colorPrimary)
                            .withTitleColor("#FFFFFF")
                            .withIcon(getResources().getDrawable(R.drawable.icon))
                            .withDuration(700)                                          //def
                            .withEffect(Effectstype.RotateBottom)
                            .withMessage(t.getMessage() + "connection error from Favorites ")
                            .show();
                }
            });

        }else {

            progressBar.setVisibility(View.GONE);
            emptySign.setVisibility(View.VISIBLE);

        }























        //Home button action
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(FavoritesScreen.this, R.anim.alpha);
                home.clearAnimation();
                home.setAnimation(anim);
                FavoritesScreen.this.finish();
            }
        });





    }





    @Override
    public void onBackPressed() {
        FavoritesScreen.this.finish();
    }


}
