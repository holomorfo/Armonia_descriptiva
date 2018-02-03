/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import polifoniaLib.defs.DefEnlace;
import java.io.Serializable;

/**
 *
 * @author Cristian
 */
public class Enlace implements Serializable {

    DefEnlace DefEn;
    UniversoTonal univ = null;
    Armonia ar1;
    Armonia ar2;
    int region1 = 0, region2 = 0;
    boolean paralelasQ;
    int puntos = 0;
    boolean cantadoVF = false;
    int distancia;
    int gravMel = 0;
    int gravArm = 0;
    int enlaceTip = -1; // 0-armonico, 1-melodico, 2-traslado
//    String enlaceTip = "x"; // 0-armonico, 1-melodico, 2-traslado
    String enlacStrCort = "x";
    String enlacStrLarg = "Ninguno";
    String paralelasQuien = "";
    int sop = 0, alt = 0, ten = 0, bas = 0, sopD = 0, altD = 0, tenD = 0, basD = 0;

    public Enlace() {
        DefEn = new DefEnlace();
        ar1 = new Armonia(0);
        ar2 = new Armonia(0);
        setDistancia(ar1.distancia(ar2));
        setParalelas();
        setEnlace();
    }

    /**
     *
     * @param Ab
     * @param Ac
     */
    public Enlace(Armonia arm1, Armonia arm2) {
        DefEn = new DefEnlace();
        if (arm1.getVoces().size() == arm2.getVoces().size()) {
            ar1 = arm1;
            ar2 = arm2;
            // setVozFija();
            // setPrepX7();
            // setPrepViiMay(baseEsc)
        } else {
            for (int i = 0; i < 300; i++) {
                System.out.println("ERROR... DIMENSIONES DE ARMONIA");
            }
        }

        if (ar1.getVoces().size() == 4) {
            // direccion de las voces; +arriba, - abajo
            // cuidado si la distancia es cero
            sop = (int) Math.signum(ar2.getVoces().get('S') - ar1.getVoces().get('S'));
            alt = (int) Math.signum(ar2.getVoces().get('A') - ar1.getVoces().get('A'));
            ten = (int) Math.signum(ar2.getVoces().get('T') - ar1.getVoces().get('T'));
            bas = (int) Math.signum(ar2.getVoces().get('B') - ar1.getVoces().get('B'));
            sopD = (int) Math.abs(ar2.getVoces().get('S') - ar1.getVoces().get('S'));
            altD = (int) Math.abs(ar2.getVoces().get('A') - ar1.getVoces().get('A'));
            tenD = (int) Math.abs(ar2.getVoces().get('T') - ar1.getVoces().get('T'));
            basD = (int) Math.abs(ar2.getVoces().get('B') - ar1.getVoces().get('B'));
        }
        setDistancia(ar1.distancia(ar2));
        setParalelas();
        setEnlace();
    }

    /**
     *
     * @param Ab
     * @param Ac
     */
    public Enlace(Armonia arm1, Armonia arm2, UniversoTonal uni) {
        DefEn = new DefEnlace();

        if (arm1.getVoces().size() == arm2.getVoces().size()) {
            univ = uni;
            ar1 = arm1;
            ar2 = arm2;
            // setVozFija();
            // setPrepX7();
            // setPrepViiMay(baseEsc)
        } else {
            for (int i = 0; i < 300; i++) {
                System.out.println("ERROR... DIMENSIONES DE ARMONIA");
            }
        }
        if (ar1.getVoces().size() == 4) {
            // direccion de las voces; +arriba, - abajo
            // cuidado si la distancia es cero
            sop = (int) Math.signum(ar2.getVoces().get('S') - ar1.getVoces().get('S'));
            alt = (int) Math.signum(ar2.getVoces().get('A') - ar1.getVoces().get('A'));
            ten = (int) Math.signum(ar2.getVoces().get('T') - ar1.getVoces().get('T'));
            bas = (int) Math.signum(ar2.getVoces().get('B') - ar1.getVoces().get('B'));
            sopD = (int) Math.abs(ar2.getVoces().get('S') - ar1.getVoces().get('S'));
            altD = (int) Math.abs(ar2.getVoces().get('A') - ar1.getVoces().get('A'));
            tenD = (int) Math.abs(ar2.getVoces().get('T') - ar1.getVoces().get('T'));
            basD = (int) Math.abs(ar2.getVoces().get('B') - ar1.getVoces().get('B'));
        }
        setDistancia(ar1.distancia(ar2));
        setParalelas();
        setEnlace();
    }

    /**
     * Establece si es enlaceTip 0-armonico, 1-melodico, 2-traslado directo
     * 7-acorde cadencial.
     *
     * @param
     */
    private void setEnlace() {
        if (ar1.getVoces().size() == 4) {
            // Enlaces de acuerdo a los temas de clase
            // Grados I,IV,V
            // armonico, melódico, traslados, inversiones, saltos
//            chkBasicos();
            // Demas grados y septimos
            // D7, inversiones y saltos
//            chkDominante();
            // II, inversiones y II7
//            chkIIgrado();
            // VI
//            chkVIgrado();
            // VII, sexto y VII7
//            chkVIIgrado();
            // III
//            chkIIIgrado();

//            if (univ != null) {
//                // Gravedad Mel 0-(30)
//                gravMel = univ.chkGravedadMel(this);
//                // Gravedad Arm  0-5
//                gravArm = univ.chkGravedadArm(this);
//            }

            // Raros de dominante D9 y D(6)
//            int pts = 0;
//            switch (ar1.DistMaxVoz(ar2)) {
//                case 1:
//                    pts = 10;
//                    break;
//                case 2:
//                    pts = 5;
//                    break;
//                case 3:
//                    pts = 5;
//                    break;
//                case 4:
//                    pts = 5;
//                    break;
//            }
//            chkCantado();
//            if (cantadoVF) {
//                pts += 15;
//            }
//            puntos = gravArm + gravMel + pts;


            chkParalelas();
            revisionCool();
        }// Fin voces tamanio 4
//        setEnlaceStr();
    }

