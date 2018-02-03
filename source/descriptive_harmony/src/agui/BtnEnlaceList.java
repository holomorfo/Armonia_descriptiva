/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import Armonia.Datos;
import polifoniaLib.Enlace;
import polifoniaLib.listas.List1Enlace;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author Cristian
 */
public class BtnEnlaceList extends Boton {

    List1Enlace enlList;
    int idxActual = 0;
    String texto = "-";
    PFont letras;

    public BtnEnlaceList(PApplet pa, List1Enlace en, int pX, int pY, int larg) {
        setP(pa);
        setLargo(larg);
        setAncho(larg);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));
        enlList = en;
    }

    public BtnEnlaceList(PApplet pa, List1Enlace en, int pX, int pY, int larg, int anch) {
        setP(pa);
        setLargo(larg);
        setAncho(anch);
        setPosX(pX);// La posicion es con respecto al esquina
        setPosY(pY);
        setColorSuelto(getP().color(255, 255, 212));
        setColorPresionado(getP().color(255, 209, 9));

//        for (int i = 0; i < en.size(); i++) {
//            if (!en.get(i).getEnlacStrCort().equals("x")) {
//                this.presionar();
//                break;
//            }
//        }
        enlList = en;
        if (!en.getFirst().getEnlacStrCort().equals("x")) {
            this.presionar();
        }else{
            this.soltar();
        }
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

    public List1Enlace getEnlList() {
        return enlList;
    }

    public void setEnlList(List1Enlace arm) {
        this.enlList = arm;
    }

    public Enlace getEnlActual() {
        return enlList.get(idxActual);
    }

    public void siguienteEnlace() {
        idxActual = (idxActual + 1) % enlList.size();
        if (!getEnlActual().getEnlacStrCort().equals("x")) {
            this.presionar();
        }else{
            this.soltar();
        }

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
//        String str = arm.getInterpretacion();
//        str += "\n" + prog.getDistancia()+" "+prog.getA2().getLongArm()+" "+prog.getEnlaceStr();
//        str += "\n" +prog.getEnlaceStr()+" "+ prog.getA2().getDisposicionStr()+""+prog.getA2().getPosMel();
//        str += "\n"+enl.getAr2().getDisposicionStrCorto();
//        str += "\n(" + arm.getDuplicacion() + ")";

        if (letras != null) {
//            getP().textFont(letras);
        }
//        getP().text(str, getPosX() + 7, getPosY() + 25);
        texto = datos.getUniv().gradoAcordeRomSecEscAct(getEnlActual().getAr2());
        texto += "\n" + getEnlActual().getAr2().getFundStr('s') + "" + getEnlActual().getAr2().getTipoAcStr();
        getP().text(texto + "(" + enlList.size() + ")", getPosX() + 7, getPosY() + 17);
    }
}
