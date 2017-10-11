package sedra.appsmatic.com.sedra.Activites;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.astuetz.PagerSlidingTabStrip;

import sedra.appsmatic.com.sedra.Adabters.CustomFragmentPagerAdapter;
import sedra.appsmatic.com.sedra.Fragments.PresentCardTabs.CardAddress;
import sedra.appsmatic.com.sedra.Fragments.PresentCardTabs.CardMessage;
import sedra.appsmatic.com.sedra.Fragments.PresentCardTabs.CardSchedule;
import sedra.appsmatic.com.sedra.R;

public class PresentCard extends AppCompatActivity {

    ViewPager p;
    PagerSlidingTabStrip tabsStrip;
    CustomFragmentPagerAdapter adapter;
    CardAddress cardAddress;
    CardMessage cardMessage;
    CardSchedule cardSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_cards);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Tab Layout With 3 fragments

        cardAddress=new CardAddress();
        cardMessage=new CardMessage();
        cardSchedule=new CardSchedule();
        adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(cardAddress,getResources().getString(R.string.cardaddress));
        adapter.addFragment(cardSchedule, getResources().getString(R.string.cardvdate));
        adapter.addFragment(cardMessage, getResources().getString(R.string.cardmessage));

        p=(ViewPager)findViewById(R.id.viewpager_presentcards);
        tabsStrip = (PagerSlidingTabStrip)findViewById(R.id.presentcrads_tabs_master);
        tabsStrip.setTextColor(Color.WHITE);
        p.setAdapter(adapter);
        tabsStrip.setViewPager(p);
        adapter.notifyDataSetChanged();


    }
}
