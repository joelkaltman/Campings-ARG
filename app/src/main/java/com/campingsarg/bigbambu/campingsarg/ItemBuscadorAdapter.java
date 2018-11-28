package com.campingsarg.bigbambu.campingsarg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemBuscadorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Camping> campings;

    public ItemBuscadorAdapter(Context context, ArrayList<Camping> campings){
        this.context = context;
        this.campings = campings;
    }

    @Override
    public int getCount(){
        return this.campings.size();
    }

    @Override
    public Object getItem(int position){
        return this.campings.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(R.layout.item_buscador_list, null);

        TextView txtNombre = (TextView) convertView.findViewById(R.id.text_item_buscador_nombre);
        TextView txtUbicacion = (TextView) convertView.findViewById(R.id.text_item_buscador_ubicacion);

        final Camping actual = campings.get(position);
        txtNombre.setText(actual.getNombre());
        txtUbicacion.setText(actual.getUbicacion());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PerfilCampingActivity.class);
                intent.putExtra("id", actual.getId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}

