/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armonia;

import agui.BtnEvento;
import agui.Evento;
import agui.Instrumento;
import aguiListas.Eventos;
import java.util.LinkedList;
import polifoniaLib.Armonia;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class LayerEventos {

    PApplet p;
    Datos mainL;
    Mapa map;
    boolean loop = true;
    public LinkedList<BtnEvento> btnsEvsPiano;
    public LinkedList<BtnEvento> btnsEvsSint;
    Instrumento ins, insO;
    int larg = 50;
    int anch = 50;
    int separacion = 50;
    int numCuads = 15;
//    int pxBtnG = 300;
//    int pyBtnG = 250;
    int pxo = 0;// px de las listas
    int pyo = 0;

    public LayerEventos(PApplet pa) {
        p = pa;
        btnsEvsPiano = new LinkedList<BtnEvento>();
        btnsEvsSint = new LinkedList<BtnEvento>();
    }

    public LayerEventos(PApplet pa, Datos m, Mapa mapa, Instrumento in, Instrumento inso) {
        p = pa;
        mainL = m;
        map = mapa;
        ins = in;
        insO = inso;
        btnsEvsPiano = new LinkedList<BtnEvento>();
        btnsEvsSint = new LinkedList<BtnEvento>();
    }

    public void setPos(int x, int y) {
        pxo = x;
        pyo = y;
    }

    public Datos getMainL() {
        return mainL;
    }

    public void setMainL(Datos mainL) {
        this.mainL = mainL;
    }
    // Poner boton de agregar melodía

    public void agregarPiano(Eventos evs) {
        int can = evs.getCanal();
//        evs.print();
//        int px = pxo + (btnsEvs.size() % can) * separacion;
        int px = pxo + (btnsEvsPiano.size() % numCuads) * separacion;
        int py = pyo + separacion * (int) Math.floor((float) btnsEvsPiano.size() / (float) numCuads);
        //public BtnMelodia(PApplet pa, Cantus melo, int larg, int anch, int pX, int pY) {
//        btnsEvs.add(new BtnMelodia(p, evsTemp, larg, anch, px, py));
        String instS = "";
        btnsEvsPiano.add(new BtnEvento(p, evs, instS, larg, anch, px, py));
    }

    // Poner boton de agregar melodía
    public void agregarSint(Eventos evs) {
        int can = evs.getCanal();
//        int px = pxo + (btnsEvs.size() % can) * separacion;
        int px = pxo + (btnsEvsSint.size() % numCuads) * separacion;
        int py = pyo + anch + separacion * (int) Math.floor((float) btnsEvsSint.size() / (float) numCuads);
        //public BtnMelodia(PApplet pa, Cantus melo, int larg, int anch, int pX, int pY) {
//        btnsEvs.add(new BtnMelodia(p, evsTemp, larg, anch, px, py));
        String instS = "";

        btnsEvsSint.add(new BtnEvento(p, evs, instS, larg, anch, px, py));
        p.println("Agregar btnEvsSint.SintPlay " + btnsEvsSint.size());
    }

    // Poner boton de agregar melodía
    public void agregarPianoPlay(Eventos evs) {
//        p.println("evstmp: " + evs.getTiemp());
//        if (btnsEvsPiano.size() > 0) {
//            stopPlayPianoFIN();
//            btnsEvsPiano.removeLast();
//        }
//        stopPlayPianoFIN();
        if (evs.size() > 2) {
//            if (mainL.modoClase) {
//                evs.setLoop(false);
//            } else {
//                evs.setLoop(true);
//            }
            agregarPiano(evs);
//            stopPlayPianoFIN();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            btnsEvsPiano.getLast().getEvs().playEventsTh(p, ins);
//            stopPlayPianoFIN();
//            btnsEvsPiano.getLast().getEvs().playEventsTh(p, ins);
        }

    }

    // Poner boton de agregar melodía
    public void agregarSintPlay(Eventos evs) {
//        p.println("evstmp: " + evs.getTiemp());
//        if (btnsEvsSint.size() > 0) {
//            stopPlaySintFIN();
//            btnsEvsSint.removeLast();
//        }
//        stopPlaySintFIN();
        if (evs.getDuracion() > 500) {
//            if (mainL.modoClase) {
//                evs.setLoop(false);
//            } else {
//                evs.setLoop(true);
//            }

            agregarSint(evs);
            stopPlaySintFIN();
            btnsEvsSint.getLast().getEvs().playEventsTh(p, insO);
        }
    }

    public boolean isPianoPlaying() {
        boolean reg = false;
        if (btnsEvsPiano.size() > 0) {
            btnsEvsPiano.getLast().getEvs().isTocando();
        }
        return reg;
    }

    public boolean isSintPlaying() {
        boolean reg = false;
        if (btnsEvsSint.size() > 0) {
            btnsEvsSint.getLast().getEvs().isTocando();
        }
        return reg;

    }

    public void pararPiano() {
        if (btnsEvsPiano.size() > 0) {
            stopPlayPianoFIN();
            btnsEvsPiano.removeLast();
        }
    }

    public void mouseMoved() {
//        for (int i = 0; i < btnsEvs.size(); i++) {
//            if (btnsEvs.get(i).mouseVF(p.mouseX, p.mouseY)) {
//                evsActual = btnsEvs.get(i).getCant();
//            }
//        }
    }

    public void cambioTempo(float porc) {
        for (int i = 0; i < btnsEvsPiano.size(); i++) {
            btnsEvsPiano.get(i).getEvs().cambioTempo(porc);
        }
        for (int i = 0; i < btnsEvsSint.size(); i++) {
            btnsEvsSint.get(i).getEvs().cambioTempo(porc);
        }
    }

    public void setLoop(boolean loo) {
        for (int i = 0; i < btnsEvsPiano.size(); i++) {
            btnsEvsPiano.get(i).getEvs().setLoop(loo);
        }
        for (int i = 0; i < btnsEvsSint.size(); i++) {
            btnsEvsSint.get(i).getEvs().setLoop(loo);
        }

    }

    public void mousePressed(int mx, int my) {
        for (int i = 0; i < btnsEvsPiano.size(); i++) {
            if (btnsEvsPiano.get(i).mouseVF(mx, my)) {
                if (p.mouseButton == p.LEFT) {
                    btnsEvsPiano.get(i).presionaTogle();
                    if (btnsEvsPiano.get(i).isPresionado()) {
                        btnsEvsPiano.get(i).getEvs().playEventsTh(p, ins);
                    } else {
                        btnsEvsPiano.get(i).getEvs().stopEventsThFIN();
                    }
                } else if (p.mouseButton == p.RIGHT) {
                    btnsEvsPiano.get(i).getEvs().stopEventsThFIN();
                    btnsEvsPiano.remove(i);
                }
            }
        }
        for (int i = 0; i < btnsEvsSint.size(); i++) {
            if (btnsEvsSint.get(i).mouseVF()) {
                if (p.mouseButton == p.LEFT) {
                    btnsEvsSint.get(i).presionaTogle();
                    if (btnsEvsSint.get(i).isPresionado()) {
                        btnsEvsSint.get(i).getEvs().playEventsTh(p, insO);
                    } else {
                        btnsEvsSint.get(i).getEvs().stopEventsThFIN();
                    }
                } else if (p.mouseButton == p.RIGHT) {
                    btnsEvsSint.get(i).getEvs().stopEventsThFIN();
                    btnsEvsSint.remove(i);
                    map.limpiar();
                }
            }
        }
    }

    public void subirVolumen(int vol) {
        // Funcion de volumen y=velAct^parametro
        float val = p.map(vol, 0, 128, 1.5f, 0.1f);
        p.println("val: " + val);
        for (int i = 0; i < btnsEvsPiano.size(); i++) {
            btnsEvsPiano.get(i).getEvs().subirVolumen(val);
        }
        for (int i = 0; i < btnsEvsSint.size(); i++) {
            btnsEvsSint.get(i).getEvs().subirVolumen(val);
        }

    }

    public void escuchaMapa(Armonia nots) {
//        System.out.println("Entro 1");
        if (btnsEvsPiano.size() > 0 && nots.getVoces().size() > 0) {
//            System.out.println("Entro 2");
            btnsEvsPiano.getLast().getEvs().transfArm(nots, ins);
//            System.out.println("Entro 100");
            btnsEvsPiano.getLast().setTextoArm();
//            agregarPlay(btnsEvs.getLast().getEvs().transfArm(nots));
        }
    }

//    public void stopPlayPianoVuelta() {
//        for (int i = 0; i < btnsEvsPiano.size(); i++) {
//            btnsEvsPiano.get(i).getEvs().stopEventsThUnaVuelta();
//        }
//    }

    public void stopPlayPianoFIN() {
        for (int i = 0; i < btnsEvsPiano.size(); i++) {
            btnsEvsPiano.get(i).getEvs().stopEventsThFIN();
        }
    }

    public void stopPlaySintFIN() {
        for (int i = 0; i < btnsEvsSint.size(); i++) {
            p.println("stop play sint fin");
            btnsEvsSint.get(i).getEvs().stopEventsThFIN();
        }

    }

    public void paint() {
        if (btnsEvsPiano.size() > 0) {
            for (int i = 0; i < btnsEvsPiano.size(); i++) {
                btnsEvsPiano.get(i).paint();
//                if (btnsEvsPiano.get(i).isPresionado()) {
//                    paintPartitura(btnsEvsPiano.get(i));
            }
//            }
        }
        if (btnsEvsSint.size() > 0) {
            for (int i = 0; i < btnsEvsSint.size(); i++) {
                btnsEvsSint.get(i).paint();
//                if (btnsEvsSint.get(i).isPresionado()) {
//                    paintPartitura(btnsEvsSint.get(i));
//                }
            }
        }


    }

    public void paintPartitura(BtnEvento btn) {
        try {
            paintEventos(btn.getEvs());
        } catch (Exception e) {
        }

    }

    public void paintPartituraPrimero() {
        try {
            paintEventos(btnsEvsPiano.get(0).getEvs());
        } catch (Exception e) {
        }

    }

    public void paintEventos(Eventos evs) {
        int suma = 0;
        //inst.silencio();
//        p.background(255);
        int notaPY = 0, tiempoPX = 0, ancho = 7, largo = p.width;
        int yMax = 600, yMin = 100;
        float notaMin, notaMax;
        int margen = 30;
        int epx, epy, div = 10;
        float etiqTemp;
//        p.strokeWeight(1);
        if (evs.size() > 0) {
//            long tempMax = 60 * 1000;
            long tempMax = evs.getLast().getTiempo();
            notaMin = evs.getNotaMin();
            notaMax = evs.getNotaMax();
            p.fill(255);
            p.rect(0, yMin, p.width, yMax);
            long t0 = 0;
            for (int i = 0; i < evs.size(); i++) {
                try {// Si son simultaneos, dejar un milisegundo
                    long del = evs.get(i).getTiempo() - t0;
                    suma += del;
//                    Thread.sleep(del);
                } catch (Exception e) {
                }
//                p.noStroke();
                switch (evs.get(i).getOnOff()) {
                    case 'f':
                        p.fill(250);
                        notaPY = (int) p.map(evs.get(i).getNota(), 30, 90, yMax, yMin + 50) + 000;
                        tiempoPX = (int) p.map(evs.get(i).getTiempo(), 0, tempMax, 0, p.width);
                        p.rect(tiempoPX, notaPY, largo - tiempoPX, ancho);
//                        inst.noteOn(evs.get(i).getNota(), evs.get(i).getVeloc());
                        break;
                    case 'o':
                        p.colorMode(p.HSB);
                        notaPY = (int) p.map(evs.get(i).getNota(), 30, 90, yMax, yMin + 50) + 000;
                        tiempoPX = (int) p.map(evs.get(i).getTiempo(), 0, tempMax, 0, p.width);
                        p.fill(p.map(evs.get(i).getNota(), notaMin, notaMax, 0, 255), 255, 255);
                        p.text(Float.toString(evs.get(i).getNota()), tiempoPX, notaPY - 10);
                        p.rect(tiempoPX, notaPY, largo - tiempoPX, ancho);
                        p.colorMode(p.RGB);
                        //                        inst.noteOff(evs.get(i).getNota());
                        break;
                }
                //            Poner las marcas del tiempo
//               p.stroke(0);
                p.redraw();
                if (i > 0) {
                    t0 = evs.get(i).getTiempo();
                }
            }
            p.line(0, yMin, p.width, yMin);
            p.line(0, yMax, p.width, yMax);
            String etiq = "";
            for (int i = 0; i < div; i++) {
                etiqTemp = i * tempMax / (float) div;
                epy = yMax + 20;
                epx = (int) p.map(etiqTemp, 0, tempMax, 0, p.width);
                etiq = Integer.toString((int) etiqTemp / 1000);
                p.fill(0);
                p.line(epx, yMax, epx, yMin);
                p.text(etiq + "s", epx, epy);
            }
        }
    }

//    public void trasponer(Escala esc, int tras) {
//        mainL.orquestita.get(btnsEvs.get(i).getEvs().getCanal()).g
//    }
    public void cargarEventos(String[] evS) {
        for (int i = 0; i < btnsEvsPiano.size(); i++) {
            btnsEvsPiano.get(i).getEvs().stopEventsThFIN();
        }
        btnsEvsPiano = new LinkedList<BtnEvento>();
        int canal = 3, onOff, vel, tiemp;
        float nota;
        String[] linea;
        for (int i = 0; i < evS.length; i++) {
            if (evS[i].charAt(0) == 'c') {
                canal = Integer.parseInt("" + evS[i].charAt(2));
                agregarPiano(new Eventos(canal));
                System.out.println("Canal: " + canal);
            } else if (evS[i].charAt(0) != 'c') {
                linea = p.split(evS[i], ' ');
                onOff = Integer.parseInt(linea[0]);
                nota = Float.parseFloat(linea[1]);
                vel = Integer.parseInt(linea[2]);
                tiemp = Integer.parseInt(linea[3]);
                btnsEvsPiano.getLast().setTexto("" + canal);
                btnsEvsPiano.getLast().getEvs().add(new Evento(onOff, nota, vel, (long) tiemp));
            }

        }
    }

    public String grabarEventos() {
        String evS = "";
        if (btnsEvsPiano != null & btnsEvsPiano.size() > 0) {
            for (int i = 0; i < btnsEvsPiano.size(); i++) {
                // pone el canal donde tocar... quiza debería tambien tener el
                // instrumento, pero ahorita todavia no.
                evS += "c-" + btnsEvsPiano.get(i).getEvs().getCanal() + "\n";
                for (int j = 0; j < btnsEvsPiano.get(i).getEvs().size(); j++) {
                    evS += "" + btnsEvsPiano.get(i).getEvs().get(j).getOnOffInt();
                    evS += " " + btnsEvsPiano.get(i).getEvs().get(j).getNota();
                    evS += " " + btnsEvsPiano.get(i).getEvs().get(j).getVeloc();
                    evS += " " + btnsEvsPiano.get(i).getEvs().get(j).getTiempo();
                    evS += "\n";
                }
            }
        }
        return evS;
    }

    public String grabarEventosJuntos() {
        String evS = "";
        long tiempo = 0;
        if (btnsEvsPiano != null & btnsEvsPiano.size() > 0) {
            for (int i = 0; i < btnsEvsPiano.size(); i++) {
                // pone el canal donde tocar... quiza debería tambien tener el
                // instrumento, pero ahorita todavia no.
                if (i == 0) {
                    evS += "c-" + btnsEvsPiano.get(i).getEvs().getCanal() + "\n";
                }
                if (i > 1) {
                    tiempo += btnsEvsPiano.get(i - 1).getEvs().getLast().getTiempo();
                }
                for (int j = 0; j < btnsEvsPiano.get(i).getEvs().size(); j++) {
                    evS += "" + btnsEvsPiano.get(i).getEvs().get(j).getOnOffInt();
                    evS += " " + btnsEvsPiano.get(i).getEvs().get(j).getNota();
                    evS += " " + btnsEvsPiano.get(i).getEvs().get(j).getVeloc();
                    evS += " " + (tiempo + btnsEvsPiano.get(i).getEvs().get(j).getTiempo());
                    evS += "\n";
                }
            }
        }
        return evS;
    }

    public void setIns(Instrumento ins) {
        this.ins = ins;
    }

    public void setInsO(Instrumento insO) {
        this.insO = insO;
    }
}
