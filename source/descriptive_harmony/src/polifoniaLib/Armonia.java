/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import polifoniaLib.defs.DefAcordes;
import polifoniaLib.listas.ListFloat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import polifoniaLib.defs.DefAcordesJazz;
import polifoniaLib.defs.Oper;

/**
 * B T A S
 *
 * @author Cristian
 */
public class Armonia implements Serializable {

    private DefAcordesJazz defAc;
    private ListFloat voces = new ListFloat();
    private ListFloat vocesTipo = new ListFloat();
    private float restar = 0f;
    private float fund = 0f;
    private int inversion;
    private boolean cruce;
    private boolean completo = true;
    private boolean sept = false;
    private int disposicion;
    private int duplicacion;
    private float posMel;
//    private int tipoAc;
    private String tipoAcStr = "";
    //private String interpretStr;
    private String invStr = "";
    private String interpretacion = ".";
    public ListFloat canonico = new ListFloat();
    private ListFloat simplificado = new ListFloat();

    public Armonia() {
        voces.add(0.0f);
        vocesTipo.add(0.0f);
        defAc = new DefAcordesJazz();

        setEstructura();

    }

    public Armonia(int nota, String tip) {
        defAc = new DefAcordesJazz();
        float[] acord = stringToAcord(tip);

        for (int i = 0; i < acord.length; i++) {
            voces.add((float) (nota + acord[i]));
            vocesTipo.add(0.0f);
        }
        setEstructura();
    }

    public Armonia(float nota, String tip) {
        defAc = new DefAcordesJazz();

        float[] acord = stringToAcord(tip);
        for (int i = 0; i < acord.length; i++) {
            voces.add((float) (nota + acord[i]));
            vocesTipo.add(0.0f);
        }
        setEstructura();
    }

    public float[] stringToAcord(String tip) {

        float[] acord = {};
        for (int i = 0; i < defAc.getArms().size(); i++) {
            if (tip.equals(defAc.getArms().get(i).getNombre())) {
                acord = defAc.getArreglo(i);
            }
        }


//        if (tip.equals("M")) {
//            acord = DefAcordes.a_mayor;
//        } else if (tip.equals("m")) {
//            acord = DefAcordes.b_menor;
//        } else if (tip.equals("o")) {
//            acord = DefAcordes.c_dim;
//        } else if (tip.equals("+")) {
//            acord = DefAcordes.d_aum;
//        } else if (tip.equals("M7")) {
//            acord = DefAcordes.e_M7;
//        } else if (tip.equals("Mm7")) {
//            acord = DefAcordes.f_Mm7;
//        } else if (tip.equals("m7")) {
//            acord = DefAcordes.g_m7;
//        } else if (tip.equals("o/7")) {
//            acord = DefAcordes.h_semiDim7;
//        } else if (tip.equals("o7")) {
//            acord = DefAcordes.i_dim7;
//        } else if (tip.equals("I+")) {
//            acord = DefAcordes.j_ImenVIIdim;
//        } else if (tip.equals("III+")) {
//            acord = DefAcordes.k_IIImenVIIaum;
//        } else if (tip.equals("VM7#5")) {
//            acord = DefAcordes.VM7p5;
//        } // DOM 9
//        else if (tip.equals("V9Ms5")) {
//            acord = DefAcordes.V9Ms5;
//        } else if (tip.equals("V9ms5")) {
//            acord = DefAcordes.V9ms5;
//        }

//        else if (tip.equals("IIb1")) {
//            acord = DefAcordes.nap;
//        }
//        else if (tip.equals("M*5")) {
//            acord = DefAcordes.m_may_sin5;
//        }else if (tip.equals("m*5")) {
//            acord = DefAcordes.n_men_sin5;
//        }else if (tip.equals("D7*5")) {
//            acord = DefAcordes.l_D7_sin5;
//        }
        return acord;
    }

    public Armonia(Armonia arm) {
        defAc = new DefAcordesJazz();

        if (arm != null & arm.getVoces().size() > 0) {
            for (int i = 0; i < arm.getVoces().size(); i++) {
                voces.add(arm.getVoces().get(i));
                vocesTipo.add(arm.getVocesTipo().get(i));
            }
            setEstructura();
        }
    }

    public Armonia(ListFloat arm) {
        if (arm != null) {
            defAc = new DefAcordesJazz();
            Collections.sort(arm, new CompararFloats());
            if (arm.size() > 0) {
                voces = arm;
                for (int i = 0; i < arm.size(); i++) {
                    vocesTipo.add(0.0f);
                }
                setEstructura();
            } else {
                voces.add(0.0f);
                vocesTipo.add(0.0f);
            }
        }
    }

    public Armonia(int voz1) {
        defAc = new DefAcordesJazz();

        voces.add((float) voz1);
        vocesTipo.add(0.0f);
        setEstructura();
    }

    public Armonia(int voz1, int voz2) {
        defAc = new DefAcordesJazz();

        voces.add((float) voz1);
        voces.add((float) voz2);
        vocesTipo.add(0.0f);
        vocesTipo.add(0.0f);
        setEstructura();
    }

    public Armonia(int voz1, int voz2, int voz3) {
        defAc = new DefAcordesJazz();

        voces.add((float) voz1);
        voces.add((float) voz2);
        voces.add((float) voz3);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        setEstructura();
    }

    public Armonia(int voz1, int voz2, int voz3, int voz4) {
        defAc = new DefAcordesJazz();

        voces.add((float) voz1);
        voces.add((float) voz2);
        voces.add((float) voz3);
        voces.add((float) voz4);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        setEstructura();
    }

