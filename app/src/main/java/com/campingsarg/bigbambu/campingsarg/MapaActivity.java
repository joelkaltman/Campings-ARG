package com.campingsarg.bigbambu.campingsarg;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.campingsarg.bigbambu.campingsarg.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<Marker> markers;
    private Map<String, Integer> mapMarkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        mapMarkers = new HashMap<String, Integer>();
        markers = new ArrayList<>();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng pos = new LatLng(0,0);
        for(int i = 0; i < CampingsManager.campings.size(); i++) {
            Camping camping = CampingsManager.campings.get(i);
            pos = camping.getLatLng();
            Marker newMarker = mMap.addMarker(new MarkerOptions()
                    .position(pos)
                    .title(camping.getNombre())
                    .snippet(camping.getDireccion())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.tent_icon)));
            this.markers.add(newMarker);
            this.mapMarkers.put(newMarker.getId(), camping.getId());
        }

        if(CampingsManager.campings.size() > 0) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(0).getPosition(), 12));
        }


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getBaseContext(), PerfilCampingActivity.class);

                Integer campingId = mapMarkers.get(marker.getId());
                if(campingId != null) {
                    intent.putExtra("id", campingId);
                    startActivity(intent);
                }else{
                    Toast.makeText(MapaActivity.this, "Camping no encontrado", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
