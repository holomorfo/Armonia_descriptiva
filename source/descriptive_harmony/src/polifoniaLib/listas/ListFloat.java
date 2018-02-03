/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.listas;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Cristian
 */
public class ListFloat extends LinkedList<Float> implements Serializable {

    public ListFloat() {
    }

    public ListFloat(float i) {
        this.add(i);
    }

    public ListFloat(float i, float i2, float i3, float i4, float i5, float i6, float i7) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
        this.add(i5);
        this.add(i6);
        this.add(i7);
    }

    public ListFloat(float i, float i2, float i3, float i4, float i5, float i6) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
        this.add(i5);
        this.add(i6);
    }

    public ListFloat(float i, float i2, float i3, float i4, float i5) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
        this.add(i5);
    }

    public ListFloat(float i, float i2, float i3, float i4) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
    }

    public ListFloat(float i, float i2, float i3) {
        this.add(i);
        this.add(i2);
        this.add(i3);
    }

    public ListFloat(float i, float i2) {
        this.add(i);
        this.add(i2);
    }

    // Crear una copia de una lista de enteros.
    public ListFloat(ListFloat ent) {
        for (int i = 0; i < ent.size(); i++) {
            this.add(ent.get(i));
        }
    }

    public boolean isEqual(ListFloat lis) {
        boolean cond = false;
        if (lis != null && this.size() == lis.size()) {
            cond = true;
            for (int i = 0; i < lis.size(); i++) {
                if (this.get(i) != lis.get(i)) {
                    cond = false;
                    break;
                }
            }
        }
        return cond;
    }

    public float get(char c) {
        int reg = 0;
        if (this.size() > 0) {
            switch (c) {
                case 'B':
                    reg = 0;
                    break;
                case 'T':
                    reg = 1;
                    break;
                case 'A':
                    reg = 2;
                    break;
                case 'S':
                    reg = 3;
                    break;
            }
        } else {
            reg = -1;
        }
        return get(reg);
    }
}
