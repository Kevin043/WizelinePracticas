package com.wizeline.prototype;

import java.util.Objects;

public abstract  class Animal {

    public int patas;
    public boolean omnivoro;
    public boolean mamifero;
    public String color;

    public Animal() {
    }
    public Animal(Animal target) {
        if (target != null) {
            this.patas = target.patas;
            this.omnivoro = target.omnivoro;
            this.mamifero = target.mamifero;
            this.color = target.color;
        }
    }
    public abstract Animal clone();
    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Animal)) return false;
        Animal animal2 = (Animal) object2;
        return animal2.omnivoro = omnivoro && animal2.patas == patas
                && animal2.mamifero == mamifero && Objects.equals(animal2.color, color);
    }

}
