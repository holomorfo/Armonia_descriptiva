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
public class PianoQwerty {

    PApplet p;
    Orquestacion orq;
    int octavaNum = 0; // Octava donde se trabajan las notasMel
    int intens = 100;// La intensidad de la nota actual
//    public boolean pedalVF = false;

    /**
     *
     * @param miBus
     * @param instr
     */
    public PianoQwerty(PApplet pa, Orquestacion ins) {
        p = pa;
        orq = ins;
    }

    public void actualizarInst(Orquestacion ins) {
        orq = ins;
    }

    /**
     *
     * @param key
     * @param keyCode
     * @param intensidad
     */
    public void presiona(int intensidad) {
        intens = intensidad;
        switch (p.keyCode) {// Pedal sus
            case 32:// Pedal
                orq.gMnInst().presionaPedal();
                break;
        }

        switch (p.keyCode) {// Pedal sus
            case 38://Arriba Cambio inst 1 siguiente
                orq.gMnInst().silencioTotalSinGrabar();
                orq.gMnInst().siguienteInst();
                break;
            case 40://Abajo Cambio inst 1 anterior
                orq.gMnInst().silencioTotalSinGrabar();
                orq.gMnInst().anteriorInst();
                break;
        }

        switch (p.key) {
            // Barra Notas sostenidos 1-0
            case '[': // octavaNum abajo
                //   inst.silencio();
                if (octavaNum > -5) {
                    octavaNum = octavaNum - 1;
                }
                break;
            case ']': // octavaNum abajo
                //     // octavaNum arriba
                //  inst.silencio();
                if (octavaNum < 4) {
                    octavaNum = octavaNum + 1;
                }
                break;
            case '-':// Anterior instr
                orq.gMnInst().silencioTotalSinGrabar();
                orq.gMnInst().anteriorInst();
                break;
            case '=':// Siguiente instr
                orq.gMnInst().silencioTotalSinGrabar();
                orq.gMnInst().siguienteInst();
                break;

            case '1':
                break;
            case '2':
                noteOnCanMel(49 + 12 * octavaNum);
                break;
            case '3':
                noteOnCanMel(51 + 12 * octavaNum);
                break;
            case '4':
                break;
            case '5':
                noteOnCanMel(54 + 12 * octavaNum);
                break;
            case '6':
                noteOnCanMel(56 + 12 * octavaNum);
                break;
            case '7':
                noteOnCanMel(58 + 12 * octavaNum);
                break;
            case '8':
                break;
            case '9':
                noteOnCanMel(61 + 12 * octavaNum);
                break;
            case '0':
                noteOnCanMel(63 + 12 * octavaNum);
                break;

            case 'q':
                noteOnCanMel(48 + 12 * octavaNum);
                break;
            case 'w':
                noteOnCanMel(50 + 12 * octavaNum);
                break;
            case 'e':
                noteOnCanMel(52 + 12 * octavaNum);
                break;
            case 'r':
                noteOnCanMel(53 + 12 * octavaNum);
                break;
            case 't':
                noteOnCanMel(55 + 12 * octavaNum);
                break;
            case 'y':
                noteOnCanMel(57 + 12 * octavaNum);
                break;
            case 'u':
                noteOnCanMel(59 + 12 * octavaNum);
                break;
            case 'i':
                noteOnCanMel(60 + 12 * octavaNum);
                break;
            case 'o':
                noteOnCanMel(62 + 12 * octavaNum);
                break;
            case 'p':
                noteOnCanMel(64 + 12 * octavaNum);
                break;

            case 'a':
                break;
            case 's':
                noteOnCanMel(61 + 12 * octavaNum);
                break;
            case 'd':
                noteOnCanMel(63 + 12 * octavaNum);
                break;
            case 'f':

                break;
            case 'g':
                noteOnCanMel(66 + 12 * octavaNum);
                break;
            case 'h':
                noteOnCanMel(68 + 12 * octavaNum);
                break;
            case 'j':
                noteOnCanMel(70 + 12 * octavaNum);
                break;
            case 'k':
                break;
            case 'l':
                noteOnCanMel(73 + 12 * octavaNum);
                break;
            case ';':
                noteOnCanMel(75 + 12 * octavaNum);
                break;

            case 'z':
                noteOnCanMel(60 + 12 * octavaNum);
                break;
            case 'x':
                noteOnCanMel(62 + 12 * octavaNum);
                break;
            case 'c':
                noteOnCanMel(64 + 12 * octavaNum);
                break;
            case 'v':
                noteOnCanMel(65 + 12 * octavaNum);
                break;
            case 'b':
                noteOnCanMel(67 + 12 * octavaNum);
                break;
            case 'n':
                noteOnCanMel(69 + 12 * octavaNum);
                break;
            case 'm':
                noteOnCanMel(71 + 12 * octavaNum);
                break;
            case ',':
                noteOnCanMel(72 + 12 * octavaNum);
                break;
            case '.':
                noteOnCanMel(74 + 12 * octavaNum);
                break;
            case '/':
                noteOnCanMel(76 + 12 * octavaNum);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param key
     * @param keyCode
     */
    public void libera() {
        switch (p.keyCode) {// Silencia al liberar el pedal
            case 32:// barra
                orq.gMnInst().silencioPedal();
                ;
                break;
        }
        switch (p.key) {
            case '1':
                break;
            case '2':
                noteOffCanMel(49 + 12 * octavaNum);
                break;
            case '3':
                noteOffCanMel(51 + 12 * octavaNum);
                break;
            case '4':
                break;
            case '5':
                noteOffCanMel(54 + 12 * octavaNum);
                break;
            case '6':
                noteOffCanMel(56 + 12 * octavaNum);
                break;
            case '7':
                noteOffCanMel(58 + 12 * octavaNum);
                break;
            case '8':
                break;
            case '9':
                noteOffCanMel(61 + 12 * octavaNum);
                break;
            case '0':
                noteOffCanMel(63 + 12 * octavaNum);
                break;

            case 'q':
                noteOffCanMel(48 + 12 * octavaNum);
                break;
            case 'w':
                noteOffCanMel(50 + 12 * octavaNum);
                break;
            case 'e':
                noteOffCanMel(52 + 12 * octavaNum);
                break;
            case 'r':
                noteOffCanMel(53 + 12 * octavaNum);
                break;
            case 't':
                noteOffCanMel(55 + 12 * octavaNum);
                break;
            case 'y':
                noteOffCanMel(57 + 12 * octavaNum);
                break;
            case 'u':
                noteOffCanMel(59 + 12 * octavaNum);
                break;
            case 'i':
                noteOffCanMel(60 + 12 * octavaNum);
                break;
            case 'o':
                noteOffCanMel(62 + 12 * octavaNum);
                break;
            case 'p':
                noteOffCanMel(64 + 12 * octavaNum);
                break;

            case 'a':
                break;
            case 's':
                noteOffCanMel(61 + 12 * octavaNum);
                break;
            case 'd':
                noteOffCanMel(63 + 12 * octavaNum);
                break;
            case 'f':

                break;
            case 'g':
                noteOffCanMel(66 + 12 * octavaNum);
                break;
            case 'h':
                noteOffCanMel(68 + 12 * octavaNum);
                break;
            case 'j':
                noteOffCanMel(70 + 12 * octavaNum);
                break;
            case 'k':
                break;
            case 'l':
                noteOffCanMel(73 + 12 * octavaNum);
                break;
            case ';':
                noteOffCanMel(75 + 12 * octavaNum);
                break;

            case 'z':
                noteOffCanMel(60 + 12 * octavaNum);
                break;
            case 'x':
                noteOffCanMel(62 + 12 * octavaNum);
                break;
            case 'c':
                noteOffCanMel(64 + 12 * octavaNum);
                break;
            case 'v':
                noteOffCanMel(65 + 12 * octavaNum);
                break;
            case 'b':
                noteOffCanMel(67 + 12 * octavaNum);
                break;
            case 'n':
                noteOffCanMel(69 + 12 * octavaNum);
                break;
            case 'm':
                noteOffCanMel(71 + 12 * octavaNum);
                break;
            case ',':
                noteOffCanMel(72 + 12 * octavaNum);
                break;
            case '.':
                noteOffCanMel(74 + 12 * octavaNum);
                break;
            case '/':
                noteOffCanMel(76 + 12 * octavaNum);
                break;
            default:
                break;

        }
    }

    public void noteOnCanMel(int i) {
        // Toca la nota
        // Si ya esta tocando, no hace nada
        orq.noteOn(i, intens);
    }

    public void noteOnCanMel(int i, int vel) {
        // Toca la nota
        orq.noteOn(i, vel);
    }

    public void noteOffCanMel(int i) {
        orq.noteOff(i);

    }
}
