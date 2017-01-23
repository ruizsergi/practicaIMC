package com.example.slafuente.imc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MiLoginActivity extends AppCompatActivity {

    private final String mibd = "miBaseDatos";
    private final String NAME_MEMBER = "name";
    private final String NUM_INTENTOS = "nintentos";
    private SharedPreferences sharedPreferences;
    private Intent goToIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_login);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        final TextView textEmail = (AutoCompleteTextView) findViewById(R.id.email);
        final EditText editPassword = (EditText) findViewById(R.id.password);
        final Button buttonSignIn = (Button) findViewById(R.id.email_sign_in_button);

        //Recuperamos de sharedPreferences el nombre del usuario
        String nombreUsuario = sharedPreferences.getString(NAME_MEMBER, "");
        Log.d(getClass().getCanonicalName(), "valor de SP- name " + nombreUsuario);
//        if (!"".equals(nombreUsuario)) {
//            //si se ha logueado antes va directo a IMC
//            goToIMC = new Intent(this, MainActivity.class);
//            startActivity(goToIMC);
//        }

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Obtiene y guarda los valores de email y  password*/
                String name = textEmail.getText().toString();
                String password = editPassword.getText().toString();

                if (Business.checkDetails(name, password)) {
                    addNombreToSharedPreferences(name);
                    accesoBBDD(name, password);
                }
            }
        });
    }

    /**
     * Anhadir nombre usuario a preferences.
     * @param nombreUsuario
     */
     public void addNombreToSharedPreferences(String nombreUsuario) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = sharedPreferences.edit();
        editor.putString(NAME_MEMBER, nombreUsuario);
        editor.commit();
     }

    /**
     * Anhadir numero de intentos usuario a preferences.
     * @param intentos numero de veces que ha intentado entrar
     */
    public void addIntentosToSharedPreferences(int intentos) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = sharedPreferences.edit();
        editor.putInt(NUM_INTENTOS, intentos);
        editor.apply();
    }

    /**
     * Anhadir numero de intentos usuario a preferences.
     * @param intentos numero de veces que ha intentado entrar
     */
    public void resetIntentos(int intentos) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = sharedPreferences.edit();
        editor.remove(NUM_INTENTOS);
        editor.commit();
    }
    /**
     * Acceso a la base de datos y operaciones sobre ella
     * @param email
     * @param password
     */
     public void accesoBBDD(String email, String password) {
        BaseDatosRegistro baseDatosRegistro = new BaseDatosRegistro(this,mibd,null,1);
        Usuario u = null;
        boolean okToProceed = false;

        //Comprobamos si el email esta en bbdd
        boolean existeUsuario = baseDatosRegistro.existeUsuario(email);

        //Si usuario esta en bbdd, comprobamos que la contrasena sea correcta
        if (existeUsuario) {
            u = baseDatosRegistro.buscarUsuario(email, password);
            if (u != null) {
                Log.i(getClass().getCanonicalName(), "user is in bbdd" + email);
                Toast toast = Toast.makeText(this, "user is in bbdd " + email, Toast.LENGTH_SHORT);
                toast.show();
                okToProceed = true;
            } else{
                Toast toast = Toast.makeText(this, "Password incorrecta " + email, Toast.LENGTH_LONG);
                toast.show();
                int intentos = sharedPreferences.getInt(NUM_INTENTOS, 0);
                Log.d(getClass().getCanonicalName(), "intentos " + intentos);
                intentos = intentos + 1;
                Toast toastIntentos = Toast.makeText(this, "intentos " + intentos, Toast.LENGTH_SHORT);
                toastIntentos.show();
                addIntentosToSharedPreferences(intentos);
                //Si ha intentado entrar 3 veces, salimos.
                if (intentos > 2) {
                    Log.d(getClass().getCanonicalName(), "saliendo con intentos " + intentos);
                    //reseteamos el valor de intentos
                    intentos = 0;
                    addIntentosToSharedPreferences(intentos);
                    Toast toastx = Toast.makeText(this, "reseteando  intentos " + intentos, Toast.LENGTH_SHORT);
                    toastx.show();
                    finish();
                }
            }
        } else {
            //Usuario no encontrado. Lo creamos usuario en bbdd
            Toast toast = Toast.makeText(this, "user not found", Toast.LENGTH_SHORT);
            toast.show();
            u = new Usuario(email, password);
            baseDatosRegistro.insertarUsuario(u);
            Log.i(getClass().getCanonicalName(), "creating user " + email);
            Toast toast2 = Toast.makeText(this, "creating user " + email, Toast.LENGTH_SHORT);
            toast2.show();
            okToProceed = true;
        }

        //si no hay fallos vamos a IMC
        if (okToProceed) {
            goToIMC = new Intent(this, MainActivity.class);
            startActivity(goToIMC);
        }

     }

}
