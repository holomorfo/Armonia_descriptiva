/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import polifoniaLib.defs.DefEscalas;
import polifoniaLib.listas.ListArmonia;
import polifoniaLib.listas.ListFloat;
import java.io.Serializable;
import java.util.LinkedList;
import polifoniaLib.defs.Oper;

/**
 *
 * @author Cristian
 */
public class Escala extends ListFloat implements Serializable {
    // Mayor

    LinkedList<Escala> escL;
    Escala iiE;
    Escala iiiE;
    Escala ivE;
    Escala vE;
    Escala viE;
    Escala viiE;
    DefEscalas defEs;
    private ListArmonia listArms;
    private char tipoEsc; //M:mayor, m: menor, a: armonica, n: natural, l:melodica
    private float baseEsc;
    // Quiza poner una variable general
    public boolean sostenido;

    /**
     *
     * @param base
     * @param tipo
     */
    public Escala(float base, char tipo) {
        escL = new LinkedList<Escala>();
        baseEsc = base;
        switch (tipo) {
            case 'M':
                for (int i = 0; i < DefEscalas.Mayor.length; i++) {
                    add(mod(baseEsc + DefEscalas.Mayor[i], 12));
                }
                break;
            case 'l':
                for (int i = 0; i < DefEscalas.MenorMelodico.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorMelodico[i], 12));
                }
                break;
            case 'm':// intercambiadas M y a
                for (int i = 0; i < DefEscalas.MenorArmonico.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorArmonico[i], 12));
                }
                break;
            case 'n':
                for (int i = 0; i < DefEscalas.MenorNat.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorNat[i], 12));
                }
                break;
            case 'a':// intercambiadas M y a
                for (int i = 0; i < DefEscalas.MenorNat.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorNat[i], 12));
                }
                break;
        }
        defEs = new DefEscalas();
        listArms = defEs.lista(base, tipo);
        tipoEsc = tipo;
        setSostenido();
    }

    /**
     * Construye la escala con la nota string y el tipo char
     * @param fund
     * @param tipo
     */
    public Escala(String fund, char tipo) {
        baseEsc = nota2num12(fund);
        switch (tipo) {
            case 'M':
                for (int i = 0; i < DefEscalas.Mayor.length; i++) {
                    add(mod(baseEsc + DefEscalas.Mayor[i], 12));
                }
                break;
            case 'l':
                for (int i = 0; i < DefEscalas.MenorMelodico.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorMelodico[i], 12));
                }
                break;
            case 'a':
                for (int i = 0; i < DefEscalas.MenorArmonico.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorArmonico[i], 12));
                }
                break;
            case 'n':
                for (int i = 0; i < DefEscalas.MenorNat.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorNat[i], 12));
                }
                break;
            case 'm':
                for (int i = 0; i < DefEscalas.MenorNat.length; i++) {
                    add(mod(baseEsc + DefEscalas.MenorNat[i], 12));
                }
                break;
        }
        defEs = new DefEscalas();
        tipoEsc = tipo;
        setSostenido();
    }

    private void setSostenido() {
        if (tipoEsc == 'M' & baseEsc == 0) {
            sostenido = true;
        } else if (tipoEsc == 'M' & baseEsc == 7) {
            sostenido = true;
        } else if (tipoEsc == 'M' & baseEsc == 2) {
            sostenido = true;
        } else if (tipoEsc == 'M' & baseEsc == 9) {
            sostenido = true;
        } else if (tipoEsc == 'M' & baseEsc == 4) {
            sostenido = true;
        } else if (tipoEsc == 'M' & baseEsc == 11) {
            sostenido = true;
        } else if (tipoEsc == 'm' & baseEsc == 9) {
            sostenido = true;
        } else if (tipoEsc == 'm' & baseEsc == 4) {
            sostenido = true;
        } else if (tipoEsc == 'm' & baseEsc == 11) {
            sostenido = true;
        } else if (tipoEsc == 'm' & baseEsc == 6) {
            sostenido = true;
        } else if (tipoEsc == 'm' & baseEsc == 1) {
            sostenido = true;
        } else if (tipoEsc == 'm' & baseEsc == 8) {
            sostenido = true;
        } else // comienzan bemoles
        if (tipoEsc == 'M' & baseEsc == 5) {
            sostenido = false;
        } else if (tipoEsc == 'M' & baseEsc == 10) {
            sostenido = false;
        } else if (tipoEsc == 'M' & baseEsc == 3) {
            sostenido = false;
        } else if (tipoEsc == 'M' & baseEsc == 8) {
            sostenido = false;
        } else if (tipoEsc == 'M' & baseEsc == 1) {
            sostenido = false;
        } else if (tipoEsc == 'M' & baseEsc == 6) {
            sostenido = false;
        } else if (tipoEsc == 'm' & baseEsc == 2) {
            sostenido = false;
        } else if (tipoEsc == 'm' & baseEsc == 7) {
            sostenido = false;
        } else if (tipoEsc == 'm' & baseEsc == 0) {
            sostenido = false;
        } else if (tipoEsc == 'm' & baseEsc == 5) {
            sostenido = false;
        } else if (tipoEsc == 'm' & baseEsc == 10) {
            sostenido = false;
        } else if (tipoEsc == 'm' & baseEsc == 3) {
            sostenido = false;
        }
    }

    public void iniciarEscalas() {
        if (this.getTipoEsc() == 'M') {
            //Mayor
            escL.add(new Escala(this.getBaseEsc() + 0, 'M'));
            escL.add(new Escala(this.getBaseEsc() + 2, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 4, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 5, 'M'));
            escL.add(new Escala(this.getBaseEsc() + 7, 'M'));
            escL.add(new Escala(this.getBaseEsc() + 9, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 11, 'm'));
        } else if (this.getTipoEsc() == 'm') {
            // MENOR
            escL.add(new Escala(this.getBaseEsc() + 0, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 2, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 3, 'M'));
            escL.add(new Escala(this.getBaseEsc() + 5, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 7, 'm'));
            escL.add(new Escala(this.getBaseEsc() + 8, 'M'));
            escL.add(new Escala(this.getBaseEsc() + 10, 'M'));
        }
    }

    public String getNombreEsc() {
        return "" + this.getBaseEscStr() + "" + this.getTipoEsc();
    }

    /**
     * Convierte la nota string a el numero cromatico
     * @param fund
     * @return notaFund
     */
    private int nota2num12(String fund) {
        int notaFund = -1; // negativo si no es nada
        if (fund.equals("C")) {
            notaFund = 0;
        } else if (fund.equals("C#") || fund.equals("Db")) {
            notaFund = 1;
        } else if (fund.equals("D")) {
            notaFund = 2;
        } else if (fund.equals("D#") || fund.equals("Eb")) {
            notaFund = 3;
        } else if (fund.equals("E")) {
            notaFund = 4;
        } else if (fund.equals("F")) {
            notaFund = 5;
        } else if (fund.equals("F#") || fund.equals("Gb")) {
            notaFund = 6;
        } else if (fund.equals("G")) {
            notaFund = 7;
        } else if (fund.equals("G#") || fund.equals("Ab")) {
            notaFund = 8;
        } else if (fund.equals("A")) {
            notaFund = 9;
        } else if (fund.equals("A#") || fund.equals("Bb")) {
            notaFund = 10;
        } else if (fund.equals("B")) {
            notaFund = 11;
        }
        return notaFund;
    }

    /**
     * Revisa si un numero nota es elemento de la escala;
     *
     * @param num
     * @return
     */
    public boolean perteneceNota(float num) {
        int cosa;
        boolean cond = false;
        cosa = indexOf(mod(num, 12));
        if (cosa >= 0) {// esto por la estructura de LinkedList
            cond = true;
        }
        return cond;
    }

    public float siguiente(float nota, int cuantos) {
        float reg = nota;
        for (int i = 1; i <= cuantos; i++) {
            reg = siguiente(reg);
        }
        return reg;
    }

    public float siguiente(float i) {
        float idx = i;
        for (int k = 1; k < 4; k++) {
            if (perteneceNota(i + k)) {
                idx = i + k;
                break;
            }
        }
        return idx;
    }

    public float anterior(float nota, int cuantos) {
        float reg = nota;
        for (int i = 1; i <= cuantos; i++) {
            reg = anterior(reg);
        }
        return reg;
    }

    public float anterior(float i) {
        float idx = i;
        for (int k = 1; k < 4; k++) {
            if (perteneceNota(i - k)) {
                idx = i - k;
                break;
            }
        }
        return idx;
    }

    public void intervalo() {
    }

    public int gravedad(float a, float b) {
        int grav = 0;
        float n1 = this.gradoNota(a);
        float n2 = this.gradoNota(b);
//        if (Math.abs(a - b) == 1 && perteneceNota(b)) {// si es semitono
//            grav = 10;
//        } else if (Math.abs(a - b) == 2
//                && perteneceNota(a) && perteneceNota(b)) {// si es tono
//            grav = 5;
//        }

        if (Math.abs(a - b) <= 2) {// si son cercanas
            if (n1 == 7 && n2 == 1) {// sensible
                grav = 10;
            } else if (n1 == 6 && n2 == 5) {
                grav = 5;
            } else if (n1 == 4 && n2 == 3) {
                grav = 10;
            } else if (n1 == 2 && n2 == 1) {
                grav = 5;
            }

        }
        return grav;
    }

    /**
     * Revisa si un acorde esta' formado por notas de la escala;
     *
     * @param unArm
     * @return
     */
    public boolean perteneceArmonia(Armonia unArm) {
        //int cosa;
        boolean cond = true;
        for (int i = 0; i < unArm.getVoces().size(); i++) {
            if (!perteneceNota(unArm.getVoces().get(i))) {
                cond = false;
            }
        }
        return cond;
    }

    public float gradoNota(float nota) {
        return indexOf(mod(nota, 12)) + 1;
    }

    public String getTipoTriada(int grado) {
        String str = "";
        if (0 <= grado & grado < this.size()) {
            // Revisar el grado que se pide, y de acuerdo a la escala
            // en la que esté regresar M, m o dim, si es dim, no se usa.
            if (this.getTipoEsc() == 'M') {
                str = defEs.Ac3EscMay[grado];
            } else if (this.getTipoEsc() == 'm') {
                str = defEs.Ac3EscMenNat[grado];
            }
        }
        return str;
    }
    public String getTipoSeptimo(int grado) {
        String str = "";
        if (0 <= grado & grado < this.size()) {
            // Revisar el grado que se pide, y de acuerdo a la escala
            // en la que esté regresar M, m o dim, si es dim, no se usa.
            if (this.getTipoEsc() == 'M') {
                str = defEs.Ac7EscMay[grado];
            } else if (this.getTipoEsc() == 'm') {
                str = defEs.Ac7EscMenNat[grado];
            }
        }
        return str;
    }

    
    /**
     * Revisa si un numero nota es elemento de la escala, y que
     * indice tendria. Si no pertenece toma el anterior o siguiente dependiendo
     * si la escala usa bemoles o sostenidos.
     * Cuidado con las escalas menores.
     *
     * @param num
     * @return
     */
    public int indiceNota(float num) {
        int grado = 0;
        int oct;
        float numRel = num - this.baseEsc;
        float numRel12 = mod(numRel, 12);
        oct = (int) (numRel / 12);
        if (this.tipoEsc == 'M') {
            switch ((int) numRel12) {
                case 0:
                    grado = 0;
                    break;
                case 1:
                    grado = 0;
                    break;
                case 2:
                    grado = 1;
                    break;
                case 3:
                    grado = 1;
                    break;
                case 4:
                    grado = 2;
                    break;
                case 5:
                    grado = 3;
                    break;
                case 6:
                    grado = 3;
                    break;
                case 7:
                    grado = 4;
                    break;
                case 8:
                    grado = 4;
                    break;
                case 9:
                    grado = 5;
                    break;
                case 10:
                    grado = 5;
                    break;
                case 11:
                    grado = 6;
                    break;
            }
        } else if (this.tipoEsc == 'm') {
            switch ((int) numRel12) {
                case 0:
                    grado = 0;
                    break;
                case 1:
                    grado = 0;
                    break;
                case 2:
                    grado = 1;
                    break;
                case 3:
                    grado = 2;
                    break;
                case 4:
                    grado = 2;
                    break;
                case 5:
                    grado = 3;
                    break;
                case 6:
                    grado = 3;
                    break;
                case 7:
                    grado = 4;
                    break;
                case 8:
                    grado = 5;
                    break;
                case 9:
                    grado = 5;
                    break;
                case 10:
                    grado = 6;
                    break;
                case 11:
                    grado = 6;
                    break;
            }
        }
//        if (this.baseEsc != 0) {
//            oct++;
//        }
        int indice = grado + 7 * oct;
        if (this.baseEsc == 10 & this.tipoEsc == 'M') {// Corregir escala sibmayor
            indice++;
        }
        if (this.baseEsc == 8 & this.tipoEsc == 'M') {// Corregir escala sibmayor
            indice++;
        }
        if (this.baseEsc == 1 & this.tipoEsc == 'M') {// Corregir escala sibmayor
            indice++;
        }
        if (this.baseEsc == 3 & this.tipoEsc == 'M') {// Corregir escala sibmayor
            indice++;
        }
        if (this.baseEsc == 10 & this.tipoEsc == 'm') {// Corregir escala sibmenor
            indice++;
        }
        return indice;
    }

