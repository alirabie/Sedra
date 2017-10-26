package sedra.appsmatic.com.sedra.Fragments.PresentCardTabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.PresentCards.Cardschedule;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.Models.VendorDateSchedule.ResVendorsSch;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.Adabters.ScheduleAdapter;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;


public class CardSchedule extends Fragment {


    private RecyclerView timesList;
    private TextView emptyflag;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        emptyflag=(TextView)view.findViewById(R.id.empty_times_flag);
        emptyflag.setVisibility(View.INVISIBLE);







        //Get Delivery Times for vendor
        timesList=(RecyclerView)view.findViewById(R.id.deleviry_times_list);
        if(PresentCard.vendorId!=0) {
            Generator.createService(SedraApi.class).getVendorSchedule(PresentCard.vendorId + "").enqueue(new Callback<ResVendorsSch>() {
                @Override
                public void onResponse(Call<ResVendorsSch> call, Response<ResVendorsSch> response) {

                    if (response.isSuccessful()) {
                        if (!response.body().getDeliveryschedules().isEmpty()) {
                            timesList.setAdapter(new ScheduleAdapter(getContext(), response.body()));
                            timesList.setLayoutManager(new LinearLayoutManager(getContext()));
                            emptyflag.setVisibility(View.INVISIBLE);
                        }else {
                            emptyflag.setVisibility(View.VISIBLE);
                        }

                    } else {
                        Toast.makeText(getContext(), "not response from delivery times", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<ResVendorsSch> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage() + " Failure from delivery times", Toast.LENGTH_LONG).show();
                }
            });
        }else {

            emptyflag.setVisibility(View.VISIBLE);


        }







    }







}
