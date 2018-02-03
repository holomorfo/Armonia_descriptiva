/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armonia;

import agui.BtnArmoniaList;
import agui.BtnEnlaceList;
import aguiListas.ListBotnArmList;
import aguiListas.ListBotnEnlList;
import polifoniaLib.Armonia;
import polifoniaLib.Enlace;
import polifoniaLib.Escala;
import polifoniaLib.UniversoTonal;
import polifoniaLib.defs.DefEscalas;
import polifoniaLib.listas.List1Enlace;
import polifoniaLib.listas.List2Armonia;
import polifoniaLib.listas.List2Enlace;
import polifoniaLib.listas.ListArmonia;
import processing.core.PApplet;

/**
 *
 * @author holomorfo
 */
public class DiccionarioMapa {

    PApplet p;
    Escala tonalidadActual;
    Enlace enlActual;
    DefEscalas defEs;
    private Datos datos;
    UniversoTonal uni;
    private ListBotnArmList listBtnArmlist = new ListBotnArmList();
    private ListBotnEnlList listBtnEnllist = new ListBotnEnlList();
    ListArmonia listArmBase;
    List2Armonia list2Arm;
    List2Enlace list2Enl;
    ///  Mediciones
    // MEDIDAS
    int longTot, anchTot;
    int largBt, anchBt;
    int separacionY, separacionX;
    int numCuads;
    int pxBtnG, pyBtnG;
    // px de las listas
    int pxo, pyo;
    int eps = 0;

    public DiccionarioMapa(PApplet pa, Datos mL, int px, int py, int lonTotal, int anchTotal) {
        p = pa;
        pxo = px;
        pyo = py;
        longTot = lonTotal;
        anchTot = anchTotal;
        numCuads = 9;
        largBt = longTot / numCuads;
        anchBt = largBt;
        separacionY = largBt;
        separacionX = anchBt;
        pxBtnG = pxo;
        pyBtnG = pyo;
        datos = mL;
        uni = datos.getUniv();
        enlActual = new Enlace();
        inicarArmBase();
        list2Arm = new List2Armonia();
        list2Enl = new List2Enlace();
        for (int i = 0; i < listArmBase.size(); i++) {
            list2Arm.add(new ListArmonia());
            list2Arm.get(i).add(listArmBase.get(i));
            list2Enl.add(new List1Enlace());
            list2Enl.get(i).add(new Enlace(new Armonia(listArmBase.get(i)), listArmBase.get(i), uni));
        }

//        System.out.println("tamanio: " + listArmBase.size());
        tonalidadActual = datos.getUniv().getEscAct();
        calcularBotonesMapa();
    }

    public Armonia mouseMoved() {
        Armonia arm = new Armonia();
//        for (int i = 0; i < listBtnArmlist.size(); i++) {
//            if (listBtnArmlist.get(i).mouseVF()) {
//                arm = listBtnArmlist.get(i).getArmActual();
//                datos.setNotasMapa(listBtnArmlist.get(i).getArmActual().getVoces());
//                //                setArmMapa(armBtnslist.get(i).getArm());
//                break;
//            } else {
//            }
//        }
        for (int i = 0; i < listBtnEnllist.size(); i++) {
            if (listBtnEnllist.get(i).mouseVF()) {
                arm = listBtnEnllist.get(i).getEnlActual().getAr2();
//                if(datos.getIdxArmActual()>0){
//                datos.getListArm().get(i)
//                        listBtnEnllist.get(i).getEnlActual().getAr2().getVoces());
//                                    }
                datos.setNotasMapa(listBtnEnllist.get(i).getEnlActual().getAr2().getVoces());

                enlActual = listBtnEnllist.get(i).getEnlActual();
                //                setArmMapa(armBtnslist.get(i).getArm());
                break;
            } else {
            }
        }

        return arm;
    }

