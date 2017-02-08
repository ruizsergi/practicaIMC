package com.example.slafuente.imc.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.slafuente.imc.negocio.ListenerEstado;
import com.example.slafuente.imc.R;

public class CambiarEstado extends AppCompatActivity {

    private EditText editEstado;
    private Button botonAceptar;
    private Button botonCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_estado);

        //edit text de estado
        editEstado = (EditText) findViewById(R.id.editEstado);

        //Recupero el estado que tengo en este momento
        String estadoAntiguo = getIntent().getStringExtra("ESTADO");
        editEstado.setText(estadoAntiguo);

        //Botones aceptar y cancelar
        botonAceptar = (Button)findViewById(R.id.botonAceptaEstado);
        botonCancelar = (Button)findViewById(R.id.botonCancelaEstado);

        //Creamos el listener de estado y lo asociamos a los botones
        ListenerEstado listenerEstado = new ListenerEstado(this, estadoAntiguo);
        botonAceptar.setOnClickListener(listenerEstado);
        botonCancelar.setOnClickListener(listenerEstado);
    }
}
