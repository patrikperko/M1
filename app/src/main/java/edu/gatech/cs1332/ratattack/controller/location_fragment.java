package edu.gatech.cs1332.ratattack.controller;


import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.gatech.cs1332.ratattack.R;

public class location_fragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    Button ratDataButton;
    Geocoder gc;
    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_DATE2 = "date";
    public static location_fragment newInstance() {
        location_fragment fragment = new location_fragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_fragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_location_fragment, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText startdate = (EditText)mView.findViewById(R.id.startdate);
        startdate.setKeyListener(null);
        startdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Datepicker dialog = new Datepicker(v);
                dialog.show(getActivity().getFragmentManager(),DIALOG_DATE);
            }
        });
        final EditText enddate = (EditText)mView.findViewById(R.id.enddate);
        enddate.setKeyListener(null);
        enddate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Datepicker dialog = new Datepicker(v);
                dialog.show(getActivity().getFragmentManager(),DIALOG_DATE2);
            }
        });

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.7484,-73.9857)).title("lol").snippet("I found a rat!"));
        CameraPosition rat = CameraPosition.builder().target(new LatLng(40.7484,-73.9857)).zoom(16).bearing(0).tilt(45).build();

//        //The place where the loop to pin all the rats is
//        List<Rat> rats = Database.getInstance().getRats();
//        Geocoder gc = new Geocoder(getView().getContext());
//        for(Rat i : rats) {
//            MarkerOptions toAdd = i.getMarker(gc);
//            if (toAdd != null) {
//                Log.d("LocationFragment","Rat" + toAdd.getPosition().latitude + toAdd.getPosition().longitude);
//                googleMap.addMarker(i.getMarker(gc));
//            }
//
//        }

        googleMap.moveCamera((CameraUpdateFactory.newCameraPosition(rat)));

    }

}
