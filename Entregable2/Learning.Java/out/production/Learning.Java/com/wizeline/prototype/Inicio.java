package com.wizeline.prototype;

import java.util.ArrayList;
import java.util.List;

public class Inicio {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        Gato gato = new Gato();
        gato.patas = 4;
        gato.mamifero = true;
        gato.omnivoro = true;
        gato.color = "gris";
        gato.flexible = true;
        animals.add(gato);
        Gato nuevoGato = (Gato) gato.clone();
        animals.add(nuevoGato);
        Cotorro loro = new Cotorro();
        loro.frase = "Hola como estas!";
        loro.color = "verde";
        loro.patas = 2;
        loro.mamifero = false;
        loro.omnivoro = true;
        animals.add(loro);
        Cotorro otroCotorro = (Cotorro) loro.clone();
        animals.add(otroCotorro);
        System.out.println("Gatos igual " + animals.get(0).equals(animals.get(1)));
        System.out.println("Valor gatos " + animals.get(0).patas);
        System.out.println("Valor gatos " + animals.get(1).patas);
        System.out.println("Valor gatos " + animals.get(0).color);
        System.out.println("Valor gatos " + animals.get(1).color);
        System.out.println("Cotorros igual " +animals.get(2).equals(animals.get(3)));
    }

}
