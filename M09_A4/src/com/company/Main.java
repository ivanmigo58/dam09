package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        ej1_5();
        ej1_6();
        ej1_8();
        ej2();

    }

    private static void ej1_5() {
        int sizeKey = 128;
        SecretKey secretKey = Xifrar.keygenKeyGeneration(sizeKey);
        String texto = "Texto de prueba";
        // Encripto el texto pasado
        byte[] textoEncriptado = Xifrar.encryptData(secretKey,texto.getBytes());

        // Desencripto texto
        byte[] textoDesencriptado = Xifrar.decryptData(secretKey, textoEncriptado);

        System.out.println("Texto encriptado: " + new String(textoEncriptado));
        System.out.println("Texto desencriptado: " + new String(textoDesencriptado));
        probarMetodo(secretKey);
    }

    private static void probarMetodo(SecretKey secretKey) {
        System.out.println("---- Secret Key data ----");
        System.out.println("Algoritmo: " + secretKey.getAlgorithm());
        System.out.println("Formato: " + secretKey.getFormat());
        System.out.println("Hash code: " + secretKey.hashCode());
        System.out.println("----------------");
    }

    private static void ej1_6() {
        int sizeKey = 128;
        String textKey = "Abrete";
        SecretKey secretKey = Xifrar.passwordKeyGeneration(textKey, sizeKey);
        boolean secretKeyFalsa = false;
        String texto = "Texto encriptado";
        // Encripto el texto
        byte[] textoEncriptado = Xifrar.encryptData(secretKey,texto.getBytes());
        // Desencripto el texto
        byte[] textoDesencriptado = Xifrar.decryptData(secretKey,textoEncriptado);
        System.out.println("Texto encriptado: " + new String(textoEncriptado));
        System.out.println("Texto desencriptado: " + new String(textoDesencriptado));
        probarMetodo(secretKey);
    }

    private static void ej1_8() {
        int sizeKey = 128;
        String textKey = "Abrete";
        SecretKey secretKey = Xifrar.passwordKeyGeneration(textKey, sizeKey);
        String texto = "Texto encriptado";
        // Encripto el texto
        byte[] textoEncriptado = Xifrar.encryptData(secretKey,texto.getBytes());
        // Cambiamos la SecretKey para que nos de error
        secretKey = Xifrar.keygenKeyGeneration(sizeKey);
        // Desencripto el texto
        byte[] textoDesencriptado = Xifrar.decryptData(secretKey,textoEncriptado);
    }

    private static void ej2() throws IOException {
        File fileClaus = new File("/home/ivanmigo58/Escritorio/clausA4.txt");
        File fileTextAmagat = new File("/home/ivanmigo58/Escritorio/textamagat");
        // Permite leer el fichero binario
        Path path = Paths.get(String.valueOf(fileTextAmagat));

        // Lee el contenido del fichero que ha leido el path
        byte[] textoEncripgtado = Files.readAllBytes(path);

        byte[] textoDesencriptado = null;

        // Leemos el fichero clausA4.txt linea a linea
        FileReader fr = new FileReader(fileClaus);
        BufferedReader br =  new BufferedReader(fr);
        String line = br.readLine();
        // Mientras textDesencriptado sea null
        while(textoDesencriptado == null ) {
           SecretKey secretKey = Xifrar.passwordKeyGeneration(line, 128);
            textoDesencriptado = Xifrar.decryptData(secretKey,textoEncripgtado);
            //Siguiente linea
            line = br.readLine();
        }
        System.out.println(new String(textoDesencriptado));

    }
}
