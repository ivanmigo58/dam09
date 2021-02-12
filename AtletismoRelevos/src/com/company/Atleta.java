package com.company;

public class Atleta extends Thread{
    private String nombre;
    Testimonio testimonio;

    public Atleta(String nombre, Testimonio testimonio) {
        this.nombre = nombre;
        this.testimonio = testimonio;
    }

    @Override
    public void run() {
        try {
            testimonio.coger();
            System.out.println("El jugador " + nombre + " ha cogido el testimonio del equipo " + testimonio.getEquipo() + ".");
            Thread.sleep(5000);
            testimonio.soltar();
            System.out.println("El jugador " + nombre + " ha soltado el testimonio del equipo " + testimonio.getEquipo() + ".");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