    public Armonia(float voz1, float voz2, float voz3, float voz4) {
        defAc = new DefAcordesJazz();
        voces.add(voz1);
        voces.add(voz2);
        voces.add(voz3);
        voces.add(voz4);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        vocesTipo.add(0f);
        setEstructura();
    }

    /**
     *
     * @param interpretacion
     */
    public void setInterpretacion(String interpretacion) {
        this.interpretacion = interpretacion;
    }

    private void setEstructura() {
        //float[] estrA = {0};
//        setCanonicoTEMP(voces);
        setSimplificado();
        // Revisar si es igual a alguno de la libreria
        ListFloat temp;
        for (int i = 0; i < defAc.getArms().size(); i++) {
            // compara el arreglo
            if (comparar(defAc.getArreglo(i))) {
                tipoAcStr = defAc.getArms().get(i).getNombre();
            }
        }


        if (voces.size() == 1) {
            fund = voces.get(0) % 12;
        }
        setTipoVOces(fund);
        setSept();
        setDisposicion();
        setPosMel();
        setDuplicacion();
        setInv();
        setInvStr();
//        setTipoAcStr();
    }

    private boolean comparar(float[] acordeNots) {
        // Le falta poner comparar transposicion
        boolean cond = false;
        float[] tempA;
        if (simplificado.size() == acordeNots.length) {
            tempA = new float[simplificado.size()];
            cosa:
            for (int r = 0; r < simplificado.size(); r++) {
                // Restar y re ordenar
                restar = simplificado.get(r);
                for (int i = 0; i < tempA.length; i++) {
                    tempA[i] = Oper.mod(simplificado.get(i) - restar, 12);
                }
                Arrays.sort(tempA);
                if (Arrays.equals(tempA, acordeNots)) {
                    fund = Oper.mod(restar, 12);
                    cond = true;
                    break cosa;
                }
            }
        } else {
            cond = false;
        }
        return cond;
    }

    private void setCanonicoTEMP(ListFloat nots) {
        // Primero modulo 12, ordenado y sin repetidas
        ListFloat dif = new ListFloat();
        float[] notasArr = new float[nots.size()];
        for (int i = 0; i < nots.size(); i++) {
            notasArr[i] = nots.get(i) % 12;
        }
        // copiar arrelgo de notas y ordenarlas.
        Arrays.sort(notasArr);
        int larg = notasArr.length;

        // luego quitar repetidas;
        dif.add(notasArr[0]);
        //int indice = 1;
        int condicion = 0;
        for (int i = 1; i < larg; i++) {
            condicion = 0;
            for (int j = 0; j < i; j++) {
                if (notasArr[i] == notasArr[j]) {
                    condicion = 1; // Revisar si hay notas repetidas
                }
            }
            if (condicion == 0) {
                // No tiene notas repetidas, se agrega al arreglo
                dif.add(notasArr[i]);
            }

        }

        float[] difA = new float[dif.size()];
        float[] tempA = new float[dif.size()];

        for (int i = 0; i < dif.size(); i++) {
            difA[i] = dif.get(i);
        }
        // Restar modulo 12 las notas y comparar los extremos
//        int distPas = 100000000;
//        int distAct;
//
//        int idxRes = 0;
//        for (int r = 0; r < difA.length; r++) {
//            for (int i = 0; i < difA.length; i++) {
//                tempA[i] = mod(difA[i] - difA[r], 12);
//            }
//            Arrays.sort(tempA);
//            // Revisar quien es el mas pequenio.
//            distAct = tempA[tempA.length - 1] - tempA[0];
////            for (int k = 0;k < tempA.length; k++) {
////                System.out.print(" " + tempA[k] + ",");
////            }
////            System.out.println("Distancia: "+distAct);
//            if (distPas > distAct) {
//                idxRes = r;
//            }
//            distPas = distAct;
//        }
        // Por lo tanto la forma setCanonicoTEMP es la que sale de restar el ''r''
//        restar = difA[idxRes];
        //        System.out.println("Restar1: " + restar + " idxRes: " + idxRes);
//        for (int i = 0; i < difA.length; i++) {
//            tempA[i] = mod(difA[i] - difA[idxRes], 12);
//        }
//        Arrays.sort(tempA);
        for (int i = 0; i < difA.length; i++) {
            canonico.add(difA[i]);
        }

    }

    private void setTipoVOces(float quienFund) {
        // Obtener la nota de quien tiene la fundamental
//        int fund = getVoces().get(quienFund);
        float dist;
        vocesTipo = new ListFloat();
        for (int i = 0; i < voces.size(); i++) {
            dist = Math.abs((fund - voces.get(i)) % 12);
//            System.out.println("Fund: " + fund + " Restar" + voces.get(i));
//            System.out.println("Dist: " + dist);
            vocesTipo.add(intervalo7(dist));
//            System.out.println("Intervalo7: " + intervalo7(dist));
            //   System.out.println(intervalo7(dist));
        }
    }

    private float intervalo7(float dist) {
        int reg = -1;
        switch ((int) dist) {
            case 0:
                reg = 1;
                break;
            case 1:
                reg = 2;
                break;
            case 2:
                reg = 2;
                break;
            case 3:
                reg = 3;
                break;
            case 4:
                reg = 3;
                break;
            case 5:
                reg = 5;
                break;
            case 6:
                reg = 5;
                break;
            case 7:
                reg = 5;
                break;
            case 8:
                reg = 3;
                break;
            case 9:
                reg = 3;
                break;
            case 10:
                reg = 7;
                break;
            case 11:
                reg = 7;
                break;
        }
        return reg;
    }

