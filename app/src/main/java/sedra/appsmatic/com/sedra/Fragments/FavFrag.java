package sedra.appsmatic.com.sedra.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Activites.FavoritesScreen;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Adabters.ProductsAdb;
import sedra.appsmatic.com.sedra.R;


public class FavFrag extends Fragment {


    private RecyclerView proudctsList;
    private ProgressBar progressBar;
    private static TextView emptySign;
    private String ids;
    ProductsAdb productsAdb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_favorite_screen, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Setup items
        progressBar = (ProgressBar)view.findViewById(R.id.fav_progressbar);
        emptySign=(TextView)view.findViewById(R.id.fav_empty_tv);
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
                            proudctsList = (RecyclerView)view.findViewById(R.id.fav_prouductslist);
                            productsAdb=new ProductsAdb(response.body(),getContext());
                            proudctsList.setAdapter(productsAdb);
                            productsAdb.notifyDataSetChanged();
                            Display display = getActivity().getWindowManager().getDefaultDisplay();
                            DisplayMetrics outMetrics = new DisplayMetrics();
                            display.getMetrics(outMetrics);
                            float density = getResources().getDisplayMetrics().density;
                            float dpWidth = outMetrics.widthPixels / density;
                            int columns = Math.round(dpWidth / 130);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), columns);
                            proudctsList.setLayoutManager(gridLayoutManager);
                        } catch (Exception e) {

                        }

                    } else {
                        Toast.makeText(getContext(), "No response from favorite products ", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ResProducts> call, Throwable t) {

                    NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(getContext());
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


    }
}
