package sedra.appsmatic.com.sedra.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;


public class ContactUsFrag extends Fragment {

    private ImageView send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_contact_us_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        send=(ImageView)view.findViewById(R.id.countact_send_btn);


        //Set images languages
        if(SaveSharedPreference.getLangId(getContext()).equals("ar")){
            send.setImageResource(R.drawable.sendbtn_ar);
        }else{
            send.setImageResource(R.drawable.sendbtn_en);
        }


    }
}
