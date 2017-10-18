package sedra.appsmatic.com.sedra.Activites;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.PresentCards.Card;
import sedra.appsmatic.com.sedra.API.Models.PresentCards.OrderCard;
import sedra.appsmatic.com.sedra.API.Models.PresentCards.ReqPresentCard;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Adabters.CustomFragmentPagerAdapter;
import sedra.appsmatic.com.sedra.Fragments.PresentCardTabs.CardAddress;
import sedra.appsmatic.com.sedra.Fragments.PresentCardTabs.CardMessage;
import sedra.appsmatic.com.sedra.Fragments.PresentCardTabs.CardSchedule;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class PresentCard extends AppCompatActivity {

    ViewPager p;
    PagerSlidingTabStrip tabsStrip;
    CustomFragmentPagerAdapter adapter;
    TextView addCard ;

    public static int vendorId;
    public static int schudleId;


    //Fragments
    CardAddress cardAddress;
    CardMessage cardMessage;
    CardSchedule cardSchedule;

    //Models
    public static Card card;
    private OrderCard orderCard;

    //Check tabs
    public static boolean gitSchudle,giftMessage,giftAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_present_cards);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        giftAddress=false;
        giftMessage=false;
        gitSchudle=false;


      //Receive vendor id
       vendorId = getIntent().getIntExtra("vendorid",0);







        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }



        Toast.makeText(this,getIntent().getIntExtra("product_id",0)+" Count "+getIntent().getIntExtra("count",0)+" Vendor Id "+vendorId,Toast.LENGTH_LONG).show();




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




        addCard=(TextView)findViewById(R.id.master_add_card_button);


        //Setup Card
        card=new Card();
        card.setProductId(getIntent().getIntExtra("product_id",0));
        card.setQuantity(getIntent().getIntExtra("count",0));


        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(PresentCard.this, R.anim.alpha);
                addCard.clearAnimation();
                addCard.setAnimation(anim);

                if(!giftMessage){
                    Toast.makeText(PresentCard.this,getResources().getString(R.string.selectgiftmessgae),Toast.LENGTH_LONG).show();
                }else if (!gitSchudle){
                    Toast.makeText(PresentCard.this,getResources().getString(R.string.selectgiftschadule),Toast.LENGTH_LONG).show();
                }else if (!giftAddress){
                    Toast.makeText(PresentCard.this,getResources().getString(R.string.selectgiftaddress),Toast.LENGTH_LONG).show();
                }else {

                    orderCard=new OrderCard();
                    List<Card>cards=new ArrayList<Card>();
                    cards.add(card);
                    orderCard.setCards(cards);
                    orderCard.setOrderId(Integer.parseInt(SaveSharedPreference.getOrderId(PresentCard.this)));
                    ReqPresentCard reqPresentCard =new ReqPresentCard();
                    reqPresentCard.setOrderCard(orderCard);


                    Generator.createService(SedraApi.class).selectPresentCard(orderCard).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(PresentCard.this, getResources().getString(R.string.doneaddcard), Toast.LENGTH_LONG).show();
                                giftAddress=false;
                                giftMessage=false;
                                gitSchudle=false;
                                card.setCardmessage(null);
                                card.setCardaddress(null);
                                card.setCardschedule(null);
                                PresentCard.this.finish();
                            } else {

                                Toast.makeText(PresentCard.this, getResources().getString(R.string.faild), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PresentCard.this, getResources().getString(R.string.erorr)+t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });





                }



            }
        });





    }










    @Override
    protected void onResume() {
        super.onResume();
        giftAddress=false;
        giftMessage=false;
        gitSchudle = false;
        card.setCardmessage(null);
        card.setCardaddress(null);
        card.setCardschedule(null);


    }
}