    public void mousePressed() {
        Armonia arm = new Armonia(0);
        if (p.mouseButton != p.RIGHT) {
//                    datos.setNotasActuales(arm.getVoces());
//                    calcularAcordesCercanos();
//            for (int i = 0; i < listBtnArmlist.size(); i++) {
//                if (listBtnArmlist.get(i).mouseVF()) {
//                    arm = listBtnArmlist.get(i).getArmActual();
//                    datos.getOrq().gMnInst().playAcordeFloat(datos.getNotasActuales(), 60, 1);
//                    datos.getOrq().gMnInst().playAcorde(arm, 60, 1);
//                    break;
//                } else {
//                }
//            }
            for (int i = 0; i < listBtnEnllist.size(); i++) {
                if (listBtnEnllist.get(i).mouseVF()) {
                    arm = listBtnEnllist.get(i).getEnlActual().getAr2();
                    datos.getOrq().gMnInst().playAcordeFloat(datos.getNotasActuales(), 60, 1);
                    datos.getOrq().gMnInst().playAcorde(arm, 60, 1);
                    break;
                } else {
                }
            }

        } else {
//            for (int i = 0; i < listBtnArmlist.size(); i++) {
//                if (listBtnArmlist.get(i).mouseVF()) {
//                    listBtnArmlist.get(i).siguienteArmonia();
//                    datos.setNotasMapa(listBtnArmlist.get(i).getArmActual().getVoces());
//                    break;
//                } else {
//                }
//            }
            for (int i = 0; i < listBtnEnllist.size(); i++) {
                if (listBtnEnllist.get(i).mouseVF()) {
                    listBtnEnllist.get(i).siguienteEnlace();
                    datos.setNotasMapa(listBtnEnllist.get(i).getEnlActual().getAr2().getVoces());
                    enlActual = listBtnEnllist.get(i).getEnlActual();
                    break;
                } else {
                }
            }

        }
    }

    public void inicarArmBase() {
        defEs = new DefEscalas();
        float base = datos.getUniv().getEscAct().getBaseEsc();
        char tip = datos.getUniv().getEscAct().getTipoEsc();

        listArmBase = defEs.lista(base, tip);
        if (datos.getEscalaAct().getTipoEsc() == 'M') {
            listArmBase.add(new Armonia(base + 9, "Mm7")); // Para II
            listArmBase.add(new Armonia(base + 11, "Mm7"));// Para III
            listArmBase.add(new Armonia(base + 0, "Mm7")); // Para IV
            listArmBase.add(new Armonia(base + 2, "Mm7")); // Para V
            listArmBase.add(new Armonia(base + 4, "Mm7")); // Para VI
            // Septimos
            listArmBase.add(new Armonia(base + 1, "o7")); // Para II,VII,IV
            listArmBase.add(new Armonia(base + 3, "o7")); // Para La
        } else {
//            listArmBase.add(new Armonia(base + 9, "Mm7")); // Para II // no lo ocupo por que es disminuido
//            listArmBase.add(new Armonia(base + 10, "Mm7"));// Para III, ya esta en el VII7 natural
            listArmBase.add(new Armonia(base + 0, "Mm7")); // Para IV
            listArmBase.add(new Armonia(base + 2, "Mm7")); // Para V
            listArmBase.add(new Armonia(base + 3, "Mm7")); // Para VI
            listArmBase.add(new Armonia(base + 5, "Mm7")); // Para VI
            // Septimos
            listArmBase.add(new Armonia(base + 4, "o7")); // Para IV,
            listArmBase.add(new Armonia(base + 6, "o7")); // Para V
        }

    }

