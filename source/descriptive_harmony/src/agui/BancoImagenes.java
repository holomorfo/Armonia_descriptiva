/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import java.util.LinkedList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Cristian
 */
public class BancoImagenes {

    private PApplet p;
//    PImage fondo;
    PImage nota;
    PImage claveSol;
    PImage claveFa;
    PImage notaRedonda;
    PImage notaSost;
    PImage notaBemol;
    LinkedList<PImage> armSost = new LinkedList();
    LinkedList<PImage> armBem = new LinkedList();

    public BancoImagenes(PApplet pa) {
        p = pa;
        //nota = p.loadImage("clave-sol.gif");
//        fondo = p.loadImage("fondo.gif");
        claveSol = p.loadImage("clave-sol.gif");
        claveFa = p.loadImage("clave-fa.gif");
        notaRedonda = p.loadImage("bolita.gif");
        notaSost = p.loadImage("sostenido.gif");
        notaBemol = p.loadImage("bemol.gif");

        armBem.add(p.loadImage("dmen.gif"));// 166,143
        armBem.add(p.loadImage("gmen.gif"));
        armBem.add(p.loadImage("c.gif"));
        armBem.add(p.loadImage("f.gif"));
        armBem.add(p.loadImage("bb.gif"));
        armBem.add(p.loadImage("eb.gif"));

        armSost.add(p.loadImage("G.gif"));// 121,131
        armSost.add(p.loadImage("D.gif"));
        armSost.add(p.loadImage("A.gif"));
        armSost.add(p.loadImage("E.gif"));
        armSost.add(p.loadImage("B.gif"));

    }

    public LinkedList<PImage> getArmBem() {
        return armBem;
    }

    public void setArmBem(LinkedList<PImage> armMay) {
        this.armBem = armMay;
    }

    public LinkedList<PImage> getArmSost() {
        return armSost;
    }

    public void setArmSost(LinkedList<PImage> armMen) {
        this.armSost = armMen;
    }

    public PImage getClaveFa() {
        return claveFa;
    }

    public void setClaveFa(PImage claveFa) {
        this.claveFa = claveFa;
    }

    public PImage getClaveSol() {
        return claveSol;
    }

    public void setClaveSol(PImage claveSol) {
        this.claveSol = claveSol;
    }

    public PImage getNota() {
        return nota;
    }

    public void setNota(PImage nota) {
        this.nota = nota;
    }

    public PImage getNotaBemol() {
        return notaBemol;
    }

    public void setNotaBemol(PImage notaBemol) {
        this.notaBemol = notaBemol;
    }

    public PImage getNotaRedonda() {
        return notaRedonda;
    }

    public void setNotaRedonda(PImage notaRedonda) {
        this.notaRedonda = notaRedonda;
    }

    public PImage getNotaSost() {
        return notaSost;
    }

    public void setNotaSost(PImage notaSost) {
        this.notaSost = notaSost;
    }

    public PApplet getP() {
        return p;
    }

    public void setP(PApplet p) {
        this.p = p;
    }
}
