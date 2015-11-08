package com.example.beto.controldegastos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends Activity implements OnClickListener{
    Button registro,verGastos,borrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflador=getMenuInflater();
        inflador.inflate(R.menu.botones_main_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.agrega:
                Intent agrega = new Intent(this,RegistraGasto_activity.class);
                startActivity(agrega);
                break;
            case R.id.Ver:
                Intent Ver = new Intent(this,VerGasto_activity.class);
                startActivity(Ver);
                break;
            case R.id.borra:
                try {
                    Base_datos obj = new Base_datos(this);
                    obj.borrartabla();
                    total obj3 = new total();
                    obj3.setTotal(0);
                } catch (Exception e) {
                    Toast.makeText(this, "No se pudo borrar la tabla", Toast.LENGTH_SHORT).show();
                }
                break;


        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {



        }
    }
}
