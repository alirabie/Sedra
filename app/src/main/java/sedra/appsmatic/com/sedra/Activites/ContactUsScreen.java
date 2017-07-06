package sedra.appsmatic.com.sedra.Activites;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class ContactUsScreen extends AppCompatActivity {

    private TextView aboutStore;
    private ImageView home,send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_screen);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //setup items
        home=(ImageView)findViewById(R.id.home_btn_contactus);
        send=(ImageView)findViewById(R.id.countact_send_btn);

        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            send.setImageResource(R.drawable.sendbtn_ar);
        }else{
            send.setImageResource(R.drawable.sendbtn_en);
        }


        //home button action
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsScreen.this.finish();
            }
        });

    }
}
