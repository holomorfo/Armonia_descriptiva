/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.listas;

import polifoniaLib.listas.List3Enlaces;
import polifoniaLib.Enlace;
import polifoniaLib.listas.List2Enlace;
import polifoniaLib.listas.List1Enlace;
import java.io.Serializable;
import java.util.LinkedList;
import polifoniaLib.Enlace;

/**
 *
 * @author Cristian
 */
public class ListMapaEnlaces implements Serializable {

    List3Enlaces gradosLs = new List3Enlaces();
    List2Enlace inversL = new List2Enlace();
    List1Enlace enlacesL = new List1Enlace();

    public ListMapaEnlaces() {
        // Site grados, con siete listas de
        for (int i = 0; i <= 7; i++) {
            gradosLs.add(new List2Enlace());
            for (int j = 0; j < 7; j++) {
                gradosLs.get(i).add(new List1Enlace());
            }
        }
    }

    public List1Enlace get(int grd, int inv) {
        return gradosLs.get(grd).get(inv);
    }

    public void agregar(int grad, int inv, Enlace e) {
        gradosLs.get(grad).get(inv).add(e);
    }

    public void agregarPrimero(int grad, int inv, Enlace e) {
        gradosLs.get(grad).get(inv).addFirst(e);
    }

    public void agregarUltimo(int grad, int inv, Enlace e) {
        gradosLs.get(grad).get(inv).addLast(e);
    }

    public int tamanio(int grad, int inv) {
        return gradosLs.get(grad).get(inv).size();
    }
}
