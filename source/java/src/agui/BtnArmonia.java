/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import polifoniaLib.Armonia;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author Cristian
 */
public class BtnArmonia extends Boton {

    Armonia arm;
    String texto="";
    PFont letras;

    public BtnArmonia(PApplet pa, Armonia en, int pX, int pY, int larg) {
        setP(pa);
        setLargo(larg);
        setAncho(larg);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        arm = en;
    }

    public BtnArmonia(PApplet pa, Armonia en, int pX, int pY, int larg, int anch) {
        setP(pa);
        setLargo(larg);
        setAncho(anch);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        arm = en;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    
    public PFont getLetras() {
        return letras;
    }

    public void setLetras(PFont letras) {
        this.letras = letras;
    }

    public Armonia getArm() {
        return arm;
    }

    public void setArm(Armonia arm) {
        this.arm = arm;
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
//7        String str = arm.getInterpretacion();
        //str += "\n" + prog.getDistancia()+" "+prog.getA2().getLongArm()+" "+prog.getEnlaceStr();
        //str += "\n" +prog.getEnlaceStr()+" "+ prog.getA2().getDisposicionStr()+""+prog.getA2().getPosMel();
//        str += "\n"+enl.getAr2().getDisposicionStrCorto();
//        str += "\n(" + arm.getDuplicacion() + ")";

        if (letras != null) {
//            getP().textFont(letras);
        }
//        getP().text(str, getPosX() + 7, getPosY() + 25);

        getP().text(texto, getPosX() + 7, getPosY() + 25);
    }
}