    /**
     * Simplifica la lista de enteros de notas, y regresa algo asi como el
     * p-class
     *
     * @param Estructura
     * @return
     */
    private void setSimplificado() {
        ListFloat diferentes = new ListFloat();
        ListFloat notas = new ListFloat();
        float[] notasArr = new float[voces.size()];
        for (int i = 0; i < voces.size(); i++) {
            notasArr[i] = voces.get(i) % 12;
        }
        // copiar arrelgo de notas y ordenarlas.
        Arrays.sort(notasArr);
        int larg = notasArr.length;

        // luego quitar repetidas;
        diferentes.add(notasArr[0]);
        //int indice = 1;
        int condicion = 0;
        for (int i = 1; i < larg; i++) {
            condicion = 0;
            for (int j = 0; j < i; j++) {
                if (notasArr[i] == notasArr[j]) {
                    condicion = 1; // Revisar si hay notas repetidas
                }
            }
            if (condicion == 0) {
                // No tiene notas repetidas, se agrega al arreglo
                diferentes.add(notasArr[i]);
            }

        }
        simplificado = new ListFloat(diferentes);
    }

    public ListFloat getRepresentante() {
//        System.out.println("+++++++++");

        ListFloat temp = new ListFloat(this.getVoces());
        Collections.sort(temp, new CompararFloatsInv());
        ListFloat reg = new ListFloat(temp);
        Armonia arm = new Armonia(temp);

        Armonia armReg = new Armonia();
        boolean cond = false;
        int cuantas = arm.getSimplificado().size();

        // Si hay mas de 4 notas...
        // Tomar el bajo y el soprano
        // Ahora ocupo rellenar las otras dos
        // recorrer las posibles combinaciones y el primero que cumpla
        // ese quiero..
        // si no hay, entonces tomo el bajo y los primeros dos de arriba

//        for (int k = 0; k < temp.size(); k++) {
//            System.out.println("Temp : " + temp.get(k));
//        }
        // orden de listas de mayor a menor
        float sop = temp.getFirst();
        float alt = 0;
        float ten = 0;
        float bas = temp.getLast();
        if (temp.size() > 4) {
            //Ahora llenar las otras dos notas
            cosa:
            for (int i = temp.size() - 2; i > 1; i--) {
                for (int j = i - 1; j >= 1; j--) {
                    reg = new ListFloat();
                    alt = temp.get(i);
                    ten = temp.get(j);
                    arm = new Armonia(bas, ten, alt, sop);
                    if (arm.getSimplificado().size() == cuantas && !arm.getTipoAcStr().equals("")) {
                        reg.add(sop);
                        reg.add(alt);
                        reg.add(ten);
                        reg.add(bas);
//                        Collections.sort(reg, new CompararFloatsInv());
//                        System.out.println("Break");
                        break cosa;
                    } else {
                        reg.add(temp.getFirst());
                        reg.add(temp.getLast());
                        reg.add(temp.get(temp.size() - 2));
                        reg.add(temp.get(temp.size() - 3));
                    }
                }
//                System.out.println("i: " + i);
            }
        } else {
            reg = new ListFloat(temp);
        }
        Collections.sort(reg, new CompararFloats());
        return reg;
//        if (temp.size() > 4) {
//            for (int i = 1; i < temp.size(); i++) {
////                reg.add(bas);
//                System.out.println("----");
//                for (int j = i + 1; j < temp.size(); j++) {
//                    reg.clear();
//                    reg = new ListFloat();
//                    reg.add(bas);
//                    if (temp.get(i) != temp.get(j)) {
//                        reg.add(temp.get(i));
//                        reg.add(temp.get(j));
//                    }
//                    reg.add(sop);
//                    for (int k = 0; k < reg.size(); k++) {
//                        System.out.println("Reg : " + reg.get(k));
//                    }
//                    arm = new Armonia(reg);
//                    if (!arm.getTipoAcStr().equals("") && arm.getVoces().size() == 4) {
//                        arm.printAcorde();
////                        reg = arm.getVoces();
//                        cond = true;
//                        break;
//                    }
//                }
//            }
//        } else {
//            reg = temp;
//        }

//        Collections.sort(reg, new CompararFloatsInv());
//        return temp;

//        System.out.println("--- temp size: " + temp.size());
////        boolean cond = false;
//        float nota;
//        Cosa:
//        for (int i = 1; i < temp.size() - 1; i++) {
//            nota = temp.get(i);
//            for (int j = i + 1; j < temp.size(); j++) {
//                if ((nota % 12) == (temp.get(j) % 12) && reg.size() > 4) {
////                    if(reg.size()<4){
//                    reg.remove(reg.indexOf(nota));
////                    }
//                    break;
//                }
//            }
//
////            if (reg.size() <= 4) {
////                System.out.println("Break");
////                break Cosa;
////            }
//            System.out.println("i " + i);
//        }
//        System.out.println("Reg Size: " + reg.size());
//        Collections.sort(reg, new CompararFloatsInv());
//        return reg;
//        }
//
//        System.out.println("Temp j: " + j + "," + temp.get(j));
//        Collections.sort(temp, new CompararFloatsInv());
//        ListFloat reps = new ListFloat();
//        ListFloat list = new ListFloat();
//        float baj = 0, sop = 0;
//        // Revisar si hay mas de 4 voces
//        if (getVoces().size() > 2) {
//            // Guardar el bajo y el soprano
//            baj = getVoces().getFirst();
//            sop = getVoces().getLast();
//
////            temp.removeFirst();
////            temp.removeLast();
//            reps.add(baj % 12);
//            reps.add(sop % 12);
//            // Rrevisar de arriba hacia abajo por notas distintas
////            System.out.println("--- temp size: " + temp.size());
////            System.out.println("--- reps size: " + reps.size());
//            for (int i = 0; i < temp.size(); i++) {
////                System.out.println("temp: " + temp.get(i));
//                //  Guardar las notas distintas del medio
//                if (reps.indexOf(temp.get(i) % 12) == -1) {
//                    // Si no la he registrado
//                    // agregarla a la lista
//                    list.add(getVoces().get(i));
//                    reps.add(getVoces().get(i) % 12);
//                }
//            }
//            for (int j = 0; j < reps.size(); j++) {
//                System.out.println("Reps j: " + j + "," + reps.get(j));
//            }
//            //  Crear un arreglo con las notas filtradas
//            Collections.sort(list, new CompararFloats());
//            list.addFirst(baj);
//            list.addLast(sop);
//            for (int j = 0; j < list.size(); j++) {
//                System.out.println("lis j: " + j + "," + list.get(j));
//            }
//
//        } else {
//            list = this.getVoces();
//        }
//        return list;
    }

