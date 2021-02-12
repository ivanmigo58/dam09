package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Testimonio
        Testimonio testimonioEquipoA = new Testimonio(Testimonio.Equipo.A);
        // Atletas equipo A
        List<Atleta> equipoA = new ArrayList<>();
        equipoA.add(new Atleta("juanmi", testimonioEquipoA));
        equipoA.add(new Atleta("ivan", testimonioEquipoA));
        equipoA.add(new Atleta("el jincho", testimonioEquipoA));

        Testimonio testimonioEquipoB = new Testimonio(Testimonio.Equipo.B);
        // Atletas equipo B
        List<Atleta> equipoB = new ArrayList<>();
        equipoB.add(new Atleta("dani", testimonioEquipoB));
        equipoB.add(new Atleta("carlos", testimonioEquipoB));
        equipoB.add(new Atleta("foyone", testimonioEquipoB));


        for (Atleta atleta : equipoA) {
            atleta.start();
        }

//        for (Atleta atleta : equipoB) {
//            atleta.start();
//        }

    }

}
}
