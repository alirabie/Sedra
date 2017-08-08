package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class DeliveryDateScreen extends AppCompatActivity {


    private ImageView saveBtn;
    private DatePicker simpleDatePicker;
    private int day,month,year;
    Date currentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_date_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        currentDate=new Date();
        currentDate.setTime(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(Integer.parseInt(getIntent().getStringExtra("dayes"))));
        simpleDatePicker = (DatePicker)findViewById(R.id.simpleDatePicker); // initiate a date picker
        saveBtn=(ImageView)findViewById(R.id.save_date_btn);
        simpleDatePicker.setSpinnersShown(false);
        simpleDatePicker.setMinDate(currentDate.getTime());



        Toast.makeText(DeliveryDateScreen.this, "Vendor Id = "+getIntent().getIntExtra("vendorid",0), Toast.LENGTH_LONG).show();


        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            saveBtn.setImageResource(R.drawable.savebtn);
        }else{
            saveBtn.setImageResource(R.drawable.save_btn_en);
        }


        //save buton action
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = simpleDatePicker.getDayOfMonth();
                month = simpleDatePicker.getMonth()+1;
                year = simpleDatePicker.getYear();
                Date date=new Date();
                date.setDate(day);
                date.setMonth(simpleDatePicker.getMonth());
              if(date.before(currentDate)){
                          Toast.makeText(DeliveryDateScreen.this, "You cannot select previous date !", Toast.LENGTH_LONG).show();
                      }else {
                          String date2 = "Selected Date : " + day + "-" + month + "-" + year;
                          Toast.makeText(DeliveryDateScreen.this, date2, Toast.LENGTH_LONG).show();
                      }
                  }

        });


    }
}