    /**
     * Regresa disposicion: 0 cerrado, 1 abierto, 2 mixto,3 mixto 7, 50
     * flexible, -1 no sirve Urgente corregir esto de las disposiciones.. en
     * realidad no se ocupa mucho...
     */
    private void setDisposicion() {
        disposicion = -1;
        switch (voces.size()) {
            case 2:
                disposicion = -1;
                break;
            case 3:
                // Si algun par de voces se separa mas de una octava
                if (Math.abs(getVoces().get(0) - getVoces().get(1)) <= 12 & Math.abs(getVoces().get(1) - getVoces().get(2)) <= 12) {
                    disposicion = 0; // cerrado
                }
                break;
            case 4:
                int disp = -1;// No util
                if (Math.abs(getVoces().get('B') - getVoces().get('T')) <= 24) {
                    if (getInversion() == 0) {
                        if (Math.abs(getVoces().get('T') - getVoces().get('A')) <= 5 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 5) {
                            disp = 0;// cerrado
                        } else if (Math.abs(getVoces().get('T') - getVoces().get('A')) > 5 & Math.abs(getVoces().get('T') - getVoces().get('A')) <= 12) {
                            if (Math.abs(getVoces().get('A') - getVoces().get('S')) > 5 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 12) {
                                disp = 1;// abierto
                            }
                        }
                    } else {
                        if (Math.abs(getVoces().get('T') - getVoces().get('A')) <= 5 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 5) {
                            disp = 0;// cerrado
                        } else if (Math.abs(getVoces().get('T') - getVoces().get('A')) > 5 & Math.abs(getVoces().get('T') - getVoces().get('A')) <= 12 & Math.abs(getVoces().get('A') - getVoces().get('S')) >= 5 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 12) {
                            disp = 1;// abierto
                        } else if ((Math.abs(getVoces().get('T') - getVoces().get('A')) <= 5 & Math.abs(getVoces().get('A') - getVoces().get('S')) >= 5 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 12) || (Math.abs(getVoces().get('S') - getVoces().get('A')) <= 5 & Math.abs(getVoces().get('A') - getVoces().get('T')) > 5 & Math.abs(getVoces().get('A') - getVoces().get('T')) <= 12)) {
                            disp = 2; // mixto
                        } else if (getSept() > 0// Si es septimo, pues no importa la disposicion
                                & (Math.abs(getVoces().get('T') - getVoces().get('A'))) < 12 & (Math.abs(getVoces().get('A') - getVoces().get('S'))) < 12) {
                            disp = 3; // mixto7
                        }
                    }
                    if (getSept() > 0 & getInversion() == 0) {
                        if (Math.abs(getVoces().get('T') - getVoces().get('A')) < 12 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 12) {
                            disp = 3; // mixto7
                        }
                    }
                    if (isCompleto() == false) {
                        if (Math.abs(getVoces().get('T') - getVoces().get('A')) <= 12 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 12) {
                            disp = 4; // Mixto Incompleto
                        }
                    }

                }

                // Flexible.. medio borrar lo anterior...
                if (Math.abs(getVoces().get('T') - getVoces().get('A')) < 12 & Math.abs(getVoces().get('A') - getVoces().get('S')) <= 12) {
                    disp = 50; // mixto7
                }
//                if (!isCompleto()) {
//                    disp = 5;// Rara
//                }
                disposicion = disp;
                break;
        }
    }

    /**
     * Asigna 0,1,3,5,7 dependiendo de la posicion melodica del acordeNots El
     * tipo de la ultima nota de la lista de notas es la posMel Solo funciona si
     * no hay cruce de voces.
     *
     */
    private void setPosMel() {
        if (voces.size() == 2 | getCurceVocesVF()) {
            posMel = -1;
        } else {
            posMel = getTipoVoces().getLast();
        }
    }

