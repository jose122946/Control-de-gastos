package com.example.beto.controldegastos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class VerGasto_activity extends ListActivity {




    static int idbase;
    TextView textTotal;
   static boolean modificar=false;
    ArrayList<Lista>lista = new ArrayList<Lista>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_gasto_activity);
        try {


            setListAdapter(new Adaptador(this));
        }catch (Exception e)
        {
            Toast.makeText(this,"No hay gastos",Toast.LENGTH_SHORT).show();

        }
            textTotal = (TextView)findViewById(R.id.totalGastos);
        total obj3 = new total();
        DecimalFormat df = new DecimalFormat("0.00");
        textTotal.setText("$"+df.format(obj3.getTotal()));
    }

    public int getIdbase() {
        return idbase;
    }

    public void setIdbase(int idbase) {
        this.idbase = idbase;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Adaptador list = new Adaptador(this);
        lista = list.getLista();
        Lista obj = lista.get(position);
         idbase = obj.getId();
        final Base_datos base = new Base_datos(this);
        final CharSequence[] items = {"Borrar","Modificar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Que desea hacer?");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                switch (item) {
                    case 0:
                        try {
                            base.obtenId(idbase);
                            total obj3 = new total();
                            obj3.setTotal(0);
                            setListAdapter(new Adaptador(VerGasto_activity.this));
                            Toast toast = Toast.makeText(getApplicationContext(), " Se ha borrado con exito! ", Toast.LENGTH_SHORT);
                            toast.show();

                            DecimalFormat df = new DecimalFormat("0.00");
                            textTotal.setText("$" + df.format(obj3.getTotal()));

                        } catch (Exception e) {

                        }
                        break;
                    case 1:
                        modificar = true;
                        Intent modificar = new Intent(VerGasto_activity.this, RegistraGasto_activity.class);
                        startActivity(modificar);

                }
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
       // base.obtenId(idbase);
        try {
            setListAdapter(new Adaptador(this));
        }catch (Exception e)
        {
            Toast.makeText(this,"No hay Gastos",Toast.LENGTH_LONG).show();
        }
            //Toast.makeText(this,String.valueOf(idbase)+" Posicion: "+String.valueOf(position),Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ver_gasto_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.agrega2:
                Intent agrega = new Intent(this,RegistraGasto_activity.class);
                startActivity(agrega);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setListAdapter(new Adaptador(this));
        total obj3 = new total();
        DecimalFormat df = new DecimalFormat("0.00");
        textTotal.setText("$" + df.format(obj3.getTotal()));
    }
}
