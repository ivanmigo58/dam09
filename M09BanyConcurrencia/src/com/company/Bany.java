package com.company;

public class Bany {
    private int limite;
    private int personasDentro;
    private boolean ocupado;
    private Persona.Genero generoActual = Persona.Genero.NULL;

    public Bany(int limite) {
        this.limite = limite;
        ocupado = false;
    }


    synchronized public void utilizar(Persona persona) {
        try {
            while (personasDentro >= limite || (!generoActual.equals(persona.getGenero()) && personasDentro > 0)) {
                wait();
            }
            ocupado = true;
            generoActual = persona.getGenero();
            personasDentro++;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Deja de utilizar el baño
    synchronized public void desocupar() {
        personasDentro--;
        if (personasDentro == 0) {
            generoActual = Persona.Genero.NULL;
        }
        ocupado = false;
        notifyAll();
    }
}