    /**
     * Regresa la version string del tipo de acordeNots o intrvalo de la armonia
     *
     */
//    private void setTipoAcStr() {
//        //int tipoNum = getTipoAc();
//        String tpS = "";
//        switch (tipoAc) {
//            case 1:
//                tpS += "M";
//                break;
//            case 2:
//                tpS += "m";
//                break;
//            case 3:
//                tpS += "o";
//                break;
//            case 4:
//                tpS += "+";
//                break;
//            case 5:
//                tpS += "M7";//"M7";
//                break;
//            case 6:
//                tpS += "Mm7";//Mm7";
//                break;
//            case 7:
//                tpS += "m7";//m7";
//                break;
//            case 8:
//                tpS += "o/7";//o7";
//                break;
//            case 9:
//                tpS += "o7";//"o7";
//                break;
//            case 10:
//                tpS += "I+";//"o7";
//                break;
//            case 11:
//                tpS += "III+";//"o7";
//                break;
//            case 12:
//                tpS += "7*5,";// Septimo incompleto sin quinta
//                break;
//            case 13:
//                tpS += "*5,";// Septimo incompleto sin quinta
//                break;
//            case 14:
//                tpS += "*5,";// Septimo incompleto sin quinta
//                break;
//
//
//            case 20:
//                tpS = "Un";
//                break;
//            case 21:
//                tpS = "2m";
//                break;
//            case 22:
//                tpS = "2M";
//                break;
//            case 23:
//                tpS = "3m";
//                break;
//            case 24:
//                tpS = "3M";
//                break;
//            case 25:
//                tpS = "P4";
//                break;
//            case 26:
//                tpS = "Tr";
//                break;
//            case 27:
//                tpS = "P5";
//                break;
//            case 28:
//                tpS = "6m";
//                break;
//            case 29:
//                tpS = "6M";
//                break;
//            case 30:
//                tpS = "7m";
//                break;
//            case 31:
//                tpS = "7M";
//                break;
//            default:
//                tpS += "";
//                break;
//        }
//        tipoAcStr = tpS;
//    }
    public String nombreAcorde(char acc) {
        return getFundStr(acc) + getTipoAcStr() + getInversionStr();
    }

    private void setDuplicacion() {
        int fund = 0;
        int terc = 0;
        int quint = 0;
        int sept = 0;
        for (int i = 0; i < vocesTipo.size(); i++) {
            switch (vocesTipo.get(i).intValue()) {
                case 1:
                    fund++;
                    break;
                case 3:
                    terc++;
                    break;
                case 5:
                    quint++;
                    break;
                case 7:
                    sept++;
                    break;
            }
        }
        if (fund == 2) {
            duplicacion = 1;
        } else if (terc == 2) {
            duplicacion = 3;
        } else if (quint == 2) {
            duplicacion = 5;
        } else if (sept == 2) {
            duplicacion = 7;
        } else {
            duplicacion = 0;
        }
    }

    private void setVocesTipo(ListFloat vocesTipoS) {
        vocesTipo = vocesTipoS;
    }

    private void setInv() {
        // Revisar inversion en acordes de 3 sonidos
        if (!tipoAcStr.equals("")) {// revisar el tipo del bajo
            switch (getTipoVoces().get(0).intValue()) {
                case 1:
                    inversion = 0;
                    break;
                case 3:
                    inversion = 1;
                    break;
                case 5:
                    inversion = 2;
                    break;
                case 7:
                    inversion = 3;
                    break;
            }
        }
    }

    /**
     *
     */
    private void setInvStr() {
        String invS = "";
        // Revisar inversion
        int inv = getInversion();
        int septimo = getSept();
        // Si no es septimo
        if (septimo < 0) {
            // 6. 6,4
            if (inv == 1) {
                invS += "6";
            }

            if (inv == 2) {
                invS += "6,4";
            }

        } else if (septimo > 0) { // > 0 significa septimo
            if (inv == 0) {
                invS += "7";
            }
            if (inv == 1) {
                invS += "6,5";
            }
            if (inv == 2) {
                invS += "4,3";
            }
            if (inv == 3) {
                invS += "2";
            }
        }
        invStr = invS;
    }

    /**
     * Si el acordeNots tiene septima, regresa su valor, si no -100. @ * Solo
     * para acordes septimos completos
     *
     * @return
     */
    public int getSept() {
        int Septima = -100;
        if (voces.size() == vocesTipo.size()) {
            for (int i = 0; i < voces.size(); i++) {
                if (getVocesTipo().get(i) == 7) {
                    Septima = getVoces().get(i).intValue();
                }
            }
        }
        return Septima;
    }

    /**
     * Si el acordeNots tiene septima, regresa su valor, si no -100. @ * Solo
     * para acordes septimos completos
     *
     * @return
     */
    public void setSept() {
        int Septima = -100;
        for (int i = 0; i < voces.size(); i++) {
            if (getVocesTipo().get(i) == 7) {
                Septima = getVoces().get(i).intValue();
            }
        }
        boolean cond = false;
        if (Septima != -100) {//o7
            cond = true;
        }
        sept = cond;

    }

    public boolean isSept() {
        return sept;
    }

    /**
     * Regresa un vector que dice las voces que tienen el significado "i", puede
     * tomar valores 0,1,3,5,7. 0=Bas... 3=Sop.
     *
     * @param i
     * @return
     */
    public LinkedList<Integer> getQuien(int i) {
        int[] out = new int[4];

        int idx = 0;
        for (int k = 0; k < 4; k++) {
            if (i == vocesTipo.get(k)) {
                out[idx] = k;
                idx = idx + 1;
            }
        }
        LinkedList<Integer> out2 = new LinkedList();
        //int[] out2 = new int[idx];
        for (int k = 0; k < idx; k++) {
            out2.add(out[k]);
            //out2[k] = out[k];
        }
        if (idx == 0) {
            out2.add(-1);
        }
        return out2;
    }

