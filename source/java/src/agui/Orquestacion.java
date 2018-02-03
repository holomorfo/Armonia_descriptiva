/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import java.util.LinkedList;
import polifoniaLib.listas.ListFloat;

import processing.core.PApplet;
import themidibus.MidiBus;

/**
 *
 * @author Cristian
 */
public class Orquestacion {

    public String[] instsLst;
    PApplet p;
    boolean ensamble = false;
    int px, py, larg;
    int btLar = 40;
    int cuadX = 16;
    MidiBus myBus;
    boolean visible = true;
    public LinkedList<Instrumento> insts;
    public int instNumMn = 1;
    public float bpm = 80;
    public float durCompSeg = 4 * 60 / bpm; //(Duracion compas en segudndos)
    LinkedList<Boton> bankBt;
    LinkedList<Boton> canBt;
    LinkedList<Boton> instBt;

    public Orquestacion(PApplet pa, MidiBus mb, int pxo, int pyo, int lon) {
        p = pa;
        px = pxo;
        py = pyo;
        larg = lon;
        btLar = larg / cuadX;
        instsLst = p.loadStrings("instrumentos.txt");
        myBus = mb;
        insts = new LinkedList<Instrumento>();
        int dc = 1;
        for (int i = 0; i < 16; i++) {
//            insts.add(new Instrumento(myBus, dc, i, 'A', 6));//Main Piano
            insts.add(new Instrumento(myBus, dc, i));//Main Piano
//            insts.get(i).cambioInstrumento('A', 1);
        }
//        insts.get(0).cambioInstrumento('A', 6);
//        insts.get(1).cambioInstrumento('A', 6);
//        insts.get(2).cambioInstrumento('A', 6);
//        insts.get(3).cambioInstrumento('A', 6);
        bankBt = new LinkedList<Boton>();
        for (int i = 0; i < 8; i++) {
            bankBt.add(new Boton(p, px + btLar * (i % cuadX), py + btLar * (i / cuadX), btLar));
        }
        bankBt.get(0).setEtiqueta("A");
        bankBt.get(1).setEtiqueta("B");
        bankBt.get(2).setEtiqueta("C");
        bankBt.get(3).setEtiqueta("D");
        bankBt.get(4).setEtiqueta("E");
        bankBt.get(5).setEtiqueta("F");
        bankBt.get(6).setEtiqueta("G");
        bankBt.get(7).setEtiqueta("H");

        instBt = new LinkedList<Boton>();
        for (int i = 0; i < 128; i++) {
            instBt.add(new Boton(p, px + btLar * (i % cuadX), py + btLar + btLar * (i / cuadX), btLar));
            instBt.get(i).setEtiqueta("" + (i + 1));
        }

        canBt = new LinkedList<Boton>();
        for (int i = 0; i < 16; i++) {
            canBt.add(new Boton(p, px + btLar * (i % cuadX), +py - btLar, btLar));
            canBt.get(i).setEtiqueta("" + (i + 1));
        }
        canBt.get(gMnInst().getChannel() - 1).presionar();
        bankBt.get(gMnInst().getBnk() - 1).presionar();
        instBt.get(gMnInst().getInstNum() - 1).presionar();

    }

    public void paint() {
        // Pinta la seleccion del instrumento main.
        if (visible) {
            if (ensamble) {
                p.fill(250);
            } else {
                p.fill(200);
            }

            p.rect(px - 10, py - btLar - 10, larg + 260, 2 * larg / 3 + 20);
            for (int i = 0; i < 8; i++) {
                bankBt.get(i).paint();
            }

            for (int i = 0; i < 128; i++) {
                instBt.get(i).paint();
            }

            for (int i = 0; i < 16; i++) {
                canBt.get(i).paint();
            }
            String str = "";
            for (int i = 0; i < 16; i++) {
                str += "\n" + (i + 1)
                        + " " + insts.get(i).getVolumen()
                        + "\t" + instsLst[128 * (insts.get(i).getBnk() - 1) + (insts.get(i).getInstNum() - 1)];

            }
            p.text(str, px + 15 + larg, py - btLar);
        }
    }

    public void mousePressed() {
        if (visible) {
            for (int i = 0; i < 8; i++) {
                if (bankBt.get(i).mouseVF()) {
                    for (int j = 0; j < 8; j++) {
                        bankBt.get(j).soltar();
                    }
                    bankBt.get(i).presionar();
                    gMnInst().cambioInstrumento(i + 1, gMnInst().getInstNum(), 127);
                    for (int k = 0; k < 128; k++) {
                        instBt.get(k).soltar();
                    }
                    instBt.get(gMnInst().getInstNum() - 1).presionar();
                    break;
                }
            }

            for (int i = 0; i < 128; i++) {
                if (instBt.get(i).mouseVF()) {
                    // cambiar instrumento actual;
                    for (int j = 0; j < 128; j++) {
                        instBt.get(j).soltar();
                    }
                    instBt.get(i).presionar();
                    gMnInst().cambioInstrumento(gMnInst().getBnk(), (i + 1), 127);
                    break;
                }
            }
            for (int i = 0; i < 16; i++) {
                if (canBt.get(i).mouseVF()) {
                    // cambiar instrumento actual;
                    for (int j = 0; j < 16; j++) {
                        canBt.get(j).soltar();
                    }
                    canBt.get(i).presionar();
                    setInstNumMn(i);
                    for (int j = 0; j < 8; j++) {
                        bankBt.get(j).soltar();
                    }
                    for (int j = 0; j < 128; j++) {
                        instBt.get(j).soltar();
                    }
                    bankBt.get(this.get(i).getBnk() - 1).presionar();
                    instBt.get(this.get(i).getInstNum() - 1).presionar();
                    p.println("Bank: " + get(i).getBnk() + " ," + get(i).getInstNum());
                    break;
                }
            }

        }
    }

