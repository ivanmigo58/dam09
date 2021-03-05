package com.company;

public class Persona extends Thread {
    private String nombre;
    private Genero genero;
    private Bany bany;

    public Genero getGenero() {
        return genero;
    }


    public enum Genero {
        CHICO, CHICA,NULL
    }

    public Persona(String nombre, Genero genero, Bany bany) {
        this.nombre = nombre;
        this.genero = genero;
        this.bany = bany;
    }

    @Override
    public void run() {
        while (true) {
            // Segundos random en el baño
            int timeRandom = (int) (Math.random()* 8);
            // Utilizando
            bany.utilizar(this);
            // Esta dentro del baño
            System.out.println(nombre + " utiliza el baño durante " + timeRandom + " segundos.");
            try {
                Thread.sleep(timeRandom * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(nombre + " deja de utilizar el baño.");
            // Sale del baño
            bany.desocupar();
            try {
                Thread.sleep(timeRandom * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