    public ListFloat getSimplificado() {
        return simplificado;
    }

    public String getInterpretacion() {
        return interpretacion;
    }

//    public int[] getRegion(Escala es) {
//        int[] reg = {0, 0, 0};
//        if (es.gradoArmSens(this) == 1) {
//            reg[0] = 2;
//            reg[1] = 0;
//            reg[2] = 0;
//            // Cadencial
//        } else if (es.gradoArmSens(this) == 3) {
//            reg[0] = 1;
//            reg[1] = 0;
//            reg[2] = 2;
//        } else if (es.gradoArmSens(this) == 6) {
//            reg[0] = 2;
//            reg[1] = 2;
//            reg[2] = 0;
//        } else if (es.gradoArmSens(this) == 4) {
//            reg[0] = 0;
//            reg[1] = 2;
//            reg[2] = 0;
//        } else if (es.gradoArmSens(this) == 2) {
//            reg[0] = 0;
//            reg[1] = 2;
//            reg[2] = 0;
//        } else if (es.gradoArmSens(this) == 5) {
//            reg[0] = 0;
//            reg[1] = 0;
//            reg[2] = 2;
//        } else if (es.gradoArmSens(this) == 7) {
//            reg[0] = 0;
//            reg[1] = 0;
//            reg[2] = 2;
//        } else if (es.gradoArmSens(this) == 7
//                && this.isSept()
//                && this.getInversion() == 2) {
//            reg[0] = 0;
//            reg[1] = 2;
//            reg[2] = 1;
//        }
//        return reg;
//    }
    // Aguas con esta definicion de equivalente
    // por que agruparía a todos los que no sabe que son, juntos.
    public boolean equivalente(Armonia unArm) {
        boolean cond = false;
        if (this.getFund12() == unArm.getFund12() && this.getTipoAcStr().equals(unArm.getTipoAcStr())) {
            cond = true;
        }
        return cond;
    }

    /**
     * distancia entre acordes utilizando la metrica melodica.
     *
     * @param K Toma un acordeNots de cuatro notasa
     * @return Regresa la distancia entre ellos
     */
    public int distancia(Armonia unArm) {
        int suma = 0;
        if (this.getVoces().size() == unArm.getVoces().size()) {
            for (int i = 0; i < getVoces().size(); i++) {
                suma += Math.abs(this.getVoces().get(i) - unArm.getVoces().get(i));
            }
        } else {
            for (int i = 0; i < 300; i++) {
                System.out.println("ERROR... DIMENSIONES DE ARMONIA");
            }
        }
        return suma;
    }

    public float dist12(float n1, float n2) {
        return Math.min(Oper.mod(Math.abs(n1 - n2), 12), 12 - Oper.mod(Math.abs(n1 - n2), 12));

    }

    public boolean pertenece(int n) {
        boolean pert = false;
        for (int i = 0; i < getVoces().size(); i++) {
            if (n == getVoces().get(i)) {
                pert = true;
            }
        }
        return pert;
    }

    public boolean pertenece12(int n) {
        boolean pert = false;
        for (int i = 0; i < getVoces().size(); i++) {
            if (n % 12 == getVoces().get(i) % 12) {
                pert = true;
            }
        }
        return pert;
    }

    /**
     * distancia entre acordes utilizando la metrica melodica.
     *
     * @param K Toma un acordeNots de cuatro notasa
     * @return Regresa la distancia entre ellos
     */
    public float distancia(int nota) {
        float dst = 0;
        float reg = 12;
        for (int i = 0; i < getVoces().size(); i++) {
            dst = dist12(nota, Oper.mod(getVoces().get(i), 12));
            if (dst < reg) {
                reg = dst;
            }
        }
        return reg;
    }

    public boolean isCompleto() {
        return completo;
    }

    public boolean distinto(Armonia ac) {
        boolean cond = false;
        int cantIguales = 0;
        if (voces.size() == ac.getVoces().size()) {
            for (int i = 0; i < getVoces().size(); i++) {
                if (this.voces.get(i) == ac.getVoces().get(i)) {
//                cond = false;
                    cantIguales++;
                }
                if (cantIguales == ac.getVoces().size()) {
                    cond = false;
                } else {
                    cond = true;
                }

            }
        } else {
            cond = true;
        }
        return cond;
    }

    /**
     * Distancia entre acordes utilizando la metrica melodica.
     *
     * @param K Toma un acordeNots de cuatro notasa
     * @return Regresa la distancia entre ellos
     */
    public int DistanciaSAT(Armonia unArm) {
        int suma = -1;
        if (voces.size() == 4) {
            if (this.getVoces().size() == unArm.getVoces().size()) {
                suma = 0;
                for (int i = 1; i < getVoces().size(); i++) {
                    suma += Math.abs(this.getVoces().get(i) - unArm.getVoces().get(i));
                }
            } else {
                for (int i = 0; i < 300; i++) {
                    System.out.println("ERROR... DIMENSIONES DE ARMONIA");
                }
            }
        }
        return suma;
    }

    /**
     * Distancia entre acordes utilizando la metrica melodica.
     *
     * @param K Toma un acordeNots de cuatro notasa
     * @return Regresa la distancia entre ellos
     */
    public int DistanciaSATB(Armonia unArm) {
        int suma = -1;
        if (voces.size() == 4) {
            if (this.getVoces().size() == unArm.getVoces().size()) {
                suma = 0;
                for (int i = 1; i < getVoces().size(); i++) {
                    suma += Math.abs(this.getVoces().get(i) - unArm.getVoces().get(i));
                }
            } else {
                for (int i = 0; i < 300; i++) {
                    System.out.println("ERROR... DIMENSIONES DE ARMONIA");
                }
            }
        }
        return suma;
    }

