package edu.gatech.cs1332.ratattack.controller;


import android.Manifest;
import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

import edu.gatech.cs1332.ratattack.R;
import edu.gatech.cs1332.ratattack.model.Database;
import edu.gatech.cs1332.ratattack.model.Rat;


public class location_fragment extends Fragment implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    Context mContext;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    Location location;
    String latitude,longitude;
    double lat, longt;
    Button ratDataButton;
    Geocoder gc;
    String fromdate;
    String todate;
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
        fromdate = "09/03/2012";
        todate = "09/05/2012";
        final EditText startdate = (EditText)mView.findViewById(R.id.startdate);
        startdate.setKeyListener(null);
        startdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Datepicker dialog = new Datepicker(v);
                fromdate = dialog.gettxtdate();
                dialog.show(getActivity().getFragmentManager(),DIALOG_DATE);
                update();
            }
        });
        final EditText enddate = (EditText)mView.findViewById(R.id.enddate);
        enddate.setKeyListener(null);
        enddate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Datepicker dialog = new Datepicker(v);
                todate = dialog.gettxtdate();
                dialog.show(getActivity().getFragmentManager(),DIALOG_DATE2);
                update();
            }
        });

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
//        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//        } else {
//            Toast.makeText(getActivity(), "Map Available", Toast.LENGTH_LONG).show();
//           if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                   == PackageManager.PERMISSION_GRANTED) {
//               locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//           } else {
//               locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//           }
//        }
//        Log.d("debug", "location!!");
//        currentlocation cl = new currentlocation(getContext());
//        location = cl.getLocation();
//        Log.d("debug", "location!!");
//        lat = cl.getlat(location);
//        longt = cl.getlog(location);
//        latitude = Double.toString(location.getLatitude());
//        longitude = Double.toString(location.getLongitude());
//        new DataLongOperationAsynchTask().execute();

    }

//    @Override
//    public void onLocationChanged(Location location) {
//        lat = location.getLatitude();
//        longt = location.getLongitude();
//        latitude = Double.toString(location.getLatitude());
//        longitude = Double.toString(location.getLongitude());
//    }
//    @Override
//    public void onProviderDisabled(String provider) {
//        Log.d("Latitude","disable");
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//        Log.d("Latitude","enable");
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//        Log.d("Latitude","status");
//    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        setMap(googleMap);
        /*
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("lol").snippet("I found a rat!"));
//        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mGoogleMap.setMyLocationEnabled(true);
//
//        } else {
//            Toast.makeText(getActivity(), "Map Available", Toast.LENGTH_LONG).show();
//            if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                mGoogleMap.setMyLocationEnabled(true);
////                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,longt)).title("lol").snippet("I found a rat!"));
//
//            }
//        }
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.7484,-73.9857)).title("lol").snippet("I found a rat!"));
        CameraPosition rat = CameraPosition.builder().target(new LatLng(40.7484,-73.9857)).zoom(16).bearing(0).tilt(45).build();


        List<Rat> rats = Database.getInstance().getRats();
        Geocoder gc = new Geocoder(getView().getContext());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Calendar c = java.util.Calendar.getInstance();
        for(Rat i : rats) {
            String ratdate = i.getCreate_date();
            String report = ratdate.substring(0,10);
            //Log.d("date", report);
            if (!(ratdate.equals(null))) {
                Date reportdate = c.getTime();
                try {
                    reportdate = sdf.parse(report);

                } catch (ParseException e) {
                    System.out.println("cannot parse");
                    e.printStackTrace();
                }
                Date before = c.getTime();
                try {
                    before = sdf.parse(todate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date after = c.getTime();
                try {
                    after = sdf.parse(fromdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (reportdate.before(before) && reportdate.after(after)) {
                    System.out.println("works");
                    MarkerOptions toAdd = i.getMarker(gc);
                    if (toAdd != null) {
                        Log.d("LocationFragment","Rat" + toAdd.getPosition().latitude + toAdd.getPosition().longitude);
                        googleMap.addMarker(i.getMarker(gc));
                    }
                }
            }
        }
        googleMap.moveCamera((CameraUpdateFactory.newCameraPosition(rat)));
        */
    }

    private void setMap(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("lol").snippet("I found a rat!"));
//        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mGoogleMap.setMyLocationEnabled(true);
//
//        } else {
//            Toast.makeText(getActivity(), "Map Available", Toast.LENGTH_LONG).show();
//            if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                mGoogleMap.setMyLocationEnabled(true);
////                googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,longt)).title("lol").snippet("I found a rat!"));
//
//            }
//        }
        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.7484,-73.9857)).title("lol").snippet("I found a rat!"));
        CameraPosition rat = CameraPosition.builder().target(new LatLng(40.7484,-73.9857)).zoom(16).bearing(0).tilt(45).build();


        List<Rat> rats = Database.getInstance().getRats();
        Geocoder gc = new Geocoder(getView().getContext());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Calendar c = java.util.Calendar.getInstance();
        for(Rat i : rats) {
            String ratdate = i.getCreate_date();
            String report = ratdate.substring(0,10);
            //Log.d("date", report);
            if (!(ratdate.equals(null))) {
                Date reportdate = c.getTime();
                try {
                    reportdate = sdf.parse(report);

                } catch (ParseException e) {
                    System.out.println("cannot parse");
                    e.printStackTrace();
                }
                Date before = c.getTime();
                try {
                    before = sdf.parse(todate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date after = c.getTime();
                try {
                    after = sdf.parse(fromdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (reportdate.before(before) && reportdate.after(after)) {
                    System.out.println("works");
                    MarkerOptions toAdd = i.getMarker(gc);
                    if (toAdd != null) {
                        Log.d("LocationFragment","Rat" + toAdd.getPosition().latitude + toAdd.getPosition().longitude);
                        googleMap.addMarker(i.getMarker(gc));
                    }
                }
            }
        }
        googleMap.moveCamera((CameraUpdateFactory.newCameraPosition(rat)));
    }

    public void update() {
        mGoogleMap.clear();
        setMap(mGoogleMap);
    }

}
