package com.example.beto.controldegastos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class RegistraGasto_activity extends Activity implements View.OnClickListener {
Button ok,borrar;
String fecha;
double importe;
    String concepto;
    DatePicker datedfecha;
    EditText concept,impor;
    static boolean modificar=false;
    ArrayList<Lista> lista= new ArrayList<Lista>();
    Base_datos obj = new Base_datos(this);
    static int idbase;
    static boolean bandera=false;
    SQLiteDatabase db;
    Cursor cursor;
    VerGasto_activity obj1 = new VerGasto_activity();
    public RegistraGasto_activity() {


        modificar = obj1.isModificar();
        idbase = obj1.getIdbase();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_gasto_activity);
        ok = (Button)findViewById(R.id.btnOkregistrar);
        datedfecha = (DatePicker)findViewById(R.id.datePicker);
        concept = (EditText)findViewById(R.id.ingresaConcepto);
        impor = (EditText)findViewById(R.id.IngresaImporte);
        ok.setOnClickListener(this);
       // borrar =(Button)findViewById(R.id.borrar);



        if(modificar)
        {

            String SQL="SELECT * FROM  gastos WHERE id="+idbase+";";
            db = this.openOrCreateDatabase("controlgastos", 0, null);
            cursor = db.rawQuery(SQL,null);
            cursor.moveToFirst();


            try {


                    //for(int i=0; i<renglones; i++)
                    //{
                      do {
                          concept.setText(cursor.getString(1));

                          impor.setText(String.valueOf(cursor.getDouble(2)));
                          String datetime = cursor.getString(3);
                          String[] resultado = datetime.split("/+");
                          int dia = Integer.parseInt(resultado[0]);
                          int mes = Integer.parseInt(resultado[1]);
                          int ano = Integer.parseInt(resultado[2]);
                          datedfecha.init(ano,mes,dia,null);
                      }while(cursor.moveToNext());
                        db.close();
                obj1.setModificar(false);
                    }catch (Exception e)
                    {
                        Toast.makeText(this, "No se pudo obtener los datos", Toast.LENGTH_SHORT).show();
                        obj1.setModificar(false);

                    }
            ok.setText("Modificar");
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            obj1.setModificar(false);
            finish();
          return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registra_gasto_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId())
        {
            case R.id.Ver2:
                Intent Ver = new Intent(this,VerGasto_activity.class);
                startActivity(Ver);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == ok.getId()) {
            if (modificar) {
                try {
                    obj.creaBase();
                    int dia, mes, año;
                    dia = datedfecha.getDayOfMonth();
                    mes = datedfecha.getMonth();
                    año = datedfecha.getYear();
                    fecha = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(año);


                    concepto = concept.getText().toString();
                    importe = Double.parseDouble(impor.getText().toString());
                    String consultados = "UPDATE gastos SET concepto='" + concepto + "',importe=" + importe + ",fecha='" + fecha + "' WHERE id=" + idbase + ";";
                    obj.insertar(consultados);
                    obj1.setModificar(false);
                    Toast.makeText(this,"Se han actualizado los datos con exito!",Toast.LENGTH_LONG).show();
                    finish();
                }catch (Exception e)
                {
                    Toast.makeText(this,"No se pudo actualizar los datos",Toast.LENGTH_SHORT).show();
                    obj1.setModificar(false);
                }
            } else {
                try {

                    obj.creaBase();
                    int dia, mes, año;
                    dia = datedfecha.getDayOfMonth();
                    mes = datedfecha.getMonth();
                    año = datedfecha.getYear();
                    fecha = String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(año);


                    concepto = concept.getText().toString();
                    importe = Double.parseDouble(impor.getText().toString());

                    String consulta = "INSERT INTO gastos(concepto, importe, fecha) VALUES('" + concepto + "'," + importe + ",'" + fecha + "')";

                    obj.insertar(consulta);
                    concept.setText("");
                    impor.setText("");
                    Toast.makeText(this, "Se han agregado los datos nuevos " + concepto, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