    /**
     * Distancia entre acordes utilizando la metrica melodica.
     *
     * @param unArm
     * @return Regresa la distancia entre ellos
     */
    public int DistMaxVoz(Armonia unArm) {
        int reg = -1;
        if (voces.size() == 4) {
            float[] voc = new float[4];
            if (this.getVoces().size() == unArm.getVoces().size()) {
                voc[0] = Math.abs(this.getVoces().get('S') - unArm.getVoces().get('S'));
                voc[1] = Math.abs(this.getVoces().get('A') - unArm.getVoces().get('A'));
                voc[2] = Math.abs(this.getVoces().get('T') - unArm.getVoces().get('T'));
                voc[3] = Math.abs(this.getVoces().get('B') - unArm.getVoces().get('B'));
                reg = (int) max(voc);
            } else {
                for (int i = 0; i < 300; i++) {
                    System.out.println("ERROR... DIMENSIONES DE ARMONIA");
                }
            }
        }
        return reg;
    }
    //===================================================== max

    public static float max(float[] t) {
        float maximum = t[0];   // start with the first value
        for (int i = 1; i < t.length; i++) {
            if (t[i] > maximum) {
                maximum = t[i];   // new maximum
            }
        }
        return maximum;
    }//end method max

    public void transponer(int n) {
        ListFloat temp = new ListFloat();
        for (int i = 0; i < getVoces().size(); i++) {
            getVoces().set(i, getVoces().get(i) + n);
//            temp.add(getVoces().get(i)+n);
        }
        setEstructura();
    }

    public float getFund() {
        return fund;
    }

    /**
     * Fund modulo 12. regresa el valor de la fundamental modulo 12.
     *
     * @return
     */
    public float getFund12() {
        float reg = -1;
        try {
            reg = voces.getFirst();
            if (voces.size() > 0 && voces.size() == vocesTipo.size()) {
                if (voces.size() >= 2) {
                    float fund = Oper.mod(voces.getFirst(), 12);
                    for (int i = 0; i < voces.size(); i++) {
                        if (vocesTipo.get(i) == 1) {
                            fund = Oper.mod(voces.get(i), 12);
                        }
                    }
                    reg = fund;
                } else if (voces.size() == 1) {
                    float fund = Oper.mod(voces.getFirst(), 12);
                    reg = fund;
                }
            }
        } catch (Exception e) {
        }
        return reg;
    }

    /**
     * Si el acordeNots tiene septima, regresa su valor, si no -1. @ * Solo para
     * acordes septimos completos
     *
     * @return
     */
    public String getFundStr(char acc) {
        String nota = "";
        String acS = "";
        switch (acc) {
            case 's':
                switch ((int) getFund12()) {
                    case 0:
                        nota += "C";
                        break;
                    case 1:
                        nota += "C#";
                        break;
                    case 2:
                        nota += "D";
                        break;
                    case 3:
                        nota += "D#";
                        break;
                    case 4:
                        nota += "E";
                        break;
                    case 5:
                        nota += "F";
                        break;
                    case 6:
                        nota += "F#";
                        break;
                    case 7:
                        nota += "G";
                        break;
                    case 8:
                        nota += "G#";
                        break;
                    case 9:
                        nota += "A";
                        break;
                    case 10:
                        nota += "A#";
                        break;
                    case 11:
                        nota += "B";
                        break;
                    default:
                        nota += "";
                }

                break;
            case 'b':
                switch ((int) getFund12()) {
                    case 0:
                        nota += "C";
                        break;
                    case 1:
                        nota += "Db";
                        break;
                    case 2:
                        nota += "D";
                        break;
                    case 3:
                        nota += "Eb";
                        break;
                    case 4:
                        nota += "E";
                        break;
                    case 5:
                        nota += "F";
                        break;
                    case 6:
                        nota += "Gb";
                        break;
                    case 7:
                        nota += "G";
                        break;
                    case 8:
                        nota += "Ab";
                        break;
                    case 9:
                        nota += "A";
                        break;
                    case 10:
                        nota += "Bb";
                        break;
                    case 11:
                        nota += "B";
                        break;
                    default:
                        nota += "";
                }

                break;
        }
        return nota;
    }

    /**
     * Escribe el string de la inversion del acordeNots.
     *
     * @return
     */
    public String getInversionStr() {
        return invStr;
    }

    public ListFloat getVocesTipo() {
        return vocesTipo;
    }

    public ListFloat getVoces() {
        return voces;
    }

    public int getDisposicion() {
        return disposicion;
    }

    /**
     * Reresa separacion: A-abierto, C-cerrado
     *
     * @return
     */
    public String getDisposicionStr() {
        String reg = "x";
        switch (disposicion) {
            case 0:
                reg = "cerrado";
                break;
            case 1:
                reg = "abierto";
                break;
            case 2:
                reg = "mixto";
                break;
            case 3:
                reg = "mixto7";
                break;
            case 4:
                reg = "mixtoInc";
                break;
            case 5:
                reg = "IncompletoRaro";
                break;
        }
        return reg;
    }

    /**
     * Reresa separacion: A-abierto, C-cerrado
     *
     * @return
     */
    public String getDisposicionStrCorto() {
        String reg = "x";
        switch (disposicion) {
            case 0:
                reg = "cer";
                break;
            case 1:
                reg = "ab";
                break;
            case 2:
                reg = "mix";
                break;
        }
        return reg;
    }

