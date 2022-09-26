package com.wizeline.prototype;

import javax.swing.text.StyledEditorKit;

public class Gato extends  Animal {
     public boolean flexible;

    public Gato() {
    }
    public Gato(Gato gato) {
        super(gato);
        if (gato != null) {
            this.flexible = gato.flexible;
        }
    }
    @Override
    public Animal clone() {
        return new Gato(this);
    }
    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Gato) || !super.equals(object2)) return false;
        Gato Animal2 = (Gato) object2;
        return Animal2.flexible == flexible;
    }
}
