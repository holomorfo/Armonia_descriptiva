/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.defs;

import polifoniaLib.Escala;

/**
 *
 * @author Cristian
 */
public class Oper {

    public static float siguiente(float nota, float clase) {
        // Dada una nota midi, y una clase de notas..
        // regresa la nota de la clase mas cercana a la origninal
        int i = 0;
        int k = 0;
        while (((nota + i) % 12) != clase % 12) {
            i++;
        }
        while (((nota + k) % 12) != clase % 12) {
            k--;
        }
        float reg = 0;
        if (Math.abs(i) < Math.abs(k)) {
            reg = i;
        } else {
            reg = k;
        }
        return nota + reg;
    }

    /**
     * Funcion modulo como la de matlab
     *
     * @param num
     * @param base
     * @return
     */
    static public float mod(float num, float base) {
        num = num % base;
        if (num < 0) {
            num = base + num;
        }
        return num;
    }

    public static String numRomano(int num) {
        String nota = "";
        switch (num) {
            case 1:
                nota += "I";
                break;
            case 2:
                nota += "II";
                break;
            case 3:
                nota += "III";
                break;
            case 4:
                nota += "IV";
                break;
            case 5:
                nota += "V";
                break;
            case 6:
                nota += "VI";
                break;
            case 7:
                nota += "VII";
                break;
            default:
                nota += "x";
                break;
        }
        return nota;
    }

    /**
     * Funcion que le asigna un numero a cada nota de la escala, utilizando
     * sostenidos. Consiste de siete octavas.
     * Debe poner bemoles o sostenidos de acuerdo a que tipo de escala.
     *
     * @param d
     * @return
     */
    static public String num2nota12(float d, Escala esc) {
        // 21= A0, 108 = C8
        // 21=12+9;
        // Ver que clase de nota es modulo 12;

        float mod = mod(d, 12);
        // Corregir de acuerdo a bemoles o sostenidos
        String nota = "";
        String comita = "" + (char) (39);

        if ((esc.getTipoEsc() == 'M'
                & (esc.getBaseEsc() == 0 || esc.getBaseEsc() == 7
                || esc.getBaseEsc() == 2 || esc.getBaseEsc() == 9
                || esc.getBaseEsc() == 4 || esc.getBaseEsc() == 11))
                || (esc.getTipoEsc() == 'm'
                & (esc.getBaseEsc() == 9 || esc.getBaseEsc() == 4
                || esc.getBaseEsc() == 11 || esc.getBaseEsc() == 6
                || esc.getBaseEsc() == 1 || esc.getBaseEsc() == 8))) {

            switch ((int) mod) {
                case 0:
                    nota += "c";
                    break;
                case 1:
                    nota += "c#";
                    break;
                case 2:
                    nota += "d";
                    break;
                case 3:
                    nota += "d#";
                    break;
                case 4:
                    nota += "e";
                    break;
                case 5:
                    nota += "f";
                    break;
                case 6:
                    nota += "f#";
                    break;
                case 7:
                    nota += "g";
                    break;
                case 8:
                    nota += "g#";
                    break;
                case 9:
                    nota += "a";
                    break;
                case 10:
                    nota += "a#";
                    break;
                case 11:
                    nota += "b";
                    break;
                default:
                    nota += "";
            }
        } else {
            if ((esc.getTipoEsc() == 'M'
                    & (esc.getBaseEsc() == 6 || esc.getBaseEsc() == 1
                    || esc.getBaseEsc() == 8 || esc.getBaseEsc() == 3
                    || esc.getBaseEsc() == 10 || esc.getBaseEsc() == 5))
                    || ((esc.getTipoEsc() == 'm'
                    & (esc.getBaseEsc() == 3 || esc.getBaseEsc() == 10
                    || esc.getBaseEsc() == 5 || esc.getBaseEsc() == 0
                    || esc.getBaseEsc() == 7 || esc.getBaseEsc() == 2)))) {

                switch ((int) mod) {
                    case 0:
                        nota += "c";
                        break;
                    case 1:
                        nota += "db";
                        break;
                    case 2:
                        nota += "d";
                        break;
                    case 3:
                        nota += "eb";
                        break;
                    case 4:
                        nota += "e";
                        break;
                    case 5:
                        nota += "f";
                        break;
                    case 6:
                        nota += "gb";
                        break;
                    case 7:
                        nota += "g";
                        break;
                    case 8:
                        nota += "ab";
                        break;
                    case 9:
                        nota += "a";
                        break;
                    case 10:
                        nota += "bb";
                        break;
                    case 11:
                        nota += "b";
                        break;
                    default:
                        nota += "";
                }
            }
        }
        return nota;
    }

