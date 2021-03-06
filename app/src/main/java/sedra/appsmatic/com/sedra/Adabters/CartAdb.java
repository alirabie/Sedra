package sedra.appsmatic.com.sedra.Adabters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ResCartItems;
import sedra.appsmatic.com.sedra.API.Models.UpdateOrder.OrderItem;
import sedra.appsmatic.com.sedra.API.Models.UpdateOrder.ReqNewOrderItem;
import sedra.appsmatic.com.sedra.API.WebServiceTools.Generator;
import sedra.appsmatic.com.sedra.API.WebServiceTools.SedraApi;
import sedra.appsmatic.com.sedra.Activites.GiftMessageScreen;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.Activites.ShoppingCart;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 6/14/2017.
 */
public class CartAdb extends RecyclerView.Adapter<CartAdb.Vh10025> {

    private ResCartItems resCartItems;
    private Context context;

    public CartAdb(ResCartItems resCartItems, Context context) {
        this.resCartItems = resCartItems;
        this.context = context;
    }

    @Override
    public Vh10025 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh10025(LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(final Vh10025 holder, final int position) {
        animate(holder);

        holder.pName.setText(resCartItems.getShoppingCarts().get(position).getProduct().getName()+" # "+resCartItems.getShoppingCarts().get(position).getQuantity());
        holder.pPrice.setText(resCartItems.getShoppingCarts().get(position).getProduct().getPrice() +" "+ Home.currency.getSymbol());


        //Date setup
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat DesiredFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        // 'a' for AM/PM

        Date date = null;
        try {
            date = sourceFormat.parse(resCartItems.getShoppingCarts().get(position).getProduct().getCreatedOnUtc()+"");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = DesiredFormat.format(date.getTime());
        holder.pDate.setText(formattedDate);


        //Delete item from shopping cart
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.deleteBtn.clearAnimation();
                holder.deleteBtn.setAnimation(anim);
                //Invoking delete item from card method
                Home.deleteItemFromShoppingCart(context,resCartItems.getShoppingCarts().get(position).getId());
                if(!SaveSharedPreference.getOrderId(context).isEmpty()){
                    Home.deleteItemFromOrder(context,SaveSharedPreference.getOrderId(context),resCartItems.getShoppingCarts().get(position).getProductId()+"");
                }

            }
        });




        //Set images languages
        if(SaveSharedPreference.getLangId(context).equals("ar")){
            holder.addGiftCard.setImageResource(R.drawable.addcardbtn);
        }else{
            holder.addGiftCard.setImageResource(R.drawable.addcardbtn_en);
        }


        //add gift card button action
        holder.addGiftCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.addGiftCard.clearAnimation();
                holder.addGiftCard.setAnimation(anim);

                context.startActivity(new Intent(context, PresentCard.class)
                        .putExtra("product_id", resCartItems.getShoppingCarts().get(position).getProductId())
                        .putExtra("count",resCartItems.getShoppingCarts().get(position).getQuantity())
                        .putExtra("vendorid",resCartItems.getShoppingCarts().get(position).getProduct().getVendorId()));


            }
        });


    }



    @Override
    public int getItemCount() {
        return resCartItems.getShoppingCarts().size();
    }

    //Animate Adapter
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    public static class Vh10025 extends RecyclerView.ViewHolder{

        private TextView pName,pPrice,pDate;
        private ImageView deleteBtn,addGiftCard;

        public Vh10025(View itemView) {
            super(itemView);

            pName=(TextView)itemView.findViewById(R.id.cart_product_name_tv);
            pPrice=(TextView)itemView.findViewById(R.id.cart_p_price);
            pDate=(TextView)itemView.findViewById(R.id.cart_p_date);
            deleteBtn=(ImageView)itemView.findViewById(R.id.cart_del_btn);
            addGiftCard=(ImageView)itemView.findViewById(R.id.addgiftcard_btn);
        }
    }
}
