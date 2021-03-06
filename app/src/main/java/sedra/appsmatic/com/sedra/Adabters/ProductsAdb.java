package sedra.appsmatic.com.sedra.Adabters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.squareup.picasso.Picasso;

import java.util.Currency;
import java.util.Locale;

import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.ProductInfoScreen;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 4/30/2017.
 */
public class ProductsAdb extends RecyclerView.Adapter<ProductsAdb.vh0> {

    private ResProducts products;
    private Context context;


    public ProductsAdb(ResProducts products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public vh0 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh0(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_prouduct,parent,false));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final vh0 holder, final int position) {
        animate(holder);


        holder.priceTv.setText(products.getProducts().get(position).getPrice()+" "+ Home.currency.getSymbol());

        if(products.getProducts().get(position).getImages().isEmpty()){
            Picasso.with(context)
                    .load(R.drawable.placeholder)
                    .fit()
                    .into(holder.productImg);

        }else{
        //Check Settings For Load images
        if(SaveSharedPreference.getImgLoadingSatatus(context)){
            try {
                Picasso.with(context)
                        .load(products.getProducts().get(position).getImages().get(0).getSrc().toString())
                        .fit()
                        .into(holder.productImg);
            }catch (Exception e){
                NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
                dialogBuilder
                        .withTitle(context.getResources().getString(R.string.conectionerrorr))
                        .withDialogColor(R.color.colorPrimary)
                        .withTitleColor("#FFFFFF")
                        .withIcon(context.getResources().getDrawable(R.drawable.icon))
                        .withDuration(700)                                          //def
                        .withEffect(Effectstype.RotateBottom)
                        .withMessage(e.getMessage() + "connection error from products adb ")
                        .show();
            }

        }else {
            Picasso.with(context)
                    .load(R.drawable.placeholder)
                    .fit()
                    .into(holder.productImg);
        }




        holder.productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animFadein = AnimationUtils.loadAnimation(context, R.anim.alpha);
                holder.productImg.clearAnimation();
                holder.productImg.startAnimation(animFadein);
                context.startActivity(new Intent(context, ProductInfoScreen.class)
                      .putExtra("product_id", products.getProducts().get(position).getId() + "")
                      .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }
    }

    @Override
    public int getItemCount() {
        return products.getProducts().size();
    }


    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }





public static class vh0 extends RecyclerView.ViewHolder{

    private ImageView productImg;
    private TextView priceTv;
    public vh0(View itemView) {
        super(itemView);

        productImg=(ImageView)itemView.findViewById(R.id.prouductimage);
        priceTv=(TextView)itemView.findViewById(R.id.pricelable);



    }
}

    }




