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
import android.widget.ImageView;
import android.widget.Toast;

import sedra.appsmatic.com.sedra.API.Models.PresentCards.Cardschedule;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;


public class CardSchedule extends Fragment {

    private Cardschedule cardschedule;
    private ImageView saveBtn;

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



                PresentCard.gitSchudle=true;
                Toast.makeText(getContext(),getResources().getString(R.string.savesuccsess),Toast.LENGTH_LONG).show();
            }
        });
    }
}
