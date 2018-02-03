/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import Armonia.Datos;
import java.util.LinkedList;
import polifoniaLib.Nota;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class PianoGUI {

    Datos mainL;
    //LayerMain mainL;
    PApplet p;
    int px, py;
    LinkedList<Octava> octs;

    public PianoGUI(PApplet pa, Datos mL, int pX, int pY) {
        p = pa;
        mainL = mL;
        px = pX;
        py = pY;
        octs = new LinkedList();
        octs.add(new Octava(p, px + 2 * 140 * 0, py, 3));
        octs.add(new Octava(p, px + 2 * 140 * 1, py, 4));
        octs.add(new Octava(p, px + 2 * 140 * 2, py, 5));
        octs.add(new Octava(p, px + 2 * 140 * 3, py, 6));
    }

    public void mousePres() {
        for (int i = 0; i < octs.size(); i++) {
            octs.get(i).mousePressed(p.mouseX, p.mouseY);
        }
    }

    public void mouseToggle(int mx, int my) {
        int octava = -1;
        int indx = -1;
        int play;
        for (int i = 0; i < octs.size(); i++) {
            //indx = octs.get(i).mousePressed(mx, my);
            indx = octs.get(i).mouseToggle(mx, my);
        }
    }

    public void mouseSuelta(int mx, int my) {
    }

    public void soltar() {
        for (int i = 0; i < octs.size(); i++) {
            octs.get(i).soltarTodos();
        }
    }

    public void paint() {
        for (int i = 0; i < octs.size(); i++) {
            octs.get(i).paint();
        }
    }

    public class Octava {

        PApplet p;
        int px, py;
        int oct;
        int largB = 2 * 20;
        int anchB = 2 * 70;
        int largN = 2 * 10;
        int anchN = 2 * 39;
        public int largoT = largB * 6;
        LinkedList<Boton> blancas;
        LinkedList<Boton> negras;

        public Octava(PApplet pa, int pX, int pY, int oc) {
            p = pa;
            oct = oc;
            px = pX;
            py = pY;
            blancas = new LinkedList<Boton>();
            negras = new LinkedList<Boton>();

            blancas.add(new Boton(p, px + (largB) * 0, py, largB, anchB));
            blancas.add(new Boton(p, px + (largB) * 1, py, largB, anchB));
            blancas.add(new Boton(p, px + (largB) * 2, py, largB, anchB));
            blancas.add(new Boton(p, px + (largB) * 3, py, largB, anchB));
            blancas.add(new Boton(p, px + (largB) * 4, py, largB, anchB));
            blancas.add(new Boton(p, px + (largB) * 5, py, largB, anchB));
            blancas.add(new Boton(p, px + (largB) * 6, py, largB, anchB));

            negras.add(new Boton(p, -10 + px + (largB) * 1, py, largN, anchN));
            negras.add(new Boton(p, -10 + px + (largB) * 2, py, largN, anchN));
            negras.add(new Boton(p, -10 + px + (largB) * 4, py, largN, anchN));
            negras.add(new Boton(p, -10 + px + (largB) * 5, py, largN, anchN));
            negras.add(new Boton(p, -10 + px + (largB) * 6, py, largN, anchN));

//            blancas.add(new Boton(p, 200, 500, 20, 60));
//            blancas.add(new Boton(p, 200 + 100, 500, 20, 60));
//            blancas.add(new Boton(p, 200 + 200, 500, 20, 60));

            for (int i = 0; i < blancas.size(); i++) {
                blancas.get(i).setColorSuelto(p.color(255, 255, 255));
                blancas.get(i).setColorPresionado(p.color(100, 100, 100));
            }
            for (int i = 0; i < negras.size(); i++) {
                negras.get(i).setColorSuelto(p.color(0, 0, 0));
                negras.get(i).setColorPresionado(p.color(100, 100, 100));
            }
        }

        
        public int mousePressed(int mX, int mY) {
            int indiceN = -1;
            int indiceB = -1;
            int reg = -1;

            for (int i = 0; i < negras.size(); i++) {
                if (negras.get(i).mouseVF(mX, mY)) {
                    indiceN = i;
                }
            }

            for (int j = 0; j < blancas.size(); j++) {
                if (blancas.get(j).mouseVF(mX, mY)) {
                    indiceB = j;
                }
            }

            if (indiceN != -1) {
                reg = indiceN;
                switch (reg) {
                    case 0:
                        reg = 1;
                        break;
                    case 1:
                        reg = 3;
                        break;
                    case 2:
                        reg = 6;
                        break;
                    case 3:
                        reg = 8;
                        break;
                    case 4:
                        reg = 10;
                        break;
                }
            } else if (indiceB != -1) {
                reg = indiceB;
                switch (reg) {
                    case 0:
                        reg = 0;
                        break;
                    case 1:
                        reg = 2;
                        break;
                    case 2:
                        reg = 4;
                        break;
                    case 3:
                        reg = 5;
                        break;
                    case 4:
                        reg = 7;
                        break;
                    case 5:
                        reg = 9;
                        break;
                    case 6:
                        reg = 11;
                        break;
                }
            }
//            System.out.println("Reg: " + reg);

            if (reg != -1) {
                int play = (12 * (oct) + reg);
//                mainL.gMnInst().noteOn(play, 100);
                mainL.getOrq().gMnInst().playNota(new Nota((float) play, 100, 0.25f));
//                mainL.setNotasPlay((float)play);
            }

            return reg;
        }

        public int mouseToggle(int mX, int mY) {
            int indiceN = -1;
            int indiceB = -1;
            int reg = -1;

            for (int i = 0; i < negras.size(); i++) {
                if (negras.get(i).mouseVF(mX, mY)) {
                    negras.get(i).presionaTogle();
                    indiceN = i;
                }
            }

            for (int j = 0; j < blancas.size(); j++) {
                if (blancas.get(j).mouseVF(mX, mY)) {
                    blancas.get(j).presionaTogle();
                    indiceB = j;
                }
            }

            if (indiceN != -1) {
                reg = indiceN;
                switch (reg) {
                    case 0:
                        reg = 1;
                        break;
                    case 1:
                        reg = 3;
                        break;
                    case 2:
                        reg = 6;
                        break;
                    case 3:
                        reg = 8;
                        break;
                    case 4:
                        reg = 10;
                        break;
                }
            } else if (indiceB != -1) {
                reg = indiceB;
                switch (reg) {
                    case 0:
                        reg = 0;
                        break;
                    case 1:
                        reg = 2;
                        break;
                    case 2:
                        reg = 4;
                        break;
                    case 3:
                        reg = 5;
                        break;
                    case 4:
                        reg = 7;
                        break;
                    case 5:
                        reg = 9;
                        break;
                    case 6:
                        reg = 11;
                        break;
                }
            }
//            System.out.println("Reg: " + reg);
            if (reg != -1) {
                int play = (12 * (oct) + reg);
                if (indiceN != -1) {
                    if (!negras.get(indiceN).isPresionado()) {
                        mainL.getOrq().gMnInst().noteOff(play);
                    } else {
                        mainL.getOrq().gMnInst().noteOn(play, 100);
                        //mainL.armL.agregarNota(play);
                    }

                } else if (indiceB != -1) {
                    if (!blancas.get(indiceB).isPresionado()) {
                        mainL.getOrq().gMnInst().noteOff(play);
                    } else {
                        mainL.getOrq().gMnInst().noteOn(play, 100);
                    }
                }
            }

            return reg;
        }

        public void soltarTodos() {
            for (int i = 0; i < negras.size(); i++) {
                negras.get(i).soltar();
            }

            for (int j = 0; j < blancas.size(); j++) {
                blancas.get(j).soltar();
            }
            mainL.getOrq().gMnInst().silencioTotalSinGrabar();
        }

        public void paint() {
            //p.rectMode(p.CORNER);
            for (int i = 0; i < blancas.size(); i++) {
                blancas.get(i).paint();
            }
            for (int i = 0; i < negras.size(); i++) {
                negras.get(i).paint();
            }

            //p.rectMode(p.CENTER);
        }
    }
}
