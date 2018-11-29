package com.campingsarg.bigbambu.campingsarg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FiltrarActivity extends AppCompatActivity {

    public static ArrayList<Camping> resultadosFiltro;

    Spinner spnCiudades;
    Spinner spnProvincias;
    RadioButton rdbMascotasSi;
    RadioButton rdbMascotasNo;
    TableLayout layoutAlojamientos;
    TableLayout layoutServicios;
    TableLayout layoutNaturaleza;
    TableLayout layoutRecreacion;
    ArrayList<CheckBox> chkAlojamientos;
    ArrayList<CheckBox> chkServicios;
    ArrayList<CheckBox> chkNaturaleza;
    ArrayList<CheckBox> chkActividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar);

        resultadosFiltro = new ArrayList<>();

        chkAlojamientos = new ArrayList<>();
        chkServicios = new ArrayList<>();
        chkNaturaleza = new ArrayList<>();
        chkActividades = new ArrayList<>();

        ArrayList<String> ciudades = CampingsManager.obtenerTodos(CampingsManager.TipoLista.CIUDADES);
        ciudades.add(0, "(no especifica)");
        ArrayList<String> provincias = CampingsManager.obtenerTodos(CampingsManager.TipoLista.PROVINCIAS);
        provincias.add(0, "(no especifica)");

        spnCiudades = findViewById(R.id.spn_filtrar_ciudad);
        ArrayAdapter<String> arrayAdapterCiudad = new ArrayAdapter<String>(FiltrarActivity.this,android.R.layout.simple_spinner_item, ciudades);
        arrayAdapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCiudades.setAdapter(arrayAdapterCiudad);

        spnProvincias = findViewById(R.id.spn_filtrar_provincia);
        ArrayAdapter<String> arrayAdapterProvincia = new ArrayAdapter<String>(FiltrarActivity.this,android.R.layout.simple_spinner_item, provincias);
        arrayAdapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProvincias.setAdapter(arrayAdapterProvincia);

        rdbMascotasSi = findViewById(R.id.rdb_filtrar_mascotas_si);
        rdbMascotasNo = findViewById(R.id.rdb_filtrar_mascotas_no);

        layoutAlojamientos  = (TableLayout)findViewById(R.id.table_filtrar_alojamientos);
        layoutServicios  = (TableLayout)findViewById(R.id.table_filtrar_servicios);
        layoutNaturaleza  = (TableLayout)findViewById(R.id.table_filtrar_naturaleza);
        layoutRecreacion  = (TableLayout)findViewById(R.id.table_filtrar_actividades);

        ArrayList<String> alojamientos = CampingsManager.obtenerTodos(CampingsManager.TipoLista.ALOJAMIENTOS);
        ArrayList<String> servicios = CampingsManager.obtenerTodos(CampingsManager.TipoLista.SERVICIOS);
        ArrayList<String> naturaleza = CampingsManager.obtenerTodos(CampingsManager.TipoLista.NATURALEZA);
        ArrayList<String> recreacion = CampingsManager.obtenerTodos(CampingsManager.TipoLista.ACTIVIDADES);

        this.cargarTabla(alojamientos, layoutAlojamientos, 2, chkAlojamientos);
        this.cargarTabla(servicios, layoutServicios, 2, chkServicios);
        this.cargarTabla(naturaleza, layoutNaturaleza, 2, chkNaturaleza);
        this.cargarTabla(recreacion, layoutRecreacion, 2, chkActividades);
    }


    private void cargarTabla(ArrayList<String> lista, TableLayout tabla, int elem_por_fila, ArrayList<CheckBox> checkBoxes){
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
            CheckBox checkItem = (CheckBox)vi.findViewById(R.id.chk_item_filtrar);
            checkItem.setText(lista.get(i));
            viewsRow.add(vi);

            checkBoxes.add(checkItem);

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

    public void buscar(View view){
        String ciudad = spnCiudades.getSelectedItem().toString();
        String provincia = spnProvincias.getSelectedItem().toString();

        if(spnCiudades.getSelectedItemPosition() == 0){
            ciudad = null;
        }
        if(spnProvincias.getSelectedItemPosition() == 0){
            provincia = null;
        }

        Boolean mascotasSi = rdbMascotasSi.isChecked();
        Boolean mascotasNo = rdbMascotasNo.isChecked();

        ArrayList<String> alojamientos = new ArrayList<>();
        ArrayList<String> servicios = new ArrayList<>();
        ArrayList<String> naturaleza = new ArrayList<>();
        ArrayList<String> actividades = new ArrayList<>();

        for(int i = 0; i < chkAlojamientos.size(); i++){
            if(chkAlojamientos.get(i).isChecked()){
                alojamientos.add(chkAlojamientos.get(i).getText().toString());
            }
        }
        for(int i = 0; i < chkServicios.size(); i++){
            if(chkServicios.get(i).isChecked()){
                servicios.add(chkServicios.get(i).getText().toString());
            }
        }
        for(int i = 0; i < chkNaturaleza.size(); i++){
            if(chkNaturaleza.get(i).isChecked()){
                naturaleza.add(chkNaturaleza.get(i).getText().toString());
            }
        }
        for(int i = 0; i < chkActividades.size(); i++){
            if(chkActividades.get(i).isChecked()){
                actividades.add(chkActividades.get(i).getText().toString());
            }
        }

        this.resultadosFiltro = CampingsManager.encontrarPorFiltros(ciudad, provincia, null, mascotasSi, mascotasNo, alojamientos, servicios, actividades, naturaleza);

        Intent myIntent = new Intent(getBaseContext(), ListaFiltrados.class);
        this.startActivity(myIntent);
    }
}
