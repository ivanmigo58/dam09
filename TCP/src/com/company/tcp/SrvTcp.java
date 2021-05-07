package com.company.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SrvTcp {

    int port;

    public SrvTcp(int port) {
        this.port = port;
    }

    public void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                clientSocket = serverSocket.accept();
                ThreadServidor hiloServidor = new ThreadServidor(clientSocket);
                Thread client = new Thread(hiloServidor);
                client.start();
            }
        } catch (IOException ex) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SrvTcp servidor = new SrvTcp(5558);
        servidor.listen();
    }

}
