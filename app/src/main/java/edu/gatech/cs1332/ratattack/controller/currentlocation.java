package edu.gatech.cs1332.ratattack.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by ziwei on 10/31/17.
 */

public class currentlocation {
    Context mContext;
    Location location;
    public currentlocation(Context mContext) {
        this.mContext = mContext;
    }

    public Location getLocation() {
        LocationManager locationManager;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager)mContext.getSystemService(context);
        String provider = LocationManager.GPS_PROVIDER;
        if (ContextCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(provider);
        } else {
                 location= locationManager.getLastKnownLocation(provider);
        }
    return location;
    }
    public String updateWithNewLocation(Location location) {
        String latLongString;

        if (location != null){
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        }else{
            latLongString = "No Location";
        }

        return latLongString;
    }
    public double getlat(Location location) {
        return location.getLatitude();
    }
    public double getlog(Location location) {
        return location.getLongitude();
    }

}
