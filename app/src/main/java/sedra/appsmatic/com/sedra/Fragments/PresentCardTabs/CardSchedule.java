package sedra.appsmatic.com.sedra.Fragments.PresentCardTabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.ImageView;
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
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;


public class CardSchedule extends Fragment {

    private Cardschedule cardschedule;
    private ImageView saveBtn;
    private DatePicker simpleDatePicker;
    private int day,month,year;
    Date currentDate;

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
        cardschedule=new Cardschedule();
        saveBtn=(ImageView)view.findViewById(R.id.save_date_btn);


        try {
            currentDate = new Date();
            currentDate.setTime(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(PresentCard.dayCount));
            simpleDatePicker = (DatePicker) view.findViewById(R.id.simpleDatePicker); // initiate a date picker
            saveBtn = (ImageView) view.findViewById(R.id.save_date_btn);
            simpleDatePicker.setSpinnersShown(false);
            simpleDatePicker.setMinDate(currentDate.getTime());
        }catch (Exception e){
            Toast.makeText(getContext(),"Error from Day count calc"+e.getMessage(),Toast.LENGTH_LONG).show();
        }



        //Set images languages
        if(SaveSharedPreference.getLangId(getContext()).equals("ar")){
            saveBtn.setImageResource(R.drawable.savebtn);
        }else{
            saveBtn.setImageResource(R.drawable.save_btn_en);
        }




        //save address btn action
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                saveBtn.clearAnimation();
                saveBtn.setAnimation(anim);
                day = simpleDatePicker.getDayOfMonth();
                month = simpleDatePicker.getMonth() + 1;
                year = simpleDatePicker.getYear();
                Date date = new Date();
                date.setDate(day);
                date.setMonth(simpleDatePicker.getMonth());
                if (date.before(currentDate)) {
                    Toast.makeText(getContext(), "You cannot select previous date !", Toast.LENGTH_LONG).show();
                } else {
                    String date2 = "Selected Date : " + day + "-" + month + "-" + year;
                    Toast.makeText(getContext(), date2, Toast.LENGTH_LONG).show();
                }


                PresentCard.gitSchudle = true;
                Toast.makeText(getContext(), getResources().getString(R.string.savesuccsess), Toast.LENGTH_LONG).show();
            }
        });
    }







}
