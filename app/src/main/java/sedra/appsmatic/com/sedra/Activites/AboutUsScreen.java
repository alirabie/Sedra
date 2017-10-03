package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
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

public class AboutUsScreen extends AppCompatActivity {

    private TextView info1,info2,info3,info4,info5,info6;
    private ImageView home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about_us_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //week end

        home=(ImageView)findViewById(R.id.home_btn_abotus);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsScreen.this.finish();
            }
        });

        info1=(TextView)findViewById(R.id.info1);
        info2=(TextView)findViewById(R.id.info2);
        info3=(TextView)findViewById(R.id.info3);
        info4=(TextView)findViewById(R.id.info4);
        info5=(TextView)findViewById(R.id.info5);
        info6=(TextView)findViewById(R.id.info6);

        Typeface face = Typeface.createFromAsset(this.getAssets(), "arabic.ttf");
        info1.setTypeface(face);
        info2.setTypeface(face);
        info3.setTypeface(face);
        info4.setTypeface(face);
        info5.setTypeface(face);
        info6.setTypeface(face);




    }
}