    public void chkBasicos() {
        // Grados I,IV,V
        // armonico, melódico, traslados, inversiones, saltos
        if (univ != null) {
            //Primero acordes NO septimos
            if (!ar1.isSept() & !ar2.isSept()) {
//                if ((univ.gradoArmSens(ar1) == 1 || univ.gradoArmSens(ar1) == 4 || univ.gradoArmSens(ar1) == 5)
//                        || (univ.gradoArmSens(ar2) == 1 || univ.gradoArmSens(ar2) == 4 || univ.gradoArmSens(ar2) == 5)) {

                // SIN INVERSION
                // Armonico: sonido comun, demas voces paralelas a los mas cercanos
                //  duplicacion del sonido fundamental. Misma disposicion.
                // solo para acordes que no son inversiones
                if (ar1.getInversion() == 0 & ar2.getInversion() == 0) {
                    if (ar1.getDisposicion() == ar2.getDisposicion()) {// Misma disp
                        // falta lo de duplicacion del sonido fundamental y que sea
                        // la mas cercana
                        if (sop == 0 & ten == alt) {// Voces paralelas
                            enlaceTip = DefEn.gIdx("Arm");// armonico
                        } else if (alt == 0 & ten == sop) {// Voces paralelas
                            enlaceTip = DefEn.gIdx("Arm");
                            ;// armonico
                        } else if (ten == 0 & alt == sop) {// Voces paralelas
                            enlaceTip = DefEn.gIdx("Arm");
                            ;// armonico
                        } else if (sop == 0 & alt == 0) {
                            enlaceTip = DefEn.gIdx("Arm");
                            ;// armonico
                        } else if (sop == 0 & ten == 0) {
                            enlaceTip = DefEn.gIdx("Arm");
                            ;// armonico
                        } else if (alt == 0 & ten == 0) {
                            enlaceTip = DefEn.gIdx("Arm");
                            ;// armonico
                        }

                    }

                    // Melodico: Bajo, intervalo no mayor a la cuarta, o segunda
                    //  demas voces en direccion contraria, proximo sin saltos
                    //  misma disposicion.
                    if (ar1.getDisposicion() == ar2.getDisposicion()) {// Misma disp
                        // falta lo de duplicacion del sonido fundamental y que sea la mas cercana
                        if (basD <= 5) {// bajo no > que 4ta
                            if (bas != 0 & ten != 0 & alt != 0 & sop != 0) {//no tono comun
                                if (bas != sop & sop == alt & sop == ten) {// demas voces paralelas contrarias
                                    if (sopD <= 4 && altD <= 4 & altD <= 4 && tenD <= 4) { // Sin saltos
                                        enlaceTip = DefEn.gIdx("Mel");
                                        ;// armonico
                                    }
                                }
                            }
                        }
                    }

                    // Traslado directo: soprano sube o baja tercera cuarta al próximo
                    //  alto y tenor misma direccion. Misma disposicion, sin inversion
                    if (ar1.getDisposicion() == ar2.getDisposicion()) {// Misma disp
                        if (ar2.getInversion() == 0 & ar2.getSept() < 0) {// sin septimos
                            if (ar1.getFund12() == ar2.getFund12()) {// si se queda en el mismo grado
                                if (sopD <= 5) {// soprano se mueve tercera o cuarta
                                    if (sop == alt & sop == ten) {// misma direccion
                                        enlaceTip = DefEn.gIdx("Tdir");// traslado directo
                                    }
                                }
                            }
                        }
                    }

                    // Traslado contrario: soprano sube o baja tercera cuarta al próximo
                    // alto se queda, tenor opuesto al soprano. Cambia disposicion
                    // falta corregir que no marque octavas paralelas en tralsados
                    if ((ar1.getDisposicion() == 0 && ar2.getDisposicion() == 1) || (ar1.getDisposicion() == 1 && ar2.getDisposicion() == 0)) {// Cambia disp, de 0-1, o 1-0
                        if (ar2.getInversion() == 0 & ar2.getSept() < 0) {// Sin septimos
                            if (ar1.getFund12() == ar2.getFund12()) {// si se queda en el mismo grado
                                if (ar1.getVoces().get('A') == ar2.getVoces().get('A')) {// Alto misma nota
                                    if (sop != ten & sop != 0 & ten != 0) {// Soprano direccion contraria del tenor (no se si afecta nota comun)
                                        enlaceTip = DefEn.gIdx("Tcon"); // traslado contrario
                                    }
                                }
                            }
                        }
                    }

                    // Traslado oblicuo: Sop sube o baja 5ta o 6ta al próximo sonido
                    //  alto misma direccion, tenor queda. Cambia disposicion.
                    if ((ar1.getDisposicion() == 0 && ar2.getDisposicion() == 1) || (ar1.getDisposicion() == 1 && ar2.getDisposicion() == 0)) {// Cambia disp, de 0-1, o 1-0
                        if (ar2.getInversion() == 0 & ar2.getSept() < 0) {// Sin septimos
                            if (ar1.getFund12() == ar2.getFund12()) {// si se queda en el mismo grado
                                if ((sopD >= 6) & (sopD <= 10)) {// Soprano entre quitna o sexta
                                    if (alt == sop && ten == 0) {// Alto en la misma direccion y tenor queda en la misma nota
                                        enlaceTip = DefEn.gIdx("Tobl");// traslado oblicui
                                    }
                                }
                            }
                        }
                    }

                    // Salto de terceras soprano
                    // terera del acorde primero pasa a tercera del acorde segundo
                    // cambio de disposición
                    if (ar1.getFund12() != ar2.getFund12()) {// Que no sea traslado
                        if ((ar1.getDisposicion() == 0 && ar2.getDisposicion() == 1) || (ar1.getDisposicion() == 1 && ar2.getDisposicion() == 0)) {// Cambia disp, de 0-1, o 1-0
                            if (ar1.getVocesTipo().get('S') == 3 & ar2.getVocesTipo().get('S') == 3) {// Sin septimos
                                enlaceTip = DefEn.gIdx("Sop3");// terceras en soprano
                            }
                        }
                        // Salto de terceras tenor
                        // terera del acorde primero pasa a tercera del acorde segundo
                        // cambio de disposición
                        // Quiza poner que las melodias van en sentido contrario
                        if ((ar1.getDisposicion() == 0 && ar2.getDisposicion() == 1) || (ar1.getDisposicion() == 1 && ar2.getDisposicion() == 0)) {// Cambia disp, de 0-1, o 1-0
                            if (ar1.getVocesTipo().get('T') == 3 & ar2.getVocesTipo().get('T') == 3) {// Sin septimos
                                enlaceTip = DefEn.gIdx("Ten3");// terceras en tenor
                            }
                        }
                    }
                }// FIN Solo acordes sin inversion

                // CON INVERSION
                // Preparacion cadencial
                if (univ.gradoArmSencEscActual(ar2) == 1 & ar2.getInversion() == 2 & ar2.getDuplicacion() == 5) {
                    //Resolviendo en acorde quinto
                    //Falta poner que se queden fijos los quintos
                    if (univ.gradoArmSencEscActual(ar1) == 4 & ar1.getInversion() != 2) {
                        enlaceTip = DefEn.gIdx("PCad");// terceras en soprano
                    }
                }

                // Acorde cadencial
                // Segunda inversidon de la tonica
                // Bajo duplicado(es decir el grado V)
                if (univ.gradoArmSencEscActual(ar1) == 1 & ar1.getInversion() == 2 & ar1.getDuplicacion() == 5) {
                    //Resolviendo en acorde quinto
                    //Falta poner que se queden fijos los quintos
                    if (univ.gradoArmSencEscActual(ar2) == 5 & ar2.getDuplicacion() == 1) {
                        enlaceTip = DefEn.gIdx("Rcad");// terceras en soprano
                    }
                }


                // Armonico con inversion, correlacion 4-5
                // Si la tonica se mueve una cuarta o quinta
                // para que sea armonico debe ser ligado y sin saltos
                if ((ar1.getInversion() == 1 || ar2.getInversion() == 1)
                        & ar1.getInversion() != ar2.getInversion()) {// Si es inversion, pero no ambos
                    float dis = Math.abs((ar1.getFund12() - ar2.getFund12()) % 12);
                    if (5 <= dis & dis <= 7) {// Cuarta o quinta
                        // Sin saltos
                        if ((Math.abs(ar1.getVoces().get('S') - ar2.getVoces().get('S')) <= 4
                                & Math.abs(ar1.getVoces().get('A') - ar2.getVoces().get('A')) <= 4
                                & Math.abs(ar1.getVoces().get('T') - ar2.getVoces().get('T')) <= 4)
                                & (sop == 0 || alt == 0 || ten == 0)) {
                            enlaceTip = DefEn.gIdx("Arm6");
                        }
                    }

                }


                // Armonico con inversion, correlacion 2
                // Si la tonica se mueve una cuarta o quinta
                // para que sea armonico debe ser ligado y sin saltos
                if ((ar1.getInversion() == 1 & univ.gradoArmSencEscActual(ar1) == 4)
                        & (ar2.getInversion() == 0 & univ.gradoArmSencEscActual(ar2) == 5)) {// Si es inversion
                    // Sin saltos
                    if (Math.abs(ar1.getVoces().get('S') - ar2.getVoces().get('S')) <= 4
                            & Math.abs(ar1.getVoces().get('A') - ar2.getVoces().get('A')) <= 4
                            & Math.abs(ar1.getVoces().get('T') - ar2.getVoces().get('T')) <= 4) {
                        enlaceTip = DefEn.gIdx("Arm6");
                    }
                }


                // Inversiones Armonicas - ligadas en general
                // Si la tonica se mueve una cuarta o quinta
                // para que sea armonico debe ser ligado y sin saltos
                if ((ar1.getInversion() == 1 || ar2.getInversion() == 1)
                        & ar1.getInversion() != ar2.getInversion()) {// Si es inversion, pero no ambos
                    // Sin saltos
                    if (Math.abs(ar1.getVoces().get('S') - ar2.getVoces().get('S')) <= 4
                            & Math.abs(ar1.getVoces().get('A') - ar2.getVoces().get('A')) <= 4
                            & Math.abs(ar1.getVoces().get('T') - ar2.getVoces().get('T')) <= 4) {
                        enlaceTip = DefEn.gIdx("Arm6");
                    }
                }

                // Traslado con inversiones
                // Si la tonica se mueve una cuarta o quinta
                // para que sea armonico debe ser ligado y sin saltos
                // No se si afecta que el traslado pueda ser de invertido a invertido
                if ((ar1.getFund12() == ar2.getFund12())
                        & (ar1.getInversion() == 1 || ar2.getInversion() == 1)) {// Si es traslado
                    if (alt == 0 || ten == 0) {// Alguna nota comun
                        enlaceTip = DefEn.gIdx("Tra6");
                    }
                }

                // Saltos asc en soprano elace de acordes sexta
                // La correlacion 4-5 viene dada con que el bajo no se mueva mas de una tercera.
                if ((ar1.getInversion() == 1 || ar2.getInversion() == 1)// si alguno es sexto
                        & ar1.getInversion() != ar2.getInversion()) {// pero no los dos
                    if (ar1.getFund12() != ar2.getFund12()) {//que no sea traslado
                        //Fundamental o quinta del primero a fundamental del segundo en el soprano
                        if ((ar1.getVocesTipo().get('S') == ar2.getVocesTipo().get('S'))
                                & (ar1.getVocesTipo().get('S') == 1 || ar1.getVocesTipo().get('S') == 5)) {
                            if (altD <= 4 & tenD <= 4 & basD <= 4) {// Las demas voces ligadas, menor a tercera
                                enlaceTip = DefEn.gIdx("Slt6");
                            }
                        }
                    }
                }

                // Saltos asc en alto elace de acordes sexta
                // La correlacion 4-5 viene dada con que el bajo no se mueva mas de una tercera.
                if ((ar1.getInversion() == 1 || ar2.getInversion() == 1)// si alguno es sexto
                        & ar1.getInversion() != ar2.getInversion()) {// pero no los dos
                    if (ar1.getFund12() != ar2.getFund12()) {//que no sea traslado
                        //Fundamental o quinta del primero a fundamental del segundo en el soprano
                        if ((ar1.getVocesTipo().get('A') == ar2.getVocesTipo().get('A'))
                                & (ar1.getVocesTipo().get('A') == 1 || ar1.getVocesTipo().get('A') == 5)) {
                            if (sopD <= 4 & tenD <= 4 & basD <= 4) {// Las demas voces ligadas, menor a tercera
                                enlaceTip = DefEn.gIdx("Slt6");
                            }
                        }
                    }
                }
                // Saltos asc en tenor elace de acordes sexta
                // La correlacion 4-5 viene dada con que el bajo no se mueva mas de una tercera.
                if ((ar1.getInversion() == 1 || ar2.getInversion() == 1)// si alguno es sexto
                        & ar1.getInversion() != ar2.getInversion()) {// pero no los dos
                    if (ar1.getFund12() != ar2.getFund12()) {//que no sea traslado
                        //Fundamental o quinta del primero a fundamental del segundo en el soprano
                        if ((ar1.getVocesTipo().get('T') == ar2.getVocesTipo().get('T'))
                                & (ar1.getVocesTipo().get('T') == 1 || ar1.getVocesTipo().get('T') == 5)) {
                            if (sopD <= 4 & altD <= 4 & basD <= 4) {// Las demas voces ligadas, menor a tercera
                                enlaceTip = DefEn.gIdx("Slt6");
                            }
                        }
                    }
                }

                // Enlace de dos acordes de sexta
                // (Puede ser Correlacion 4-5)
                if ((ar1.getInversion() == 1 & ar2.getInversion() == 1)) {// si alguno es sexto
                    // Revisar que solo haya un salto
                    if (sopD <= 4 && altD <= 4 && tenD <= 4) {
                        enlaceTip = DefEn.gIdx("6-6");
                    } else if (sopD <= 4 && altD <= 4 && tenD > 4) {
                        enlaceTip = DefEn.gIdx("6-6");
                    } else if (sopD <= 4 && tenD <= 4 && altD > 4) {
                        enlaceTip = DefEn.gIdx("6-6");
                    } else if (altD <= 4 && tenD <= 4 && sopD > 4) {
                        enlaceTip = DefEn.gIdx("6-6");
                    }
                }

                // Enlace de dos acordes de sexta
                // (Puede ser Correlacion 2)
                if ((ar1.getInversion() == 1 & ar2.getInversion() == 1)) {// si alguno es sexto
                    if ((univ.gradoArmSencEscActual(ar1) == 4 & ar1.getDuplicacion() == 1)
                            || (univ.gradoArmSencEscActual(ar1) == 5 & ar1.getDuplicacion() == 5)) {
                        // duplicacion de quinta en la dominante, y fund en la sub
                        if ((univ.gradoArmSencEscActual(ar2) == 4 & ar2.getDuplicacion() == 1)
                                || (univ.gradoArmSencEscActual(ar2) == 5 & ar2.getDuplicacion() == 5)) {
                            enlaceTip = DefEn.gIdx("6-6b");
                        }
                    }
                }

                // Falta analizar los saltos t6-D6, y s6-D6
                // para cuidar evitar la quinta aumentada en favor de la
                // cuarta disminuida. Asi como lo de cambiar S6 en vez de s6
                if (univ.getEscAct().getTipoEsc() == 'm') {
                    if ((univ.gradoArmSencEscActual(ar1) == 1 & ar1.getInversion() == 1)// I6
                            & (univ.gradoArmSencEscActual(ar2) == 5 & ar1.getInversion() == 1)// V6
                            & (Math.abs(ar1.getVoces().get('B') - ar2.getVoces().get('B')) == 4)) {// paso decuarta aumentada
                        enlaceTip = DefEn.gIdx("6-6c");
                    }
                    if ((univ.gradoArmSencEscActual(ar1) == 5 & ar1.getInversion() == 1)// I6
                            & (univ.gradoArmSencEscActual(ar2) == 1 & ar1.getInversion() == 1)// V6
                            & (Math.abs(ar1.getVoces().get('B') - ar2.getVoces().get('B')) == 4)) {// paso decuarta aumentada
                        enlaceTip = DefEn.gIdx("6-6c");
                    }
                    if ((univ.gradoArmSencEscActual(ar1) == 4 & ar1.getInversion() == 1 & ar1.getTipoAcStr().equals("M"))// S6
                            & (univ.gradoArmSencEscActual(ar2) == 5 & ar1.getInversion() == 1)// V6
                            & (Math.abs(ar1.getVoces().get('B') - ar2.getVoces().get('B')) == 4)) {// paso decuarta aumentada
                        enlaceTip = DefEn.gIdx("6-6d");
                    }
                }

                // Acordes 6,4
                // Revisar si alguno de los acordes es de 6,4
                // Revisar si la diferencia en el bajo es de maximo 2 semitonos
                if ((ar1.getInversion() == 2 & ar1.getDuplicacion() == 5)
                        || (ar2.getInversion() == 2 & ar2.getDuplicacion() == 5)
                        & (ar1.getInversion() != ar2.getInversion())
                        & ar1.getFund12() != ar2.getFund12()) {
                    if (basD <= 2) {
                        enlaceTip = DefEn.gIdx("6,4Pas");
                    }
                }

            }
        }
    }

