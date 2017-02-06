package com.example.slafuente.imc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MiEstado extends AppCompatActivity {

    private Button botonEstado;
    private TextView tvEstado;
    private Button botonMenuPpal;
    private String nuevoEstado;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_estado);

        botonEstado = (Button) findViewById(R.id.botonEstado);
        botonMenuPpal = (Button) findViewById(R.id.buttonVolverMenu);
        tvEstado = (TextView)findViewById(R.id.idEstadoR);

        //sharedpreferences
        sp = getSharedPreferences("mispfs", Context.MODE_PRIVATE);

        botonEstado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCambiarEstado = new Intent(getApplicationContext(), CambiarEstado.class);
                String estadoActual = (String) tvEstado.getText();
                intentCambiarEstado.putExtra("ESTADO", estadoActual);
                startActivity(intentCambiarEstado);
            }
        });

        //Recupero el estado introducido por el usuario
        nuevoEstado = getIntent().getStringExtra("ESTADONUEVO");
        if (nuevoEstado != null) {
            tvEstado.setText(nuevoEstado);
        } else {
            //sinuevo estado es nulo -> venimos de la mainActivity,lo recuperamos de sharedpreferences
            String state = sp.getString("Estado", "");
            tvEstado.setText(state);
        }


        botonMenuPpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(getApplicationContext(), MainActivity.class);
                //Escribimos el estado en preferences para poder mostrarlo luego
                SharedPreferences.Editor ed = sp.edit();
                ed.putString("Estado" , nuevoEstado);
                ed.commit();
                startActivity(volver);
            }
        });
    }

}
