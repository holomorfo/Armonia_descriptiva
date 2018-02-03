/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import java.util.LinkedList;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class PanelOrquesta {

    PApplet p;
    Orquestacion orq;
    int px, py, larg;
    int btLar = 40;
    int cuadX = 15;
    boolean visible = true;
    LinkedList<Boton> canBt;
    Boton toggleVFBt;

    public PanelOrquesta(Orquestacion o, int pox, int poy) {
        orq = o;
        p = o.p;
        px = pox;
        py = poy;
        canBt = new LinkedList<Boton>();

        canBt.add(new Boton(p, px + btLar * 1, py, btLar));
        canBt.add(new Boton(p, px + btLar * 2, py, btLar));
        canBt.add(new Boton(p, px + btLar * 3, py, btLar));
        canBt.add(new Boton(p, px + btLar * 4, py, btLar));

        canBt.get(0).setEtiqueta("A");

    }

    public void asignarRangos() {
        orq.get(0).setRang(36, 36 + 11);
        orq.get(1).setRang(48, 48 + 11);
        orq.get(2).setRang(60, 60 + 11);
        orq.get(3).setRang(72, 72 + 11);
    }

    public void mousePressed() {
        if (visible) {
            for (int i = 0; i < 4; i++) {
                if (canBt.get(i).mouseVF()) {
                    for (int j = 0; j < 8; j++) {
                        canBt.get(j).soltar();
                    }
                    canBt.get(i).presionar();
//                    bankNum = i + 1;
                    break;
                }
            }
        }
    }

    public void paint() {
        if (visible) {
            p.fill(200);
            p.rect(px - 10, py - 10, larg + 20, 2 * larg / 3 + 20);
            for (int i = 0; i < 8; i++) {
                canBt.get(i).paint();
            }

            for (int i = 0; i < 128; i++) {
                canBt.get(i).paint();
            }
        }

    }
}