    public void chkDominante() {
        // Preparacion D7
        // revisar si el segundo acorde es septimo, de grado 5 y se prepara
        if (ar2.isSept() & ar2.getDuplicacion() != 7 & univ.gradoArmSencEscActual(ar2) == 5) {
            int quien7 = ar2.getQuien(7).getFirst();
            // revisar si el movimiento es de paso, puede ser de tercera.
            if (Math.abs(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) < 4) {
                enlaceTip = DefEn.gIdx("D7Prep");
            }
            // puede ser movimiento de salto, pero tiene que ser ascendente
            if (ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7) > 4
                    & ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7) < 12) {
                enlaceTip = DefEn.gIdx("D7Prep");
            }
            // Si la disonancia esta repetida, es disonancia preparada
            if (ar2.getVoces().get(quien7) == ar1.getVoces().get(quien7)) {
                enlaceTip = DefEn.gIdx("D7PrepB");
            }
            // S-D7, con D7 incompleta
            if (univ.gradoArmSencEscActual(ar1) == 4 & univ.gradoArmSencEscActual(ar2) == 5
                    & ar2.isCompleto() == false) {
                enlaceTip = DefEn.gIdx("S-D7*");
            }
            //&ar2.getQuien(7).getFirst()!=-1){
        }

        // D7-T*5
        // Revisar si el primer acorde es septimo, de grado 5
        // y se resuelve a la tonica incompleta sin quinta
        if (ar1.isSept() & ar1.getDuplicacion() != 7 & ar1.getInversion() == 0
                & univ.gradoArmSencEscActual(ar1) == 5 & ar1.isCompleto() == true
                & ar2.isCompleto() == false & univ.gradoArmSencEscActual(ar2) == 1
                & ar2.getDisposicion() == 4) {
            // Resolucion completa de D7
            // Quien tenga 7 va al grado 3 descendiendo
            // Quien tenga 5, se va al primer grado
            // Quien tenga 3, sube al grado 1
            // Quien tenga 1, va grado 1 por salto
            // Se resuelve a tonica incompleto sin quinta
            int quien7 = ar1.getQuien(7).getFirst();
            int quien5 = ar1.getQuien(5).getFirst();
            int quien3 = ar1.getQuien(3).getFirst();
            int quien1 = ar1.getQuien(1).getFirst();
            if ((ar2.getTipoVoces().get(quien7) == 3
                    & Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
                    & (ar2.getTipoVoces().get(quien5) == 1)
                    & (ar2.getTipoVoces().get(quien3) == 1
                    & Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == 1)
                    & (ar2.getTipoVoces().get(quien1) == 1
                    & Math.abs(ar2.getVoces().get(quien1) - ar1.getVoces().get(quien1)) > 0)) {
                enlaceTip = DefEn.gIdx("D7-T*5");
            }
        }

        // D7-T
        // Revisar si el primer acorde es septimo, de grado 5
        // y se resuelve a la tonica completa
        if (ar1.isSept() & ar1.getDuplicacion() != 7 & ar1.getInversion() == 0
                & univ.gradoArmSencEscActual(ar1) == 5 & ar1.isCompleto() == true
                & ar2.isCompleto() == true & univ.gradoArmSencEscActual(ar2) == 1
                & ar2.getDisposicion() >= 0) {
            // Resolucion completa de D7
            // Quien tenga 7 va al grado 3 descendiendo
            // Quien tenga 5, se va al primer grado
            // Quien tenga 3, sube al grado 1
            // Quien tenga 1, va grado 1 por salto
            // Se resuelve a tonica incompleto sin quinta
            int quien7 = ar1.getQuien(7).getFirst();
            int quien5 = ar1.getQuien(5).getFirst();
            int quien3 = ar1.getQuien(3).getFirst();
            int quien1 = ar1.getQuien(1).getFirst();
            if (quien7 > 0 && quien5 > 0 && quien3 > 0 && quien1 > 0) {
                if ((ar2.getTipoVoces().get(quien7) == 3
                        & Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
                        & (ar2.getTipoVoces().get(quien5) == 1)
                        & (ar2.getTipoVoces().get(quien3) == 5
                        & Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == -1)
                        & (ar2.getTipoVoces().get(quien1) == 1
                        & Math.abs(ar2.getVoces().get(quien1) - ar1.getVoces().get(quien1)) > 0)) {
                    enlaceTip = DefEn.gIdx("D7-T5");
                }
            }
        }


        // D7*5-T
        // Revisar si el primer acorde es septimo, de grado 5
        // y se resuelve a la tonica completa
        if (ar1.isSept() & ar2.getDuplicacion() != 7 & ar1.getInversion() == 0
                & univ.gradoArmSencEscActual(ar1) == 5 & ar1.isCompleto() == false
                & ar2.isCompleto() == true & univ.gradoArmSencEscActual(ar2) == 1) {
            // Resolucion completa de D7
            // Quien tenga 7 va al grado 3 descendiendo
            // Quien tenga 5, se va al primer grado
            // Quien tenga 3, sube al grado 1
            // Quien tenga 1, va grado 1 por salto
            // Se resuelve a tonica incompleto sin quinta
            int quien7 = ar1.getQuien(7).getFirst();
            int quien5 = ar1.getQuien(5).getFirst();
            int quien3 = ar1.getQuien(3).getFirst();
            // Revisar que este duplicada la fundamental
            if (ar1.getQuien(1).size() == 2) {
                int quien1a = ar1.getQuien(1).get(0);// esta debe ser la duplicacion inferior(bajo)
                int quien1b = ar1.getQuien(1).get(1);// esta debe ser la duplicacion superior
                if ((ar2.getTipoVoces().get(quien7) == 3
                        & Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
                        & (ar2.getTipoVoces().get(quien3) == 1
                        & Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == 1)
                        & (ar2.getVoces().get(quien1b) == ar2.getVoces().get(quien1b)
                        & (ar2.getTipoVoces().get(quien1a) == 1)
                        & Math.abs(ar2.getVoces().get(quien1a) - ar1.getVoces().get(quien1a)) > 0)) {
                    enlaceTip = DefEn.gIdx("D7*5-T");
                }
            }
        }



        // DInver-T
        // Revisar si el primer acorde es septimo, de grado 5, inversion
        // y se resuelve a la tonica completa
        if (univ != null) {
            if (ar1.isSept() & ar1.isCompleto() & univ.gradoArmSencEscActual(ar1) == 5
                    & ar2.isCompleto() & univ.gradoArmSencEscActual(ar2) == 1
                    & ar2.getDisposicion() >= 0) {
                // Fundamental se queda en el mismo lugar
                // Tercera sube al primer grado
                // Quinta baja al primer grado
                // Septima baja al tercer grado
                int quien1 = ar1.getQuien(1).getFirst();
                int quien3 = ar1.getQuien(3).getFirst();
                int quien5 = ar1.getQuien(5).getFirst();
                int quien7 = ar1.getQuien(7).getFirst();

                if (quien1 != -1 && quien3 != -1 && quien5 != -1 && quien7 != -1) {
                    if ((ar2.getTipoVoces().get(quien3) == 1
                            & Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == 1)
                            & (ar2.getTipoVoces().get(quien7) == 3
                            & Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
                            & (ar2.getTipoVoces().get(quien5) == 1
                            & Math.signum(ar2.getVoces().get(quien5) - ar1.getVoces().get(quien5)) == -1)
                            & (ar2.getTipoVoces().get(quien1) == 5
                            & Math.signum(ar2.getVoces().get(quien1) - ar1.getVoces().get(quien1)) == 0)) {
                        enlaceTip = DefEn.gIdx("D7-T*5");
                    }
                }
            }
        }

        // D2 -T6 salto quintas
        // Revisar si el primer acorde es septimo, de grado 5, inversion
        // y se resuelve a la tonica completa
        if (univ != null) {
            if (ar1.isSept() & ar1.isCompleto() & univ.gradoArmSencEscActual(ar1) == 5
                    & ar2.isCompleto() & univ.gradoArmSencEscActual(ar2) == 1
                    & ar2.getDisposicion() >= 0) {
                // D2- T6
                // Si D7 pos mel 5
                // Si t6 pos mel 5

                if (ar1.getPosMel() == 5 & ar2.getPosMel() == 5
                        & ar1.getInversion() == 3 & ar2.getInversion() == 1) {
                    // Demas voces a pasitos
                    if (tenD <= 4 & altD <= 4 & basD <= 4) {
                        enlaceTip = DefEn.gIdx("Slt5");
                    }

                }
            }
        }

        // D2 -T6 salto quintas-primeras
        // Revisar si el primer acorde es septimo, de grado 5, inversion
        // y se resuelve a la tonica completa
        if (univ != null) {
            if (ar1.isSept() & ar1.isCompleto() & univ.gradoArmSencEscActual(ar1) == 5
                    & ar2.isCompleto() & univ.gradoArmSencEscActual(ar2) == 1
                    & ar2.getDisposicion() >= 0) {
                // D2- T6
                // Si D7 pos mel 5
                // Si t6 pos mel 5
                // si la primera de ar1 va a la primera de ar2
                int quien1 = ar1.getQuien(5).getFirst();
                //int quien5 = ar1.getQuien(5).getFirst();
                //& (ar2.getTipoVoces().get(quien1) == 5
                if (quien1 != -1) {
                    if (ar1.getPosMel() == 1 & ar2.getPosMel() == 1
                            & (ar2.getTipoVoces().get(quien1) == 5)
                            // la voz que tiene primera se queda con ella
                            & ar1.getInversion() == 3 & ar2.getInversion() == 1) {
                        // Demas voces a pasitos
                        if (basD <= 4) {
                            enlaceTip = DefEn.gIdx("Slt5-1");
                        }
                    }
                }
            }
        }
    }

    public void chkIIgrado() {
        //Tema 22 SII7

        // Preparacion de SII6, // Duplicacion del bajo
        if (univ.gradoArmSencEscActual(ar2) == 2 && ar2.getInversion() == 1 && ar2.getDuplicacion() == 3) {
            if (univ.gradoArmSencEscActual(ar1) == 1 || univ.gradoArmSencEscActual(ar1) == 4) {
                enlaceTip = DefEn.gIdx("Pr-SII6");
            }
        }
        // Resolución de SII6
        if (univ.gradoArmSencEscActual(ar1) == 2 && ar1.getInversion() == 1 && ar1.getDuplicacion() == 3) {
            // Se resuelve a D, D6, D7 o K46
            if (univ.gradoArmSencEscActual(ar2) == 5 || (univ.gradoArmSencEscActual(ar2) == 1 && ar2.getInversion() == 2)) {
                enlaceTip = DefEn.gIdx("SII6-Rs");
            }
        }
        // Preparacion de SII
        if (univ.gradoArmSencEscActual(ar2) == 2 && ar2.getInversion() == 0) {
            // Va antes de S, T6, T
            if (univ.gradoArmSencEscActual(ar1) == 4 || univ.gradoArmSencEscActual(ar1) == 1) {
                enlaceTip = DefEn.gIdx("Pr-SII");
            }
        }
        // Resolución de SII
        if (univ.gradoArmSencEscActual(ar2) == 2 && ar2.getInversion() == 0) {
            if (univ.gradoArmSencEscActual(ar2) == 5 || (univ.gradoArmSencEscActual(ar2) == 1 && ar2.getInversion() == 2)) {
                enlaceTip = DefEn.gIdx("SII-Rs");
            }
        }

        // Septimos del grado II
        // Preparacion de SII7 e inversiones
        if (univ.gradoArmSencEscActual(ar2) == 2 && ar2.isSept()) {
            // despues de T,T6,T46,S,S6,TSVI,SII,SII6
            if (univ.gradoArmSencEscActual(ar1) == 1
                    || univ.gradoArmSencEscActual(ar1) == 4
                    || univ.gradoArmSencEscActual(ar1) == 6
                    || (univ.gradoArmSencEscActual(ar1) == 2 && !ar1.isSept())) {
                enlaceTip = DefEn.gIdx("Pr-SII7");
            }
        }
        // Resolucion SII7 a V
        if (univ.gradoArmSencEscActual(ar1) == 2 && ar1.isSept() && univ.gradoArmSencEscActual(ar2) == 5 && !ar2.isSept()) {
            // Se resuelve a V, pero falta corregir la conduccion de voces
            // SII2 va a V6 los demas a V
            if ((ar1.getInversion() == 3 && ar2.getInversion() == 0)
                    || (ar1.getInversion() != 3 && ar2.getInversion() == 1)) {
                enlaceTip = DefEn.gIdx("SII7-V");
            }
        }

        // Resolucion SII7 a K46
        if (univ.gradoArmSencEscActual(ar1) == 2 && ar1.isSept() && ar1.getInversion() != 3
                && univ.gradoArmSencEscActual(ar2) == 1 && ar2.getInversion() == 2 && !ar2.isSept()) {
            enlaceTip = DefEn.gIdx("SII7-K");
        }

        // Resolucion SII7 a T
        if (univ.gradoArmSencEscActual(ar1) == 2 && ar1.isSept() && univ.gradoArmSencEscActual(ar2) == 1 && !ar2.isSept()) {
            if ((ar1.getInversion() == 0 && ar2.getInversion() == 1)
                    || (ar1.getInversion() == 1 && ar2.getInversion() == 0)
                    || (ar1.getInversion() == 1 && ar2.getInversion() == 1)
                    || (ar1.getInversion() == 1 && ar2.getInversion() == 2)
                    || (ar1.getInversion() == 2 && ar2.getInversion() == 2)
                    || (ar1.getInversion() == 2 && ar2.getInversion() == 0)) {
                enlaceTip = DefEn.gIdx("SII7-T");
            }
        }
        // Paso de SII7 a D7 e inversiones
        if (univ.gradoArmSencEscActual(ar1) == 2 && ar1.isSept()
                && univ.gradoArmSencEscActual(ar2) == 5 && ar2.isSept()) {
            if ((ar1.getInversion() == 0 && ar2.getInversion() == 2)
                    || (ar1.getInversion() == 1 && ar2.getInversion() == 3)
                    || (ar1.getInversion() == 2 && ar2.getInversion() == 0)
                    || (ar1.getInversion() == 3 && ar2.getInversion() == 1)) {
                enlaceTip = DefEn.gIdx("SII7-D7");
            }
        }
        // Paso de SII - SII2 de paso
        if (univ.gradoArmSencEscActual(ar1) == 2 && !ar1.isSept()
                && univ.gradoArmSencEscActual(ar2) == 1 && !ar2.isSept() && ar2.getInversion() == 3) {
            enlaceTip = DefEn.gIdx("SII-SII2");
        }


//        if (ar1.getInversion() == 0 & ar1.isSept() & ar1.isCompleto() & univ.gradoArmSens(ar1) == 2
//                & ar2.isCompleto() & univ.gradoArmSens(ar2) == 5
//                & ar2.getDisposicion() >= 0) {
//            // La septima desciende un grado
//            // Tercera sube un grado o baja una tercera
//            // Quinta baja un grado
//            // fundamental se queda si es inversion, se va a la fund si no
//            int quien1 = ar1.getQuien(1).getFirst();
//            int quien3 = ar1.getQuien(3).getFirst();
//            int quien5 = ar1.getQuien(5).getFirst();
//            int quien7 = ar1.getQuien(7).getFirst();
//            if ((ar2.getTipoVoces().get(quien3) == 1
//                    & Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == 1)
//                    & (ar2.getTipoVoces().get(quien7) == 3
//                    & Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
//                    & (ar2.getTipoVoces().get(quien5) == 1
//                    & Math.signum(ar2.getVoces().get(quien5) - ar1.getVoces().get(quien5)) == -1)
//                    & (ar2.getTipoVoces().get(quien1) == 5
//                    & Math.signum(ar2.getVoces().get(quien1) - ar1.getVoces().get(quien1)) == 0)) {
//                enlaceTip = 22;//
//            }
//        }

    }

    public void chkVIgrado() {
        // Tema 21 - Giro interrumpido,
        if (univ.gradoArmSencEscActual(ar2) == 6 && ar2.getInversion() == 0) {
            if (univ.gradoArmSencEscActual(ar1) == 5 && ar1.getInversion() == 0) {
                enlaceTip = DefEn.gIdx("V-VI");
            }
        }
        // Eslabon intermedio I-VI
        if (univ.gradoArmSencEscActual(ar2) == 6 && ar2.getInversion() == 0) {
            if (univ.gradoArmSencEscActual(ar1) == 1) {
                enlaceTip = DefEn.gIdx("TSVI Esl");
            }
        }
        // Eslabon intermedio VI-IV
        if (univ.gradoArmSencEscActual(ar1) == 6 && ar1.getInversion() == 0) {
            if (univ.gradoArmSencEscActual(ar2) == 4) {
                enlaceTip = DefEn.gIdx("TSVI Esl");
            }
        }
        // TSVI
        if (univ.gradoArmSencEscActual(ar1) == 6 && ar1.getInversion() == 0) {
            // Antes del dominante o el cadencial
            if (univ.gradoArmSencEscActual(ar2) == 5 || (univ.gradoArmSencEscActual(ar2) == 1 && ar2.getInversion() == 2)) {
                enlaceTip = DefEn.gIdx("TSVI sub");
            }
        }


    }

    public void chkVIIgrado() {
        // VII7 resolucion T con 3ra duplicada
        // Revisar si el primer acorde es septimo, de grado 5, inversion
        // y se resuelve a la tonica completa

        // Primer acorde VII7
        if (ar1.isSept() & ar1.isCompleto() & univ.gradoArmSencEscActual(ar1) == 7
                // Segundo acorde Tonica con tercera duplicada
                & ar2.isCompleto() & univ.gradoArmSencEscActual(ar2) == 1
                & ar2.getDuplicacion() == 3
                & ar2.getDisposicion() >= 0) {
            // Fundamental se queda en el mismo lugar
            // Tercera sube al primer grado
            // Quinta baja al primer grado
            // Septima baja al tercer grado
            int quien1 = ar1.getQuien(1).getFirst();
            int quien3 = ar1.getQuien(3).getFirst();
            int quien5 = ar1.getQuien(5).getFirst();
            int quien7 = ar1.getQuien(7).getFirst();

            if ((Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == 1)
                    & (Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
                    & (Math.signum(ar2.getVoces().get(quien5) - ar1.getVoces().get(quien5)) == -1)
                    & (Math.signum(ar2.getVoces().get(quien1) - ar1.getVoces().get(quien1)) == 1)) {
                // Si no se mueven mas de un grado
                if (sopD <= 2 & altD <= 2 & tenD <= 2 & basD <= 2) {
                    enlaceTip = DefEn.gIdx("VII7-T");
                }
            }
        }


        // VII7 resolucion D7 e inversiones
        // Revisar si el primer acorde es septimo, de grado 5, inversion
        // y se resuelve a la Dominante alguna inversion completa
        // Primer acorde VII7
        if (ar1.isSept() & ar1.isCompleto() & univ.gradoArmSencEscActual(ar1) == 7
                // Segundo acorde Dominante con tercera duplicada
                & ar2.isCompleto() & univ.gradoArmSencEscActual(ar2) == 5
                & ar2.isSept()
                & ar2.getDisposicion() >= 0) {
            // Fundamental se queda en el mismo lugar
            // Tercera sube al primer grado
            // Quinta baja al primer grado
            // Septima baja al tercer grado
            int quien1 = ar1.getQuien(1).getFirst();
            int quien3 = ar1.getQuien(3).getFirst();
            int quien5 = ar1.getQuien(5).getFirst();
            int quien7 = ar1.getQuien(7).getFirst();

            if ((Math.signum(ar2.getVoces().get(quien3) - ar1.getVoces().get(quien3)) == 0)
                    & (Math.signum(ar2.getVoces().get(quien7) - ar1.getVoces().get(quien7)) == -1)
                    & (Math.signum(ar2.getVoces().get(quien5) - ar1.getVoces().get(quien5)) == 0)
                    & (Math.signum(ar2.getVoces().get(quien1) - ar1.getVoces().get(quien1)) == 0)) {
                // Si no se mueven mas de un grado
                enlaceTip = DefEn.gIdx("VII7-D7");
            }
        }

        // T(6) - vii6
        // Primera inversion vii
        // duplicacion de la no fundamental
        if (univ.gradoArmSencEscActual(ar1) == 7 & ar1.getInversion() == 1 & ar1.getDuplicacion() != 1) {
            if (univ.gradoArmSencEscActual(ar2) == 5 & ar2.getDuplicacion() == 1) {
                enlaceTip = DefEn.gIdx("VII7-D7b");
            }
        }
    }

    public void chkIIIgrado() {
        // En movimiento descendente I-VII-VI-V
        // VII-VI
        if (univ.gradoArmSencEscActual(ar2) == 3 & ar2.getInversion() == 0) {
            float sop1 = ar1.getVoces().get(3);// get soprano
            float sop2 = ar2.getVoces().get(3);// get soprano
            if (univ.getEscAct().gradoNota(sop1) == 7 && univ.getEscAct().gradoNota(sop2) == 6) {
                enlaceTip = DefEn.gIdx("III-mel");
            }
        }
        // VI-V
        if (univ.gradoArmSencEscActual(ar1) == 3 & ar1.getInversion() == 0) {
            float sop1 = ar1.getVoces().get(3);// get soprano
            float sop2 = ar2.getVoces().get(3);// get soprano
            if (univ.getEscAct().gradoNota(sop1) == 6 && univ.getEscAct().gradoNota(sop2) == 5) {
                enlaceTip = DefEn.gIdx("III-mel");
            }
        }

    }

    public void chkMelodia() {
//        if (sopD) {
//        }
    }

    public void chkParalelas() {
        // Quintas paralelas
        // Corregir quintas dismunuidas en grado II
        // Paralelas falsas, justas a disminuidas
        if (this.isParalelasQ()) { // Si es paralelo y no es traslado.
            enlaceTip = DefEn.gIdx("58Par");
        }
    }

    public void chkCantado() {
        // Enlace cantado
        // Revisar si entre las 4 voces el movimiento es a paso.
        cantadoVF = false;
        if (sopD <= 2 && altD <= 2 && tenD <= 2 && basD <= 4) {
            // ver que no tenga saltos disonantes
            cantadoVF = true;
        }
        // Revisar melodia
    }

    /**
     * Clasifica la progresion de acurdo a quintas y octavas paralelas 1=bueno,
     * 2 =quintas u octava paralela
     *
     * // falta corregir las paralelas para armonias de 2 y 3 voces
     *
     * @return
     */
    private void setParalelas() {
        paralelasQ = false;
        if (ar1.getVoces().size() == 4
                && !(univ.gradoArmSencEscActual(ar1) == 1
                && ar1.getInversion() == 2)) {
            float BT1 = mod(Math.abs(ar1.getVoces().get('B') - ar1.getVoces().get('T')), 12);
            float BA1 = mod(Math.abs(ar1.getVoces().get('B') - ar1.getVoces().get('A')), 12);
            float BS1 = mod(Math.abs(ar1.getVoces().get('B') - ar1.getVoces().get('S')), 12);
            float TA1 = mod(Math.abs(ar1.getVoces().get('T') - ar1.getVoces().get('A')), 12);
            float TS1 = mod(Math.abs(ar1.getVoces().get('T') - ar1.getVoces().get('S')), 12);
            float AS1 = mod(Math.abs(ar1.getVoces().get('A') - ar1.getVoces().get('S')), 12);

            float BT2 = mod(Math.abs(ar2.getVoces().get('B') - ar2.getVoces().get('T')), 12);
            float BA2 = mod(Math.abs(ar2.getVoces().get('B') - ar2.getVoces().get('A')), 12);
            float BS2 = mod(Math.abs(ar2.getVoces().get('B') - ar2.getVoces().get('S')), 12);
            float TA2 = mod(Math.abs(ar2.getVoces().get('T') - ar2.getVoces().get('A')), 12);
            float TS2 = mod(Math.abs(ar2.getVoces().get('T') - ar2.getVoces().get('S')), 12);
            float AS2 = mod(Math.abs(ar2.getVoces().get('A') - ar2.getVoces().get('S')), 12);

            float gBT1 = mod(Math.abs(ar1.getVoces().get('B') - ar1.getVoces().get('T')), 12);
            float gBA1 = mod(Math.abs(ar1.getVoces().get('B') - ar1.getVoces().get('A')), 12);
            float gBS1 = mod(Math.abs(ar1.getVoces().get('B') - ar1.getVoces().get('S')), 12);
            float gTA1 = mod(Math.abs(ar1.getVoces().get('T') - ar1.getVoces().get('A')), 12);
            float gTS1 = mod(Math.abs(ar1.getVoces().get('T') - ar1.getVoces().get('S')), 12);
            float gAS1 = mod(Math.abs(ar1.getVoces().get('A') - ar1.getVoces().get('S')), 12);

            float xBT2 = mod(Math.abs(ar2.getVoces().get('B') - ar2.getVoces().get('T')), 12);
            float xBA2 = mod(Math.abs(ar2.getVoces().get('B') - ar2.getVoces().get('A')), 12);
            float xBS2 = mod(Math.abs(ar2.getVoces().get('B') - ar2.getVoces().get('S')), 12);
            float xTA2 = mod(Math.abs(ar2.getVoces().get('T') - ar2.getVoces().get('A')), 12);
            float xTS2 = mod(Math.abs(ar2.getVoces().get('T') - ar2.getVoces().get('S')), 12);
            float xAS2 = mod(Math.abs(ar2.getVoces().get('A') - ar2.getVoces().get('S')), 12);


            if (BT1 == BT2 && (BT1 == 7 || BT1 == 0)) { // Quinta perfecta paralela
                // falta revisar que no sean las mismas notas
                if (ar1.getVoces().get('B') != ar2.getVoces().get('B') & ar1.getVoces().get('T') != ar2.getVoces().get('T')) {
                    paralelasQ = true;
                    paralelasQuien = "BT";
                }
            }
            if (BA1 == BA2 && (BA1 == 7 || BA1 == 0)) { // Quinta perfecta paralela
                if (ar1.getVoces().get('B') != ar2.getVoces().get('B') & ar1.getVoces().get('A') != ar2.getVoces().get('A')) {
                    paralelasQ = true;
                    paralelasQuien = "BA";
                }
            }
            if (BS1 == BS2 && (BS1 == 7 || BS1 == 0)) { // Quinta perfecta paralela
                if (ar1.getVoces().get('B') != ar2.getVoces().get('B') & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                    paralelasQ = true;
                    paralelasQuien = "BS";
                }
            }
            if (TA1 == TA2 && (TA1 == 7 || TA1 == 0)) { // Quinta perfecta paralela
                if (ar1.getVoces().get('T') != ar2.getVoces().get('T') & ar1.getVoces().get('A') != ar2.getVoces().get('A')) {
                    paralelasQ = true;
                    paralelasQuien = "TA";
                }
            }
            if (TS1 == TS2 && (TS1 == 7 || TS1 == 0)) { // Quinta perfecta paralela
                if (ar1.getVoces().get('T') != ar2.getVoces().get('T') & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                    paralelasQ = true;
                    paralelasQuien = "TS";
                }
            }
            if (AS1 == AS2 && (AS1 == 7 || AS1 == 0)) { // Quinta perfecta paralela
                if (ar1.getVoces().get('A') != ar2.getVoces().get('A') & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                    paralelasQ = true;
                    paralelasQuien = "AS";
                }
            }

        }
    }

    public boolean revisionCool() {
        // Aquí revisar las duplicaciones en general
        // Sin inversion, duplicar tónica
        int dupl = 0;
        int paso = 0;
        int sensible = 0;
        boolean cond = false;
        // primera inversión, duplicar 5 o 3, revisar caso II6
        // Segunda inversion, revisar caso II64 y K64
        if (!ar2.isSept()) {
            switch (ar2.getInversion()) {
                case 0:// Sin inversion
                    if (ar2.getDuplicacion() == 1) {
                        dupl = 1;// Duplicar toonica
                    }
                    break;
                case 1: // primera inversion sin septimos
                    if (ar2.getDuplicacion() == 1 || ar2.getDuplicacion() == 5) {
                        dupl = 1;// Duplicar toonica
                    }
                    // Si es II6, duplicar la tercera para que sea como subD
                    if (univ.gradoArmSencEscActual(ar2) == 2 && univ.getEscAct().perteneceArmonia(ar2)) {
                        if (ar2.getDuplicacion() == 3) {
                            dupl = 1;// Duplicar toonica
                        } else {
                            dupl = -1;
                        }
                    }
                    break;
                case 2: // segunda inversion sin septimos
                    // Si es I64, duplicar el bajo, es decir la quinta
                    if (univ.gradoArmSencEscActual(ar2) == 1 && univ.getEscAct().perteneceArmonia(ar2)) {
                        if (ar2.getDuplicacion() == 5) {
                            dupl = 1;// Duplicar toonica
                        }
                    }
                    break;
            }
        } else {// si es septimo que no tenga duplicacion
            if (ar2.getDuplicacion() == 0) {
                dupl = 1;
            }
        }
        // Revisar si tiene SENSIBLES
        //    Resolver la sensible forzosamente
//        if (revisionSensibleVF()) {
//            sensible = 1;
//        }

        // Revisar si es cercano
        if (ar1.DistMaxVoz(ar2) <= 4) {
            paso = 1;
        }
        if (dupl + paso == 2) {
            cond = true;
            enlaceTip = DefEn.gIdx("Cool");// terceras en soprano
        }

        return cond;
        // Gravedad melódica en general.
        // aguas con los acordes incompletos
    }

    public boolean revisionSensibleVF() {
        float a = 0, b = 0;
        boolean cond = true;

        if (ar1.getVoces().size() == ar2.getVoces().size()
                && ar1.getVoces().size() == 4) {
            for (int i = 0; i < 4; i++) {
                a = ar1.getVoces().get(i);
                b = ar2.getVoces().get(i);
                if (univ.getEscAct().gradoNota(a) == 7
                        && univ.getEscAct().gradoNota(b) == 1) {
                    if (Math.abs(a - b) != 1) {
                        cond = false;
                        break;
                    }
                }
            }
        }
        return cond;
    }

    /**
     *
     * @param distancia
     */
    private void setDistancia(int dist) {
        distancia = dist;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getGravMel() {
        return gravMel;
    }

    public int getGravArm() {
        return gravArm;
    }

    public Armonia getAr1() {
        return ar1;
    }

    public Armonia getAr2() {
        return ar2;
    }

    public String getEnlacStrCort() {
        String str = "x";
        if (enlaceTip > 0 && enlaceTip < DefEn.getStrCortoL().size()) {
            str = DefEn.getStrCortoL().get(enlaceTip);
        }
        return str;

    }

    public String getEnlacStrLarg() {
        String str = "x";
        if (enlaceTip > 0 && enlaceTip < DefEn.getStrCortoL().size()) {
            str = DefEn.getStrLargoL().get(enlaceTip);
        }
        return str;
    }

    public int getEnlaceTip() {
        return enlaceTip;
    }

    public boolean isParalelasQ() {
        return paralelasQ;
    }

    public boolean isCantadoVF() {
        return cantadoVF;
    }

    public String getParalelasQuien() {
        return paralelasQuien;
    }

    public void setParalelasQuien(String paralelasQuien) {
        this.paralelasQuien = paralelasQuien;
    }

    public String vocesComunes() {
        String reg = "";
        if (ar1.getVoces().size() == ar2.getVoces().size()
                && ar1.getVoces().size() == 4) {

            if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "B";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "T";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "A";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "S";
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "BT";
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "BA";
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "BS";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "TA";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "TS";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "AS";
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "BTA";
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "BTS";
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "BAS";
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "TAS";
            }

        }
        return reg;
    }

    public int vocesComunesNum() {
        String reg = "";
        int rg = -1;
        if (ar1.getVoces().size() == ar2.getVoces().size()
                && ar1.getVoces().size() == 4) {

            if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "B";
                rg = 0;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "T";
                rg = 1;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "A";
                rg = 2;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "S";
                rg = 3;
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "BT";
                rg = 4;
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "BA";
                rg = 5;
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "BS";
                rg = 6;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "TA";
                rg = 7;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "TS";
                rg = 8;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "AS";
                rg = 9;
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') != ar2.getVoces().get('S')) {
                reg = "BTA";
                rg = 10;
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') != ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "BTS";
                rg = 11;
            } else if (ar1.getVoces().get('B') == ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') != ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "BAS";
                rg = 12;
            } else if (ar1.getVoces().get('B') != ar2.getVoces().get('B')
                    & ar1.getVoces().get('T') == ar2.getVoces().get('T')
                    & ar1.getVoces().get('A') == ar2.getVoces().get('A')
                    & ar1.getVoces().get('S') == ar2.getVoces().get('S')) {
                reg = "TAS";
                rg = 13;
            }

        }
        return rg;
    }

    /**
     * Tipo de acorde en formato dos digitos Mayor
     *
     */
    public void print() {
        System.out.println("\nProgresion A1 A2");
        ar1.printAcorde();
        ar2.printAcorde();
//        System.out.println("Voz fija: " + getVozFija());
        System.out.println("Paralelas: " + isParalelasQ());
//        System.out.println("Preparacion X7: " + getPrepX7());
//        System.out.println("Preparacion vii: " + getPrepVii());
//        System.out.println("Resolucion: " + getResolucion());
        System.out.println("Distancia: " + ar1.distancia(ar2));
        System.out.println("Tipo Enlace: " + getEnlacStrLarg());
        System.out.println("Quien Par: " + getParalelasQuien() + "\n");
        System.out.println("Grav mel: " + getGravMel() + "\n");
        System.out.println("Grav arm: " + getGravArm() + "\n");
    }

    /**
     * Tipo de acorde en formato dos digitos Mayor
     *
     */
    public String printString() {
        String str = "";
//        str += "\nProgresion A1 A2";

//        str+="Voz fija: " + getVozFija());
        str += "\nDuplicacion: " + ar2.getDuplicacion();
        str += "\nDistanciaMax: " + ar1.DistMaxVoz(ar2);
        
        str += "\nParalelas: " + isParalelasQ();
//        str+="\nPreparacion X7: " + getPrepX7());
//        str+="\nPreparacion vii: " + getPrepVii());
//        str+="\nResolucion: " + getResolucion());
        str += "\nDistancia: " + ar1.distancia(ar2);
        str += "\nTipo Enlace: " + getEnlacStrLarg();
        str += "\nQuien Par: " + getParalelasQuien();
        str += "\nGrav mel: " + getGravMel();
        str += "\nGrav arm: " + getGravArm();
        return str;
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
