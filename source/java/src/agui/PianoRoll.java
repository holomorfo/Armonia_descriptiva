/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import java.util.LinkedList;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 * Genera un piano roll que ayuda a visualizar la interpretacion
 * @author Cristian
 */
public class PianoRoll {

    PApplet p;
    int pxo, pyo;
    int larg, anch;
    int notI = 36;
    int notF = 84;
    LinkedList<Rectangulo> recs;

    public PianoRoll(PApplet pa, int px, int py, int lar, int anc) {
        p = pa;
        recs = new LinkedList<Rectangulo>();
        pxo = px;
        pyo = py;
        larg = lar;
        anch = anc;
    }

    public void noteOn(float nota, int vel) {
        // Intentar agregar un rectangulo
        int posY = (int) p.map(nota, notI, notF, anch, 0);
        int alt = (int) p.map(vel, 30, 127, 0, anch / (notF - notI));
        recs.add(new Rectangulo(p, nota, vel, pxo + larg - 100, pyo + posY, alt));
//        p.println("Tam: " + recs.size());

    }

    public void noteOff(float nota) {
        for (int i = 0; i < recs.size(); i++) {
            if (nota == recs.get(i).getNota()) {
                recs.get(i).setPres(false);
            }
        }
    }

    public void sueltaPedal() {
        for (int i = 0; i < recs.size(); i++) {
            recs.get(i).setPres(false);
        }
    }

    public void paso() {
        for (int i = 0; i < recs.size(); i++) {
//            if(recs.get(i).getPxo()<)
            recs.get(i).avanzar(-5);
        }

    }

    public void paint(ListFloat flAc) {
//        p.noFill();
        p.fill(255);
        paso();
        //if(flAc.getLast()>)
        // Revisar que en las notas activas los extremos sean siempre mayores
        p.rect(pxo, pyo, larg, anch);
        for (int i = 0; i < recs.size(); i++) {

            recs.get(i).paint();
        }
//        p.println("Tam: " + recs.size());
    }
}
