package com.campingsarg.bigbambu.campingsarg;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.campingsarg.bigbambu.campingsarg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DatabaseReference dbCampings;

    ImageView btnVerMapa;
    EditText txtBuscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbCampings = FirebaseDatabase.getInstance().getReference("campings");

        btnVerMapa = (ImageView)findViewById(R.id.btn_ver_mapa);

        txtBuscador = (EditText)findViewById(R.id.txt_buscador);
        txtBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String texto = txtBuscador.getText().toString();
                ArrayList<Camping> encontrados = CampingsManager.encontrarPorTexto(texto);

                ListView lstResultados = (ListView)findViewById(R.id.lst_resultados);
                if(encontrados.size() > 0) {
                    ItemBuscadorAdapter adapter = new ItemBuscadorAdapter(getBaseContext(), encontrados);
                    lstResultados.setAdapter(adapter);
                }else{
                    lstResultados.setAdapter(null);
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        dbCampings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(CampingsManager.campings.size() > 0){
                    CampingsManager.campings.clear();
                }

                int i = 0;
                for(DataSnapshot campingSnapshot : dataSnapshot.getChildren()){
                    Camping camping = campingSnapshot.getValue(Camping.class);
                    camping.setId(i);

                    CampingsManager.campings.add(camping);
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void addCamping(String name){
        Camping camp = new Camping();

        dbCampings.child("camping_3").setValue(camp);
    }

    public void verMapa(View v){
        Intent myIntent = new Intent(getBaseContext(), MapaActivity.class);
        MainActivity.this.startActivity(myIntent);

    }

    public void filtrar(View v){
        Intent myIntent = new Intent(getBaseContext(), FiltrarActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}