    public Instrumento get(int i) {
        return insts.get(i);
    }

    public void setInstNumMn(int instNumMn) {
        this.instNumMn = instNumMn;
    }

    public int gMnInstNUM() {
        return instNumMn;
    }

    public void ensambleOn() {
        // Asignar rangos a instrumentos
        get(0).setRang(36, 36 + 11);
        get(1).setRang(48, 48 + 11);
        get(2).setRang(60, 60 + 11);
        get(3).setRang(72, 72 + 11);
        p.println("Activar Ensamble");
    }

    public void ensambleOff() {
        // Quitar rangos a instrumentos
        get(0).resetRang();
        get(1).resetRang();
        get(2).resetRang();
        get(3).resetRang();

    }

    public ListFloat getFloatActivos() {
        ListFloat list = new ListFloat();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < get(i).getFloatsActivos().size(); j++) {
                list.add(get(i).getFloatsActivos().get(j));
            }
        }
        return list;
    }

    public void noteOn(int pitch, int velocity) {
        if (ensamble) {
            for (int i = 0; i < 4; i++) {
//            insts.add(new Instrumento(myBus, dc, i, 'A', 1));//Main Piano
                get(i).noteOn(pitch, velocity);
            }
            p.println("Note On ensamble");
        } else {
            gMnInst().noteOn(pitch, velocity);
//            p.println(gMnInst().getBnk() + " " + gMnInst().getInstNum());
        }
    }

    public void noteOnMute(int pitch, int velocity) {
        if (ensamble) {
            for (int i = 0; i < 4; i++) {
//            insts.add(new Instrumento(myBus, dc, i, 'A', 1));//Main Piano
                get(i).noteOnMute(pitch, velocity);
            }
            p.println("Note On ensamble");
        } else {
            gMnInst().noteOnMute(pitch, velocity);
//            p.println(gMnInst().getBnk() + " " + gMnInst().getInstNum());
        }
    }

    public void noteOff(int pitch) {
        if (ensamble) {
            for (int i = 0; i < 16; i++) {
//            insts.add(new Instrumento(myBus, dc, i, 'A', 1));//Main Piano
                get(i).noteOffMute(pitch);
            }
        } else {
            gMnInst().noteOffMute(pitch);
        }
    }

    public void noteOffMute(int pitch) {
        if (ensamble) {
            for (int i = 0; i < 16; i++) {
//            insts.add(new Instrumento(myBus, dc, i, 'A', 1));//Main Piano
                get(i).noteOff(pitch);
            }
        } else {
            gMnInst().noteOff(pitch);
        }
    }

    public Instrumento gMnInst() {
        return insts.get(instNumMn);
    }

    public boolean isVisible() {
        return visible;
    }

    public void toggleVisible() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void toggleEnsamble() {
        if (ensamble) {
            ensamble = false;
            ensambleOff();
        } else {
            ensamble = true;
            ensambleOn();
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setBpm(float bpm) {
        this.bpm = bpm;
        durCompSeg = 4 * 60 / bpm;
    }

    public float getBpm() {
        return bpm;
    }

    public String cargarOrquesta(String[] orqS) {
        String evS = "";
        int bnk, nm, vol;
        String[] linea;
        if (insts != null & insts.size() > 0) {
            setBpm((float) Double.parseDouble(orqS[0]));
            for (int i = 0; i < insts.size(); i++) {
                // pone el canal donde tocar... quiza debería tambien tener el
                // instrumento, pero ahorita todavia no.
                linea = p.split(orqS[i + 1], ' ');
                vol = Integer.parseInt("" + linea[0]);
                bnk = Integer.parseInt("" + linea[1]);
                nm = Integer.parseInt("" + linea[2]);
                insts.get(i).cambioInstrumento(bnk, nm, vol);
            }
        }
        return evS;
    }

    public String grabarOrquesta() {
        String evS = "";
        try {
            int bnk, nm, vol;
            int nmList;
            if (insts != null & insts.size() > 0) {
                evS += "" + getBpm() + "\n";
                for (int i = 0; i < insts.size(); i++) {
                    // pone el canal donde tocar... quiza debería tambien tener el
                    // instrumento, pero ahorita todavia no.
                    bnk = insts.get(i).getBnk();
                    nm = insts.get(i).getInstNum();
                    vol = insts.get(i).getVolumen();
                    evS += "" + vol;
                    evS += " " + bnk;
                    if (nm < 10) {
                        evS += " 00" + nm;
                    } else if (nm >= 10 & nm < 100) {
                        evS += " 0" + nm;
                    } else {
                        evS += " " + nm;
                    }
                    nmList = 128 * (bnk - 1) + nm - 1;
                    evS += " " + instsLst[nmList];
                    evS += "\n";
                }
            }
        } catch (Exception e) {
        }
        return evS;
    }
}
