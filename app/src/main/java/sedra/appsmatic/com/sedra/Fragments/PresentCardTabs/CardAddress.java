package sedra.appsmatic.com.sedra.Fragments.PresentCardTabs;

import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import sedra.appsmatic.com.sedra.API.Models.PresentCards.Cardaddress;
import sedra.appsmatic.com.sedra.Activites.Home;
import sedra.appsmatic.com.sedra.Activites.PresentCard;
import sedra.appsmatic.com.sedra.GPS.GPSTracker;
import sedra.appsmatic.com.sedra.Prefs.SaveSharedPreference;
import sedra.appsmatic.com.sedra.R;

public class CardAddress extends Fragment implements OnMapReadyCallback {


    private Cardaddress cardaddress;
    private ImageView saveBtn;
    private GoogleMap mMap;
    private Double lat,lang;
    private GPSTracker gpsTracker;
    private Marker marker;
    private ImageView saveLocationBtn;
    private MapView mapView;
    private EditText street,buldingNum,receverPhone,notes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_address, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardaddress=new Cardaddress();
        gpsTracker=new GPSTracker(getContext());
        street=(EditText)view.findViewById(R.id.st_input);
        buldingNum=(EditText)view.findViewById(R.id.home_num_input);
        receverPhone=(EditText)view.findViewById(R.id.rciver_phone_input);
        notes=(EditText)view.findViewById(R.id.more_info_input);
        saveBtn=(ImageView)view.findViewById(R.id.save_delivery_location_btn);
        mapView=(MapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        //Set images languages
        if(SaveSharedPreference.getLangId(getContext()).equals("ar")){
           saveBtn.setImageResource(R.drawable.savebtn);
        }else{
            saveBtn.setImageResource(R.drawable.save_btn_en);
        }








        //Check GPS status
        final LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.gpsoff)
                    .setCancelable(false)
                    .setPositiveButton(R.string.turnon, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Home.turnLocationOn(getContext());
                        }
                    }).setIcon(android.R.drawable.alert_light_frame);
            AlertDialog alert = builder.create();
            alert.show();
        }










        //save address btn action
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                saveBtn.clearAnimation();
                saveBtn.setAnimation(anim);

                if(street.getText().toString().isEmpty()){
                    street.setError(getResources().getString(R.string.addsstreet));
                }else if(buldingNum.getText().toString().isEmpty()){
                    buldingNum.setError(getResources().getString(R.string.addbuldingnum));
                }else if(receverPhone.getText().toString().isEmpty()){
                    receverPhone.setError(getResources().getString(R.string.addrecevernum));
                }else if(notes.getText().toString().isEmpty()){
                    notes.setError(getResources().getString(R.string.addnote));
                }else {
                    cardaddress.setStreet(street.getText().toString());
                    cardaddress.setBuildingNo(buldingNum.getText().toString());
                    cardaddress.setRecieverPhoneNo(receverPhone.getText().toString());
                    cardaddress.setNote(notes.getText().toString());
                    PresentCard.card.setCardaddress(cardaddress);
                    PresentCard.giftAddress=true;
                    Toast.makeText(getContext(), getResources().getString(R.string.savesuccsess), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        lat=gpsTracker.getLatitude();
        lang=gpsTracker.getLongitude();
        LatLng currentLocation=new LatLng(lat,lang);
        marker=mMap.addMarker(new MarkerOptions().position(currentLocation).title(getResources().getString(R.string.locationdet)));
        float zoomLevel = (float) 16.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, zoomLevel));
    }
}
