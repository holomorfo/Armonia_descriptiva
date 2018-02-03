/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometria;

import polifoniaLib.Armonia;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 * Esta clase debe pintar un par de notas en el espacio R2
 * una linea que conecte el intervalo pasado con el nuevo.
 * Cuadricula de puntos.
 *
 * @author Cristian
 */
public class R2 {

    PApplet p;
    private int sep = 30;
    private int diam = 10;
    int px, py;
    Armonia armonia;

    public R2(PApplet pa, int x, int y) {
        p = pa;
        px = x;
        py = y;
    }

    public void setArmonia(ListFloat ls) {
        armonia = new Armonia(ls);

    }

    // r: R2, m: mobius
    public void paint(char modo) {
        p.ellipseMode(p.CENTER);
        switch (modo) {
            case 'r':
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {

                        if (i != j && armonia.pertenece12(i) && armonia.pertenece12(j)) {
                            p.fill(0);
                        } else {
                            p.fill(255);
                        }
                        p.ellipse(px + i * sep, py + j * sep, diam, diam);

                    }
                }
                break;
            case 'm':
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {

                        if (i != j && armonia.pertenece12(i) && armonia.pertenece12(j)) {
                            p.fill(0);
                        } else {
                            p.fill(255);
                        }
//                        if (i < j) {
                            p.ellipse(px + i * sep - j * (sep / 2), py + j * sep/2, diam, diam);
//                        }
                    }
                }
        }
        p.ellipseMode(p.CORNER);
    }
}
