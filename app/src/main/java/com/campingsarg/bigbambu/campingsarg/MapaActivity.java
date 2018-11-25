package com.campingsarg.bigbambu.campingsarg;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.campingsarg.bigbambu.campingsarg.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng marker = new LatLng(0,0);
        for(int i = 0; i < MainActivity.campings.size(); i++) {
            Camping camping = MainActivity.campings.get(i);
            marker = camping.getLatLng();
            mMap.addMarker(new MarkerOptions().position(marker).title(camping.getNombre()));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker,14));
    }
}
