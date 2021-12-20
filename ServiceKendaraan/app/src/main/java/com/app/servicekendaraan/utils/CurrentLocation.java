package com.app.servicekendaraan.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class CurrentLocation {
    private static LocationRequest lr = null;

    public static MutableLiveData<LatLng> getLatestLocation(Context ctx) {
        System.out.println("getLatestLocation");
        FusedLocationProviderClient fpc = LocationServices.getFusedLocationProviderClient(ctx);
        MutableLiveData<LatLng> curLoc = new MutableLiveData<>();

        if (lr == null) {
            lr = LocationRequest.create();
            lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        }
        LocationCallback lc = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                System.out.println(locationResult);
                if (locationResult != null) {
                    for (Location loc : locationResult.getLocations()) {
                        if (loc != null) {
                            LatLng latlng = new LatLng(loc.getLatitude(), loc.getLongitude());
                            curLoc.postValue(latlng);
                        }
                    }
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            fpc.requestLocationUpdates(lr, lc, null);

        return curLoc;
    }
}
