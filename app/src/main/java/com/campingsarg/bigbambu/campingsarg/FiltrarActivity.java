package com.campingsarg.bigbambu.campingsarg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FiltrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar);


        ArrayList<String> ciudades = CampingsManager.obtenerTodos(CampingsManager.TipoLista.CIUDADES);
        ArrayList<String> provincias = CampingsManager.obtenerTodos(CampingsManager.TipoLista.PROVINCIAS);


        Spinner spnCiudades = findViewById(R.id.spn_filtrar_ciudad);
        ArrayAdapter<String> arrayAdapterCiudad = new ArrayAdapter<String>(FiltrarActivity.this,android.R.layout.simple_spinner_item, ciudades);
        arrayAdapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCiudades.setAdapter(arrayAdapterCiudad);

        Spinner spnProvincias = findViewById(R.id.spn_filtrar_provincia);
        ArrayAdapter<String> arrayAdapterProvincia = new ArrayAdapter<String>(FiltrarActivity.this,android.R.layout.simple_spinner_item, provincias);
        arrayAdapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvincias.setAdapter(arrayAdapterProvincia);


        ArrayList<String> tipos = CampingsManager.obtenerTodos(CampingsManager.TipoLista.TIPOS);

        Spinner spnTipo = findViewById(R.id.spn_filtrar_tipo);
        ArrayAdapter<String> arrayAdapterTipo = new ArrayAdapter<String>(FiltrarActivity.this,android.R.layout.simple_spinner_item, tipos);
        arrayAdapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(arrayAdapterTipo);

        CheckBox chkMascotas = findViewById(R.id.chk_filtrar_mascotas);

        TableLayout layoutAlojamientos  = (TableLayout)findViewById(R.id.table_filtrar_alojamientos);
        TableLayout layoutServicios  = (TableLayout)findViewById(R.id.table_filtrar_servicios);
        TableLayout layoutNaturaleza  = (TableLayout)findViewById(R.id.table_filtrar_naturaleza);
        TableLayout layoutRecreacion  = (TableLayout)findViewById(R.id.table_filtrar_actividades);

        ArrayList<String> alojamientos = CampingsManager.obtenerTodos(CampingsManager.TipoLista.ALOJAMIENTOS);
        ArrayList<String> servicios = CampingsManager.obtenerTodos(CampingsManager.TipoLista.SERVICIOS);
        ArrayList<String> naturaleza = CampingsManager.obtenerTodos(CampingsManager.TipoLista.NATURALEZA);
        ArrayList<String> recreacion = CampingsManager.obtenerTodos(CampingsManager.TipoLista.ACTIVIDADES);

        this.cargarTabla(alojamientos, layoutAlojamientos, 2);
        this.cargarTabla(servicios, layoutServicios, 2);
        this.cargarTabla(naturaleza, layoutNaturaleza, 2);
        this.cargarTabla(recreacion, layoutRecreacion, 2);
    }


    private void cargarTabla(ArrayList<String> lista, TableLayout tabla, int elem_por_fila){
        // Si la lista es nula dejo el texto de perfil vacio y saco la imagen
        if(lista == null){
            View vi = LayoutInflater.from(this).inflate(R.layout.item_filtrar_list, null);
            tabla.addView(vi);
            return;
        }
        // Sino cargo la tabla segun la cantidad de elementos por fila
        ArrayList<View> viewsRow = new ArrayList<>();
        for (int i=0; i < lista.size(); i++) {
            View vi = LayoutInflater.from(this).inflate(R.layout.item_filtrar_list, null);
            TextView textItem = (TextView)vi.findViewById(R.id.chk_item_filtrar);
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
}
