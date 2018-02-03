/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import aguiListas.Eventos;
import javax.sound.midi.Track;
import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import polifoniaLib.Armonia;
import polifoniaLib.Enlace;
import polifoniaLib.Escala;
import polifoniaLib.Nota;
import polifoniaLib.listas.List1Nota;
import polifoniaLib.listas.ListArmonia;
import polifoniaLib.listas.ListEntero;
import polifoniaLib.listas.ListFloat;
import polifoniaLib.listas.List2Float;
import processing.core.PApplet;
import themidibus.MidiBus;

/**
 *
 * @author Cristian
 */
public class Instrumento {

    // Datos
    MidiBus myBus;
    OscP5 oscP5;
    public int numOsc = 4;
    OscMessage myOscMessage;
    NetAddress myRemoteLocation;
    Eventos eventos;
    public int bnkNum = 1;// 1-8
    public int instNum = 1;//1-128
    public int volumen;
    public int channel;
    float durCuartSeg;// en segundos
    public char tipo;// 'm'-midi, 'o'-osc
    float[] veloc = new float[109]; //109 Intensidad de cada nota en cada canal
    long[] durc = new long[109]; //109 Intensidad de cada nota en cada canal
    ListFloat floatsActivos = new ListFloat();
    ListEntero velActivos = new ListEntero();
//    List1Nota notasActivas = new List1Nota();
//    ListFloat notasActivasSinGrab = new ListFloat();
    ListFloat notasPedal = new ListFloat();
    Armonia actualA;
    PlayMelodia playMel = new PlayMelodia();
    PlayNota playNota = new PlayNota();
    PlayCrawler playCraw = new PlayCrawler();
    PlayTrack playTrack = new PlayTrack();
    Thread playSecTh;
    Thread playMelTh;
    Thread playNotTh;
    Thread playCrawTh;
    Thread playTrackTh;
    private boolean pedal = false;
    private boolean isGrabando = false;
    private boolean isCrawling = false;
    int rangMin = 0;
    int rangMax = 108;
    // 4* por la notacion lilypond

    /**
     * Crea una instancia del instrumento.
     *
     * @param miBus
     * @param canal
     * @param banco
     * @param numInst
     */
    public Instrumento(MidiBus miBus, float dCuart, int canal, char banco, int numInst) {
        tipo = 'm';
        myBus = miBus;
        durCuartSeg = dCuart;
        volumen = 100;
        channel = canal;
        cambioInstrumento(chartoInt(banco), numInst, volumen);
        for (int i = 0; i < veloc.length; i++) {
            veloc[i] = 0;
            durc[i] = 0;
        }
    }

    /**
     * Crea una instancia del instrumento.
     *
     * @param miBus
     * @param canal
     * @param banco
     * @param numInst
     */
    public Instrumento(MidiBus miBus, float dCuart, int canal) {
        tipo = 'm';
        myBus = miBus;
        durCuartSeg = dCuart;
        volumen = 100;
        channel = canal;
        for (int i = 0; i < veloc.length; i++) {
            veloc[i] = 0;
            durc[i] = 0;
        }
//        cambioInstrumento('A', 1);
    }

    /**
     * Crea una instancia del instrumento.
     *
     * @param miBus
     * @param canal
     * @param banco
     * @param numInst
     */
    public Instrumento(OscP5 op, float dCuart, int canal, char banco, int numInst) {
        tipo = 'o';
        oscP5 = op;
        myRemoteLocation = new NetAddress("127.0.0.1", 12001);
        durCuartSeg = dCuart;
        volumen = 100;
        channel = 0;
        bnkNum = 1;
        instNum = 1;
        for (int i = 0; i < veloc.length; i++) {
            veloc[i] = 0;
            durc[i] = 0;
        }
    }

