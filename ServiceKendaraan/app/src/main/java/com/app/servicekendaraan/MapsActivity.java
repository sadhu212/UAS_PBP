package com.app.servicekendaraan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.servicekendaraan.utils.MapsHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.app.servicekendaraan.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String name;
    private String dataAddress, dataLatitude, dataLongitude, dataCountryName, dataCountryCode, dataAdminArea, dataPostalCode, dataSubAdminArea, dataLocality, dataSubThoroughfare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // view
        initView();

        // map
        loadMap();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initView() {
        binding.buttonSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataAddress != null && dataLatitude != null && dataLongitude != null) {
                    Intent intent = new Intent();
                    if (getIntent().hasExtra("type")) {
                        intent.putExtra("type", getIntent().getStringExtra("type"));
                    }
                    intent.putExtra("dataAddress", dataAddress);
                    intent.putExtra("dataLatitude", dataLatitude);
                    intent.putExtra("dataLongitude", dataLongitude);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(MapsActivity.this, "Lokasi belum dipilih!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapsHelper mapsHelper = new MapsHelper(this);
        if (mapsHelper.canGetLocation()) {
            double latitude = mapsHelper.getLatitude();
            double longitude = mapsHelper.getLongitude();
            setCurrentLocation(MapsActivity.this, latitude, longitude);
            LatLng latLng = new LatLng(latitude, longitude);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
                googleMap.setTrafficEnabled(true);
                googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        googleMap.clear();
                        googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        onSelectLocation(MapsActivity.this, latLng.latitude, latLng.longitude);
                    }
                });
            }
        }
    }

    public void setCurrentLocation(Activity activity, double lat, double lng) {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
            if (addressList!=null && addressList.size() > 0) {
                Address address = addressList.get(0);
                dataLatitude = String.valueOf(lat);
                dataLongitude = String.valueOf(lng);
                dataAddress = address.getAddressLine(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onSelectLocation(Activity activity, double lat, double lng) {
        Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(lat, lng, 1);
            if (addressList!=null && addressList.size() > 0) {
                Address address = addressList.get(0);
                dataLatitude = String.valueOf(lat);
                dataLongitude = String.valueOf(lng);
                dataAddress = address.getAddressLine(0);
                dataCountryName = address.getCountryName();
                dataCountryCode = address.getCountryCode();
                dataAdminArea = address.getAdminArea();
                dataPostalCode = address.getPostalCode();
                dataSubAdminArea = address.getSubAdminArea();
                dataLocality = address.getLocality();
                dataSubThoroughfare = address.getSubThoroughfare();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }


}