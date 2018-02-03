/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.listas;

import java.io.Serializable;
import java.util.LinkedList;
import polifoniaLib.Enlace;

/**
 *
 * @author Cristian
 */
public class List3Enlaces extends LinkedList<List2Enlace> implements
        Serializable {

    List2Enlace gradosLs =  new List2Enlace();
    List1Enlace inversL = new List1Enlace();
    public List3Enlaces(){
        // Site grados, con siete listas de
        for(int i=0;i<7;i++){
            gradosLs.add(new List1Enlace());
            for(int j=0;j<7;j++){
                gradosLs.get(i).add(null);
            }
        }

        gradosLs.add(new List1Enlace());
        gradosLs.add(new List1Enlace());
        gradosLs.add(new List1Enlace());
        gradosLs.add(new List1Enlace());
        gradosLs.add(new List1Enlace());
        gradosLs.add(new List1Enlace());
        gradosLs.add(new List1Enlace());

    }
    public List1Enlace get(int grd, int inv) {
        return get(grd).get(inv);
    }

    public void agregar(int grad, int inv, Enlace e) {
    this.get(grad).get(inv).add(e);
    }
}