    /**
     *
     */
    public void silencioEmergencia() {
        setPedal(false);
        switch (tipo) {
            case 'o':
                oscSendCon(5, 0);// -1, silencio TOODOS los osciladores
//                for (int i = 0; i <= 108; i++) {
//                    noteOff(i);
//                    oscSend(i, 0);
//                }
                break;
            case 'm':
                for (int i = 0; i < 109; i++) {
                    myBus.sendNoteOff(channel, (int) i, (int) veloc[(int) i]);
                }
                break;
        }
        notasPedal = new ListFloat();
        floatsActivos = new ListFloat();
    }

    /**
     *
     */
    public void silencioTotalSinGrabar() {
        setPedal(false);
        switch (tipo) {
            case 'o':
                for (int i = 0; i < floatsActivos.size(); i++) {
                    noteOffSinGrabar(floatsActivos.get(i));
                }
                break;
            case 'm':
                for (int i = 0; i < 109; i++) {
                    noteOffSinGrabar(i);
                    myBus.sendNoteOff(channel, (int) i, (int) veloc[(int) i]);
                }
                break;
        }
        notasPedal = new ListFloat();
        floatsActivos = new ListFloat();
        velActivos = new ListEntero();

    }

    public void silencioPedal() {
        pedal = false;
        for (int i = 0; i < notasPedal.size(); i++) {
            noteOff(notasPedal.get(i));
        }
        notasPedal = new ListFloat();
//        floatsActivos = new ListFloat();
//        velActivos = new ListEntero();
    }

    public void oscSendNota(float nota, int vel) {
        myOscMessage = new OscMessage("/test");
        double not = 100 * nota;
        double velo = 100 * vel;
        //(24,60)
        //(72,72)
        myOscMessage.add((int) not);
        myOscMessage.add((int) velo);
        oscP5.send(myOscMessage, myRemoteLocation);
//        System.out.println("nota: "+not+" vel: "+vel);
    }

    public void oscSendCon(float nota, float vel) {
        myOscMessage = new OscMessage("/test");
        double not = nota;
        double velo = vel * 100;
        //(24,60)
        //(72,72)
        myOscMessage.add((int) -nota);
        myOscMessage.add((int) velo);
        oscP5.send(myOscMessage, myRemoteLocation);
//        System.out.println("nota: "+not+" vel: "+vel);
    }

    public void sendIdxCM(float nota, float vel) {
        oscSendCon(nota, vel);
    }

    public void sendIdxMod(float nota, float vel) {
        oscSendCon(nota, vel);
    }

    public void playToggle(float nota, int vel) {
        if (floatsActivos.indexOf(nota) != -1) {
            // Si ya se esta tocando, que se pare.
            noteOff(nota);
        } else {
            // Si no se esta tocando, que la toque.
            noteOn(nota, vel);
        }
    }

    /**
     *
     * @param nota
     * @param vel
     */
    public void noteOn(float nota, int vel) {
//        System.out.println("ins.NOTA: " + nota + " v: " + vel + " can: " + channel);
        if (rangMin <= nota && nota <= rangMax) {
//            System.out.println("Inst.noton " + rangMin + " " + rangMax);
//            if (vel > 0 && -1 == floatsActivos.indexOf(nota)) {
            if (vel > 0) {
                if (-1 == floatsActivos.indexOf(nota)) {
                    myBus.sendNoteOn(channel, (int) nota, vel);
                    floatsActivos.add(nota);
                    velActivos.add(vel);
                    if (isGrabando) {
                        eventos.agregar('o', nota, vel);
                    }
                } else {
                    myBus.sendNoteOn(channel, (int) nota, vel);
                }
            }
//            }
        }
    }

    /**
     *
     * @param nota
     * @param vel
     */
    public void noteOnMute(float nota, int vel) {
//        System.out.println("ins.NOTA: " + nota + " v: " + vel + " can: " + channel);
        if (rangMin <= nota && nota <= rangMax) {
            if (vel > 0 && -1 == floatsActivos.indexOf(nota)) {
                floatsActivos.add(nota);
                velActivos.add(vel);
                if (isGrabando) {
                    eventos.agregar('o', nota, vel);
                }
            }
        }
    }

