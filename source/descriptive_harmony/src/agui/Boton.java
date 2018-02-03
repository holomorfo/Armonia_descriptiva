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
public class Boton {

    Object cosa;
    private PApplet p;
    private boolean presionado = false;
    private boolean notaVF = false;
    private boolean cornerVF = true;
    private boolean rectVF = true;
//    private boolean info = false;
    private int anchoY;
    private int largoX;
    private int posX, posY;
    private int colorPresionado, colorSuelto, colorTexto;// = color(255,255,212);
    private int colorNota;
    String etiqueta = "";

    public Boton() {
    }

    public Boton(PApplet pa) {
        p = pa;
        posX = 0;
        posY = 0;
        anchoY = 10;
    }

    public Boton(PApplet pa, int pX, int pY, int larg) {
        p = pa;
        largoX = larg;
        anchoY = largoX;
        posX = pX;// La posicion es con respecto al esquina
        posY = pY;
        colorSuelto = p.color(255, 255, 212);
        colorPresionado = p.color(255, 209, 9);
        colorNota = p.color(0, 0, 250);
        colorTexto = p.color(255);

    }

    public Object getCosa() {
        return cosa;
    }

    public void setCosa(Object cosa) {
        this.cosa = cosa;
    }

    public Boton(PApplet pa, int pX, int pY, int larg, int anch) {
        p = pa;
        largoX = larg;
        anchoY = anch;
        posX = pX;// La posicion es con respecto al esquina
        posY = pY;
        colorSuelto = p.color(255, 255, 212);
        colorPresionado = p.color(255, 209, 9);
        colorTexto = p.color(0);
    }

    public void presionaTogle() {
        if (presionado) {
            presionado = false;
        } else {
            presionado = true;
        }
    }

    public void notaOn() {
        notaVF = true;
    }

    public void notaOff() {
        notaVF = false;
    }

    public void presionar() {
        presionado = true;
    }

    public void soltar() {
        presionado = false;
    }

    public boolean isPresionado() {
        return presionado;
    }

    public void setPresionado(boolean presionado) {
        this.presionado = presionado;
    }

    public boolean mouseTogle() {
        boolean cond = false;
        if (p.mouseX > posX && p.mouseX < posX + largoX && p.mouseY > posY && p.mouseY < posY + anchoY) {
            presionaTogle();
            cond = true;
        }
        return cond;
    }

    public void mousePres() {
        if (p.mouseX > posX & p.mouseX < posX + largoX && p.mouseY > posY & p.mouseY < posY + anchoY) {
            presionar();
        }
    }


    public void mousePres(int msX, int msY) {
        if (msX > posX & msX < posX + largoX && msY > posY & msY < posY + anchoY) {
            presionar();
        }
    }

    public void mouseSuelta(int msX, int msY) {
        if (msX > posX & msX < posX + largoX && msY > posY & msY < posY + anchoY) {
            soltar();
        }
    }

    public boolean mouseVF() {
        if (p.mouseX > posX && p.mouseX < (posX + largoX) && p.mouseY > posY && p.mouseY < (posY + anchoY)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean mouseVF(int mx, int my) {
        if (mx > posX && mx < (posX + largoX) && my > posY && my < (posY + anchoY)) {
            return true;
        } else {
            return false;
        }
    }

    public int getAncho() {
        return anchoY;
    }

    public void setAncho(int ancho) {
        this.anchoY = ancho;
    }

    public void setAnchoLargo(int anc) {
        this.anchoY = anc;
        this.largoX = anc;
    }

    public int getColorPresionado() {
        return colorPresionado;
    }

    public void setColorPresionado(int colorPresionado) {
        this.colorPresionado = colorPresionado;
    }

    public int getColorSuelto() {
        return colorSuelto;
    }

    public void setColorSuelto(int colorSuelto) {
        this.colorSuelto = colorSuelto;
    }

    public int getColorTemporal() {
        return colorNota;
    }

    public void setColorTemporal(int colorTemporal) {
        this.colorNota = colorTemporal;
    }

    public int getLargo() {
        return largoX;
    }

    public void setLargo(int largo) {
        this.largoX = largo;
    }

    public PApplet getP() {
        return p;
    }

    public void setP(PApplet p) {
        this.p = p;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosXY(int pX, int pY) {
        this.posX = pX;
        this.posY = pY;
    }

    public void setPosX(int pX) {
        this.posX = pX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int pY) {
        this.posY = pY;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(int colorTexto) {
        this.colorTexto = colorTexto;
    }

    public void setCornerVF(boolean cond) {
        cornerVF = cond;
    }

    public void setRectVF(boolean rectVF) {
        this.rectVF = rectVF;
    }

    public void paint() {
        if (this.presionado) {
            p.fill(this.colorPresionado);
        } else {
            p.fill(this.colorSuelto);
        }
        if (notaVF) {
            p.fill(this.colorNota);
        }
        if (cornerVF) {
            p.rectMode(p.CORNER);
        } else {
            p.ellipseMode(p.CENTER);
        }
        if (rectVF) {
            p.rect(posX, posY, largoX, anchoY);
        } else {
            p.ellipse(posX, posY, largoX, anchoY);
        }
//        getP().fill(colorTexto);
        getP().fill(0);
        getP().text(etiqueta, getPosX() + largoX / 2 - largoX / 3, getPosY() + anchoY / 2 + 3);
        p.fill(255);
        p.rectMode(p.CORNER);
        p.ellipseMode(p.CORNER);
    }
}
