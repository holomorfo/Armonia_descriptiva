/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class Rectangulo {

    PApplet p;
    int pIni, pFin;
    int pxo, pyo;
    float nota;
    int vel;
    int dur;
    int anch = 30;
    int larg = 10;
    boolean pres = true;

    public Rectangulo(PApplet pa, float not, int vl, int px, int py, int alt) {
        p = pa;
        nota = not;
        vel = vl;
        pxo = px;
        pyo = py;
        anch = alt;
    }

    public float getNota() {
        return nota;
    }

    public boolean isPres() {
        return pres;
    }

    public void setPres(boolean pres) {
        this.pres = pres;
    }

    public int getPxo() {
        return pxo;
    }

    public void setPxo(int pxo) {
        this.pxo = pxo;
    }

    public void avanzar(int avanzar) {
        if (pres) {
            larg = larg + avanzar;
        } else {
            pxo = pxo + avanzar;
        }
    }

    public void paint() {
//        p.fill(0);
//        p.strokeWeight(1);
        p.colorMode(p.HSB);
        p.fill(p.map(vel, 0, 127, 0, 255), 255, 255);
        p.rect(pxo, pyo, larg, anch);
        p.colorMode(p.RGB, 255);
        p.fill(0);
        p.text("" + vel, pxo, pyo);
        p.fill(255);
    }
}
