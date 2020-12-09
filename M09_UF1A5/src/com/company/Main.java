package com.company;

import javax.crypto.SecretKey;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
//        ej1();
//        ej1_2();
//        ej1_3();
//        ej1_4();
        ej1_5();
        ej1_6();
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

    public static void ej1_3() throws FileNotFoundException, CertificateException {
        String fichero = ("/home/ivanmigo58/Escritorio/jordi.cer");
        try {
            PublicKey publicKey = Xifrar.getPublicKey(fichero);
            System.out.println(publicKey);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        }

    }

    public static void ej1_4() {
        // Datos del keystore
        String ksFile = "/home/ivanmigo58/keystore_imiralles.ks";
        String alias = "lamevaclaum9";
        String password = "usuario";
        try {
            // Cargo el keystore
            KeyStore keyStore = Xifrar.loadKeyStore(ksFile, password);
            PublicKey publicKey = Xifrar.getPublicKey(keyStore, alias, password);
            System.out.println(publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ej1_5() {
        KeyPair keyPair = Xifrar.randomGenerate(1024);
        byte[] firma = Xifrar.signData("hola".getBytes(), keyPair.getPrivate());
        System.out.println(new String(firma));
    }

    public static void ej1_6() {
        KeyPair keyPair= Xifrar.randomGenerate(1024);
        byte[] texto = "Hola".getBytes();
        byte[] firma = Xifrar.signData(texto, keyPair.getPrivate());
        boolean firmaCorrecta = Xifrar.validateSignature(texto, firma, keyPair.getPublic());
        System.out.println(firmaCorrecta);
    }

    public static void ej2() {
        KeyPair keyPair= Xifrar.randomGenerate(1024);
        byte[] texto = "Texto de prueba".getBytes();
        // Encripto el texto pasado
        byte[][] textoEncriptado = Xifrar.encryptWrappedData(texto,keyPair.getPublic());

        // Desencripto texto
        byte[] textoDesencriptado = Xifrar.dencryptWrappedData(textoEncriptado,keyPair.getPrivate());
        System.out.println(new String(textoDesencriptado));


    }



}
