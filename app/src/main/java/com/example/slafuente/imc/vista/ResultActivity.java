package com.example.slafuente.imc.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.slafuente.imc.negocio.AdapterPropio;
import com.example.slafuente.imc.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView textView = null;


    //Array que asociaremos al adaptador
    ArrayList<String> alist = new ArrayList<String>();
    String[] array = new String[] {
            "< 16 DESNUTRIDO",
            " => 16 < 18,5 BAJO PESO",
            "=> 18,5 < 25 NORMAL",
            ">=25 < 31 SOBREPESO",
            ">= 31 OBESIDAD"
    };
    //ListAdapter adaptador = null;*/
    AdapterPropio ap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        for (int i = 0; i< array.length; i++) {
            alist.add(i,array[i]);
        }

        //Creación del adaptador, vamos a escoger el layout
        //adaptador = new ArrayAdapter<String>(this, R.layout.fila, array);
        textView = (TextView)findViewById(R.id.textviewFila) ;

        ap = new AdapterPropio(this, alist);

        Button button = (Button)findViewById(R.id.botonMostrar);

        TextView resultView = (TextView)findViewById(R.id.textResult2);

        Intent intent = getIntent();
        String result = intent.getStringExtra(MainActivity.EXTRA_RESULT_IMC);

        resultView.setText(result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Asociamos el adaptador a la vista.
                ListView lv = (ListView) findViewById(R.id.listaPesos);
                lv.setAdapter(ap);
            }
        });

    }
}
