package com.campingsarg.bigbambu.campingsarg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaFiltrados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filtrados);

        ListView lstResultados = (ListView)findViewById(R.id.lst_lista_filtrados);
        if(FiltrarActivity.resultadosFiltro.size() > 0) {
            ItemBuscadorAdapter adapter = new ItemBuscadorAdapter(getBaseContext(), FiltrarActivity.resultadosFiltro);
            lstResultados.setAdapter(adapter);
        }else{
            lstResultados.setAdapter(null);
        }
    }
}