    /**
     *
     * @param nota
     * @param vel
     */
    public void noteOnSinGrabar(float nota, int vel) {
//        System.out.println("ins.NOTA: " + nota + " v: " + vel + " can: " + channel);
//        System.out.println("idx: " + notasActivas.indexOf(nota));
        if (rangMin <= nota && nota <= rangMax) {
//            if (vel > 0 && -1 == floatsActivos.indexOf(nota)) {
            if (vel > 0) {
                myBus.sendNoteOn(channel, (int) nota, vel);
            }
//            }
        }
    }

    /**
     *
     * @param nota
     */
    public void noteOff(float nota) {
        if (rangMin <= nota && nota <= rangMax) {
            if (pedal == false) {
                int idx = floatsActivos.indexOf(nota);
                if (-1 != idx) {
                    floatsActivos.remove(idx);
                    velActivos.remove(idx);
                }
                myBus.sendNoteOff(channel, (int) nota, (int) veloc[(int) nota]);
                if (isGrabando) {
                    eventos.agregar('f', nota, 0);
//                            eventos.add(new Evento('f', nota, 0, System.currentTimeMillis() - toGrab));
//                        System.out.println("Del grab Off: " + (System.currentTimeMillis() - toGrab - eventos.getFirst().getTiempo()));
                }

            } else {
                // Si el pedal esta presionado
                // no silenciar la nota, pero guardar que ya se solto
                // aqui tiene que ir una lista de notas que se deben
                // silenciar al soltar el pedal
//                    myBus.sendNoteOff(channel, (int) nota, (int) veloc[(int) nota]);
                notasPedal.add(nota);
                if (isGrabando) {
//                        eventos.add(new Evento('f', nota, 0, System.currentTimeMillis() - toGrab));
//                        System.out.println("Del grab Off: " + (System.currentTimeMillis() - toGrab - eventos.getFirst().getTiempo()));
                }
            }
        }
    }

    /**
     *
     * @param nota
     */
    public void noteOffSinGrabar(float nota) {
        if (rangMin <= nota && nota <= rangMax) {
            int idx = floatsActivos.indexOf(nota);
            if (-1 != idx) {
                floatsActivos.remove(idx);
                velActivos.remove(idx);
            }
            myBus.sendNoteOff(channel, (int) nota, (int) veloc[(int) nota]);
        }

    }

    /**
     *
     * @param nota
     */
    public void noteOffMute(float nota) {
        if (rangMin <= nota && nota <= rangMax) {
            if (pedal == false) {
                int idx = floatsActivos.indexOf(nota);
                if (-1 != idx) {
                    floatsActivos.remove(idx);
                    velActivos.remove(idx);
                }
                myBus.sendNoteOff(channel, (int) nota, (int) veloc[(int) nota]);
                if (isGrabando) {
                    eventos.agregar('f', nota, 0);
//                        eventos.add(new Evento('f', nota, 0, System.currentTimeMillis() - toGrab));
//                        System.out.println("Del grab Off: " + (System.currentTimeMillis() - toGrab - eventos.getFirst().getTiempo()));
                }

            } else {
                // Si el pedal esta presionado
                // no silenciar la nota, pero guardar que ya se solto
                // aqui tiene que ir una lista de notas que se deben
                // silenciar al soltar el pedal
//                    myBus.sendNoteOff(channel, (int) nota, (int) veloc[(int) nota]);
                notasPedal.add(nota);
                if (isGrabando) {
//                        eventos.add(new Evento('f', nota, 0, System.currentTimeMillis() - toGrab));
//                        System.out.println("Del grab Off: " + (System.currentTimeMillis() - toGrab - eventos.getFirst().getTiempo()));
                }

            }
        }
    }
    // Cuidado que se grabe SOOOLO una nota a la vez

    public void grabarOn() {
        if (isGrabando == false) {
            eventos = new Eventos(channel);
            isGrabando = true;
//            System.out.println("Grabar on, canal: " + channel);
        }
    }

