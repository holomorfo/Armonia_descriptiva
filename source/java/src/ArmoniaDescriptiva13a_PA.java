
import agui.BancoColoresNFonts;
import Armonia.CirculoCromatico;
import Armonia.Datos;
import Armonia.Pentagrama;
import agui.PianoGUI;
import agui.PianoQwerty;
import javax.swing.JOptionPane;
import processing.core.PApplet;
import processing.core.PFont;
import themidibus.MidiBus;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Cristian
 */
public class ArmoniaDescriptiva13a_PA extends PApplet {

    MidiBus myBusPn;
    int wid, hei;
    long tiempoOff = 0;
    boolean sustain = false;
    boolean tecladoMicroT = true;
    boolean setNotInic = true;
    PianoQwerty teclado; // para tocar el piano con el tec de la compu
    BancoColoresNFonts paleta; // colores y tipos de letras estandard
    public Pentagrama pent;
    public CirculoCromatico circ;
    public Datos datos;
    public PianoGUI pianoGui;

    public ArmoniaDescriptiva13a_PA(int w, int h) {
        wid = w;
        hei = h;
    }

    /**
     *
     */
    public void setup() {
        println(PFont.list());
        background(255);
        smooth();
        midiGUI();
        datos = new Datos(this, myBusPn, wid, hei - 40);
        datos.getUniv().setPosition(0, 0);
        datos.getUniv().setEscalaAct(0, 'M');
        pianoGui = new PianoGUI(this, datos,0, 100);
        teclado = new PianoQwerty(this, datos.getOrq());
        pent = new Pentagrama(this, datos, 15, 100, 300);
        //circ = new CirculoCromatico(this, datos, 860, 220, 300);
        circ = new CirculoCromatico(this, datos, 165, 500, 280);
        
        noLoop();
    }

    /**
     *
     */
    @Override
    public void draw() {
        println(mouseX+", "+mouseY);
        background(255);
        if (System.currentTimeMillis() - tiempoOff >= 3000) {
            noLoop();
        }
        strokeWeight(3);
        line(350, 0, 350 , height);
        strokeWeight(1);

        datos.getUniv().paint();
        pent.paint();
        circ.actualizarNotasPiano(datos.getOrq().getFloatActivos());
        circ.paint();
        
        //pianoGui.paint();

        // Pinta los diagramas de las coordenadas tonales
//
////        orq.paint();
//
//        // Geometria
//        datos.getUniv().coordenadaTonal(orq.getFloatActivos());
    }

    @Override
    public void mousePressed() {
        datos.getUniv().mousePressed();
        //pianoGui.mousePres();
        redraw();
    }

    public void mouseMoved() {
        redraw();
    }

    public void mouseDragged() {
        redraw();
    }

    public void keyPressed() {
//        println(keyCode);
        teclado.presiona(100);
        switch (keyCode) {
            case 32:// Barra
                presionaPedalPrincipal();
                break;
            case 8:// Backspace
                break;
            case 10:// Enter, play
                // Grabar acorde actual
                break;
            case 16://shift
//                orq.toggleVisible(); // Visualizar instrumentos.
                break;
            case 18://alt optn
                break;
        }
        switch (key) {
            case '`':
                break;
            case '1':
                break;
            case 'g':
                break;
            case '/':
                break;
            case 'm':
                break;
        }
        redraw();
    }

    public void keyReleased() {
        switch (keyCode) {
            case 32:// Barra
                sueltaPedalPrincipal();
                break;
        }
        teclado.libera();
        redraw();
    }

    public void sueltaPedalPrincipal() {
        datos.getOrq().gMnInst().setPedal(false);
        datos.getOrq().gMnInst().silencioPedal();
        redraw();
    }

    public void presionaPedalPrincipal() {
        datos.getOrq().gMnInst().setPedal(true);
    }
    //-------------------------------------------------------------
    // Funciones q  ue llama el dispositivo MIDI
    //-------------------------------------------------------------

    public void noteOn(int channel, int pitch, int velocity, String bus_name) {
//        println("IN Piano noteOn: " + channel + " Nota: " + pitch + " Vel: " + velocity + " Bus: " + bus_name);
        if (!looping) {// Para ahorrar energia
            loop();
        }
//        datos.getOrq().noteOnMute(pitch, velocity);
//        orq.noteOn(pitch, (int) map(velocity, 0, 128, 100, 128));
        datos.getOrq().noteOn(pitch, velocity);
        redraw();
    }

    public void noteOff(int channel, int pitch, int velocity, String bus_name) {
//        println("IN Piano noteOff: " + channel + " Nota: " + pitch + " Vel: " + velocity);
        tiempoOff = System.currentTimeMillis();
        datos.getOrq().gMnInst().noteOffMute(pitch);
//        datos.getOrq().noteOff(pitch);
        redraw();
    }

    /**
     *
     * @param channel
     * @param number
     * @param valuecontrol
     * @param bus_name
     */
    public void controllerChange(int channel, int number, int value, String bus_name) {
//        println("IN Teclado Controller Change:" + channel + " Num: " + number + " Val: " + value);
        switch (number) {
            case 64: // pedal der
                switch (value) {
                    case 0: // Suelta pedal
                        // Terminar de grabar INSTRUMENTO poner.
                        sueltaPedalPrincipal();
//                        println("Suelta Pedal");
                        break;
                    default: // Presiona pedal
                        // Comenzar a grabar Instrumento PONER
                        presionaPedalPrincipal();
                        break;
                }
                break;
            case 91:// Circulito 1
                break;
            case 93:// Circulito 2
                break;
            case 1:// modulation wheel (volumen principal sint)
                break;
            case 7:// Volumen Main
                datos.getOrq().gMnInst().cambioVolumen(value);
                break;
            case 97:// boton 1 piano rojo
                break;
            case 96:// boton 2 piano rojo
                break;
            case 66:// boton 3 piano rojo
                break;
            case 67: // pedal izq
                switch (value) {
                    case 0: // Suelta pedal
                        // Agregar armonia
                        break;
                    default: // Presiona pedal
                        break;
                }
                break;
            default:
                break;
        }
        redraw();
    }

    public void midi(int inP, int outP) {
        setNotInic = true;
        MidiBus.list();
        try {
            myBusPn.clearAll();
        } catch (Exception e) {
        }
        myBusPn = new MidiBus(this, inP, outP);
        // Sint
//        orq = new Orquestacion(this, myBusPn, 150, 300, 550);
//        teclado = new PianoQwerty(this, orq);
        redraw();
        println("In: " + inP + ", Out: " + outP);
    }

    public void midiGUI() {
        MidiBus.list();
        try {
            myBusPn.clearAll();
        } catch (Exception e) {
        }
        int midiIn = MidiBus.returnList().length - 2;
        int midiOut = MidiBus.returnList().length - 1;
        String mdCosa = "";
        for (int i = 0; i < MidiBus.returnList().length; i++) {
            mdCosa += "[" + i + "] " + MidiBus.returnList()[i][0] + " " + MidiBus.returnList()[i][1] + "\n";
        }
        String ans = JOptionPane.showInputDialog(null, "Piano In:\n" + mdCosa);
        try {
          
            midiIn = Integer.parseInt(ans);
        } catch (Exception e) {
        }

        ans = JOptionPane.showInputDialog(null, "Piano Out:\n" + mdCosa);
        try {
            midiOut = Integer.parseInt(ans);
        } catch (Exception e) {
        }
        //println("In: " + midiIn + " Out: " + midiOut);
        myBusPn = new MidiBus(this, 9, 11);
        
        myBusPn = new MidiBus(this, midiIn, midiOut);
        redraw();
    }
}
