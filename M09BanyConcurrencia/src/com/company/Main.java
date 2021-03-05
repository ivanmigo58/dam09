package com.company;

public class Main {

    public static void main(String[] args) {
        Bany bany = new Bany(2);
        Persona  ivan = new Persona("Ivan", Persona.Genero.CHICO, bany);
        Persona  nerea = new Persona("Nerea", Persona.Genero.CHICA, bany);
        Persona  juanmi = new Persona("Juanmi", Persona.Genero.CHICO, bany);
        Persona  andrea = new Persona("Andrea", Persona.Genero.CHICA, bany);

        ivan.start();
        nerea.start();
        juanmi.start();
        andrea.start();
    }
}