    public void calcularAcordesCercanos() {
        long t0 = System.currentTimeMillis();
        if (datos.getNotasActuales().size() == 4) {
            inicarArmBase();
            list2Arm = new List2Armonia();
            list2Enl = new List2Enlace();

            Armonia elArmActual = new Armonia(datos.getNotasActuales());
            Armonia armTemp = new Armonia(0, 0, 0, 0);// armonia temporal, de prueba

            String gradoInterp;
//            int gradoNum;
            Enlace enlac;
            int b1, b2, t1, t2, a1, a2, s1, s2;
            eps = 4;

            // De la voz que este mas cercana
//            b1 = (int) elArmActual.getVoces().get('B') - eps;
//            b2 = (int) elArmActual.getVoces().get('B') + eps;
            b1 = (int) elArmActual.getVoces().get('B') - 5;
            b2 = (int) elArmActual.getVoces().get('B') + 7;
            t1 = (int) elArmActual.getVoces().get('T') - eps;
            t2 = (int) elArmActual.getVoces().get('T') + eps;
            a1 = (int) elArmActual.getVoces().get('A') - eps;
            a2 = (int) elArmActual.getVoces().get('A') + eps;
            s1 = (int) elArmActual.getVoces().get('S') - eps;
            s2 = (int) elArmActual.getVoces().get('S') + eps;
            p.println("------------");
            for (int i = 0; i < listArmBase.size(); i++) {
                long tb = System.currentTimeMillis();

                list2Arm.add(new ListArmonia());
                list2Arm.get(i).add(listArmBase.get(i));
                list2Enl.add(new List1Enlace());
                // pone los enlaces a acordes por default
                list2Enl.get(i).add(new Enlace(listArmBase.get(i), listArmBase.get(i), uni));

                for (int Sop = s1; Sop <= s2; Sop++) {
                    long tc = System.currentTimeMillis();
                    for (int Alt = a1; Alt <= Math.min(Sop, a2); Alt++) {
                        for (int Ten = t1; Ten <= Math.min(Alt, t2); Ten++) {
                            for (int Bas = b1; Bas <= Math.min(Ten, b2); Bas++) {
                                //long tc = System.currentTimeMillis();
                                // Inicio de revision
                                armTemp = new Armonia(Bas, Ten, Alt, Sop);
                                // Ver si es equivalente al acorde de la lista
                                if (listArmBase.get(i).equivalente(armTemp)) {
                                    // Ver si pertenece a la escala y si esta en el rango
//                                    if ((Bas != Ten && Bas != Alt && Bas != Sop && Ten != Alt && Ten != Sop && Alt != Sop)// && uni.gradoArmSecund(armTemp).getGrado() > 0// Grado diatonico
//                                            && (armTemp.getDisposicion() == 50)) {// Disp. flexible
                                    if (armTemp.getDisposicion() == 50) {// Disp. flexible

                                        gradoInterp = uni.gradoAcordeRomSecEscAct(armTemp);
                                        if (!armTemp.isCompleto()) {
                                            gradoInterp += "?";
                                        }

                                        // Asignar interpretacion
                                        armTemp.setInterpretacion(gradoInterp);
                                        enlac = new Enlace(elArmActual, armTemp, uni);
                                        if (!enlac.isParalelasQ()) {
//                                            p.println("enlace: " + enlac.getEnlacStrCort());
                                            if (!enlac.getEnlacStrCort().equals("x")) {
                                                list2Arm.get(i).addFirst(armTemp);
                                                list2Enl.get(i).addFirst(enlac);
                                            } else {
                                                list2Arm.get(i).addLast(armTemp);
                                                list2Enl.get(i).addLast(enlac);
                                            }
                                        }
                                    }
                                }

                            }// fin bass
                        }
                    }
                    //p.println("paso: " + (System.currentTimeMillis() - tc));
                } // for voces
                //p.println("paso: " + (System.currentTimeMillis() - tb));
            }// for acorde listas

        }
//        p.println("tamBae: " + listArmBase.size());
//        p.println("tamLists: " + list2Armonia.size());
        p.println("Tiempo Calculo: " + (System.currentTimeMillis() - t0));
        calcularBotonesMapa();

    }

    public void calcularBotonesMapa() {

        defEs = new DefEscalas();
        int px, py;
        BtnArmoniaList btnArmTemp;
        BtnEnlaceList btnEnlTemp;
        listBtnArmlist = new ListBotnArmList();
        listBtnArmlist.clear();
        listBtnEnllist = new ListBotnEnlList();
        listBtnEnllist.clear();

        String str = "";
        int grado = 0;
        int[] cont = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < listArmBase.size(); i++) {
            int corEsc = datos.getUniv().gradoArmSecundEscAct(listArmBase.get(i)).getEscala();
            int corGra = datos.getUniv().gradoArmSecundEscAct(listArmBase.get(i)).getGrado();
//            p.println(corEsc+" gra:"+corGra);
            if (corEsc == 1) {
//                grado = datos.getUniv().gradoArmSencEscActual(listArmBase.get(i));
//                grado = corEsc;
                grado = corGra;
            } else {
//                grado = corGra;
                grado = corEsc;
            }
            px = pxo + (grado % numCuads) * separacionX;
            py = pyo + separacionY * (cont[grado]);
            cont[grado]++;
            //py = pyo + separacionY * (i / numCuads);
//            try {
            btnArmTemp = new BtnArmoniaList(p, list2Arm.get(i), px, py, largBt, anchBt);
            listBtnArmlist.add(btnArmTemp);
            btnEnlTemp = new BtnEnlaceList(p, list2Enl.get(i), px, py, largBt, anchBt);
            listBtnEnllist.add(btnEnlTemp);
//            } catch (Exception e) {
//                p.println("Exception DiccionarioMapa.calcularBOtonesMapa");
//            }
//            btnPTemp.setLetras(datos.getColores().getLetrasC());
        }
    }

    public void paint() {
//        calcularBotonesMapa();
        p.textFont(datos.getColoresFonts().getLetrasB());
        if (tonalidadActual != datos.getUniv().getEscAct()) {
            calcularAcordesCercanos();
            tonalidadActual = datos.getUniv().getEscAct();
        }
        for (int i = 0; i < listBtnArmlist.size(); i++) {
            //listBtnArmlist.get(i).paint(datos);
        }
        for (int i = 0; i < listBtnEnllist.size(); i++) {
            listBtnEnllist.get(i).paint(datos);
        }

        p.textFont(datos.getColoresFonts().getLetrasA());
        String str = "COSA ";
        str = enlActual.printString();
        p.text(str, pxo - 60, pyo + 150);

    }
}
