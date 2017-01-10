package com.example.slafuente.imc;

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
}
