/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import Armonia.Datos;
import polifoniaLib.Armonia;
import polifoniaLib.listas.ListArmonia;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author Cristian
 */
public class BtnArmoniaList extends Boton {

    ListArmonia armList;
    int idxActual = 0;
    String texto = "-";
    PFont letras;

    public BtnArmoniaList(PApplet pa, ListArmonia en, int pX, int pY, int larg) {
        setP(pa);
        setLargo(larg);
        setAncho(larg);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        armList = en;
    }

    public BtnArmoniaList(PApplet pa, ListArmonia en, int pX, int pY, int larg, int anch) {
        setP(pa);
        setLargo(larg);
        setAncho(anch);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        armList = en;
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

    public ListArmonia getArmList() {
        return armList;
    }

    public void setArmList(ListArmonia arm) {
        this.armList = arm;
    }

    public Armonia getArmActual() {
        return armList.get(idxActual);
    }

    public void siguienteArmonia() {
        idxActual = (idxActual + 1) % armList.size();
//        System.out.println("Siguiente: " + idxActual);
//        getArmActual().printAcorde();
    }

    public void paint(Datos datos) {
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
        texto = datos.getUniv().gradoAcordeRomSecEscAct(getArmActual());
        texto += "\n" + getArmActual().getFundStr('s') + "" + getArmActual().getTipoAcStr();
        getP().text(texto + "(" + armList.size() + ")", getPosX() + 7, getPosY() + 17);
    }
}
