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

    /**
     * Comprobamos que email y password no son vacios.
     * @param name
     * @param password
     * @return
     */
    public static boolean checkDetails(String name, String password) {

        /**Comprobar si password ingresado no es nulo */
        if (password.equals(null) || "".equals(password)) {
            return false;
        }

        /**Comprobar si el campo para el Email esta vacio. */
        else if (name.equals(null) || "".equals(name)) {
            return false;
        } else {
            /**HA IDO BIEN, Creamos un nuevo Usuario */
            Usuario user = new Usuario(name, password);
            return true;
        }

    }


}
