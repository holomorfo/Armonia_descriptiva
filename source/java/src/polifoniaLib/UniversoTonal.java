/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import agui.Boton;
import java.util.LinkedList;
import polifoniaLib.defs.DefEscalas;
import polifoniaLib.defs.DefRegiones;
import polifoniaLib.defs.Oper;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class UniversoTonal {

    PApplet p;
    boolean appletVF = false;
    boolean cromatico = false;
    int btLar = 40;
    DefEscalas defEs;
    LinkedList<Escala> universoMayor;
    LinkedList<Escala> universoMenor;
    LinkedList<Boton> escalasBt;
    int gravMel = 0;
    int gravArm = 0;
    float escActFund = 0;
    char escActTip = 'M';
    int px = 0, py = 0;

    public UniversoTonal() {
        appletVF = false;
        escActFund = 0;
        escActTip = 'M';
        universoMayor = new LinkedList<Escala>();
        for (int i = 0; i < 12; i++) {// antes tenía 24, no se por que
            universoMayor.add(new Escala((float) i, 'M'));
//            universoMayor.getLast().iniciarEscalas();
        }
        universoMenor = new LinkedList<Escala>();
        for (int i = 0; i < 12; i++) {
            universoMenor.add(new Escala((float) i, 'm'));
//            universoMayor.getLast().iniciarEscalas();
        }
        defEs = new DefEscalas();
    }

    public UniversoTonal(PApplet pa, int longit, int alt) {
        p = pa;
        btLar = longit / 12;
        appletVF = true;
        escActFund = 0;
        escActTip = 'M';
        universoMayor = new LinkedList<Escala>();
        escalasBt = new LinkedList<Boton>();


        for (int i = 0; i < 24; i++) {
            escalasBt.add(new Boton(p, px + btLar * (i / 12), py + btLar * (i % 12), btLar));
            escalasBt.get(i).setEtiqueta("" + (i % 12));
        }
        for (int i = 0; i < 12; i++) {
            universoMayor.add(new Escala((float) i, 'M'));
//            universoMayor.getLast().iniciarEscalas();
        }
        universoMenor = new LinkedList<Escala>();
        for (int i = 0; i < 12; i++) {
            universoMenor.add(new Escala((float) i, 'm'));
//            universoMayor.getLast().iniciarEscalas();
        }
        defEs = new DefEscalas();
    }

    public void paint() {
        if (appletVF) {
            for (int i = 0; i < 24; i++) {
                escalasBt.get(i).paint();
            }
        }
    }

    public void mousePressed() {
        if (appletVF) {
            // cambia tonalidad
            int mx = p.mouseX;
            int my = p.mouseY;
            int id = -1;
            int tip = 0;
            int fund = 0;
            for (int i = 0; i < 24; i++) {
                if (escalasBt.get(i).mouseVF(mx, my)) {
                    id = i;
                    break;
                }
            }
            if (id >= 0) {
                fund = id % 12;
                tip = id / 12;
//                p.println("Fund: " + (fund) + " tip: " + (id / 12));
                if (tip == 0) {
                    this.setEscalaAct(fund, 'M');
                } else {
                    this.setEscalaAct(fund, 'm');
                }
            } else if (fund == 0 && tip == 0) {
//                setEscalaAct(0, 'M');
            }
            for (int i = 0; i < 24; i++) {
                if (i / 12 == 0) {
//                    escalasBt.get(i).setEtiqueta("" + Oper.numRomano((int) getEscAct().gradoNota(i % 12)) + " " + Oper.num2nota12((i % 12), getEscAct()) + " M");
                    escalasBt.get(i).setEtiqueta(" " + Oper.num2nota12((i % 12), getEscAct()) + " M");

                } else {
//                    escalasBt.get(i).setEtiqueta("" + Oper.numRomano((int) getEscAct().gradoNota(i % 12)) + " " + Oper.num2nota12((i % 12), getEscAct()) + " m");
                    escalasBt.get(i).setEtiqueta(" " + Oper.num2nota12((i % 12), getEscAct()) + " m");
                }
//                if (getEscAct().perteneceNota(i % 12) && getEscAct().gradoNota(i % 12) != -1) {
//                    escalasBt.get(i).setEtiqueta("" + Oper.numRomano((int) getEscAct().gradoNota(i % 12)));
//                }
            }
        }
    }

    public void setPosition(int x, int y) {
        px = x;
        py = y;
        for (int i = 0; i < 24; i++) {
            escalasBt.get(i).setPosXY(px + btLar * (i % 12), py + btLar * (i / 12));
            if (i / 12 == 0) {
                escalasBt.get(i).setEtiqueta("" + Oper.num2nota12((i % 12), getEscAct()) + " M");
//                if (getEscAct().perteneceNota(i % 12) && getEscAct().gradoNota(i % 12) != -1) {
//                    escalasBt.get(i).setEtiqueta("" + Oper.numRomano((int) getEscAct().gradoNota(i % 12)) + " " + Oper.num2nota12((i % 12), getEscAct()) + " M");
//                }

            } else {
                escalasBt.get(i).setEtiqueta("" + Oper.num2nota12((i % 12), getEscAct()) + " m");
//                if (getEscAct().perteneceNota(i % 12) && getEscAct().gradoNota(i % 12) != -1) {
//                    escalasBt.get(i).setEtiqueta("" + Oper.numRomano((int) getEscAct().gradoNota(i % 12)) + " " + Oper.num2nota12((i % 12), getEscAct()) + " m");
//                }

            }
        }
    }

    public int chkGravedadArm(Enlace enl) {
        // Aqui debo revisar la magnitud de la gravedad armonica
        int reg = 0;
        int gra1 = this.chkRegion(enl.getAr1());
        int gra2 = this.chkRegion(enl.getAr2());
        if (gra2 > gra1) {
            gravArm = 5;
        }
        return gravArm;
    }

    public boolean sensibleVF() {
        boolean cond = false;
        return cond;
    }

    public int chkGravedadMel(Enlace enl) {
        // Aqui debo revisar la magnitud de la gravedad melodica
        Armonia ar1 = enl.getAr1();
        Armonia ar2 = enl.getAr2();
        Escala escAct = getEscAct();
        // Ok, primero ver si el acorde esta en la escala orignal
        // Primero revisar en que escala esta el acorde original
        // Revisar escala del acorde uno...
//        int fundNva = gradoArmSecund(ar1).getEscala();
//        int fundNva = gradoArmSens(ar2);
//        char excNvTipo = escAct.getTipoGrado(fundNva).charAt(0);
//        Escala escNva = getEscala(fundNva, excNvTipo);
        // calcular la gravedad con respecto a esa escala
        if (ar1.getVoces().size() == 4 && ar2.getVoces().size() == 4) {
            gravMel = escAct.gravedad(ar1.getVoces().get(0), ar2.getVoces().get(0));
            gravMel += escAct.gravedad(ar1.getVoces().get(1), ar2.getVoces().get(1));
            gravMel += escAct.gravedad(ar1.getVoces().get(2), ar2.getVoces().get(2));
            gravMel += escAct.gravedad(ar1.getVoces().get(3), ar2.getVoces().get(3));
        }
        return gravMel;
    }

    public void setEscalaAct(float fund, char tip) {
        escActFund = fund;
        escActTip = tip;
    }

    public Escala getEscAct() {
        Escala esc = null;
        if (escActTip == 'M') {
            esc = universoMayor.get((int) escActFund);
        } else if (escActTip == 'm') {
            esc = universoMenor.get((int) escActFund);
        }
        return esc;
    }

    public LinkedList<Escala> getUniversoMayor() {
        return universoMayor;
    }

    public void setUniversoMayor(LinkedList<Escala> universoMayor) {
        this.universoMayor = universoMayor;
    }

    public LinkedList<Escala> getUniversoMenor() {
        return universoMenor;
    }

    public void setUniversoMenor(LinkedList<Escala> universoMenor) {
        this.universoMenor = universoMenor;
    }

    public Escala getEscala(int fund, char tip) {
        Escala esc = getEscAct(); //= new Escala(0, 'M');
        if (tip == 'M') {
            esc = universoMayor.get((int) fund);
        } else if (tip == 'm') {
            esc = universoMenor.get((int) fund);
        } else if (tip == 'o') {
            // No se que hacer si se modula a un grado disminuido
            // por lo pronto lo pondré como menor.
            esc = universoMenor.get((int) fund);
        }
        return esc;
    }

    public int gradoArmSencEscActual(Armonia unArm) {
        return gradoArmSens(unArm, getEscAct());
    }

    public int gradoArmSens(Armonia unArm, Escala esc) {
        // Revisar si unArm es equivalente a alguno de los acordes de la lista
        // y si es asi, ver que grado es la fundamental
        int reg = 0;
        boolean cond = false;
        int fund = -1;

        for (int i = 0; i < esc.getListArms().size(); i++) {
            if (esc.getListArms().get(i).equivalente(unArm)) {
                // Aqui se revisa si es o7
                if (unArm.getTipoAcStr().equals("o7")) {
                    for (int j = 0; j < unArm.getVoces().size(); j++) {
                        if (esc.gradoNota(unArm.getVoces().get(j)) == 7) {
                            fund = (int) Oper.mod(unArm.getVoces().get(j).intValue(), 12);
                            cond = true;
                            break;
                        }
                    }
//                } else if (unArm.getFund12() == getEscAct().getBaseEsc() + 1) {
//                    //Está deshabilitado el napolitano por cuestiones de modulacion                   
//                    fund = (int) getEscAct().getBaseEsc() + 2;// napolitano 
//                    cond = true;
                } else {
                    fund = (int) unArm.getFund12();
                    cond = true;
                    break;
                }
            }
        }
        if (cond) {
            reg = (int) esc.gradoNota(fund);
        } else {
            reg = 0;
        }
        return reg;
    }

    /**
     * Funciona similar a la coordenada tonal regresa el valor del grado del
     * acorde en todas las escalas con relaciones diatonicas 51 V/I 52 V/II etc
     * 41 IV/I 42 IV/II etc..
     *
     * @param unArm
     * @return
     */
    public CoorTon gradoArmSecundEscAct(Armonia unArm) {
        // Antes solo revisaba si el acorde era uno de los diatonicos
        // ahora puedo revisar todas las escalas, pero no se si quiero eso..
        // creo que mejor reviso s∑olo las diatonicas, es cuestion de llamar
        // a los indices correctos.
        // Revisar si unArm es equivalente a alguno de los acordes de la lista
        // y si es asi, ver que grado es la fundamental

        return gradoArmSecund(unArm, getEscAct());
    }

    /**
     * Funciona similar a la coordenada tonal regresa el valor del grado del
     * acorde en todas las escalas con relaciones diatonicas 51 V/I 52 V/II etc
     * 41 IV/I 42 IV/II etc..
     *
     * @param unArm
     * @return
     */
    public CoorTon gradoArmSecund(Armonia unArm, Escala escala) {
        // Antes solo revisaba si el acorde era uno de los diatonicos
        // ahora puedo revisar todas las escalas, pero no se si quiero eso..
        // creo que mejor reviso s∑olo las diatonicas, es cuestion de llamar
        // a los indices correctos.
        // Revisar si unArm es equivalente a alguno de los acordes de la lista
        // y si es asi, ver que grado es la fundamental

        CoorTon coor = new CoorTon();
//        int[] reg = {0, 0};
//        int reg = 0;
        int fund = -1;
        char tipo = 'M';
        boolean cond = false;
        fund = (int) escala.getBaseEsc();
        Escala esc;

        if (gradoArmSens(unArm, escala) > 0) {
            // si es acorde de la escala
            coor.setReg(1, escala.getTipoEsc(), gradoArmSens(unArm, escala));
        } else {
            // si es dominante de otra escala
            for (int i = 0; i < escala.size(); i++) {
                fund = escala.get(i).intValue();
                tipo = escala.getTipoTriada(i).charAt(0);
                esc = getEscala(fund, tipo);
                int grad = gradoArmSens(unArm, esc);
//                System.out.println(esc.getBaseEsc() + " " + esc.getTipoEsc() + " g: " + grad);
//                if (grad == 5 || grad == 7) {
                if (grad == 5) {
                    coor.setReg(i + 1, esc.getTipoEsc(), grad);
                }
                if (grad == 7) {
                    // Aqui se revisa si es o7
                    if (unArm.getTipoAcStr().equals("o7")) {
                        for (int j = 0; j < unArm.getVoces().size(); j++) {
                            if (esc.gradoNota(unArm.getVoces().get(j)) == 7) {
                                coor.setReg(i + 1, esc.getTipoEsc(), grad);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return coor;
    }

    public int chkRegion(Armonia arm) {
        CoorTon cor = this.gradoArmSecundEscAct(arm);
        int esc = cor.getEscala();
        int gra = cor.getGrado();
//        int gra = this.gradoArmSencilla(arm);

        int inv = arm.getInversion();
        int reg = -1;
        // Clasificar los acordes
        if (esc == 1) {
            switch (gra) {
                case 1:
//                    if (!arm.isSept()) {
                    reg = DefRegiones.TON_F;
//                    }
                    break;
                case 2:
                    if (!arm.isSept()) {
                        if (arm.getDuplicacion() == 3) {
                            reg = DefRegiones.SUB_D;
                        }
                    } else {
                        if (arm.isCompleto()) {
                            reg = DefRegiones.SUB_F;
                        }
                    }
                    break;
                case 3:
//                    if (!arm.isSept()) {
                    reg = DefRegiones.DOM_D;
//                    }
                    break;
                case 4:
//                    if (!arm.isSept()) {
                    reg = DefRegiones.SUB_D;
//                    }
                    break;
                case 5:
                    if (!arm.isSept()) {
                        reg = DefRegiones.DOM_D;
                    } else {
                        reg = DefRegiones.DOM_F;
                    }
                    break;
                case 6:
//                    if (!arm.isSept()) {
                    reg = DefRegiones.SUB_D;
//                    }
                    break;
                case 7:
                    if (!arm.isSept()) {
                        if (inv != 0) {
                            reg = DefRegiones.DOM_D;
                        }
                    } else {
                        reg = DefRegiones.DOM_F;
                    }
                    break;
            }
        } else {
            switch (esc) {
                case 1:
                    reg = DefRegiones.MOD_TON;
                    break;
                case 2:
                    reg = DefRegiones.MOD_SUB;
                    break;
                case 3:
                    reg = DefRegiones.MOD_DOM;
                    break;
                case 4:
                    reg = DefRegiones.MOD_SUB;
                    break;
                case 5:
                    reg = DefRegiones.MOD_DOM;
                    break;
                case 6:
                    reg = DefRegiones.MOD_SUB;
                    break;
                case 7:
                    reg = DefRegiones.MOD_DOM;
                    break;
            }
//            reg = DefRegiones.MOD;
        }
        return reg;
    }

    /**
     *
     * @param unAcorde
     * @return
     */
    public boolean perteneceAcDiat(Armonia unAcorde) {
        boolean cond = false;
        if (gradoArmSencEscActual(unAcorde) >= 1 & gradoArmSencEscActual(unAcorde) <= 8) {// 8 son los DD
            cond = true;
        }
        return cond;
    }

    /**
     * Regresa el grado romano de la nota, 0 si no pertenece, usando propiedades
     * de indexOf En menor aun no pone si es accidental o no
     *
     * @param unAcorde
     * @return
     */
    public String gradoAcordeRomSecEscAct(Armonia unAcorde) {
        return gradoAcordeRomSec(unAcorde, getEscAct());
    }

    /**
     * Regresa el grado romano de la nota, 0 si no pertenece, usando propiedades
     * de indexOf En menor aun no pone si es accidental o no
     *
     * @param unAcorde
     * @return
     */
    public String gradoAcordeRomSec(Armonia unAcorde, Escala escala) {
        CoorTon cor = gradoArmSecund(unAcorde, escala);
        int grado = cor.getGrado();
        int es = cor.getEscala();
//        System.out.println(es+"Escala.gradoArodeRomano: " + grado);
        String gradStr = "";
        String esS = "", graS = "", pertS = "";
        if (grado > 0) {// 8 significa dominante secundario
            switch (es) {
                case 1:
                    esS = "";
                    break;
                case 2:
                    esS = "II";
                    break;
                case 3:
                    esS = "III";
                    break;
                case 4:
                    esS = "IV";
                    break;
                case 5:
                    esS = "V";
                    break;
                case 6:
                    esS = "VI";
                    break;
                case 7:
                    esS = "VII";
                    break;
            }
            switch (grado) {
                case 1:
                    if (unAcorde.getInversion() == 2) {
                        graS = "K";
                    } else {
                        graS = "I";
                    }
                    break;
                case 2:
                    if (unAcorde.getFund12() == getEscAct().getBaseEsc() + 1) {
                        //graS = "N";
                        graS = "II";
                    } else {
                        graS = "II";
                    }
                    break;
                case 3:
                    graS = "III";
                    break;
                case 4:
                    graS = "IV";
                    break;
                case 5:
                    if (unAcorde.getTipoAcStr().equals("D7")) {
                        //graS = "N";
                        graS = "DV";
                    } else {
                        graS = "V";
                    }
                    break;
                case 6:
                    graS = "VI";
                    break;
                case 7:
                    if (unAcorde.getTipoAcStr().equals("o7")) {
                        //graS = "N";
                        graS = "Dvii";
                    } else {
                        graS = "VII";
                    }
//                    graS = "VII";
                    break;
            }
            if (escala.perteneceArmonia(unAcorde) == false) {
                pertS = "*";
            }
            if (es != 1) {
                gradStr = pertS + graS + "" + unAcorde.getInversionStr() + "/" + esS;
            } else {
                gradStr = pertS + "" + graS + "" + unAcorde.getInversionStr();
            }
        } else {
            gradStr = "x";
        }
        return gradStr;
    }

//    /**
//     * Regresa el grado romano de la nota, 0 si no pertenece, usando propiedades
//     * de indexOf En menor aun no pone si es accidental o no
//     *
//     * @param unAcorde
//     * @return
//     */
//    public String gradoAcStrSens(Armonia unAcorde, Escala esc) {
//        int grado = gradoArmSens(unAcorde, esc);
//        String gradStr = "";
//        String esS = "", graS = "", pertS = "";
//        if (grado > 0) {// 8 significa dominante secundario
//            switch (grado) {
//                case 1:
//                    graS = "I";
//                    break;
//                case 2:
//                    graS = "II";
//                    break;
//                case 3:
//                    graS = "III";
//                    break;
//                case 4:
//                    graS = "IV";
//                    break;
//                case 5:
//                    graS = "V";
//                    break;
//                case 6:
//                    graS = "VI";
//                    break;
//                case 7:
//                    graS = "VII";
//                    break;
//            }
//            if (esc.perteneceArmonia(unAcorde) == false) {
//                pertS = "*";
//            }
//            gradStr = pertS + "" + graS + "" + unAcorde.getInversionStr();
//        } else {
//            gradStr = "x";
//        }
//        return gradStr;
//    }

    /**
     * Regresa el grado romano de la nota, 0 si no pertenece, usando propiedades
     * de indexOf En menor aun no pone si es accidental o no
     *
     * Sin los asteriscos en la escala menor para que lilypond no se aguite
     *
     * @param unAcorde
     * @return
     */
    public String gradoAcordeRomanoLilY(Armonia unAcorde) {
        int grado = gradoArmSencEscActual(unAcorde);
        String gradStr = "";

        if (grado >= 1 & grado <= 7) {
            switch (getEscAct().getTipoEsc()) {
                case 'M':
                    switch (grado) {
                        case 1:
                            gradStr += "I";
                            break;
                        case 2:
                            gradStr += "II";
                            break;
                        case 3:
                            gradStr += "III";
                            break;
                        case 4:
                            gradStr += "IV";
                            break;
                        case 5:
                            gradStr += "V";
                            break;
                        case 6:
                            gradStr += "VI";
                            break;
                        case 7:
                            gradStr += "VII";
                            break;
                        default:
                            gradStr += "N/A";
                    }

                    break;
                case 'm':
                    switch (grado) {
                        case 1:
                            gradStr += "I";
                            break;
                        case 2:
                            gradStr += "II";
                            break;
                        case 3:
                            gradStr += "III";
                            break;
                        case 4:
                            gradStr += "IV";
                            break;
                        case 5:
                            gradStr += "V";
                            break;
                        case 6:
                            gradStr += "VI";
                            break;
                        case 7:
                            gradStr += "VII";
                            break;
                        default:
                            gradStr += "N/A";
                    }
                    break;
            }
        }
        return gradStr;
    }

    public void cambioEscala(String res) {
//        setEscalaAct(0, 'M');
        if (res.equals("Do Mayor")) {
            setEscalaAct(0, 'M');
        } else if (res.equals("Do# Mayor (Reb)")) {
            setEscalaAct(1, 'M');
        } else if (res.equals("Re Mayor")) {
            setEscalaAct(2, 'M');
        } else if (res.equals("Re# Mayor (Mib)")) {
            setEscalaAct(3, 'M');
        } else if (res.equals("Mi Mayor")) {
            setEscalaAct(4, 'M');
        } else if (res.equals("Fa Mayor")) {
            setEscalaAct(5, 'M');
        } else if (res.equals("Fa# Mayor (Solb)")) {
            setEscalaAct(6, 'M');
        } else if (res.equals("Sol Mayor")) {
            setEscalaAct(7, 'M');
        } else if (res.equals("Sol# Mayor (Lab)")) {
            setEscalaAct(8, 'M');
        } else if (res.equals("La Mayor")) {
            setEscalaAct(9, 'M');
        } else if (res.equals("La# Mayor (Sib)")) {
            setEscalaAct(10, 'M');
        } else if (res.equals("Si Mayor")) {
            setEscalaAct(11, 'M');
        }
        if (res.equals("Do menor")) {
            setEscalaAct(0, 'm');
        } else if (res.equals("Do# menor (Reb)")) {
            setEscalaAct(1, 'm');
        } else if (res.equals("Re menor")) {
            setEscalaAct(2, 'm');
        } else if (res.equals("Re# menor (Mib)")) {
            setEscalaAct(3, 'm');
        } else if (res.equals("Mi menor")) {
            setEscalaAct(4, 'm');
        } else if (res.equals("Fa menor")) {
            setEscalaAct(5, 'm');
        } else if (res.equals("Fa# menor (Solb)")) {
            setEscalaAct(6, 'm');
        } else if (res.equals("Sol menor")) {
            setEscalaAct(7, 'm');
        } else if (res.equals("Sol# menor (Lab)")) {
            setEscalaAct(8, 'm');
        } else if (res.equals("La menor")) {
            setEscalaAct(9, 'm');
        } else if (res.equals("La# menor (Sib)")) {
            setEscalaAct(10, 'm');
        } else if (res.equals("Si menor")) {
            setEscalaAct(11, 'm');
        }
    }
}
