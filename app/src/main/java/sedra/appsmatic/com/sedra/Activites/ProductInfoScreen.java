package sedra.appsmatic.com.sedra.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ReqCartItems;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ResCartItems;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ShoppingCartItem;
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
    NiftyDialogBuilder dialogBuildercard;
    private FrameLayout bg;
    private ImageView contin,finishShopping;
    private Boolean isRental;

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

        isRental=false;
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
                        isRental=response.body().getProducts().get(0).getIsRental();
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
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                addToCartBtn.clearAnimation();
                addToCartBtn.setAnimation(anim);

                if(SaveSharedPreference.getCustomerId(ProductInfoScreen.this).isEmpty()){

                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ProductInfoScreen.this);
                    dialogBuilder
                            .withTitle(getResources().getString(R.string.sedra))
                            .withDialogColor(R.color.colorPrimary)
                            .withTitleColor("#FFFFFF")
                            .withIcon(getResources().getDrawable(R.drawable.icon))
                            .withDuration(700)                                          //def
                            .withEffect(Effectstype.RotateBottom)
                            .withMessage(getResources().getString(R.string.loginplease))
                            .withButton1Text(getResources().getString(R.string.logintitle))
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(ProductInfoScreen.this,SignInScreen.class));
                                }
                            })
                            .show();

                }else {
                    if (count == 0) {

                        NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ProductInfoScreen.this);
                        dialogBuilder
                                .withTitle(getResources().getString(R.string.sedra))
                                .withDialogColor(R.color.colorPrimary)
                                .withTitleColor("#FFFFFF")
                                .withIcon(getResources().getDrawable(R.drawable.icon))
                                .withDuration(700)                                          //def
                                .withEffect(Effectstype.RotateBottom)
                                .withMessage(getResources().getString(R.string.countitem))
                                .show();

                    } else {
                        //Get Current Date in UTC
                        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        Date currentLocalTime = cal.getTime();
                        DateFormat date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
                        date.setTimeZone(TimeZone.getTimeZone("UTC"));
                        final String localTime = date.format(currentLocalTime);
                        ReqCartItems reqCartItems = new ReqCartItems();
                        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                        if(isRental){
                            shoppingCartItem.setRentalStartDateUtc("2030-06-23T16:15:47-04:00");
                            shoppingCartItem.setRentalEndDateUtc("2030-07-23T16:15:47-06:00");
                        }
                        shoppingCartItem.setId(null);
                        shoppingCartItem.setCustomerEnteredPrice(null);
                        shoppingCartItem.setQuantity(count + "");
                        shoppingCartItem.setCreatedOnUtc(localTime);
                        shoppingCartItem.setUpdatedOnUtc(localTime);
                        shoppingCartItem.setShoppingCartType("1");
                        shoppingCartItem.setProductId(getIntent().getStringExtra("product_id"));
                        shoppingCartItem.setCustomerId(SaveSharedPreference.getCustomerId(ProductInfoScreen.this));
                        reqCartItems.setShoppingCartItem(shoppingCartItem);
                        Generator.createService(SedraApi.class).addItemToCart(reqCartItems).enqueue(new Callback<ResCartItems>() {
                            @Override
                            public void onResponse(Call<ResCartItems> call, Response<ResCartItems> response) {

                                if (response.isSuccessful()) {
                                    Home.getCartItemsCount(ProductInfoScreen.this, "2");
                                    //Initialize Done Dialog
                                    dialogBuildercard = NiftyDialogBuilder.getInstance(ProductInfoScreen.this);
                                    dialogBuildercard
                                            .withDuration(700)//def
                                            .withDialogColor(getResources().getColor(R.color.colorPrimary))
                                            .withEffect(Effectstype.Newspager)
                                            .withTitleColor(getResources().getColor(R.color.colorPrimary))
                                            .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                                            .setCustomView(R.layout.activity_done_screen, addToCartBtn.getContext())
                                            .show();


                                    dialogBuildercard.setOnKeyListener(new DialogInterface.OnKeyListener() {
                                        @Override
                                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                                            return keyCode == KeyEvent.KEYCODE_BACK;
                                        }
                                    });




                                    bg = (FrameLayout) dialogBuildercard.findViewById(R.id.donebox);
                                    contin = (ImageView) dialogBuildercard.findViewById(R.id.contin_shopping_btn);
                                    finishShopping = (ImageView) dialogBuildercard.findViewById(R.id.done_shopping_btn);
                                    //Set images languages
                                    if (SaveSharedPreference.getLangId(ProductInfoScreen.this).equals("ar")) {
                                        contin.setImageResource(R.drawable.continueshoppingbtn_ar);
                                        finishShopping.setImageResource(R.drawable.completepurchasebtn_ar);
                                        bg.setBackground(getResources().getDrawable(R.drawable.donemessage_ar));
                                    } else {
                                        contin.setImageResource(R.drawable.continueshoppingbtn_en);
                                        finishShopping.setImageResource(R.drawable.completepurchasebtn_en);
                                        bg.setBackground(getResources().getDrawable(R.drawable.donemessage_en));
                                    }

                                    contin.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialogBuildercard.dismiss();
                                            ProductInfoScreen.this.finish();
                                        }
                                    });

                                    finishShopping.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialogBuildercard.dismiss();
                                            startActivity(new Intent(ProductInfoScreen.this, ShoppingCart.class));
                                            ProductInfoScreen.this.finish();
                                        }
                                    });

                                    Log.e("Success", response.body().getShoppingCarts().size() + "");
                                    //reset count
                                    count = 0;
                                    isRental=false;
                                } else {


                                    NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(ProductInfoScreen.this);
                                    dialogBuilder
                                            .withTitle(getResources().getString(R.string.sedra))
                                            .withDialogColor(R.color.colorPrimary)
                                            .withTitleColor("#FFFFFF")
                                            .withIcon(getResources().getDrawable(R.drawable.icon))
                                            .withDuration(700)                                          //def
                                            .withEffect(Effectstype.RotateBottom)
                                            .withMessage(getResources().getString(R.string.erorr))
                                            .show();

                                    Log.e("NotSuccess", localTime + " " + getIntent().getStringExtra("product_id"));

                                }

                            }

                            @Override
                            public void onFailure(Call<ResCartItems> call, Throwable t) {
                                Log.e("fail", t.getMessage());
                            }
                        });

                    }

                }



            }
        });


        //Delivery Date btn
        deliveryTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                deliveryTimeBtn.clearAnimation();
                deliveryTimeBtn.setAnimation(anim);
                startActivity(new Intent(ProductInfoScreen.this,DeliveryDateScreen.class).putExtra("dayes",dayCount));
                //Reset Day Count
                dayCount="0";
            }
        });

        //Gift Msg btn
        giftMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                giftMsgBtn.clearAnimation();
                giftMsgBtn.setAnimation(anim);
                startActivity(new Intent(ProductInfoScreen.this, GiftMessageScreen.class));
            }
        });


        //Delivery Address btn
        deliveryAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                deliveryAddressBtn.clearAnimation();
                deliveryAddressBtn.setAnimation(anim);
                startActivity(new Intent(ProductInfoScreen.this, DeliveryAddress.class));
            }
        });


        ////up down actions
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                up.clearAnimation();
                up.setAnimation(anim);
                count++;
                countTv.setText(count + "");
            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                down.clearAnimation();
                down.setAnimation(anim);
                if(count==0){
                    return;
                }else {
                    count--;
                    countTv.setText(count+"");
                }
            }
        });



        //Fav button logic
        favBtn.setImageResource(R.drawable.favoriteheartunacvtive);
        favBtn.setTag("1");

        //fav btn action
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favBtn.getTag().equals("1")){
                favBtn.setImageResource(R.drawable.favoriteheart);
                    favBtn.setTag("2");
                }else {
                 favBtn.setImageResource(R.drawable.favoriteheartunacvtive);
                    favBtn.setTag("1");
                }
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
