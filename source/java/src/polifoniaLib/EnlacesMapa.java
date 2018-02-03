/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import polifoniaLib.listas.Listas3Enlaces;
import polifoniaLib.listas.List2Enlace;
import polifoniaLib.listas.List1Enlace;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Cristian
 */
public class EnlacesMapa implements Serializable {

    Listas3Enlaces gradosLs = new Listas3Enlaces();
    List2Enlace inversL = new List2Enlace();
    List1Enlace enlacesL = new List1Enlace();
    int dimX = 0, dimY = 0;

    public EnlacesMapa() {
        // Site grados, con siete listas de
        for (int i = 0; i <= 7; i++) {
            gradosLs.add(new List2Enlace());
            for (int j = 0; j < 7; j++) {
                gradosLs.get(i).add(new List1Enlace());
            }
        }
    }

    public List1Enlace getGrado(int grd) {
        List1Enlace en = new List1Enlace();
        for (int inv = 0; inv < 7; inv++) {
            for (int i = 0; i < gradosLs.get(grd, inv).size(); i++) {
                en.add(gradosLs.get(grd, inv).get(i));
            }
        }
        Collections.sort(en, new OrdenPuntos());
        return en;
    }

    // Regresa la colección en el orden de grados
    public List1Enlace getMapaListaGrad() {
        List1Enlace en = new List1Enlace();
        for (int grd = 0; grd < 7; grd++) {
            for (int inv = 0; inv < 7; inv++) {
                for (int i = 0; i < gradosLs.get(grd, inv).size(); i++) {
                    en.add(gradosLs.get(grd, inv).get(i));
                }
            }
        }
        return en;
    }

    // Regresa la colección en el orden correpondient
    public List1Enlace getMapaListaPuntos() {
        List1Enlace en = new List1Enlace();
        for (int grd = 0; grd < 7; grd++) {
            for (int inv = 0; inv < 7; inv++) {
                for (int i = 0; i < gradosLs.get(grd, inv).size(); i++) {
                    en.add(gradosLs.get(grd, inv).get(i));
                }
            }
        }
        Collections.sort(en, Collections.reverseOrder(new OrdenPuntos()));
        return en;
    }

    // Regresa la colección en el orden correpondient
    public List1Enlace getMapaListaGrados() {
        List1Enlace en = new List1Enlace();
        for (int grd = 0; grd < 7; grd++) {
            for (int inv = 0; inv < 7; inv++) {
                for (int i = 0; i < gradosLs.get(grd, inv).size(); i++) {
                    en.add(gradosLs.get(grd, inv).get(i));
                }
            }
        }
        Collections.sort(en, new OrdenGrado());
        return en;
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

    public void printSize() {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.println(gradosLs.get(i, j).size());
            }
        }
    }
}

class OrdenPuntos implements Comparator {

    public int compare(Object en1, Object en2) {

        /*
         * parameter are of type Object, so we have to downcast it
         * to Employee objects
         */
        int emp1 = ((Enlace) en1).getPuntos();
        int emp2 = ((Enlace) en2).getPuntos();

        if (emp1 > emp2) {
            return 1;
        } else if (emp1 < emp2) {
            return -1;
        } else {
            return 0;
        }
    }
}

class OrdenGrado implements Comparator {

    public int compare(Object en1, Object en2) {

        /*
         * parameter are of type Object, so we have to downcast it
         * to Employee objects
         */
        float emp1 = ((Enlace) en1).getAr2().getFund12();
        float emp2 = ((Enlace) en2).getAr2().getFund12();

        if (emp1 > emp2) {
            return 1;
        } else if (emp1 < emp2) {
            return -1;
        } else {
            return 0;
        }
    }
}
