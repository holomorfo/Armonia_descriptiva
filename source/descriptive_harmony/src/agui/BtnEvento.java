/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import aguiListas.Eventos;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class BtnEvento extends Boton {

    Eventos evs;
    String texto = "A";

    public BtnEvento(PApplet pa, Eventos evts, String inS, int larg, int anch, int pX, int pY) {
        setP(pa);
        texto = inS;
        setLargo(larg);
        setAncho(anch);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        evs = evts;
        setTexto("" + evs.getArm().nombreAcorde('s'));
    }

    public void setTextoArm() {
        setTexto("" + evs.getArm().nombreAcorde('s'));
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void paint() {
        if (isPresionado()) {
            getP().fill(getColorPresionado());
        } else {
            getP().fill(getColorSuelto());
        }
        if (getEvs().isTocando()) {
            getP().fill(getColorPresionado());
        } else {
            getP().fill(getColorSuelto());
        }

        if (evs.isTocando()) {
            getP().text(">", getPosX() + 6, getPosY() + 20);
        }
        getP().rectMode(getP().CORNER);
        getP().rect(getPosX(), getPosY(), getAncho(), getLargo());
        getP().fill(getColorTexto());
//        String str = enl.getAr2().getInterpretacion();
        getP().text(texto, getPosX() + 6, getPosY() + 20);
    }

    public Eventos getEvs() {
        return evs;
    }

    public void presionaTogle() {
        if (isPresionado()) {
            setPresionado(false);
        } else {
            setPresionado(true);
        }
    }
}
