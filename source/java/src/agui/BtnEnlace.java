/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import polifoniaLib.Enlace;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author Cristian
 */
public class BtnEnlace extends Boton {

    Enlace enl;
    String texto;
    PFont letras;

    public BtnEnlace(PApplet pa, Enlace en, int pX, int pY, int larg) {
        setP(pa);
        setLargo(larg);
        setAncho(larg);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        enl = en;
    }

    public BtnEnlace(PApplet pa, Enlace en, int pX, int pY, int larg, int anch) {
        setP(pa);
        setLargo(larg);
        setAncho(anch);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        enl = en;
    }

    public PFont getLetras() {
        return letras;
    }

    public void setLetras(PFont letras) {
        this.letras = letras;
    }


    public void paint() {
        if (isPresionado()) {
            getP().fill(getColorPresionado());
        } else {
            getP().fill(getColorSuelto());
        }
        getP().rectMode(getP().CORNER);
        getP().rect(getPosX(), getPosY(), getAncho(), getLargo());
        getP().fill(getColorTexto());
        String str = enl.getAr2().getInterpretacion();
        //str += "\n" + prog.getDistancia()+" "+prog.getA2().getLongArm()+" "+prog.getEnlaceStr();
        //str += "\n" +prog.getEnlaceStr()+" "+ prog.getA2().getDisposicionStr()+""+prog.getA2().getPosMel();
//        str += "\n"+enl.getAr2().getDisposicionStrCorto();
        str += "\n(" + enl.getAr2().getDuplicacion() + ")";
        str += "" + enl.getEnlacStrCort();
        str += "\np:" + enl.getPuntos();
        str += " m:" + enl.getGravMel();
//        str += " a " + enl.getGravArm();

        if (letras != null) {
//            getP().textFont(letras);
        }
        getP().text(str, getPosX() + 7, getPosY() + 25);
    }

    public Enlace getEnl() {
        return enl;
    }

    public void setProg(Enlace en) {
        this.enl = en;
    }
}
