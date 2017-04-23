package sedra.appsmatic.com.sedra.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sedra.appsmatic.com.sedra.R;

public class Products extends Fragment {

    private TextView test;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        test=(TextView)view.findViewById(R.id.text);
        //receive Id and use it
        Bundle b = this.getArguments();
        String id = b.getString("Placeid");
        test.setText(id);

    }
}
