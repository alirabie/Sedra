package sedra.appsmatic.com.sedra.Activites;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class ProductInfoScreen extends ActionBarActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private SliderLayout mDemoSlider;
    private TextView pName,pPrice,pDec,pReady,countTv;
    private ImageView addToCartBtn,deliveryAddressBtn,deliveryTimeBtn,giftMsgBtn,sugTitle,up,down,favBtn;
    private static int count =0;
    private static String dayCount="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info_screen);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        pName=(TextView)findViewById(R.id.product_name_tv);
        pDec=(TextView)findViewById(R.id.desc_tv);
        pPrice=(TextView)findViewById(R.id.price_tv_info);
        pReady=(TextView)findViewById(R.id.time_to_redy);
        countTv=(TextView)findViewById(R.id.count_tv);
        addToCartBtn=(ImageView)findViewById(R.id.add_to_cart_tv);
        deliveryAddressBtn=(ImageView)findViewById(R.id.deliver_btn);
        deliveryTimeBtn=(ImageView)findViewById(R.id.time_deliver_btn);
        giftMsgBtn=(ImageView)findViewById(R.id.gift_msg_btn);
        sugTitle=(ImageView)findViewById(R.id.sug_title_id);
        up=(ImageView)findViewById(R.id.up_count);
        down=(ImageView)findViewById(R.id.dwon_count);
        favBtn=(ImageView)findViewById(R.id.fav_btn);


        //Set images languages
        if(SaveSharedPreference.getLangId(this).equals("ar")){
            addToCartBtn.setImageResource(R.drawable.addtocart_btn);
            deliveryAddressBtn.setImageResource(R.drawable.deliveryaddress);
            deliveryTimeBtn.setImageResource(R.drawable.deliverytime);
            giftMsgBtn.setImageResource(R.drawable.giftingmessage);
            sugTitle.setImageResource(R.drawable.suggestedtitle);
        }else{
            addToCartBtn.setImageResource(R.drawable.add_to_cart_btn_en);
            deliveryAddressBtn.setImageResource(R.drawable.delivery_address_btn_en);
            deliveryTimeBtn.setImageResource(R.drawable.delivery_time_btn_en);
            giftMsgBtn.setImageResource(R.drawable.gift_msg_btn_en);
            sugTitle.setImageResource(R.drawable.suggested_title_en);
        }


        Generator.createService(SedraApi.class).getProductInfo(getIntent().getStringExtra("product_id")).enqueue(new Callback<ResProducts>() {
            @Override
            public void onResponse(Call<ResProducts> call, Response<ResProducts> response) {
                if (response.isSuccessful()) {

                    try {
                        pName.setText(response.body().getProducts().get(0).getName() + "");
                        pDec.setText(response.body().getProducts().get(0).getShortDescription() + "");
                        pPrice.setText(response.body().getProducts().get(0).getPrice() + getResources().getString(R.string.sr));
                        if(!response.body().getProducts().get(0).getAttributes().isEmpty()){
                            if(response.body().getProducts().get(0).getAttributes().get(0).getDefaultValue()!=null) {
                                if (response.body().getProducts().get(0).getAttributes().get(0).getDefaultValue().equals("0")) {
                                    pReady.setText(getResources().getString(R.string.sameday));
                                } else {
                                    pReady.setText(response.body().getProducts().get(0).getAttributes().get(0).getDefaultValue() + " " + getResources().getString(R.string.day));
                                    dayCount=response.body().getProducts().get(0).getAttributes().get(0).getDefaultValue();
                                }
                            }else {pReady.setText(getResources().getString(R.string.notset));}
                        }else {
                            pReady.setText(getResources().getString(R.string.notset));
                        }

                        //Check Settings For Load images
                        if (SaveSharedPreference.getImgLoadingSatatus(ProductInfoScreen.this)) {
                            for (int i = 0; i < response.body().getProducts().get(0).getImages().size(); i++) {
                                TextSliderView textSliderView = new TextSliderView(ProductInfoScreen.this);
                                textSliderView
                                        .image(response.body().getProducts().get(0).getImages().get(i).getSrc())
                                        .setScaleType(BaseSliderView.ScaleType.Fit);
                                mDemoSlider.addSlider(textSliderView);
                            }
                        } else {
                            for (int i = 0; i < response.body().getProducts().get(0).getImages().size(); i++) {
                                TextSliderView textSliderView = new TextSliderView(ProductInfoScreen.this);
                                textSliderView
                                        .image(R.drawable.placeholder)
                                        .setScaleType(BaseSliderView.ScaleType.Fit);
                                mDemoSlider.addSlider(textSliderView);
                            }
                        }
                        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                        mDemoSlider.setDuration(4000);
                }catch (Exception e){
                        NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ProductInfoScreen.this);
                        dialogBuilder
                                .withTitle(getResources().getString(R.string.conectionerrorr))
                                .withDialogColor(R.color.colorPrimary)
                                .withTitleColor("#FFFFFF")
                                .withIcon(getResources().getDrawable(R.drawable.icon))
                                .withDuration(700)                                          //def
                                .withEffect(Effectstype.RotateBottom)
                                .withMessage(e.getMessage() + " : Error from loading images from server ")
                                .show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResProducts> call, Throwable t) {

                NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(ProductInfoScreen.this);
                dialogBuilder
                        .withTitle(getResources().getString(R.string.conectionerrorr))
                        .withDialogColor(R.color.colorPrimary)
                        .withTitleColor("#FFFFFF")
                        .withIcon(getResources().getDrawable(R.drawable.icon))
                        .withDuration(700)                                          //def
                        .withEffect(Effectstype.RotateBottom)
                        .withMessage(t.getMessage() + " : From product info ")
                        .show();


            }
        });

        /////add to cart button
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset count
                count=0;
                startActivity(new Intent(ProductInfoScreen.this,DoneScreen.class));
                ProductInfoScreen.this.finish();
            }
        });


        //Delivery Date btn
        deliveryTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductInfoScreen.this,DeliveryDateScreen.class).putExtra("dayes",dayCount));
                //Reset Day Count
                dayCount="0";
            }
        });

        //Gift Msg btn
        giftMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductInfoScreen.this, GiftMessageScreen.class));
            }
        });


        //Delivery Address btn
        deliveryAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductInfoScreen.this, DeliveryAddress.class));
            }
        });


        ////up down actions
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countTv.setText(count + "");
            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    return;
                }else {
                    count--;
                    countTv.setText(count+"");
                }
            }
        });




        //fav btn action
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favBtn.setImageResource(R.drawable.favoriteheart);
            }
        });










    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        count=0;
    }
}
