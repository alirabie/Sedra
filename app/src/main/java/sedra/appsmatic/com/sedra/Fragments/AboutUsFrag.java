package sedra.appsmatic.com.sedra.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import sedra.appsmatic.com.sedra.R;


public class AboutUsFrag extends Fragment {

    private TextView info1,info2,info3,info4,info5,info6;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_about_us_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        info1=(TextView)view.findViewById(R.id.info1);
        info2=(TextView)view.findViewById(R.id.info2);
        info3=(TextView)view.findViewById(R.id.info3);
        info4=(TextView)view.findViewById(R.id.info4);
        info5=(TextView)view.findViewById(R.id.info5);
        info6=(TextView)view.findViewById(R.id.info6);
        Typeface face = Typeface.createFromAsset(getContext().getAssets(), "arabic.ttf");
        info1.setTypeface(face);
        info2.setTypeface(face);
        info3.setTypeface(face);
        info4.setTypeface(face);
        info5.setTypeface(face);
        info6.setTypeface(face);
    }
}
