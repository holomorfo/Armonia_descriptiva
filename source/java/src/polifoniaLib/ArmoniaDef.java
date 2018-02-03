/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import polifoniaLib.listas.ListFloat;

/**
 *
 * @author holomorfo
 */
public class ArmoniaDef {

    String nombre;
    ListFloat notas;
    public ArmoniaDef(String str,ListFloat not) {
        nombre = str;
        notas= not;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public  ListFloat getNotas() {
        return notas;
    }

    public void setNotas(ListFloat not) {
        notas = not;    
    }
 
}
