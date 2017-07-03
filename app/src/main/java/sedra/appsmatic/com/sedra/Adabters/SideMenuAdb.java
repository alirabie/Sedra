package sedra.appsmatic.com.sedra.Adabters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import sedra.appsmatic.com.sedra.Activites.AboutUsScreen;
import sedra.appsmatic.com.sedra.Activites.ContactUsScreen;
import sedra.appsmatic.com.sedra.Activites.DiscountedScreen;
import sedra.appsmatic.com.sedra.Activites.FloatingLoginDialog;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.ShoppingCart;
import sedra.appsmatic.com.sedra.Activites.SignInScreen;
import sedra.appsmatic.com.sedra.Activites.SignUpScreen;
import sedra.appsmatic.com.sedra.Activites.SplashScreen;
import sedra.appsmatic.com.sedra.Fragments.Products;
import sedra.appsmatic.com.sedra.Fragments.Settngs;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 5/1/2017.
 */
public class SideMenuAdb extends RecyclerView.Adapter<SideMenuAdb.vh1> {

    Context context;
    DrawerLayout drawer;

    public SideMenuAdb(Context context) {
        this.context = context;
    }

    private int[] lablesLoggedIn={
            R.string.home,
            R.string.settings,
            R.string.myaccount,
            R.string.shoppingbasket,
            R.string.descounted,
            R.string.morerequsted,
            R.string.fav,
            R.string.aboutus,
            R.string.callus,
            R.string.exit};

    private int[] lablesLoggedOut={
            R.string.home,
            R.string.settings,
            R.string.newaccount,
            R.string.shoppingbasket,
            R.string.descounted,
            R.string.morerequsted,
            R.string.fav,
            R.string.aboutus,
            R.string.callus,
            R.string.enter};

    private int[] logosLoggedIn={
            R.drawable.home,
            R.drawable.settings,
            R.drawable.myaccount,
            R.drawable.shoppingbasket,
            R.drawable.discountedproducts,
            R.drawable.morerequested,
            R.drawable.favorites,
            R.drawable.aboutus,
            R.drawable.contactus,
            R.drawable.signout};

    private int[] logosLoggedOut={
            R.drawable.home,
            R.drawable.settings,
            R.drawable.newaccount,
            R.drawable.shoppingbasket,
            R.drawable.discountedproducts,
            R.drawable.morerequested,
            R.drawable.favorites,
            R.drawable.aboutus,
            R.drawable.contactus,
            R.drawable.signin};




    @Override
    public vh1 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh1(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_side_menu,parent,false));
    }

    @Override
    public void onBindViewHolder(vh1 holder, final int position) {

        if(SaveSharedPreference.getCustomerId(context).isEmpty()) {

            // In case of customer is logged out and customer id empty
            animate(holder);
            holder.lable.setText(lablesLoggedOut[position]);
            //Check Os Ver
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.lable.setBackgroundResource(R.drawable.ripple);
            }
            Picasso.with(context).load(logosLoggedOut[position]).into(holder.logo);
            drawer = (DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout);
            Typeface face = Typeface.createFromAsset(context.getAssets(), "arabic.ttf");
            holder.lable.setTypeface(face);
            holder.lable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            //Home
                            Products products = new Products();
                            Bundle bundle = new Bundle();
                            products.setArguments(bundle);
                            android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragmentcontener, products);
                            fragmentTransaction.commit();
                            //un active all buttons
                            Home.flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                            Home.giftBtn.setImageResource(R.drawable.giftbtnunactive);
                            Home.plantsBtn.setImageResource(R.drawable.plantsbtnunactive);
                            Home.cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 1:
                            Settngs settngs = new Settngs();
                            android.support.v4.app.FragmentManager fragmentManager2 = ((FragmentActivity) context).getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                            fragmentTransaction2.replace(R.id.fragmentcontener, settngs);
                            fragmentTransaction2.commit();
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 2:
                            //Create new account
                            context.startActivity(new Intent(context, SignUpScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;

                        case 3:
                            //Shopping cart
                            if(SaveSharedPreference.getCustomerId(context).isEmpty()){
                                FloatingLoginDialog.startShow(context);
                            }else {
                                context.startActivity(new Intent(context, ShoppingCart.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                drawer.closeDrawer(GravityCompat.START);
                            }
                            break;

                        case 4:
                            //Discounted screen
                            context.startActivity(new Intent(context, DiscountedScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;

                        case 5:

                            break;

                        case 6:

                            break;

                        case 7:

                            //About Us
                            context.startActivity(new Intent(context, AboutUsScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 8:
                            //Contact Us
                            context.startActivity(new Intent(context, ContactUsScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 9:
                            //Sign in
                            context.startActivity(new Intent(context, SignInScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                    }


                }
            });

        }else {

            // in case of customer logged in and customer id not Empty
            animate(holder);
            holder.lable.setText(lablesLoggedIn[position]);
            //Check Os Ver
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.lable.setBackgroundResource(R.drawable.ripple);
            }
            Picasso.with(context).load(logosLoggedIn[position]).into(holder.logo);
            drawer = (DrawerLayout) ((Activity) context).findViewById(R.id.drawer_layout);
            Typeface face = Typeface.createFromAsset(context.getAssets(), "arabic.ttf");
            holder.lable.setTypeface(face);
            holder.lable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            //Home
                            Products products = new Products();
                            Bundle bundle = new Bundle();
                            products.setArguments(bundle);
                            android.support.v4.app.FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragmentcontener, products);
                            fragmentTransaction.commit();
                            //un active all buttons
                            Home.flwerBtn.setImageResource(R.drawable.flowerbtnunactive);
                            Home.giftBtn.setImageResource(R.drawable.giftbtnunactive);
                            Home.plantsBtn.setImageResource(R.drawable.plantsbtnunactive);
                            Home.cookiesBtn.setImageResource(R.drawable.cookiesbtnunactive);
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 1:
                            Settngs settngs = new Settngs();
                            android.support.v4.app.FragmentManager fragmentManager2 = ((FragmentActivity) context).getSupportFragmentManager();
                            android.support.v4.app.FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                            fragmentTransaction2.replace(R.id.fragmentcontener, settngs);
                            fragmentTransaction2.commit();
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 2:
                            //My account

                            break;

                        case 3:
                            //Shopping cart
                            context.startActivity(new Intent(context, ShoppingCart.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;

                        case 4:
                            //Discounted screen
                            context.startActivity(new Intent(context, DiscountedScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;

                        case 5:

                            break;

                        case 6:

                            break;

                        case 7:

                            //About Us
                            context.startActivity(new Intent(context, AboutUsScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 8:
                            //Contact Us
                            context.startActivity(new Intent(context, ContactUsScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                        case 9:
                            //Sign out

                            SaveSharedPreference.setCustomerId(context,"");
                            context.startActivity(new Intent(context, SplashScreen.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            ((Activity) context).finish();
                            drawer.closeDrawer(GravityCompat.START);
                            break;
                    }


                }
            });





































        }




















    }

    @Override
    public int getItemCount() {
        return 10;
    }

    //Animate Adapter
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
    public static class vh1 extends RecyclerView.ViewHolder{

        private ImageView logo;
        private TextView lable;
        public vh1(View itemView) {
            super(itemView);

            logo=(ImageView)itemView.findViewById(R.id.side_menu_icon);
            lable=(TextView)itemView.findViewById(R.id.side_menu_lable);
        }
    }
}
