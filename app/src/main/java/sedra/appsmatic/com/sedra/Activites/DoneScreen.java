package sedra.appsmatic.com.sedra.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class DoneScreen extends AppCompatActivity {

    private FrameLayout bg;
    private ImageView contin,finishShopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_done_screen);

        bg=(FrameLayout)findViewById(R.id.donebox);
        contin=(ImageView)findViewById(R.id.contin_shopping_btn);
        finishShopping=(ImageView)findViewById(R.id.done_shopping_btn);

        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            contin.setImageResource(R.drawable.continueshoppingbtn_ar);
            finishShopping.setImageResource(R.drawable.completepurchasebtn_ar);
            bg.setBackground(getResources().getDrawable(R.drawable.donemessage_ar));

        }else{
            contin.setImageResource(R.drawable.continueshoppingbtn_en);
            finishShopping.setImageResource(R.drawable.completepurchasebtn_en);
            bg.setBackground(getResources().getDrawable(R.drawable.donemessage_en));

        }


        contin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoneScreen.this.finish();
            }
        });

    }
}
