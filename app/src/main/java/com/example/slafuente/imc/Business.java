package com.example.slafuente.imc;

import android.content.Context;
import android.util.Log;

/**
 * Created by slafuente on 02/01/2017.
 */

public class Business {

    public static String calculateIMC(float weight, float height) {
        height = (height / 100);
        //weight = (weight / 100);

        float result = weight / (height * height);
        String truncResult = String.valueOf(result).substring(0,5);

        String outputText;

        if (result < 16) {
            outputText = "Tu IMC es " + truncResult + "--> Estas desnutrido tete";
        } else if (result < 18.5) {
            outputText = "Tu IMC es " + truncResult + "--> Estas por debajo de tu peso";
        } else if (result < 25) {
            outputText = "Tu IMC es " + truncResult + "--> Estas normal maquina";
        } else if (result < 31.5) {
            outputText = "Tu IMC es " + truncResult + "--> Sobrepeso, ten cuidado";
        } else {
            outputText = "Tu IMC es " + truncResult + "--> OBESO, deja de comer ya";
        }
        return outputText;
    }

    public static boolean checkDetails(String name, String password) {

        /**
         * Bandera evidenciar algun error durante la validación de los datos
         * Variable para contener el campo a ser enfocado
         */
        boolean cancel = false;
        //View focusView = null;

        /**Comprobar si el password ingresado no es nulo y es valido*/
        if (password.equals(null) || "".equals(password)) {
            /**Envia el error a la caja de Texto*/
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
            cancel = true;
            return false;
        }

        /**Comprobar si el campo para el Email esta vacio. */
        if (name.equals(null) || "".equals(name)) {
            /**Envia el error a la caja de Texto*/
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
            cancel = true;
            return false;
        }

        /**Comprobar si hubo un fallo durante el ingreso de datos*/
        if (cancel) {
            /**Enfocar el Campo del Error*/
            //focusView.requestFocus();
            return false;
        } else {
            /**Cargar Animación con una barra de progreso*/
            //showProgress(true);
            /**Crea un nuevo Usuario a partir de la clase  mAuthTask*/
            Usuario user = new Usuario(name, password);
            /**Lanzar el Hilo para la Autenticación del Usuario*/
            //mAuthTask.execute((Void) null);
            return true;
        }
    }


}
