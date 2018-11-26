package com.campingsarg.bigbambu.campingsarg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class PerfilCampingActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private Camping camping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_camping);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_perfil);
        mapFragment.getMapAsync(this);

        int index = getIntent().getExtras().getInt("index");

        camping = MainActivity.campings.get(index);

        cargarInformacion();
    }

    private void cargarInformacion(){
        TextView nombre = findViewById(R.id.lbl_perfil_nombre);
        TextView ubicacion = findViewById(R.id.lbl_perfil_ubicacion);

        TextView telefono = findViewById(R.id.lbl_perfil_telefono);
        TextView direccion = findViewById(R.id.lbl_perfil_direccion);
        TextView abierto = findViewById(R.id.lbl_perfil_abierto);
        TextView tipo = findViewById(R.id.lbl_perfil_tipo);

        TextView tipos = findViewById(R.id.lbl_perfil_tipo);
        TextView parcelas = findViewById(R.id.lbl_perfil_parcelas);
        TextView mascotas = findViewById(R.id.lbl_perfil_mascotas);

        nombre.setText(camping.getNombre());
        ubicacion.setText(camping.getCiudad() + ", " + camping.getProvincia());

        telefono.setText(camping.getTelefono());
        direccion.setText(camping.getDireccion());
        abierto.setText(camping.getAbierto());
        tipo.setText(camping.getTipo());

        //tipos.setText();
        parcelas.setText(String.valueOf(camping.getParcelas()));
        if(camping.isMascotas()) {
            mascotas.setText("Si");
        }else{
            mascotas.setText("No");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos = camping.getLatLng();
        mMap.addMarker(new MarkerOptions()
                .position(pos)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tent_icon)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 12));
    }

}
