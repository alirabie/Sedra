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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import sedra.appsmatic.com.sedra.API.Models.PresentCards.Cardmessage;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;


public class CardMessage extends Fragment {


    private Cardmessage cardmessage;
    private ImageView saveBtn;
    private EditText sendername,senderPhoneNum,receverName,message;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_present_message, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardmessage=new Cardmessage();
        saveBtn=(ImageView)view.findViewById(R.id.gift_msg_save_btn);
        sendername=(EditText)view.findViewById(R.id.gift_msg_name_input);
        senderPhoneNum=(EditText)view.findViewById(R.id.gift_msg_phone_input);
        receverName=(EditText)view.findViewById(R.id.gift_msg_recever_name_input);
        message=(EditText)view.findViewById(R.id.gift_msg_message_input);

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

                if(sendername.getText().toString().isEmpty()){
                    sendername.setError(getResources().getString(R.string.addsendername));
                }else if(senderPhoneNum.getText().toString().isEmpty()){
                    senderPhoneNum.setError(getResources().getString(R.string.addsenderphone));
                }else if(receverName.getText().toString().isEmpty()){
                    receverName.setError(getResources().getString(R.string.addrecevername));
                }else if(message.getText().toString().isEmpty()){
                    message.setError(getResources().getString(R.string.addmessage));
                }else {
                    cardmessage.setSender(sendername.getText().toString());
                    cardmessage.setSenderPhoneNo(senderPhoneNum.getText().toString());
                    cardmessage.setReciever(receverName.getText().toString());
                    cardmessage.setMessage(message.getText().toString());
                    PresentCard.card.setCardmessage(cardmessage);
                    PresentCard.giftMessage=true;
                    Toast.makeText(getContext(), getResources().getString(R.string.savesuccsess), Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
