package com.example.slafuente.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_RESULT_IMC = "com.example.slafuente.RESULTIMC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_calculate = (Button)findViewById(R.id.buttonCalculate);
        final EditText editHeight = (EditText)findViewById(R.id.editHeight);
        final EditText editWeight = (EditText)findViewById(R.id.editWeight);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = editHeight.getText().toString();
                String weight = editWeight.getText().toString();

                if (height.length() > 0 && weight.length() > 0) {
                    float h = Float.parseFloat(height);
                    float w = Float.parseFloat(weight);

                    String result = Business.calculateIMC(w, h);

                    Intent irAResult = new Intent(getApplicationContext(), ResultActivity.class);
                    irAResult.putExtra(EXTRA_RESULT_IMC, result);
                    startActivity(irAResult);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case  R.id.idFoto :
                Intent intentFoto = new Intent(getApplicationContext(), PantallaFoto.class);
                startActivity(intentFoto);
                break;
            case R.id.idEstado :
                Intent intentEstado = new Intent(getApplicationContext(), MiEstado.class);
                startActivity(intentEstado);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
