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
public class ListEntero extends LinkedList<Integer> implements Serializable {

    public ListEntero() {
    }

    public ListEntero(int i) {
        this.add(i);
    }

    public ListEntero(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
        this.add(i5);
        this.add(i6);
        this.add(i7);
    }

    public ListEntero(int i, int i2, int i3, int i4, int i5, int i6) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
        this.add(i5);
        this.add(i6);
    }

    public ListEntero(int i, int i2, int i3, int i4, int i5) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
        this.add(i5);
    }

    public ListEntero(int i, int i2, int i3, int i4) {
        this.add(i);
        this.add(i2);
        this.add(i3);
        this.add(i4);
    }

    public ListEntero(int i, int i2, int i3) {
        this.add(i);
        this.add(i2);
        this.add(i3);
    }

    public ListEntero(int i, int i2) {
        this.add(i);
        this.add(i2);
    }

    // Crear una copia de una lista de enteros.
    public ListEntero(ListEntero ent) {
        for (int i = 0; i < ent.size(); i++) {
            this.add(ent.get(i));
        }
    }

    public int get(char c) {
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
