package com.campingsarg.bigbambu.campingsarg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        int campingId = getIntent().getExtras().getInt("id");

        camping = CampingsManager.encontrarPorId(campingId);

        cargarInformacion();
    }

    private void cargarInformacion(){
        TextView txtNombre = findViewById(R.id.lbl_perfil_nombre);
        TextView txtUbicacion = findViewById(R.id.lbl_perfil_ubicacion);

        TextView txtTelefono = findViewById(R.id.lbl_perfil_telefono);
        TextView txtDireccion = findViewById(R.id.lbl_perfil_direccion);
        TextView txtWeb = findViewById(R.id.lbl_perfil_web);

        TextView txtAbierto = findViewById(R.id.lbl_perfil_abierto);
        TextView txtTipo = findViewById(R.id.lbl_perfil_tipo);
        TextView txtParcelas = findViewById(R.id.lbl_perfil_parcelas);
        TextView txtMascotas = findViewById(R.id.lbl_perfil_mascotas);

        TableLayout layoutAlojamientos  = (TableLayout)findViewById(R.id.table_perfil_alojamientos);
        TableLayout layoutServicios  = (TableLayout)findViewById(R.id.table_perfil_servicios);
        TableLayout layoutNaturaleza  = (TableLayout)findViewById(R.id.table_perfil_naturaleza);
        TableLayout layoutRecreacion  = (TableLayout)findViewById(R.id.table_perfil_actividades);

        txtNombre.setText(camping.getNombre());
        txtUbicacion.setText(camping.getUbicacion());

        txtTelefono.setText(camping.getTelefono());
        txtDireccion.setText(camping.getDireccion());
        txtWeb.setText(camping.getWeb());

        txtAbierto.setText(camping.getAbierto());
        txtTipo.setText(camping.getTipo());
        Integer parcelas = camping.getParcelas();
        if(parcelas != null)
            txtParcelas.setText(String.valueOf(parcelas));
        Boolean mascotas = camping.isMascotas();
        if(mascotas != null) {
            if (mascotas) {
                txtMascotas.setText("Si");
            } else {
                txtMascotas.setText("No");
            }
        }

        ArrayList<String> alojamientos = camping.getAlojamientos();
        ArrayList<String> servicios = camping.getServicios();
        ArrayList<String> naturaleza = camping.getNaturaleza();
        ArrayList<String> recreacion = camping.getActividades();

        this.cargarTabla(alojamientos, layoutAlojamientos, 2);
        this.cargarTabla(servicios, layoutServicios, 2);
        this.cargarTabla(naturaleza, layoutNaturaleza, 2);
        this.cargarTabla(recreacion, layoutRecreacion, 2);
    }

    private void cargarTabla(ArrayList<String> lista, TableLayout tabla, int elem_por_fila){
        // Si la lista es nula dejo el texto de perfil vacio y saco la imagen
        if(lista == null){
            View vi = LayoutInflater.from(this).inflate(R.layout.item_profile_list, null);
            TextView textItem = (TextView)vi.findViewById(R.id.text_item);
            ImageView imageItem = (ImageView)vi.findViewById(R.id.img_item);
            textItem.setText(R.string.perfil_vacio);
            ((ViewManager)imageItem.getParent()).removeView(imageItem);
            tabla.addView(vi);
            return;
        }
        // Sino cargo la tabla segun la cantidad de elementos por fila
        ArrayList<View> viewsRow = new ArrayList<>();
        for (int i=0; i < lista.size(); i++) {
            View vi = LayoutInflater.from(this).inflate(R.layout.item_profile_list, null);
            TextView textItem = (TextView)vi.findViewById(R.id.text_item);
            ImageView imageItem = (ImageView)vi.findViewById(R.id.img_item);
            textItem.setText(lista.get(i));
            viewsRow.add(vi);

            if(viewsRow.size() == elem_por_fila || (i == lista.size() - 1)) {
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
                for(View view : viewsRow){
                    row.addView(view);
                }
                viewsRow.clear();
                tabla.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
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
