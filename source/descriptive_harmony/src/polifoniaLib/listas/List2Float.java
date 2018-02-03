/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.listas;

import java.io.Serializable;
import java.util.LinkedList;
import polifoniaLib.Armonia;

/**
 *
 * @author Cristian
 */
public class List2Float extends LinkedList<ListFloat> implements Serializable {

    public void play() {
        //poner thread de play secuencia (donde va el thread, creo que aqui)
    }

    public void print() {
        Armonia arm;
        System.out.println("----------------");
        for (int i = 0; i < this.size(); i++) {
            arm = new Armonia(this.get(i));
            arm.printAcorde();
//            for (int j = 0; j < get(i).size(); j++) {
//                System.out.print(this.get(i).get(j) + ", ");
//            }
//            System.out.println("");
        }
    }
}
