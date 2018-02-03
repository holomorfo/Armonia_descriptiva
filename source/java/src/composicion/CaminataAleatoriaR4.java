/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composicion;

import agui.Orquestacion;
import polifoniaLib.Armonia;
import polifoniaLib.UniversoTonal;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class CaminataAleatoriaR4 {

    PApplet p;
    Orquestacion orq;
    UniversoTonal uni;
    ListFloat notas;
    boolean play = true;
    boolean busca = true;

    public CaminataAleatoriaR4(PApplet pa, Orquestacion o, UniversoTonal u) {
        p = pa;
        orq = o;
        uni = u;
    }

    public void setNotas(ListFloat notas) {
        this.notas = notas;
    }

    public void play() {
        play = true;
        int ind = 0;
        float nueva = 0;
        while (play) {
            // Dar un paso en la armonia, con ciertas restricciones.
            //  Solo acrodes de la escala
//            while (busca) {
            // Asignar nuevas notas a cada voz
            // Distancia cercana
            // Acorde en la escala
            ind = (int) p.random(notas.size());
            nueva = notas.get(ind) + (int) p.random(-2, 3);
            p.println("Ind: " + ind + "nueva: " + nueva);
            notas.set(ind, nueva);
//                p.println("Buscando");
//                if (uni.perteneceAcDiat(new Armonia(notas))) {
//                busca = false;
//                }
//            }
            orq.gMnInst().playAcordeFloat(notas, 100, 0.5);
            p.redraw();
        }

    }
}