    public float getPosMel() {
        return posMel;
    }

    public ListFloat getTipoVoces() {
        return vocesTipo;
    }

    public boolean getCurceVocesVF() {
        return cruce;
    }

    public String getTipoAcStr() {
        return tipoAcStr;
    }

    // 1 es inversion 6, 2 es 6/4
    public int getInversion() {
        return inversion;
    }

    public int getRnglonInv() {
        int reg = 0;
        if (!isSept()) {
            switch (getInversion()) {
                case 0:
                    reg = 0;
                    break;
                case 1:
                    reg = 1;
                    break;
                case 2:
                    reg = 2;
                    break;
            }
        } else {
            switch (getInversion()) {
                case 0:
                    reg = 3;
                    break;
                case 1:
                    reg = 4;
                    break;
                case 2:
                    reg = 5;
                    break;
                case 3:
                    reg = 6;
                    break;
            }
        }
        return reg;
    }

    /**
     * Regresa la nota duplicada, 1,3,5,7 o ninguna: 0
     *
     */
    public int getDuplicacion() {
        return duplicacion;
    }

    /**
     * Imprime la informacion del acordeNots
     */
    public void printAcorde() {
        String var = "";
        var += "---------------------------\n";
        for (int i = 0; i < voces.size(); i++) {
            var += "Voz(" + i + "): " + Oper.num2notaMIDI(getVoces().get(i), 's') + " parte " + Float.toString(vocesTipo.get(i)) + "\n";
        }
        var += "Cruce Voces: " + getCurceVocesVF() + "\n";
        var += "Fundamental: " + getFund12() + "\n";
        var += "Tipo: " + getTipoAcStr() + "\n";
        var += "InversionStr: " + getInversionStr() + "\n";
        var += "Inversion: " + getInversion() + "\n";
        var += "Disposicion: " + getDisposicion() + "\n";
        var += "Pos.Melodica: " + getPosMel() + "\n";
        var += "Duplicacion: " + getDuplicacion() + "\n";
        var += "Completo: " + isCompleto() + "\n";

        var += "---------------------------\n";
        System.out.println(var);
    }

    /**
     * Imprime la informacion del acordeNots
     */
    public String stringAcorde(char acc) {
        String var = "";
        var += nombreAcorde(acc) + "\n";
        var += "---------------------------\n";
        var += "Cruce Voces: " + getCurceVocesVF() + "\n";
        var += "Fundamental: " + getFund12() + "\n";
        var += "Tipo: " + getTipoAcStr() + "\n";
        var += "Inversion: " + getInversionStr() + "\n";
        var += "Disposicion: " + getDisposicion() + "\n";
        var += "Pos.Melodica: " + getPosMel() + "\n";
        var += "---------------------------\n";
        if (voces.size() > 0 && vocesTipo.size() > 0) {
            for (int i = voces.size() - 1; i >= 0; i--) {
                try {
                    var += "Voz(" + i + "): " + Oper.num2nota12(voces.get(i), 's') + " parte " + Float.toString(vocesTipo.get(i)) + "\n";
                } catch (Exception e) {
                }
            }
        }

        return var;
    }

    /**
     * Imprime la informacion del acordeNots
     */
    public String stringAcorde(UniversoTonal univ) {
        String var = "";
        var += "----------------------------------------------\n";
        var += "Tonalidad: " + univ.getEscAct().getNombreEsc() + "\n";
        var += "Region: " + univ.gradoAcordeRomSecEscAct(this) + "\n";
//        var += "Cruce Voces: " + getCurceVocesVF() + "\n";
        var += "Nombre: " + Oper.num2nota12(this.getFund(), univ.getEscAct());
        var += "" + getTipoAcStr() + "";
        var += " " + getInversionStr() + "\n";
//        var += "Disposicion: " + getDisposicion() + "\n";
//        var += "Pos.Melodica: " + getPosMel() + "\n";
        var += "----------------------------------------------\n" + "Notas:\n";
        if (voces.size() > 0 && vocesTipo.size() > 0) {
            for (int i = voces.size() - 1; i >= 0; i--) {
                try {
                    var += "" + Oper.num2notaMIDI(voces.get(i).intValue(), univ.getEscAct()) + " parte " + Integer.toString(vocesTipo.get(i).intValue()) + " (" + Float.toString(voces.get(i)) + ")\n";
//                    var += "Voz(" + i + "): " + Float.toString(voces.get(i))
//                            + " " + esc.num2nota(voces.get(i).intValue())
//                            + " parte " + Float.toString(vocesTipo.get(i)) + "\n";
                } catch (Exception e) {
                }
            }
        }

        return var;
    }
}

class CompararFloats implements Comparator {

    public int compare(Object en1, Object en2) {

        /*
         * parameter are of type Object, so we have to downcast it to Employee
         * objects
         */
        float emp1 = ((Float) en1);
        float emp2 = ((Float) en2);

        if (emp1 > emp2) {
            return 1;
        } else if (emp1 < emp2) {
            return -1;
        } else {
            return 0;
        }
    }
}

class CompararFloatsInv implements Comparator {

    public int compare(Object en1, Object en2) {

        /*
         * parameter are of type Object, so we have to downcast it to Employee
         * objects
         */
        float emp1 = ((Float) en1);
        float emp2 = ((Float) en2);

        if (emp1 > emp2) {
            return -1;
        } else if (emp1 < emp2) {
            return 1;
        } else {
            return 0;
        }
    }
}
