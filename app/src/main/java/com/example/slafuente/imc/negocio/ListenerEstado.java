package com.example.slafuente.imc.negocio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.slafuente.imc.R;
import com.example.slafuente.imc.vista.MiEstado;


/**
 * Created by slafuente on 06/02/2017.
 */

public class ListenerEstado implements View.OnClickListener {

    private Context context;
    private EditText editEstado;
    private String estado;

    public ListenerEstado (Context context, String estadoAntiguo) {
        this.context = context;
        this.estado = estadoAntiguo;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        int botonSeleccionado = v.getId();

        //Recupero la actividad mediante el contexto
        Activity a = (Activity)context;

        //Declaro un intent
        Intent vuelta = new Intent(a.getApplicationContext(), MiEstado.class);

        switch (botonSeleccionado) {
            case R.id.botonAceptaEstado :
                //actualizo el estado
                editEstado = (EditText) a.findViewById(R.id.editEstado);
                estado = editEstado.getText().toString();
                break;
            case R.id.botonCancelaEstado:
                break;
        }
        //anadimos el estado al intent y volvemos
        vuelta.putExtra("ESTADONUEVO", estado);
        a.startActivity(vuelta);
    }
}
