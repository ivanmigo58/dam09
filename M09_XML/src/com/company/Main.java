package com.company;

import javax.crypto.SecretKey;

public class Main {

    public static void main(String[] args) {
        ej1_4();
    }

    private static void ej1_4() {
        int sizeKey = 128;
        SecretKey secretKey = Xifrar.keygenKeyGeneration(sizeKey);
        // Encripto el texto pasado
        byte[] textoEncriptado = Xifrar.encryptData(secretKey,"IvanMiralles".getBytes());

        String texto = new String(textoEncriptado);
        System.out.println(texto);
    }
}
