package com.example.slafuente.imc.vista;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.slafuente.imc.R;
import com.example.slafuente.imc.vista.PantallaFoto;

public class SeleccionarFotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_foto);

        Intent intentpidefoto = new Intent();
        intentpidefoto.setAction(Intent.ACTION_PICK);
        intentpidefoto.setType("image/*");//TIPO MIME

        startActivityForResult(intentpidefoto, 30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            //El usuario ha seleccionado una foto
            Uri uri = data.getData();

            Intent selectFoto = new Intent(getApplicationContext(), PantallaFoto.class);
            selectFoto.putExtra("imageUri", uri.toString());
            startActivity(selectFoto);


        }  else {
            Log.e("ERROR" , "El usuario le dio a salir ");
        }
    }
}
