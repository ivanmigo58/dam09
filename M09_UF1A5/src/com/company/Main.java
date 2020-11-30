package com.company;

import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
//        ej1();
        ej1_2();
    }

    private static void ej1() {
        Scanner scanner = new Scanner(System.in);

        int len = 1024;
        Xifrar.randomGenerate(len);

        KeyPair keyPair = Xifrar.randomGenerate(len);
        keyPair.getPrivate();
        keyPair.getPublic();

        System.out.println("Escribe el texto para encriptytar: ");
        String texto = scanner.nextLine();
        System.out.println("\n");

        byte[] textoEncriptado = Xifrar.encryptData(texto.getBytes(), keyPair.getPublic());

        byte[] textoDesencriptado = Xifrar.decryptData(textoEncriptado, keyPair.getPrivate());

        System.out.println("Texto encriptado: " + new String(textoEncriptado));
        System.out.println("Texto desencriptado: " + new String(textoDesencriptado));

    }

    public static void ej1_2() throws Exception {
        Scanner scanner = new Scanner(System.in);
        KeyStore loadKeyStore = Xifrar.loadKeyStore("/home/ivanmigo58/keystore_imiralles.ks", "usuario");
        System.out.println("Tipos de Keystore: " + loadKeyStore.getType());
        System.out.println("Medida del almacenamiento: " + loadKeyStore.size());
        Enumeration enumeration = loadKeyStore.aliases();

        while (enumeration.hasMoreElements()) {
            System.out.println("Alias: " + enumeration.nextElement());
        }

        System.out.print("Que alias quieres mostrar? ");
        String alias = scanner.next();
        System.out.println("Certificado: " + loadKeyStore.getCertificate(alias));


        char[] password = "usuario".toCharArray();
        SecretKey secretKey = Xifrar.keygenKeyGeneration(128);
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter protectionParameter = new KeyStore.PasswordProtection(password);
        loadKeyStore.setEntry("mykey58", secretKeyEntry, protectionParameter);
        loadKeyStore.store(new FileOutputStream("/home/ivanmigo58/keystore_imiralles.ks"), "usuario".toCharArray());


    }


}
