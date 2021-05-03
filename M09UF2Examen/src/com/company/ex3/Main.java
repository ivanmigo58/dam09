package com.company.ex3;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int a = 320;
        int b = 720;
        MCDTask tarea = new MCDTask(a, b);
        pool.invoke(tarea);
        long result = tarea.join();
        System.out.println("Resultado MCD(" + a + "," + b + ") => " + result);
    }
}
