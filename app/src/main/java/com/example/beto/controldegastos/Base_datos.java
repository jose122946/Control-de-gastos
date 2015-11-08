package com.example.beto.controldegastos;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by beto on 17/07/15.
 */

public class Base_datos {
    double total;
    String concepto;
    double importe;
    String fecha;
    int id;
    Activity actividad;
    SQLiteDatabase db ;
    Cursor cursor;
    public Base_datos(Activity actividad) {
        this.actividad = actividad;total=0;
    }


    public void creaBase()
    {
        db = actividad.openOrCreateDatabase("controlgastos",0,null);
//Crea una tabla si no existe
        String creaTabla="create table if not exists " +"gastos (id INTEGER PRIMARY KEY AUTOINCREMENT,concepto text, importe double, fecha text);";
        db.execSQL(creaTabla);
        db.close();
    }

    public void consulta(ArrayList lista,String consultaSQL)
    {
        db = actividad.openOrCreateDatabase("controlgastos",0,null);
        cursor = db.rawQuery(consultaSQL, null);
        cursor.moveToFirst();
        total obj2 = new total();
        do{
            //for(int i=0; i<renglones; i++)
            //{
            id=cursor.getInt(0);
            concepto = cursor.getString(1);
            importe = cursor.getDouble(2);
            fecha = cursor.getString(3);
            total = total+importe;
            Lista obj1 = new Lista(concepto,fecha,importe,id);
            lista.add(obj1);
            try {

                obj2.setTotal(total);

            }catch (Exception e)
            {
                Toast.makeText(actividad, "Parece que hubo un error lo sentimos", Toast.LENGTH_SHORT).show();
            }
        }while(cursor.moveToNext());
        db.close();

    }
    public void insertar(String insertar)
    {

        db = actividad.openOrCreateDatabase("controlgastos", 0, null);
        db.execSQL(insertar);
        db.close();
    }
    public void borrartabla()
    {
        db = actividad.openOrCreateDatabase("controlgastos", 0, null);
        String borrar = "DROP TABLE gastos";
        db.execSQL(borrar);
        db.close();
    }
    public void obtenId(int obtenid)
    {
        db = actividad.openOrCreateDatabase("controlgastos", 0, null);
        String borrar = "DELETE FROM gastos WHERE id="+obtenid+";";
        db.execSQL(borrar);
        db.close();
    }

}
