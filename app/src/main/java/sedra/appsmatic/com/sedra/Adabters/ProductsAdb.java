package sedra.appsmatic.com.sedra.Adabters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
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

    @Override
    public void onBindViewHolder(vh0 holder, int position) {

        holder.priceTv.setText(products.getProducts().get(position).getPrice()+" SR");

        Picasso.with(context).load(products.getProducts().get(position).getImages().get(0).getSrc().toString()).fit().into(holder.productImg);


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




