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

import java.util.ArrayList;

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
        TextView txtNombre = findViewById(R.id.lbl_perfil_nombre);
        TextView txtUbicacion = findViewById(R.id.lbl_perfil_ubicacion);

        TextView txtTelefono = findViewById(R.id.lbl_perfil_telefono);
        TextView txtDireccion = findViewById(R.id.lbl_perfil_direccion);
        TextView txtAbierto = findViewById(R.id.lbl_perfil_abierto);
        TextView txtTipo = findViewById(R.id.lbl_perfil_tipo);

        TextView txtAlojamientos = findViewById(R.id.lbl_perfil_alojamientos);
        TextView txtParcelas = findViewById(R.id.lbl_perfil_parcelas);
        TextView txtMascotas = findViewById(R.id.lbl_perfil_mascotas);

        txtNombre.setText(camping.getNombre());
        txtUbicacion.setText(camping.getCiudad() + ", " + camping.getProvincia());

        txtTelefono.setText(camping.getTelefono());
        txtDireccion.setText(camping.getDireccion());
        txtAbierto.setText(camping.getAbierto());
        txtTipo.setText(camping.getTipo());

        ArrayList<String> alojamientos = camping.getAlojamientos();
        String textoAloja = "";
        for(int i = 0; i < alojamientos.size(); i++){
            textoAloja += alojamientos.get(i);
            if(i < alojamientos.size() - 1)
                textoAloja += ", ";
        }
        txtAlojamientos.setText(textoAloja);

        txtParcelas.setText(String.valueOf(camping.getParcelas()));
        if(camping.isMascotas()) {
            txtMascotas.setText("Si");
        }else{
            txtMascotas.setText("No");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos = camping.getLatLng();
        mMap.addMarker(new MarkerOptions()
                .position(pos)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.tent_icon)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
    }

}