//    /**
//     *
//     * regresa el valor del grado del acorde en todas las escalas con relaciones
//     * diatonicas
//     * 51 V/I  52 V/II etc
//     * 41 IV/I  42 IV/II etc..
//     * @param unArm
//     * @return
//     */
//    public int gradoArmSecund(Armonia unArm) {
//        // Revisar si unArm es equivalente a alguno de los acordes de la lista
//        // y si es asi, ver que grado es la fundamental
//
//        int reg = 0;
//        int fund = -1;
//        boolean cond = false;
//
//        if (escL.get(0).gradoArmSens(unArm) > 0) {
//            // si es acorde de la escala
//            reg = (escL.get(0).gradoArmSens(unArm)) * 10 + 1;
//        } else {
//            // si es dominante o subdom de otra escala
//            for (int i = 1; i < escL.size(); i++) {
//                if (escL.get(i).gradoArmSens(unArm) == 5) {
//                    // por ejemplo si vE.gradoArmSens(unArm) == 5
//                    // 51 V/I  52 V/II etc
//                    // 41 IV/I  42 IV/II etc..
//                    reg = 50 + (i + 1);
////                    System.out.println("Reg " + reg);
//                }
//            }
//        }
//        return reg;
//    }
//
//    public int gradoArmSens(Armonia unArm) {
//        // Revisar si unArm es equivalente a alguno de los acordes de la lista
//        // y si es asi, ver que grado es la fundamental
//
//        int reg = 0;
//        int fund = -1;
//        boolean cond = false;
//
//        for (int i = 0; i < listArms.size(); i++) {
//            if (listArms.get(i).equivalente(unArm)) {
////                System.out.println("Lista:"+listArms.get(i).getFund12() + ", " + listArms.get(i).getTipoAcStr());
////                System.out.println("unArm:" + unArm.getFund12() + ", " + unArm.getTipoAcStr());
//                fund = (int) unArm.getFund12();
////                System.out.println("Escala.grado: " + fund);
//                cond = true;
//                break;
//            }
//        }
//        if (cond) {
//            reg = (int) gradoNota(fund);
//        } else {
//            reg = 0;
//        }
//        return reg;
//    }
    public String nombreAcordeAbsoluto(Armonia elAcorde) {
        return "" + Oper.num2nota12(elAcorde.getFund12(), this) + elAcorde.getTipoAcStr();
    }

    /**
     *
     * @return
     */
    public char getTipoEsc() {
        return tipoEsc;
    }

    /**
     *
     * @return
     */
    public float getBaseEsc() {
        return baseEsc;
    }

    /**
     *
     * @return
     */
    public String getBaseEscStr() {
        return Oper.num2nota12(baseEsc, this);
    }

    //////
    /**
     * Notas de la escala
     *
     */
    public void printStr() {
        // String imp = "";
        for (int i = 0; i < size(); i++) {
            System.out.println(Oper.num2nota12(get(i), this));
        }
    }

    public ListArmonia getListArms() {
        return listArms;
    }

    /**
     * Funcion modulo como la de matlab
     *
     * @param num
     * @param base
     * @return
     */
    private float mod(float num, float base) {
        num = num % base;
        if (num < 0) {
            num = base + num;
        }
        return num;
    }
}
