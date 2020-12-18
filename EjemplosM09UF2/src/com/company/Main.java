package com.company;

import java.util.*;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<MultiplicaLlista.Multiplicacio> llistaTasques= new ArrayList<MultiplicaLlista.Multiplicacio>();
        for (int i = 0; i < 10; i++) {
            MultiplicaLlista.Multiplicacio calcula = new MultiplicaLlista.Multiplicacio((int)(Math.random()*10), (int)(Math.random()*10));
            llistaTasques.add(calcula);
        }
        List <Future<Integer>> llistaResultats;
        llistaResultats = executor.invokeAll(llistaTasques);

        executor.shutdown();
        for (int i = 0; i < llistaResultats.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            try {
                System.out.println("Resultat tasca "+i+ " és:" + resultat.get());
            } catch (InterruptedException | ExecutionException e) {
            }
        }

    }
}

