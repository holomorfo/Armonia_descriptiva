/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author Cristian
 */
public class BancoColoresNFonts {

    private PApplet p;
    private int colorA;
    private int colorB;
    private int botonMenu;
    private int btnAcorde;
    private int btnAcordeDis;
    private int btnAcordePar;
    private int btnAcordeRojo;
    private int btnAcordeRojo2;
    private int btnAcordePrepVde;
    private int btnAcordeDuplCafe;
    private int btnAcordePrep;
    private int btnAcordeBlanco;
    private int btnGrado;
    PFont letrasA;
    PFont letrasB;
    PFont letrasC;

    public BancoColoresNFonts(PApplet pa) {
        p = pa;
        colorA = p.color(255);
        colorB = p.color(178, 178, 178);
        //letrasA = p.createFont("Helvetica", 14);
        letrasA = p.createFont("AGaramondPro-Bold", 14);
        
        letrasB = p.createFont("AGaramondPro-Bold", 13);
        letrasC = p.createFont("AGaramondPro-Bold", 25);


        botonMenu = p.color(82, 113, 224);
        btnGrado = p.color(32, 158, 178);
        btnAcordePrep = p.color(51, 92, 101);
        btnAcorde = p.color(82, 113, 224);
        btnAcordePar = p.color(159, 34, 20);
        btnAcordeDis = p.color(185, 109, 42);
        btnAcordeBlanco = p.color(255);

        btnAcordeRojo = p.color(159, 34, 20);
        btnAcordeRojo2 = p.color(255, 10, 10);
        btnAcordePrepVde = p.color(51, 92, 101);
        btnAcordeDuplCafe = p.color(185, 109, 42);

    }

    public int getColorA() {
        return colorA;
    }

    public int getBlanco() {
        return p.color(255);
    }

    public int getNegro() {
        return p.color(0);
    }

    public int getVerdecitoOpaco() {
        return p.color(51, 92, 101);
    }

    public int getVerde() {
        return p.color(81, 112, 151);
    }

    public int getAzulito() {
        return p.color(82, 113, 224);
    }

    public void setColorA(int colorA) {
        this.colorA = colorA;
    }

    public int getColorB() {
        return colorB;
    }

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    public PFont getLetrasA() {
        return letrasA;
    }

    public void setLetrasA(PFont letrasA) {
        this.letrasA = letrasA;
    }

    public PFont getLetrasB() {
        return letrasB;
    }


    public void setLetrasB(PFont letrasB) {
        this.letrasB = letrasB;
    }

    public PFont getLetrasC() {
        return letrasC;
    }


    public void setLetrasC(PFont letraC) {
        this.letrasC = letraC;
    }

    public PApplet getP() {
        return p;
    }

    public int getBotonMenu() {
        return botonMenu;
    }

    public int getBtnAcorde() {
        return btnAcorde;
    }

    public int getBtnAcordeBlanco() {
        return btnAcordeBlanco;
    }

    public int getBtnAcordeDis() {
        return btnAcordeDis;
    }

    public int getBtnAcordeDuplCafe() {
        return btnAcordeDuplCafe;
    }

    public int getBtnAcordePar() {
        return btnAcordePar;
    }

    public int getBtnAcordePrep() {
        return btnAcordePrep;
    }

    public int getBtnAcordePrepVde() {
        return btnAcordePrepVde;
    }

    public int getBtnAcordeRojo() {
        return btnAcordeRojo;
    }

    public int getBtnAcordeRojo2() {
        return btnAcordeRojo2;
    }

    public int getBtnGrado() {
        return btnGrado;
    }

    public void setP(PApplet p) {
        this.p = p;
    }
}