    /**
     * Funcion que le asigna un numero a cada nota de la escala, utilizando
     * sostenidos. Consiste de siete octavas.
     * Debe poner bemoles o sostenidos de acuerdo a que tipo de escala.
     *
     * @param d
     * @return
     */
    static public String num2nota12(float d, char acc) {
        // 21= A0, 108 = C8
        // 21=12+9;
        // Ver que clase de nota es modulo 12;

        float mod = mod(d, 12);
        // Corregir de acuerdo a bemoles o sostenidos
        String nota = "";
        String comita = "" + (char) (39);

        if (acc == 's') {

            switch ((int) mod) {
                case 0:
                    nota += "c";
                    break;
                case 1:
                    nota += "c#";
                    break;
                case 2:
                    nota += "d";
                    break;
                case 3:
                    nota += "d#";
                    break;
                case 4:
                    nota += "e";
                    break;
                case 5:
                    nota += "f";
                    break;
                case 6:
                    nota += "f#";
                    break;
                case 7:
                    nota += "g";
                    break;
                case 8:
                    nota += "g#";
                    break;
                case 9:
                    nota += "a";
                    break;
                case 10:
                    nota += "a#";
                    break;
                case 11:
                    nota += "b";
                    break;
                default:
                    nota += "";
            }
        } else {
            if (acc == 'b') {

                switch ((int) mod) {
                    case 0:
                        nota += "c";
                        break;
                    case 1:
                        nota += "db";
                        break;
                    case 2:
                        nota += "d";
                        break;
                    case 3:
                        nota += "eb";
                        break;
                    case 4:
                        nota += "e";
                        break;
                    case 5:
                        nota += "f";
                        break;
                    case 6:
                        nota += "gb";
                        break;
                    case 7:
                        nota += "g";
                        break;
                    case 8:
                        nota += "ab";
                        break;
                    case 9:
                        nota += "a";
                        break;
                    case 10:
                        nota += "bb";
                        break;
                    case 11:
                        nota += "b";
                        break;
                    default:
                        nota += "";
                }
            }
        }
        return nota;
    }

    /**
     * Funcion que le asigna un numero a cada nota de la escala, utilizando
     * sostenidos. Consiste de siete octavas.
     * Poner sostenidos o bemoles
     * @param d
     * @return
     */
    static public String num2notaMIDI(float d, Escala esc) {
        // 21= A0, 108 = C8
        // 21=12+9;
        // Ver que clase de nota es modulo 12;
        // Corregir de acuerdo a bemoles o sostenidos
        String nota = "";

        nota += num2nota12(d, esc);
        // Ver que octava es
        int octava = (int) Math.floor(((double) d) / 12) - 1;
        nota += "" + octava;
        return nota;
    }

    /**
     * Funcion que le asigna un numero a cada nota de la escala, utilizando
     * sostenidos. Consiste de siete octavas.
     * Poner sostenidos o bemoles
     * @param d
     * @return
     */
    static public String num2notaMIDI(float d, char acc) {
        // 21= A0, 108 = C8
        // 21=12+9;
        // Ver que clase de nota es modulo 12;
        // Corregir de acuerdo a bemoles o sostenidos
        String nota = "";

        nota += num2nota12(d, acc);
        // Ver que octava es
        int octava = (int) Math.floor(((double) d) / 12) - 1;
        nota += "" + octava;
        return nota;
    }

    /**
     * Funcion que le asigna un numero a cada nota de la escala, utilizando
     * sostenidos. Consiste de siete octavas.
     * Poner sostenidos o bemoles
     * @param d
     * @return
     */
    static public String num2notaLily(float d, Escala esc) {
        // 21= A0, 108 = C8
        // 21=12+9;
        // Ver que clase de nota es modulo 12;
        // Corregir de acuerdo a bemoles o sostenidos
        String nota = "";
        String comita = "" + (char) (39);
        nota += num2nota12(d, esc);
        // Ver que octava es
        int octava = (int) Math.floor(((double) d) / 12);
        if (octava == 0) {
            nota += ",,,,";
        }
        if (octava == 1) {
            nota += ",,,";
        }
        if (octava == 2) {
            nota += ",,";
        }
        if (octava == 3) {
            nota += ",";
        }
        if (octava == 4) {
            nota += "";
        }
        if (octava == 5) {
            nota += comita;
        }
        if (octava == 6) {
            nota += comita;
            nota += comita;
        }
        if (octava == 7) {
            nota += comita;
            nota += comita;
            nota += comita;
        }
        if (octava == 8) {
            nota += comita;
            nota += comita;
            nota += comita;
            nota += comita;
        }
        return nota;
    }
}
