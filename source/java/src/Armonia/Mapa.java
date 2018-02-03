/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armonia;

import agui.BtnEnlace;
import agui.Instrumento;
import aguiListas.ListBotnEnlac;
import aguiListas.ListBoton;
import polifoniaLib.Armonia;
import polifoniaLib.Enlace;
import polifoniaLib.EnlacesMapa;
import polifoniaLib.UniversoTonal;
import polifoniaLib.defs.DefRegiones;
import polifoniaLib.listas.List1Enlace;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class Mapa {

    PApplet p;
    Armonia ar;
    UniversoTonal univ;
    char tipoMapa = 'v';
    private EnlacesMapa enlaceMapa;
    private int numArmAct = 0;
    private int velocidadBtns = 0;
    private ListBotnEnlac enlaceBtnsGrad = new ListBotnEnlac();
    ListBoton grados;
    private boolean calculando = false;
    private Datos datos;
    private long t0, temp = 0, tempGrab = 1000;
    private ListFloat notasGuia;
    private ListFloat notasExplorando;
    private ListFloat notasComparar;
    // MEDIDAS
    int longTot, anchTot;
    int largBt, anchBt;
    int separacionY, separacionX;
    int numCuads;
    int pxBtnG, pyBtnG;
    // px de las listas
    int pxo, pyo;
    int eps = 2;
    int porc = 0, anchLoad = 30, sep = 5; // Loading

    public Mapa(PApplet pa, Datos mL, int px, int py, int lon, int anc) {
        p = pa;
        pxo = px;
        pyo = py;
        longTot = lon;
        anchTot = anc;
        numCuads = 9;
        largBt = longTot / numCuads;
        anchBt = largBt;
        separacionY = largBt;
        separacionX = anchBt;
        p.println(largBt + " " + separacionY + " ");
//        largBt = 70;
//        separacionY = 70;
//        separacionX = 70;

        pxBtnG = pxo;
        pyBtnG = pyo;
        datos = mL;
        enlaceMapa = new EnlacesMapa();
        notasGuia = new ListFloat();
        notasExplorando = new ListFloat();
        notasComparar = new ListFloat();
//        armoniaLista.add(new ListFloat(0f));
    }

    public boolean isCalculando() {
        return calculando;
    }

    public void setNumArmActMapa(int numArmAct) {
        this.numArmAct = numArmAct;
    }

    public int getNumArmActMapa() {
        return numArmAct;
    }

    public void siguienteArm() {
        velocidadBtns++;
        if (velocidadBtns % 5 == 0 && enlaceBtnsGrad.size() > 0) {
            for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
                enlaceBtnsGrad.get(i).soltar();
            }
            numArmAct = (numArmAct + 1) % enlaceBtnsGrad.size();
            setNotasExplorando(enlaceBtnsGrad.get(numArmAct).getEnl().getAr2().getVoces());
            enlaceBtnsGrad.get(numArmAct).presionar();
            p.println("Cuadro Actual: " + numArmAct);
        }
    }

    public void anteriorArm() {
        numArmAct = (numArmAct - 1) % enlaceBtnsGrad.size();
    }

    public void setCalculando(boolean calculando) {
        this.calculando = calculando;
    }

    public char getTipoMapa() {
        return tipoMapa;
    }

    public void setTipoMapa(char tipoMapa) {
        this.tipoMapa = tipoMapa;
    }

    public int getEpsilon() {
        return eps;
    }

    public void setEpsilon(int epsilon) {
        this.eps = epsilon;
//        try{
//        this.MAPA();
//        }catch(Exception e){
//            p.println("Exc: Mapa.setEpsilon");
//        }
    }

    public ListFloat getNotasGuia() {
        return notasGuia;
    }

    public void setNotasGuia(ListFloat notasGuia) {
        this.notasGuia = notasGuia;
    }

    public ListFloat getNotasExplorando() {
        return notasExplorando;
    }

    public void setNotasExplorando(ListFloat armTemp) {
        this.notasExplorando = armTemp;
    }

    public Enlace getEnlacExplor() {
        Enlace reg = new Enlace();
        if (enlaceBtnsGrad.size() > 0 && numArmAct < enlaceBtnsGrad.size()) {
            reg = enlaceBtnsGrad.get(this.numArmAct).getEnl();
        }
        return reg;
    }

    public void mouseMoved() {
        for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
            if (enlaceBtnsGrad.get(i).mouseVF()) {
                enlaceBtnsGrad.get(i).presionar();
                this.setNotasExplorando(enlaceBtnsGrad.get(i).getEnl().getAr2().getVoces());
//                enlaceBtnsGrad.get(i).getEnl().getAr2().printAcorde();
                break;
            } else {
                this.setNotasExplorando(new ListFloat());
                enlaceBtnsGrad.get(i).soltar();
            }
        }
    }

    public void playEnlaceExplor(Instrumento ins) {
        if (getEnlacExplor() != null) {
            try {
                ins.playProg(getEnlacExplor(), 0.75);
            } catch (Exception e) {
                p.println("Exception: Mapa.playENlaceExplor");
            }
        }
    }

    public Armonia mousePressedSelect() {
        Armonia reg = new Armonia();
        if (p.mouseButton == p.LEFT) {
            for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
                if (enlaceBtnsGrad.get(i).mouseVF()) {
//                    ins.playProg(enlaceBtnsGrad.get(i).getEnl(), (double) 0.75);
                }
            }
        } else {
            // Agregar con click derecho
            for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
                if (enlaceBtnsGrad.get(i).mouseVF()) {
                    reg = enlaceBtnsGrad.get(i).getEnl().getAr2();
//                    ins.playAcorde(reg, 100, (double) 0.75);
                    agregarNotas(reg.getVoces());
                    MAPA();
                }
            }
        }
        return reg;
    }

    public Armonia mousePressedPlay(Instrumento ins) {
        Armonia reg = new Armonia();
        if (p.mouseButton == p.LEFT) {
            for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
                if (enlaceBtnsGrad.get(i).mouseVF()) {
                    ins.playProg(enlaceBtnsGrad.get(i).getEnl(), (double) 0.75);
                }
            }
        } else {
            // Agregar con click derecho
            for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
                if (enlaceBtnsGrad.get(i).mouseVF()) {
                    reg = enlaceBtnsGrad.get(i).getEnl().getAr2();
                    ins.playAcorde(reg, 100, (double) 0.75);
                    agregarNotas(reg.getVoces());
                    MAPA();
                }
            }
        }
        return reg;
    }

    public void limpiar() {
        notasComparar = new ListFloat();
        notasExplorando = new ListFloat();
        enlaceMapa = new EnlacesMapa();
        enlaceBtnsGrad = new ListBotnEnlac();
        p.redraw();

    }

    public void agregarNotas(ListFloat notasPres) {
        // Agregar aromnia
        datos.setNotasActuales(new ListFloat(notasPres));// Agregar aromnia
        notasComparar = new ListFloat();
        notasExplorando = new ListFloat();
        enlaceMapa = new EnlacesMapa();
        enlaceBtnsGrad = new ListBotnEnlac();
        p.redraw();
    }

    /**
     * Esta es LA funcion.. la que se debe definir bien.
     *
     * @param notaSigF
     * @return
     */
    private void calcularVecindad() {
        // char tipo; // v-vecindad, s- soprano, b-bajo
        // int nota, es la que debe ser fija.
        int notaSigB = 60;
        int notaSigS = 60;
        enlaceMapa = new EnlacesMapa();
        UniversoTonal uni = datos.getUniv();
        //if (datos.getArmFlotasList().size() > 0) {
        if (true) {

            Armonia elArm = new Armonia(datos.getNotasActuales());
            Armonia armTemp;// armonia temporal, de prueba
            String gradoInterp;
            int gradoNum;
            tipoMapa = 'v';
            Enlace enlac;
            String inv;
            int b1, b2, t1, t2, a1, a2, s1, s2;
            eps = 2;
            if (notasGuia.size() == 2) {
                tipoMapa = 'c';
                notaSigB = notasGuia.get(0).intValue();
                notaSigS = notasGuia.get(1).intValue();
//                eps = 5;
            } else if (notasGuia.size() == 1) {
                if (notasGuia.get(0) < 60) {
                    tipoMapa = 'b';
//                    eps = 5;
                    notaSigB = notasGuia.get(0).intValue();
                } else {
                    tipoMapa = 's';
//                    eps = 5;
                    notaSigS = notasGuia.get(0).intValue();
                }
            }

            // De la voz que este mas cercana
            switch (elArm.getVoces().size()) {
                case 4:
                    switch (tipoMapa) {
                        case 's':
                            b1 = (int) elArm.getVoces().get('B') - eps;
                            b2 = (int) elArm.getVoces().get('B') + eps;
                            t1 = (int) elArm.getVoces().get('T') - eps;
                            t2 = (int) elArm.getVoces().get('T') + eps;
                            a1 = (int) elArm.getVoces().get('A') - eps;
                            a2 = (int) elArm.getVoces().get('A') + eps;
                            s1 = notaSigS;
                            s2 = notaSigS;
                            break;
                        case 'b':
                            b1 = notaSigB;
                            b2 = notaSigB;
                            t1 = (int) elArm.getVoces().get('T') - eps;
                            t2 = (int) elArm.getVoces().get('T') + eps;
                            a1 = (int) elArm.getVoces().get('A') - eps;
                            a2 = (int) elArm.getVoces().get('A') + eps;
                            s1 = (int) elArm.getVoces().get('S') - eps;
                            s2 = (int) elArm.getVoces().get('S') + eps;
                            break;
                        case 'c':
                            b1 = notaSigB;
                            b2 = notaSigB;
                            t1 = (int) elArm.getVoces().get('T') - eps;
                            t2 = (int) elArm.getVoces().get('T') + eps;
                            a1 = (int) elArm.getVoces().get('A') - eps;
                            a2 = (int) elArm.getVoces().get('A') + eps;
                            s1 = notaSigS;
                            s2 = notaSigS;
                            break;
                        default:// v
                            //eps = 7;
                            b1 = (int) elArm.getVoces().get('B') - eps;
                            b2 = (int) elArm.getVoces().get('B') + eps;
                            t1 = (int) elArm.getVoces().get('T') - eps;
                            t2 = (int) elArm.getVoces().get('T') + eps;
                            a1 = (int) elArm.getVoces().get('A') - eps;
                            a2 = (int) elArm.getVoces().get('A') + eps;
                            s1 = (int) elArm.getVoces().get('S') - eps;
                            s2 = (int) elArm.getVoces().get('S') + eps;
                            break;
                    }
//                    b1 = (int) elArm.getVoces().get('B') - 12;
//                    b2 = (int) elArm.getVoces().get('B') + 12;
                    for (int Sop = s1; Sop <= s2; Sop++) {
                        for (int Alt = a1; Alt <= Math.min(Sop, a2); Alt++) {
                            for (int Ten = t1; Ten <= Math.min(Alt, t2); Ten++) {
                                for (int Bas = b1; Bas <= Math.min(Ten, b2); Bas++) {
                                    // Inicio de revision
                                    armTemp = new Armonia(Bas, Ten, Alt, Sop);
                                    // Ver si pertenece a la escala y si esta en el rango
                                    if ((Bas != Ten && Bas != Alt && Bas != Sop && Ten != Alt && Ten != Sop && Alt != Sop) && uni.gradoArmSecundEscAct(armTemp).getGrado() > 0// Grado diatonico
                                            && (armTemp.getDisposicion() == 50)) {// Disp. flexible
                                        gradoNum = uni.gradoArmSecundEscAct(armTemp).getGrado();
                                        gradoInterp = uni.gradoAcordeRomSecEscAct(armTemp);
                                        inv = armTemp.getInversionStr();
                                        if (!armTemp.isCompleto()) {
                                            gradoInterp += "?";
                                        }
                                        // Asignar interpretacion
                                        armTemp.setInterpretacion(gradoInterp);
                                        enlac = new Enlace(elArm, armTemp, uni);
                                        //Aqui es donde debo organizarlos bien...
                                        // Primero poner los cercanos con:
                                        //    gravedad tonal muy fuerte
                                        //    gravedad muy fuerte
                                        // Si.. ambos son diatonicos
                                        // Si.. no son 5tas8vas paralelas
                                        // Si.. tienen alta gravedad melódica y armonica
                                        //      poner primero.
                                        // Los demas poner después.
                                        if (!enlac.isParalelasQ()) {
                                            enlaceMapa.agregarPrimero(gradoNum - 1, 0, enlac);
//                                        if (enlac.getGravMel() > 0 && uni.chkRegion(enlac.getAr2()) > 0) {
////                                            enlaceMapa.agregarPrimero(gradoNum - 1, armTemp.getRnglonInv(), enlac);
//                                            enlaceMapa.agregarPrimero(gradoNum - 1, 0, enlac);
//                                        } else {
////                                            enlaceMapa.agregarUltimo(gradoNum - 1, armTemp.getRnglonInv(), enlac);
//                                            enlaceMapa.agregarUltimo(gradoNum - 1, 0, enlac);
//                                        }
//                                        cuantos++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }// if armonia lista mayor que 0
    }

    /**
     * Inicia el calculo del mapa en un Thread separado.
     *
     * @param drop
     */
    public void MAPA() {
        if (calculando == false) {
            calculando = true;
            CalcularMapa calcArm = new CalcularMapa(this);
            Thread playSecTh = new Thread(calcArm);
            playSecTh.start();
        }
    }

    public void calcularBotonesMapa() {
        int px, py;
        BtnEnlace btnPTemp;
        Armonia a1, a2;
        enlaceBtnsGrad = new ListBotnEnlac();
        enlaceBtnsGrad.clear();
        List1Enlace enGrad = enlaceMapa.getMapaListaGrad();
//        List1Enlace enGrad = enlaceMapa.getMapaListaPuntos();
//        List1Enlace enGrad = enlaceMapa.getMapaListaGrados();

        for (int i = 0; i < enGrad.size(); i++) {// siete grados
            px = pxo + (i % numCuads) * separacionX;
            py = pyo + separacionY * (i / numCuads);
            btnPTemp = new BtnEnlace(p, enGrad.get(i), px, py, largBt, anchBt);
            btnPTemp.setLetras(datos.getColoresFonts().getLetrasC());
            enlaceBtnsGrad.add(btnPTemp);
            a2 = enlaceBtnsGrad.getLast().getEnl().getAr2();
            try {
                if (datos.getUniv().chkRegion(a2) == DefRegiones.SUB_D || datos.getUniv().chkRegion(a2) == DefRegiones.SUB_F) {
                    enlaceBtnsGrad.getLast().setColorSuelto(datos.getColoresFonts().getVerdecitoOpaco());
                } else if (datos.getUniv().chkRegion(a2) == DefRegiones.DOM_D || datos.getUniv().chkRegion(a2) == DefRegiones.DOM_F) {
                    enlaceBtnsGrad.getLast().setColorSuelto(datos.getColoresFonts().getBtnAcordeRojo2());
                } else if (datos.getUniv().chkRegion(a2) == DefRegiones.TON_D || datos.getUniv().chkRegion(a2) == DefRegiones.TON_F) {
                    enlaceBtnsGrad.getLast().setColorSuelto(datos.getColoresFonts().getBtnAcorde());
                } else if (datos.getUniv().chkRegion(a2) == DefRegiones.MOD_TON) {
                    enlaceBtnsGrad.getLast().setColorSuelto(datos.getColoresFonts().getBtnAcorde());
                } else if (datos.getUniv().chkRegion(a2) == DefRegiones.MOD_SUB) {
                    enlaceBtnsGrad.getLast().setColorSuelto(datos.getColoresFonts().getVerde());
                } else if (datos.getUniv().chkRegion(a2) == DefRegiones.MOD_DOM) {
                    enlaceBtnsGrad.getLast().setColorSuelto(datos.getColoresFonts().getBtnAcordeRojo());
                }
            } catch (Exception e) {
                System.out.println("Exception Mapa.calcularBotonesMapa");
            }

        }

    }

    public void paint() {

        for (int i = 0; i < enlaceBtnsGrad.size(); i++) {
            enlaceBtnsGrad.get(i).paint();
//            p.println("E: "+enlaceBtnsGrad.get(i).getEnl().getEnlacStrCort());
        }
        if (calculando == true) {
//            p.textFont(datos.getColores().getLetrasA());
            p.text("...", p.width / 2, 10);
//            p.textFont(datos.getColores().getLetrasA());
        }
    }

    /**
     * Clase que se encarga de calcular el mapa.
     */
    public static class CalcularMapa implements Runnable {

        Mapa mapa;

        /**
         *
         * @param map
         */
        public CalcularMapa(Mapa map) {
            mapa = map;
        }

        /**
         *
         */
        public void run() {
//            System.out.println("Comienza calculo");
            mapa.calcularVecindad();
            mapa.calcularBotonesMapa();
            mapa.setCalculando(false);
            mapa.setNumArmActMapa(0);
//            System.out.println("Termina calculo: ");
//            mapa.enlaceMapa.printSize();
            mapa.p.redraw();
        }

        /**
         *
         */
        public void fin() {
        }
    }
}