    public Eventos grabarOff() {
        silencioEmergencia();
        eventos.finGrabacionLimpia();
        isGrabando = false;
//        System.out.println("Grabar off: " + channel);
        return eventos;
    }

    public void playNotaTh(Nota not, long inic) {
        playNota = new PlayNota(this, not, inic);
        playNotTh = new Thread(playNota);
        playNotTh.start();
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playCrawlerTh(Escala es) {
        if (playCraw.isFiesta() == false) {
            playCraw = new PlayCrawler(this, es);
            playCrawTh = new Thread(playCraw);
            playCrawTh.start();
        }
    }

    public void playCantus(List1Nota sec) {
        for (int i = 0; i < sec.size(); i++) {
            playNota(sec.get(i));
//            System.out.println("Dur: " + durCuartSeg * sec.getDurs().get(i));
        }
    //        System.out.println("OUT Instrumento playNota: " + nota + " Volumen: " + vel + " DuracionMs: " + durMs);
    }

    public void playCantusTh(List1Nota sec) {
        //if (playMel.isFiesta() == false) {
        playMel = new PlayMelodia(this, sec);
        playMelTh = new Thread(playMel);
        playMelTh.start();
    //}
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playNota(Nota not) {
        float nota = not.getNotaNum();
        int vel = not.getVel();
        float seg = not.getDurFig();
        int dur = (int) (not.getDurFig() * durCuartSeg * 1000);
        System.out.println("Durcuart: " + durCuartSeg + " dur: " + dur);

        if (nota >= 0) {// si no es fin
            noteOn(nota, vel);
        }
        try {
            Thread.sleep(dur);

        } catch (Exception e) {
        }
        noteOff(nota);
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playListNotaeg(Nota not) {
        float nota = not.getNotaNum();
        int vel = not.getVel();
        float seg = not.getDurFig();
        //int nota, int vel, float seg) {
//        seg = seg * 1000;
        int dur = (int) seg;

        if (nota >= 0) {// si no es fin
            noteOn(nota, vel);
        }

        try {
            Thread.sleep(dur);
        } catch (Exception e) {
        }
        noteOff(nota);
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playAcorde(Armonia ac, int velocidad, double seg) {
        double durMs = seg * 1000;
        int vel = velocidad;
//        System.out.println("Cant dur: " + durCuartSeg);
        int dur = (int) (durCuartSeg * durMs);
        for (int i = 0; i < ac.getVoces().size(); i++) {
            noteOn(ac.getVoces().get(i), vel); // Send a Midi noteOn
        }
        try {
            Thread.sleep(dur);
        } catch (Exception e) {
        }
        for (int i = 0; i < ac.getVoces().size(); i++) {
            noteOff(ac.getVoces().get(i));
        }
        this.silencioEmergencia();
//        System.out.println("OUT Instrumento playNota: " + nota + " Volumen: " + vel + " DuracionMs: " + durMs);
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playAcordeFloat(ListFloat ac, int velocidad, double seg) {
        double durMs = seg * 1000;
        int vel = velocidad;
//        System.out.println("Cant dur: " + durCuartSeg);
        int dur = (int) (durCuartSeg * durMs);
        for (int i = 0; i < ac.size(); i++) {
            noteOn(ac.get(i), vel); // Send a Midi noteOn
        }
        try {
            Thread.sleep(dur);
        } catch (Exception e) {
        }
        for (int i = 0; i < ac.size(); i++) {
            noteOff(ac.get(i));
        }
//        System.out.println("OUT Instrumento playNota: " + nota + " Volumen: " + vel + " DuracionMs: " + durMs);
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playProg(Enlace en, double seg) {
        playAcorde(en.getAr1(), 100, seg);
        playAcorde(en.getAr2(), 100, seg);
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playSecuencia(ListArmonia sec, double durSeg) {
        for (int i = 0; i < sec.size(); i++) {
            playAcorde(sec.get(i), 50, durSeg);
        }
//        System.out.println("OUT Instrumento playNota: " + nota + " Volumen: " + vel + " DuracionMs: " + durMs);
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playSecuenciaFloats(List2Float sec, double durSeg) {
        ListArmonia lis = new ListArmonia();
        for (int i = 0; i < sec.size(); i++) {
            lis.add(new Armonia(sec.get(i)));
        }
        for (int i = 0; i < lis.size(); i++) {
            playAcorde(lis.get(i), 100, durSeg);
        }
//        System.out.println("OUT Instrumento playNota: " + nota + " Volumen: " + vel + " DuracionMs: " + durMs);
    }

    public void playMelodia(List1Nota sec) {
        for (int i = 0; i < sec.size(); i++) {
            playNota(sec.get(i));
//            System.out.println("Dur: " + durCuartSeg * sec.getDurs().get(i));
        }
    //        System.out.println("OUT Instrumento playNota: " + nota + " Volumen: " + vel + " DuracionMs: " + durMs);
    }

    public void playMelodiaTh(List1Nota sec) {
        //if (playMel.isFiesta() == false) {
        playMel = new PlayMelodia(this, sec);
        playMelTh = new Thread(playMel);
        playMelTh.start();
    //}
    }

    public void playTrackTh(String nom, Track trk, PApplet p) {
        //if (playMel.isFiesta() == false) {
        playTrack = new PlayTrack(this, trk, p, nom);
        playTrackTh = new Thread(playTrack);
        playTrackTh.start();
    //}
    }

    public void playEvents(Eventos ev) {
    }

    /**
     *
     * @param durMs
     */
    public void playSilencio(double durMs) {
        durMs = durMs * 1000;
        int dur = (int) durMs;
        try {
            Thread.sleep(dur);
        } catch (Exception e) {
        }
//        System.out.println("OUT Instrumento fin: " + durMs);
    }

    /**
     *
     */
    public void anteriorInst() {
        if (instNum > 1) {
            instNum = instNum - 1;
        } else {
            if (bnkNum > 1) {
                instNum = 128;
                bnkNum = bnkNum - 1;
            } else {
                instNum = 128;
                bnkNum = 8;
            }
        }
        cambioInstrumento(bnkNum, instNum, volumen);
    }

    /**
     *
     */
    public void siguienteInst() {
        if (instNum < 128) {
            instNum = instNum + 1;
        } else {
            if (bnkNum < 8) {
                instNum = 1;
                bnkNum++;
            } else {
                instNum = 1;
                bnkNum = 1;
            }
        }
        cambioInstrumento(bnkNum, instNum, volumen);
    }

    public void cambioVolumen(int vol) {
        volumen = vol;
        myBus.sendControllerChange(channel, 7, vol);
        System.out.println("Cambio de volumen en canal: " + channel + "; a volumen: " + vol);
    }

//    public void silencioMidi() {
//        myBus.sendControllerChange(channel, 120, 0);
//        System.out.println("Silencio en canal: " + channel);
//    }
    public void cambioPan(int pan) {
        myBus.sendControllerChange(channel, 10, pan);
//        System.out.println("Cambio de pan en canal: " + channel + "; a pan: " + pan);
    }

    public void cambioMod(int mod) {
        myBus.sendControllerChange(channel, 43, mod);
        System.out.println("Cambio de pan en mod: " + channel + "; a mod: " + mod);
    }

    /**
     * El bank va de 1 en adelante!
     * @param bank
     * @param instrumento
     */
    public void cambioInstrumento(int bank, int instrumento, int vol) {
        if (tipo == 'm') {
            bnkNum = bank;
            instNum = instrumento;

            bank = bank - 1;
            instrumento = instrumento - 1;

            silencioTotalSinGrabar();
            int status_byte = 0xC0;
            // This is the status byte for a program change

            switch (channel) {
                case 0:
                    status_byte = 0xC0;
                    break;
                case 1:
                    status_byte = 0xC1;
                    break;
                case 2:
                    status_byte = 0xC2;
                    break;
                case 3:
                    status_byte = 0xC3;
                    break;
                case 4:
                    status_byte = 0xC4;
                    break;
                case 5:
                    status_byte = 0xC5;
                    break;
                case 6:
                    status_byte = 0xC6;
                    break;
                case 7:
                    status_byte = 0xC7;
                    break;
                case 8:
                    status_byte = 0xC8;
                    break;
                case 9:
                    status_byte = 0xC9;
                    break;
                case 10:
                    status_byte = 0xCA;
                    break;
                case 11:
                    status_byte = 0xCB;
                    break;
                case 12:
                    status_byte = 0xCC;
                    break;
                case 13:
                    status_byte = 0xCD;
                    break;
                case 14:
                    status_byte = 0xCE;
                    break;
                case 15:
                    status_byte = 0xCF;
                    break;
            }
            // This will be the preset you are sending with your program change
            int byte1 = instrumento;
            // This is not used for program change so ignore it and set it to 0
            int byte2 = 0;
            //Send the custom message
            myBus.sendControllerChange(channel, 0, bank); //0 es el control number
            myBus.sendMessage(status_byte, channel, byte1, byte2);
            cambioVolumen(vol);
            System.out.println("Cambio en canal: " + channel + "; a banco: " + intToChar(bnkNum) + " inst: " + instNum);
        }
    }

    /**
     *
     * @param bank
     * @param instrumento
     */
    public void cambioInstrumento(char cbank, int instrumento) {
        if (tipo == 'm') {
            bnkNum = this.chartoInt(cbank);
            instNum = instrumento;
            int bank = bnkNum;
            bank = bank - 1;
            instrumento = instrumento - 1;

            silencioTotalSinGrabar();
            int status_byte = 0xC0;
            // This is the status byte for a program change

            switch (channel) {
                case 0:
                    status_byte = 0xC0;
                    break;
                case 1:
                    status_byte = 0xC1;
                    break;
                case 2:
                    status_byte = 0xC2;
                    break;
                case 3:
                    status_byte = 0xC3;
                    break;
                case 4:
                    status_byte = 0xC4;
                    break;
                case 5:
                    status_byte = 0xC5;
                    break;
                case 6:
                    status_byte = 0xC6;
                    break;
                case 7:
                    status_byte = 0xC7;
                    break;
                case 8:
                    status_byte = 0xC8;
                    break;
                case 9:
                    status_byte = 0xC9;
                    break;
                case 10:
                    status_byte = 0xCA;
                    break;
                case 11:
                    status_byte = 0xCB;
                    break;
                case 12:
                    status_byte = 0xCC;
                    break;
                case 13:
                    status_byte = 0xCD;
                    break;
                case 14:
                    status_byte = 0xCE;
                    break;
                case 15:
                    status_byte = 0xCF;
                    break;
            }
            // This will be the preset you are sending with your program change
            int byte1 = instrumento;
            // This is not used for program change so ignore it and set it to 0
            int byte2 = 0;
            //Send the custom message
            myBus.sendControllerChange(channel, 0, bank); //0 es el control number
            myBus.sendMessage(status_byte, channel, byte1, byte2);
            System.out.println("Cambio en canal: " + channel + "; a banco: " + intToChar(bnkNum) + " inst: " + instNum);
        }
    }

    public boolean isIsGrabando() {
        return isGrabando;
    }

    public int getRangMax() {
        return rangMax;
    }

    public int getRangMin() {
        return rangMin;
    }

    public void setRang(int min, int max) {
        rangMin = min;
        rangMax = max;
    }

    public void resetRang() {
        rangMin = 0;
        rangMax = 108;
    }

    /**
     *
     * @param nota
     * @return
     */
    public int getVeloc(int nota) {
        return (int) veloc[nota];
    }

    /**
     *
     * @return
     */
    public int getBnk() {
        return bnkNum;
    }

    /**
     *
     * @param bnk
     */
    public void setBnk(int bnk) {
        this.bnkNum = bnk;
    }

    /**
     * 1-16
     * @return
     */
    public int getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     */
    public void setChannel(int channel) {
        this.channel = channel;
    }

    /**
     *
     * @return
     */
    public int getInstNum() {
        return instNum;
    }

    public int getVolumen() {
        return volumen;
    }

    /**
     *
     * @param instNum
     */
    public void setInstNum(int instNum) {
        this.instNum = instNum;
    }

    public boolean isPedal() {
        return pedal;
    }

    public void setPedal(boolean pedal) {
        this.pedal = pedal;
    }

    public void presionaPedal() {
        setPedal(true);
    }

    /**
     *
     * @param bnk
     * @return
     */
    public int chartoInt(char bnk) {
        int bnkN = 1;
        switch (bnk) {
            case 'A':
                bnkN = 1;
                break;
            case 'B':
                bnkN = 2;
                break;
            case 'C':
                bnkN = 3;
                break;
            case 'D':
                bnkN = 4;
                break;
            case 'E':
                bnkN = 5;
                break;
            case 'F':
                bnkN = 6;
                break;
            case 'G':
                bnkN = 7;
                break;
            case 'H':
                bnkN = 8;
                break;
            case 'a':
                bnkN = 1;
                break;
            case 'b':
                bnkN = 2;
                break;
            case 'c':
                bnkN = 3;
                break;
            case 'd':
                bnkN = 4;
                break;
            case 'e':
                bnkN = 5;
                break;
            case 'f':
                bnkN = 6;
                break;
            case 'g':
                bnkN = 7;
                break;
            case 'h':
                bnkN = 8;
                break;
        }
        return bnkN;
    }

    /**
     *
     * @param bnkN
     * @return
     */
    public char intToChar(int bnkN) {
        char bnk = 'A';
        switch (bnkN) {
            case 1:
                bnk = 'A';
                break;
            case 2:
                bnk = 'B';
                break;
            case 3:
                bnk = 'C';
                break;
            case 4:
                bnk = 'D';
                break;
            case 5:
                bnk = 'E';
                break;
            case 6:
                bnk = 'F';
                break;
            case 7:
                bnk = 'G';
                break;
            case 8:
                bnk = 'H';
                break;
        }
        return bnk;
    }

    public ListFloat getFloatsActivos() {
        return floatsActivos;
    }

    public ListEntero getVelActivos() {
        return velActivos;
    }

    /**
     *
     * @return
     */
    public Armonia getArmonia() {
        actualA = new Armonia(getFloatsActivos());
        return actualA;
    }

    /**
     *
     */
    public static class PlayMelodia implements Runnable {

        public Instrumento inst;
        List1Nota comp;
        boolean fiesta = false;

        /**
         *
         * @param inst1
         * @param ritmo
         */
        public PlayMelodia(Instrumento inst1, List1Nota acs) {
            //System.out.println(beat);
            inst = inst1;
            comp = acs;
        }

        public PlayMelodia() {
        }

        /**
         *
         */
        public void run() {
            for (int i = 0; i < comp.size(); i++) {
                inst.playNota(comp.get(i));
            }
        }

        /**
         *
         */
        public void fin() {
        }

        public boolean isFiesta() {
            return fiesta;
        }

        public void setFiesta(boolean fiesta) {
            this.fiesta = fiesta;
        }
    }

    /**
     *
     */
    public static class PlayNota implements Runnable {

        public Instrumento inst;
        Nota nota;
        long inic;

        /**
         *
         * @param inst1
         * @param ritmo
         */
        public PlayNota(Instrumento inst1, Nota not, long ini) {
            //System.out.println(beat);
            inst = inst1;
            nota = not;
            inic = ini;
        }

        public PlayNota() {
        }

        /**
         *
         */
        public void run() {
            try {
                Thread.sleep(inic);
            } catch (Exception e) {
            }
            inst.playNota(nota);
        }
    }

    /**
     *
     */
    public static class PlayCrawler implements Runnable {

        public Escala esc;
        public Instrumento inst;
        boolean fiesta = false;

        /**
         *
         * @param inst1
         * @param ritmo
         */
        public PlayCrawler(Instrumento inst1, Escala es) {
            //System.out.println(beat);
            inst = inst1;
            esc = es;
        }

        public PlayCrawler() {
        }

        /**
         *
         */
        public void run() {
            fiesta = true;
            inst.isCrawling = true;
            while (fiesta) {
                for (int i = 0; i < 10; i++) {
                    int not = (int) (7 * Math.random());
                    int vel = (int) (80 * Math.random() + 40);
                    float[] dur = {1, 0.5f, 0.5f, 0.5f, 0.25f};
                    int ind = (int) (dur.length * Math.random());
                    inst.playNota(new Nota(esc.get(not) + 60, vel, dur[ind]));
                }
                inst.siguienteInst();
                if (!inst.isCrawling) {
                    fiesta = false;
                }
            }
        }

        /**
         *
         */
        public void fin() {
        }

        public boolean isFiesta() {
            return fiesta;
        }

        public void setFiesta(boolean fiesta) {
            this.fiesta = fiesta;
        }
    }

    /**
     *
     */
    public static class PlayTrack implements Runnable {

        public Instrumento inst;
        Track trk;
        long inic;
        PApplet p;
        int cont = 0;
        int color;
        String nm;
        long to;

        /**
         *
         * @param inst1
         * @param ritmo
         */
        public PlayTrack(Instrumento inst1, Track tr, PApplet pa, String nom) {
            //System.out.println(beat);
            inst = inst1;
            trk = tr;
            p = pa;
            nm = nom;
            color = (int) p.random(0, 255);
        }

        public PlayTrack() {
        }

        /**
         *
         */
        public void run() {
            byte[] bytes;
            to = System.currentTimeMillis();
            long tpas = 0, tick = 0;

            for (int i = 0; i < trk.size(); i++) {
                bytes = trk.get(i).getMessage().getMessage();
                tick = trk.get(i).getTick() - tpas;
                tpas = trk.get(i).getTick();

                message(bytes, tick);
            }
        }

        public void message(byte[] bts, long tick) {
            int nm = (int) (bts[0] & 0xFF);
            if (nm >= 128 & nm <= 143) {// note Off
                int nota = (int) bts[1];
                try {
                    Thread.sleep(4 * tick);
                } catch (Exception e) {
                }
                inst.noteOff(nota);
                cont++;
            } else if (144 <= nm & nm <= 159) {
                int nota = (int) bts[1];
                int vel = bts[2];
                // Paint
                int notaH = 300 - (int) p.map(nota, 0, 127, 0, 300);
//                int velH = (int) p.map(vel, 0, 127, 0, 255);
                p.colorMode(p.HSB, 255);
//                p.fill(velH);
                p.fill(color);
                p.ellipse(cont * 10, notaH, 10, 10);
                p.colorMode(p.RGB, 255);
                cont++;
                ////////
                try {
                    Thread.sleep(4 * tick);
                } catch (Exception e) {
                }
                if (vel == 0) {
                    inst.noteOff(nota);
                } else {
                    inst.noteOn(nota, 100);
                    System.out.println(nm + " tiempo: " + ((System.currentTimeMillis() - to) / 1000));
                }
            }
            p.rect(0, 0, 200, 200);
            p.fill(255);
            String str = "";
            try {
                str = inst.getArmonia().stringAcorde('s') + "\n";
                str += inst.getArmonia().getFundStr('s') + "\n";
                str += inst.getArmonia().getTipoAcStr();
            } catch (Exception e) {
            }
            p.text(str, 20, 20);
            p.redraw();

            if (cont * 10 > p.width) {
                p.background(255);
                cont = 0;
            }
            p.redraw();
        }
    }
}
