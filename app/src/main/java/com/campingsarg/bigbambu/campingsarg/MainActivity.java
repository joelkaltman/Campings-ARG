package com.campingsarg.bigbambu.campingsarg;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.campingsarg.bigbambu.campingsarg.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DatabaseReference dbCampings;
    public static ArrayList<Camping> campings;

    Button btnVerMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbCampings = FirebaseDatabase.getInstance().getReference("campings");
        campings = new ArrayList<>();

        btnVerMapa = findViewById(R.id.btn_ver_mapa);
    }

    @Override
    protected void onStart(){
        super.onStart();

        dbCampings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot campingSnapshot : dataSnapshot.getChildren()){
                    Camping camping = campingSnapshot.getValue(Camping.class);

                    campings.add(camping);
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
        Intent myIntent = new Intent(MainActivity.this, MapaActivity.class);
        MainActivity.this.startActivity(myIntent);

    }
}
