package com.wizeline.prototype;

public class Cotorro extends Animal {

    public String frase;

    public Cotorro() {
    }

    public Cotorro(Cotorro loro) {
        super(loro);
        if (loro!= null) {
            this.frase = loro.frase;
        }
    }
    @Override
    public Animal clone() {
        return new Cotorro(this);
    }
    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Cotorro) || !super.equals(object2)) return false;
        Cotorro shape2 = (Cotorro) object2;
        return shape2.frase == frase;
    }

}
