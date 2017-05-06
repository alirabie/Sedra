package sedra.appsmatic.com.sedra.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.SplashScreen;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class Settngs extends Fragment {

    private BetterSpinner langList;
    private CheckBox yes;
    private CheckBox no;
    List<String>languas=new ArrayList<>();
    private ImageView save;

    private int langFlag=0;
    private boolean loadImagesFlag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settngs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        languas.add(0, "عربي");
        languas.add(1, "English");

        loadImagesFlag=SaveSharedPreference.getImgLoadingSatatus(getContext());
        langList=(BetterSpinner)view.findViewById(R.id.lang_drop_dwon);
        yes=(CheckBox)view.findViewById(R.id.yes_check);
        no=(CheckBox)view.findViewById(R.id.no_check);
        save=(ImageView)view.findViewById(R.id.save_settings);
        //Set images languages
        if(SaveSharedPreference.getLangId(getContext()).equals("ar")){
            save.setImageResource(R.drawable.savebtn);
        }else{
            save.setImageResource(R.drawable.save_btn_en);
        }


        if(SaveSharedPreference.getImgLoadingSatatus(getContext())){
            yes.setChecked(true);
        }else {
            no.setChecked(true);
        }





        //Lang selection
        ArrayAdapter<String> langListdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome, languas);
        langList.setAdapter(langListdapter);
        langList.setHint(getResources().getString(R.string.selectlang));
        langList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set Lang Flag
                switch (position){
                    case 0:
                        langFlag = 1;
                        break;
                    case 1:
                        langFlag = 2;
                        break;

                }

            }
        });





























        yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    no.setChecked(false);
                    loadImagesFlag = true;
                }


            }
        });


        no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    yes.setChecked(false);
                    loadImagesFlag = false;
                }
            }
        });





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Save Lang Selection depended on lang flag
                switch (langFlag) {
                    case 0:

                        break;
                    case 1:
                        SaveSharedPreference.setLangId(getActivity().getApplicationContext(), "ar");
                        break;
                    case 2:
                        SaveSharedPreference.setLangId(getActivity().getApplicationContext(), "en");
                        break;
                }


                if (loadImagesFlag == true) {
                    SaveSharedPreference.setImgLoadStatus(getActivity().getApplicationContext(), true);
                } else {
                    SaveSharedPreference.setImgLoadStatus(getActivity().getApplicationContext(), false);
                }
                getActivity().finish();
                getContext().startActivity(new Intent(getContext(), SplashScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });







    }
}
