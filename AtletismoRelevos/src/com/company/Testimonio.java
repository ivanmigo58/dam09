package com.company;

public class Testimonio {
    private boolean libre;
    private Equipo equipo;

    enum Equipo {
        A, B;
    }

    public Testimonio(Equipo equipo) {
        this.equipo = equipo;
        this.libre = true;
    }

    synchronized void coger() throws InterruptedException {
        // Mientras no este libre, se espera
        while (!libre) {
            wait();
        }
        libre = false;
        // Notifico el cambio de la variable
        notifyAll();
    }


    synchronized void soltar() {
        libre = true;
        notifyAll();
    }


    public Equipo getEquipo() {
        return equipo;
    }
}

