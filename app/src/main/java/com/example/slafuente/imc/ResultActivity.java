package com.example.slafuente.imc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    //Array que asociaremos al adaptador
    String[] array = new String[] {
            "< 16 DESNUTRIDO",
            " => 16 < 18,5 BAJO PESO",
            "=> 18,5 < 25 NORMAL",
            ">=25 < 31 SOBREPESO",
            ">= 31 OBESIDAD"
    };
    ListAdapter adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Creación del adaptador, vamos a escoger el layout
        //simple_list_item_1, que los mostr
        adaptador = new ArrayAdapter<String>(this, R.layout.fila, array);

        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton4) ;

        TextView resultView = (TextView)findViewById(R.id.textResult2);

        Intent intent = getIntent();
        String result = intent.getStringExtra(MainActivity.EXTRA_RESULT_IMC);

        resultView.setText(result);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Asociamos el adaptador a la vista.
                ListView lv = (ListView) findViewById(R.id.listaPesos);
                lv.setAdapter(adaptador);
            }
        });

    }
}
