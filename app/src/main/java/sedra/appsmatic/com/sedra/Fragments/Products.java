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
import sedra.appsmatic.com.sedra.Adabters.ProductsAdb;
import sedra.appsmatic.com.sedra.R;

public class Products extends Fragment {

    private String id="";
    private RecyclerView proudctsList;
    private ProgressBar progressBar;
    private static String categoryKey,countryKey,stateKey,vendorKey,minPriceKey,flag,searchKeyword;
    private static Bundle b;
    private static TextView emptySign;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        emptySign=(TextView)view.findViewById(R.id.empty_tv);
        emptySign.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);




        //receive Id and use i

        b = this.getArguments();


        if(b.isEmpty()) {
            //Get all products
            Generator.createService(SedraApi.class).getAllProuducts().enqueue(new Callback<ResProducts>() {
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
                            proudctsList = (RecyclerView) view.findViewById(R.id.prouductslist);
                            proudctsList.setAdapter(new ProductsAdb(response.body(), getContext()));
                            Display display = getActivity().getWindowManager().getDefaultDisplay();
                            DisplayMetrics outMetrics = new DisplayMetrics();
                            display.getMetrics(outMetrics);
                            float density = getResources().getDisplayMetrics().density;
                            float dpWidth = outMetrics.widthPixels / density;
                            int columns = Math.round(dpWidth / 130);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columns);
                            proudctsList.setLayoutManager(gridLayoutManager);
                        } catch (Exception e) {

                        }

                    } else {
                        Toast.makeText(getContext(), "No response from products Fragment ", Toast.LENGTH_LONG).show();
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
                            .withMessage(t.getMessage() + "connection error from products Fragment ")
                            .show();

                }
            });

        }else {
            if(b.getString("flag")!=null){

                Generator.createService(SedraApi.class).getFilter(
                        countryKey=b.getString("countryKey"),
                        searchKeyword=b.getString("serachKeyword"),
                        stateKey=b.getString("stateKey"),
                        categoryKey=b.getString("categoryKey"),
                        vendorKey=b.getString("vendorKey"),
                        minPriceKey=b.getString("priceKey")).enqueue(new Callback<ResProducts>() {
                    @Override
                    public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                        if (response.isSuccessful()) {
                            if (progressBar.isShown())
                                progressBar.setVisibility(View.GONE);

                            try {
                                if(response.body().getProducts().isEmpty()){
                                    emptySign.setVisibility(View.VISIBLE);
                                }else {
                                    emptySign.setVisibility(View.INVISIBLE);
                                }
                                proudctsList = (RecyclerView) view.findViewById(R.id.prouductslist);
                                proudctsList.setAdapter(new ProductsAdb(response.body(), getContext()));
                                Display display = getActivity().getWindowManager().getDefaultDisplay();
                                DisplayMetrics outMetrics = new DisplayMetrics();
                                display.getMetrics(outMetrics);
                                float density = getResources().getDisplayMetrics().density;
                                float dpWidth = outMetrics.widthPixels / density;
                                int columns = Math.round(dpWidth / 130);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columns);
                                proudctsList.setLayoutManager(gridLayoutManager);
                            } catch (Exception e) {

                            }

                        } else {
                            Toast.makeText(getContext(), "No response from products Fragment ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResProducts> call, Throwable t) {

                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());
                        dialogBuilder
                                .withTitle(getResources().getString(R.string.conectionerrorr))
                                .withDialogColor(R.color.colorPrimary)
                                .withTitleColor("#FFFFFF")
                                .withIcon(getResources().getDrawable(R.drawable.icon))
                                .withDuration(700)                                          //def
                                .withEffect(Effectstype.RotateBottom)
                                .withMessage(t.getMessage() + "connection error from products Fragment ")
                                .show();

                    }
                });

            } else {
                id = b.getString("id");
                Generator.createService(SedraApi.class).getCategoryProducts(id).enqueue(new Callback<ResProducts>() {
                    @Override
                    public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                        if (response.isSuccessful()) {
                            if (progressBar.isShown())
                                progressBar.setVisibility(View.GONE);
                            try {

                                if(response.body().getProducts().isEmpty()){
                                    emptySign.setVisibility(View.VISIBLE);
                                }else {
                                    emptySign.setVisibility(View.INVISIBLE);
                                }
                                proudctsList = (RecyclerView) view.findViewById(R.id.prouductslist);
                                proudctsList.setAdapter(new ProductsAdb(response.body(), getContext()));
                                Display display = getActivity().getWindowManager().getDefaultDisplay();
                                DisplayMetrics outMetrics = new DisplayMetrics();
                                display.getMetrics(outMetrics);
                                float density = getResources().getDisplayMetrics().density;
                                float dpWidth = outMetrics.widthPixels / density;
                                int columns = Math.round(dpWidth / 130);
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), columns);
                                proudctsList.setLayoutManager(gridLayoutManager);
                            } catch (Exception e) {

                            }

                        } else {
                            Toast.makeText(getContext(), "No response from products Fragment ", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResProducts> call, Throwable t) {
                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());
                        dialogBuilder
                                .withTitle(getResources().getString(R.string.conectionerrorr))
                                .withDialogColor(R.color.colorPrimary)
                                .withTitleColor("#FFFFFF")
                                .withIcon(getResources().getDrawable(R.drawable.icon))
                                .withDuration(700)                                          //def
                                .withEffect(Effectstype.RotateBottom)
                                .withMessage(t.getMessage() + "connection error from products Fragment ")
                                .show();
                    }
                });


            }

        }







    }
}
