package com.example.slafuente.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaFoto extends AppCompatActivity {

    private Button botonHacerFoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_foto);

        botonHacerFoto = (Button) findViewById(R.id.buttonFoto);

        botonHacerFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hacerFoto = new Intent(getApplicationContext(), HacerFoto.class);
                startActivity(hacerFoto);

            }
        });
    }
}
