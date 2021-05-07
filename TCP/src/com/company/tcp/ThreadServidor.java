package com.company.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadServidor implements Runnable {

        Socket clientSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        boolean end;
        Llista list;

    public ThreadServidor(Socket clientSocket) throws IOException, ClassNotFoundException {
            this.clientSocket = clientSocket;
            end = false;
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        }



        @Override
        public void run() {
            while(!end) {
                try {
                    list = (Llista) in.readObject();
                    out.writeObject(ordenarLista(list));
                    out.flush();
                    end = true;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        private Llista ordenarLista(Llista llista) {
            List<Integer> num = llista.getNumberList();
            Collections.sort(num);
            List<Integer> numUnicos = num.stream().distinct().collect(Collectors.toList());
            llista.setNumberList(numUnicos);
            return llista;
        }

}

