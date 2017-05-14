package sedra.appsmatic.com.sedra.Adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sedra.appsmatic.com.sedra.R;

/**
 * Created by Eng Ali on 5/14/2017.
 */
public  class PaymentTypeAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private String[] contentArray;
    private Integer[] imageArray={R.drawable.v_isa,R.drawable.master_card};

    public PaymentTypeAdapter(Context context, int resource, String[] objects) {
        super(context,R.layout.spinner_value_layout,R.id.type_card,objects);
        this.ctx = context;
        this.contentArray = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }



    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_value_layout, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.type_card);
        textView.setText(contentArray[position]);
        ImageView imageView = (ImageView)row.findViewById(R.id.card_logo);
        imageView.setImageResource(imageArray[position]);

        return row;
    }

}