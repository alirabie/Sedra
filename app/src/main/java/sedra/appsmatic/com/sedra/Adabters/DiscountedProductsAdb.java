package sedra.appsmatic.com.sedra.Adabters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.ProductInfoScreen;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 4/30/2017.
 */
public class DiscountedProductsAdb extends RecyclerView.Adapter<DiscountedProductsAdb.vh0> {

    private ResProducts products;
    private Context context;


    public DiscountedProductsAdb(ResProducts products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public vh0 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new vh0(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_disc_prouduct,parent,false));
    }

    @Override
    public void onBindViewHolder(vh0 holder, final int position) {

        holder.priceTv.setText(products.getProducts().get(position).getPrice()+" "+ Home.currency.getSymbol());

        if(products.getProducts().get(position).getImages().isEmpty()){
            Picasso.with(context)
                    .load(R.drawable.placeholder)
                    .fit()
                    .into(holder.productImg);

        }else {
            //Check Settings For Load images
            if (SaveSharedPreference.getImgLoadingSatatus(context)) {
                Picasso.with(context)
                        .load(products.getProducts().get(position).getImages().get(0).getSrc().toString())
                        .fit()
                        .into(holder.productImg);
            } else {
                Picasso.with(context)
                        .load(R.drawable.placeholder)
                        .fit()
                        .into(holder.productImg);
            }


            holder.productImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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




