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
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.ProductsAdb;
import sedra.appsmatic.com.sedra.R;

public class Products extends Fragment {

    private TextView test;

    private RecyclerView proudctsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //receive Id and use it
        Bundle b = this.getArguments();
        String id = b.getString("Placeid");


        Generator.createService(SedraApi.class).getAllProuducts().enqueue(new Callback<ResProducts>() {
            @Override
            public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                if(response.isSuccessful()){
                    proudctsList=(RecyclerView)view.findViewById(R.id.prouductslist);
                    proudctsList.setAdapter(new ProductsAdb(response.body(), getContext()));


                    Display display = getActivity().getWindowManager().getDefaultDisplay();
                    DisplayMetrics outMetrics = new DisplayMetrics();
                    display.getMetrics(outMetrics);

                    float density  = getResources().getDisplayMetrics().density;
                    float dpWidth  = outMetrics.widthPixels / density;
                    int columns = Math.round(dpWidth/130);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),columns);
                    proudctsList.setLayoutManager(gridLayoutManager);
                }
            }

            @Override
            public void onFailure(Call<ResProducts> call, Throwable t) {

            }
        });






    }
}
