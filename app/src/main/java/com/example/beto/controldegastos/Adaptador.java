package com.example.beto.controldegastos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by beto on 17/07/15.
 */

public class Adaptador extends BaseAdapter{
Activity actividad;
ArrayList<Lista>lista = new ArrayList<Lista>();
    public Adaptador(Activity actividad) {
        this.actividad = actividad;
        Base_datos b = new Base_datos(actividad);
        b.creaBase();
        String consulta = "select * from gastos";
        b.consulta(lista, consulta);

    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflador = actividad.getLayoutInflater();
        View vista = inflador.inflate(R.layout.vistalista, null, true);
        TextView fecha =(TextView)vista.findViewById(R.id.fecha);
        TextView importe =(TextView)vista.findViewById(R.id.importe);
        TextView concepto =(TextView)vista.findViewById(R.id.concepto);
        Lista obj = lista.get(position);
        DecimalFormat df = new DecimalFormat("0.00");
        fecha.setText(String.valueOf(obj.getFecha()));
        importe.setText("$"+String.valueOf(df.format(obj.getImporte())));
        concepto.setText(String.valueOf(obj.getConcepto()));

        return vista;
    }
    public ArrayList<Lista> getLista() {
        return lista;
    }
}
