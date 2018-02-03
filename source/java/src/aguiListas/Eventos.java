/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aguiListas;

import agui.Evento;
import agui.Instrumento;
import java.util.LinkedList;
import polifoniaLib.Armonia;
import polifoniaLib.Escala;
import polifoniaLib.defs.Oper;
import polifoniaLib.listas.ListEntero;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class Eventos extends LinkedList<Evento> {

    public PlayEventos playEventos;
    int canal;
    ListEntero vols;
    private boolean tocando = false;
    public boolean loop = true;
    public boolean mute = false;
    public long t0;
    public long duracion = 0;
    public int numPlay = 0;
    public Thread playEventosTh;
    public Armonia arm;

    /**
     *
     */
    public Eventos(int can) {
        canal = can;
        ///
        this.add(new Evento('x', 0, 100, 0));
//        t0 = System.currentTimeMillis();
        ///
        arm = new Armonia();
        vols = new ListEntero();
//        playEventos = new PlayEventos();
    }

    public boolean isLoop() {
        return loop;
    }

    public void agregar(char tipo, float nota, int vel) {
        if (this.size() == 1) {
            t0 = System.currentTimeMillis();
            this.add(new Evento(tipo, nota, vel, 0));
        } else {
            this.add(new Evento(tipo, nota, vel, System.currentTimeMillis() - t0));
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isTocando() {
        return tocando;
    }

    public void print() {
        System.out.println("------------");
        for (int i = 0; i < this.size(); i++) {
            System.out.println("Ev: " + i
                    + " Tipo: " + get(i).getOnOff()
                    + " nota: " + get(i).getNota()
                    + " vel: " + get(i).getVeloc()
                    + " temp: " + get(i).getTiempo());
        }
    }

    public float getNotaMin() {
        float min = 200;
        for (int i = 0; i < this.size(); i++) {
            if (min > this.get(i).getNota()) {
                min = this.get(i).getNota();
            }
        }
        return min;
    }

    public float getNotaMax() {
        float max = 0;
        for (int i = 0; i < this.size(); i++) {
            if (max < this.get(i).getNota()) {
                max = this.get(i).getNota();
            }
        }
        return max;
    }

    /**
     *
     */
    public Eventos() {
        canal = 0;
        playEventos = new PlayEventos();
    }

    public void finGrabacionLimpia() {
        ListFloat lis = new ListFloat();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getOnOff() == 'o') {
                // Hacer metodo que revise la paridad de las notas
                this.add(new Evento('f', get(i).getNota(), 0, System.currentTimeMillis() - t0));
            }
        }
        this.add(new Evento('x', 0, 0, System.currentTimeMillis() - t0 + 100));
        for (int i = 0; i < this.size(); i++) {
            vols.add(this.get(i).getVeloc());
        }
        if (this.size() > 2) {
            duracion = this.getLast().getTiempo() - this.getFirst().getTiempo();
        } else {
            duracion = 0;
        }
        this.setArmonia();
    }

    public void finGrabacionManual() {
        this.add(new Evento('x', 0, 0, System.currentTimeMillis() - t0 + 100));
        for (int i = 0; i < this.size(); i++) {
            vols.add(this.get(i).getVeloc());
        }
        if (this.size() > 2) {
            duracion = this.getLast().getTiempo() - this.getFirst().getTiempo();
        } else {
            duracion = 0;
        }
        this.setArmonia();
//        for (int i = 0; i < this.size(); i++) {
//            this.get(i).getOnOff();
//            vols.add(this.get(i).getVeloc());
//        }
    }

    /**
     * Ejecuta la grabación del instrumento
     * @param mb
     * @param canal el canal donde toca
     */
    public void playEventsTh(PApplet p, Instrumento inst) {
//        if (playEventos.fiesta == false
//               & isGrabando == false) {// Si no está tocando, ni isGrabando
        if (tocando == false && this != null && this.size() > 0) {
            tocando = true;
            playEventos = new PlayEventos(p, inst, this);
            playEventosTh = new Thread(playEventos);
            playEventosTh.start();
        }
//        }
    }

//    public void stopEventsThUnaVuelta() {
//        playEventos.finVuelta();
//    }
    public void stopEventsThFIN() {
        try {
            playEventos.finFin();
        } catch (Exception e) {
        }
    }

    public void cambioArmonia() {
        try {
            playEventos.finFin();
        } catch (Exception e) {
        }
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    // Duracion Nueva = DurPasada*(BPMnuevo / BPMactual)
    public void cambioTempo(float porc) {
        if (this.size() > 0) {
            int tiemp0 = (int) getFirst().getTiempo();
            for (int i = 0; i < this.size(); i++) {
                get(i).setTiempo((int) (get(i).getTiempo() * (float) porc));
//                System.out.println("Cambio tempo: "+get(i).getTiempo());
            }
        }
    }

    public void transponer(float num) {
        if (this.size() > 0) {

//            playEventos.silencioLoop();

            for (int i = 0; i < this.size(); i++) {
                get(i).transponerCromatica(num);
            }
        }
    }

    public void transponerDiatonico(Escala esc, int num) {
        if (this.size() > 0) {
            for (int i = 0; i < this.size(); i++) {
//                playEventos.silencioLoop();
                get(i).transponerDiatonica(esc, num);
            }
        }

    }

    // Hacer que los tiempos empiecen en cero, al menos el primero
    public void normalizarTiempo() {
        if (this.size() > 1) {
            int tiemp0 = (int) get(1).getTiempo();
//            int tiemp0 = (int) get(1).getTiempo();
            for (int i = 0; i < this.size(); i++) {
//                System.out.println(get(i).getTiempo() + " " + (get(i).getTiempo() - tiemp0));
                if ((get(i).getTiempo() - tiemp0) >= 0) {
                    get(i).setTiempo(get(i).getTiempo() - tiemp0);
                } else {
                    get(i).setTiempo(0);
                }

            }
//            this.removeFirst();
        }
    }

    public void setArmonia() {
        ListFloat not = new ListFloat();
        if (this.size() > 0) {
            for (int i = 0; i < this.size(); i++) {
                if (get(i).getOnOff() == 'o') {
                    if (not.indexOf(get(i).getNota()) == -1) {
                        not.add(get(i).getNota());
                    }
                }
            }
            arm = new Armonia(not);
//          arm = new Armonia(arm.getRepresentante());
//            arm.printAcorde();
        }
    }

    public void forzarArmonia(Armonia ar) {
        arm = new Armonia(ar);
//        arm.printAcorde();
    }

    public Armonia getArm() {
        return arm;
    }

    public long getDuracion() {
        return duracion;
    }

    public void transfArm(Armonia list, Instrumento ins) {
//        cambioArmonia();
//        System.out.println("Entro 3");
        Eventos evs = new Eventos(this.canal);
//        evs.add(new Evento('x', 0, 0, 0));
        Armonia arAcRep = new Armonia(arm.getRepresentante());
//        arAcRep.printAcorde();
        Armonia arNuev = new Armonia(list);
//        arNuev.printAcorde();
        ListFloat rep = new ListFloat();
        for (int i = 0; i < arAcRep.getVoces().size(); i++) {
            rep.add(arAcRep.getVoces().get(i) % 12);
        }
        boolean cond = true;// no se para que es esto
        int idx;
        char onOff;
        float nota;
        int vel;
        long tmp;
//        System.out.println("Entro 5");
        if (arAcRep.getVoces().size() == arNuev.getVoces().size() && arAcRep.getVoces().size() == 4) {
            // Revisar cada uno de los eventos,
            // identificar su indice en el acorde 1
            // asignar su indice en el acorde 2
            // intercambiar las notas.
//            System.out.println("Entro 6 this.size() " + this.size());

            CICLO:
            for (int i = 1; i < this.size() - 1; i++) {
//                System.out.println("Entro 7 " + i);
                // Revisar si la nota igualita esta en la armonia nueva
                idx = arAcRep.getVoces().lastIndexOf(get(i).getNota());

                if (idx != -1) {
//                    System.out.println("Entro 8 nota: " + get(i).getNota() + " va a: " + arNuev.getVoces().get(idx));
                    onOff = get(i).getOnOff();

                    nota = arNuev.getVoces().get(idx);
                    vel = get(i).getVeloc();
                    tmp = get(i).getTiempo();
                    evs.add(new Evento(onOff, nota, vel, tmp));
                } else {
//                    System.out.println("Entro 9 " + i);
                    // Si no, revisar si esta modulo 12 en el rep
                    idx = rep.lastIndexOf(get(i).getNota() % 12);
                    if (idx != -1) {
                        onOff = get(i).getOnOff();
                        nota = Oper.siguiente(this.get(i).getNota(), arNuev.getVoces().get(idx) % 12);
//                        System.out.println("Entro 9 nota: " + get(i).getNota() + " va a: " + nota);
//                  System.out.println("Actual " + get(i).getNota() + " Siguiente: " + nota);
                        vel = get(i).getVeloc();
                        tmp = get(i).getTiempo();
                        evs.add(new Evento(onOff, nota, vel, tmp));

                    } else {
                        System.out.println("Eventso.transfArm Error de dimensiones ");
                        cond = false;
                        break CICLO;
//                        // Si no lo reconoce que se quede como esta
//                        onOff = get(i).getOnOff();
//                        nota = get(i).getNota();
////
//                        vel = get(i).getVeloc();
//                        tmp = get(i).getTiempo();
//                        evs.add(new Evento(onOff, nota, vel, tmp));
                    }
                }
            }

            evs.finGrabacionManual();
//            System.out.println("size this: " + this.size() + " otro: " + evs.size());
            // Creo que esto es para que se caye el anterior
            if (this.size() == evs.size() && cond) {
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i).getOnOff() == 'o') {
                        ins.noteOff(get(i).getNota());
                    }
                    this.get(i).setNota(evs.get(i).getNota());
                }
                this.setArmonia();
            }
        }

    }

    public void subirVolumen(float val) {
        // Funcion de volumen y=velAct^parametro
        for (int i = 1; i < this.size() - 1; i++) {
//            System.out.print("VelAct: " + this.get(i).getVeloc());
            this.get(i).setVeloc((int) (Math.pow(vols.get(i), val)));
//            System.out.println(" VelCamb: " + this.get(i).getVeloc());
        }
    }

    /**
     *
     */
    public static class PlayEventos implements Runnable {

        /**
         *
         */
        PApplet p;
        public Eventos evs;
        Instrumento inst;
        boolean silenc = false;
        int id = 0;
        int c = 0;
//        public boolean fiesta = false; // Permite que run comience
        public boolean salir = false;

        /**
         *
         * @param mibus
         * @param canal
         * @param events
         */
        public PlayEventos() {
        }

        /**
         *
         * @param mibus
         * @param canal
         * @param events
         */
        public PlayEventos(PApplet pa, Instrumento ins, Eventos events) {
            p = pa;
            inst = ins;
            evs = events;
            id = (int) (1000 * Math.random());
        }

        /**
         *
         */
        public void run() {

            evs.tocando = true;
//          Aqui se tocan los eventos, ya sea en forma de loop o individual.
            COSA:
            while (evs.tocando) {
                if (!evs.loop) {
                    finFin();
                }

                p.println("Loop id: " + id + " vuelta:" + (c++) + " tam: " + evs.size());
                if (salir) {
                    p.println("SALGO!!");
                    break COSA;
                }
                int suma = 0;
//                inst.silencioTotal();
                if (evs.size() > 3) {
                    long t0 = 0;
//                    long t0 = evs.get(1).getTiempo();
//                    p.println("Play: --------------");

                    for (int i = 0; i < evs.size(); i++) {
//                        p.println("Suma check: " + suma + " to: " + t0);
//                        p.println("Eventos.run");
                        if (salir) {
                            break;
                        }

//                        if (silenc) {
//                            inst.silencioTotal();
//                            silenc = false;
//                        }
                        long del = evs.get(i).getTiempo() - t0;
                        suma += del;
//                            if (del > 0) {
                        long cosa = System.currentTimeMillis();
                        try {// Si son simultaneos, dejar un milisegundo
                            Thread.sleep(del);
                        } catch (Exception e) {
                            System.out.println("Exception. eventos play delay: ");
                        }

//                            System.out.print("DELAY: " + del);
//                         x   System.out.println(" REAL: " + (System.currentTimeMillis() - cosa));

                        //                            p.println("Play: " + i + " del: " + del);
//                        System.out.println("Play Ev: " + i
//                                + " Tipo: " + evs.get(i).getOnOff()
//                                + " nota: " + evs.get(i).getNota()
//                                + " vel: " + evs.get(i).getVeloc()
//                                + " del: " + del);

//                            }

                        switch (evs.get(i).getOnOff()) {
                            case 'o':
                                if (!evs.mute) {
                                    inst.noteOnSinGrabar(evs.get(i).getNota(), evs.get(i).getVeloc());
//                                    inst.noteOn(evs.get(i).getNota(), evs.get(i).getVeloc());
                                }
                                break;
                            case 'f':
                                inst.noteOffSinGrabar(evs.get(i).getNota());
//                                inst.noteOff(evs.get(i).getNota());
                                break;
                        }
                        p.redraw();
//                        if (i > 0) {
                        t0 = evs.get(i).getTiempo();
//                        }
                    }

                } else {
                    finFin();
                }
                evs.numPlay++;
                if (!evs.loop) {
                    finFin();
                }
            }
            if (inst != null) {
                inst.silencioTotalSinGrabar();
            }

        }

//        /**
//         *
//         */
//        public void finVuelta() {
//            try {
//                evs.tocando = false;
//            } catch (Exception e) {
//            }
//            if (inst != null) {
//                inst.silencioTotalSinGrabar();
//            }
////            inst.silencio();
//        }
        /**
         *
         */
        public void finFin() {
            evs.tocando = false;
            salir = true;
//            try {
////                p.println("Eventos.finFin");
//            } catch (Exception e) {
//            }
            if (inst != null) {
                inst.silencioTotalSinGrabar();
            }
//            inst.silencio();
        }

        /**
         *
         */
        public void cambioArmonia() {
            // enviar silencio para cambiar armonia
            if (inst != null) {
                inst.silencioTotalSinGrabar();
            }

        }

        /**
         *
         */
        public void silencioLoop() {
            // enviar silencio para cambiar armonia
            silenc = true;
        }
    }
}
