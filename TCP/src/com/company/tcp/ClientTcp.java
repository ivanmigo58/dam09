package com.company.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTcp extends Thread {
    String hostname;
    int port;
    boolean continueConnected;
    int intents;
    Llista list;

    public ClientTcp(String hostname, int port, Llista list) {
        this.hostname = hostname;
        this.port = port;
        this.list = list;
        continueConnected = true;
        intents=0;
    }

    public void run() {
        Socket socket;
        ObjectOutputStream out;
        ObjectInputStream in;

        try {
            socket = new Socket(InetAddress.getByName(hostname), port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            while (continueConnected) {
                out.writeObject(list);
                out.flush();
                Llista llista1 = (Llista) in.readObject();
                imprimirLista(llista1);
                continueConnected = false;
            }
            close(socket);
        } catch (UnknownHostException ex) {
            System.out.println("Error de connexió. No existeix el host: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de connexió indefinit: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void imprimirLista(Llista llista1) {
        System.out.println(llista1);
    }


    private void close(Socket socket){
        try {
            //tancament de tots els recursos
            if(socket!=null && !socket.isClosed()){
                if(!socket.isInputShutdown()){
                    socket.shutdownInput();
                }
                if(!socket.isOutputShutdown()){
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            //enregistrem l'error amb un objecte Logger
            Logger.getLogger(ClientTcp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(5);
        numeros.add(3);
        numeros.add(1);
        numeros.add(8);
        numeros.add(8);
        numeros.add(7);
        numeros.add(7);
        numeros.add(4);
        ClientTcp clientTcp = new ClientTcp("localhost", 5558, new Llista("Ivan", numeros));
        clientTcp.start();
    }


}
