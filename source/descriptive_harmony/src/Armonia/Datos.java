/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armonia;

import agui.*;
import aguiListas.ListBotnArm;
import com.sun.org.apache.bcel.internal.generic.CALOAD;
import polifoniaLib.Armonia;
import polifoniaLib.Escala;
import polifoniaLib.UniversoTonal;
import polifoniaLib.listas.ListArmonia;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;
import themidibus.MidiBus;

/**
 * Se encarga de toda la informacion principal del programa, las referencias se
 * deben hacer a el. Por ejemplo, la escala, y quiza la vecindad epsilon
 *
 * @author Cristian
 */
public class Datos {

//    String[] selValues;
    private PApplet p;
    private MidiFile midExp;
    ListBotnArm listBotnArm;
    Boton playBtn;
    public UniversoTonal univ;
    public Orquestacion orq;
    public ListFloat notasActuales;
    public ListArmonia listArm;
    private int idxArmActual = -1;
    public ListFloat notasMapa;
    public ListFloat notasPlay;
//    private List2Float armoniaLista;
    private BancoColoresNFonts coloresNfonts;
    private BancoImagenes imags;

    public Datos(PApplet pa, MidiBus mBus, int largEsc, int altEsc) {
        p = pa;
        listBotnArm = new ListBotnArm();
        coloresNfonts = new BancoColoresNFonts(p);
        imags = new BancoImagenes(p);
        //println(PFont.list());
        univ = new UniversoTonal(p, largEsc, altEsc);
        univ.setEscalaAct(0, 'M');
        // Instrumentos
        playBtn = new Boton(p, 300, 10, 50, 20);
        playBtn.setEtiqueta("Play");
        orq = new Orquestacion(p, mBus, 150, 300, 550);
        orq.setVisible(false);
        listArm = new ListArmonia();
        notasActuales = new ListFloat();
        notasMapa = new ListFloat();
        notasPlay = new ListFloat();
        p.textFont(coloresNfonts.getLetrasA());
        //armoniaLista = new List2Float();
    }

    public void mousePressed() {
        if (playBtn.mouseVF()) {
            orq.gMnInst().playSecuencia(listArm, 1);
        }
    }

    public void exportarMidi(String nom) {
        midExp = new MidiFile();

        midExp.noteOff(0, 60);
        for (int i = 0; i < listArm.size(); i++) {
            for (int j = 0; j < listArm.get(i).getVoces().size(); j++) {
                if (j == 0) {
                    midExp.noteOn(0, listArm.get(i).getVoces().get(j).intValue(), 50);
                } else {
                    midExp.noteOn(0, listArm.get(i).getVoces().get(j).intValue(), 50);
                }
            }
            for (int j = 0; j < listArm.get(i).getVoces().size(); j++) {
                if (j == 0) {
                    midExp.noteOff(32, listArm.get(i).getVoces().get(j).intValue());
                } else {
                    midExp.noteOff(0, listArm.get(i).getVoces().get(j).intValue());
                }
            }

        }
        try {
//            String nom = "" + (System.currentTimeMillis() / 1000) + "-Armonia.mid";
            midExp.writeToFile(nom);
        } catch (Exception e) {
        }
    }

    public void paint() {
    }

    public ListArmonia getListArm() {
        return listArm;
    }

    public int getIdxArmActual() {
        return idxArmActual;
    }

    public ListBotnArm getListBotnArm() {
        return listBotnArm;
    }

    public void setIdxArmActual(int idxArmActual) {
        this.idxArmActual = idxArmActual;
    }

    public void actualizarIdx(ListFloat notasNvs) {
    }

    public void setNotasActuales(ListFloat notasNvs) {
        if (idxArmActual < 0) {
            boolean cond = true;
            // Revisar que no sean acoordes de referencia.
            for (int i = 0; i < notasNvs.size(); i++) {
                if (notasNvs.get(i) < 12) {
                    cond = false;
                    break;
                }
            }
            if (cond) {
                listArm.add(new Armonia(notasNvs));
                BtnArmonia btnTmp = new BtnArmonia(p, listArm.getLast(), 0, 0, 33);
                btnTmp.setTexto(getUniv().gradoAcordeRomSecEscAct(listArm.getLast()));
                listBotnArm.add(btnTmp);
                this.notasActuales = notasNvs;
                orq.gMnInst().playAcorde(listArm.getLast(), 50, 1);
            }
        } else {
            boolean cond = true;
            // Revisar que no sean acoordes de referencia.
            for (int i = 0; i < notasNvs.size(); i++) {
                if (notasNvs.get(i) < 12) {
                    cond = false;
                    break;
                }
            }
            if (cond) {
                listArm.set(idxArmActual, new Armonia(notasNvs));
//            listArm.add(new Armonia(notasNvs));
                listBotnArm.get(idxArmActual).setArm(listArm.get(idxArmActual));
                listBotnArm.get(idxArmActual).setTexto(getUniv().gradoAcordeRomSecEscAct(listArm.getLast()));
                orq.gMnInst().playAcorde(listArm.get(idxArmActual), 50, 1);
            }
        }
    }

    public ListFloat getNotasActuales() {
        ListFloat lis = new ListFloat();
        if (idxArmActual > 0) {
            lis = getListArm().get(idxArmActual - 1).getVoces();
        } else {
            lis = notasActuales;
        }

        return lis;
    }

    public void borrarAcordeActual(DiccionarioMapa dicc) {
        if (idxArmActual > 0) {
            listBotnArm.remove(idxArmActual);
            listArm.remove(idxArmActual);
            idxArmActual = -1;
            dicc.calcularAcordesCercanos();
        } else {
            if (listBotnArm.size() > 0) {
                listBotnArm.removeLast();
                listArm.removeLast();
                idxArmActual =-1;
                dicc.calcularAcordesCercanos();
            }
        }

    }

    public ListFloat getNotasMapa() {
        return notasMapa;
    }

    public void setNotasMapa(ListFloat notasMapa) {
        this.notasMapa = notasMapa;
    }

    public ListFloat getNotasPlay() {
        return notasPlay;
    }

    public void setNotasPlay(ListFloat notasPlay) {
        this.notasPlay = notasPlay;
    }

//    public void borrarUltimaArmonia() {
//        if (armoniaLista.size() > 0) {
//            armoniaLista.removeLast();
//        }
//    }
    public BancoColoresNFonts getColoresFonts() {
        return coloresNfonts;
    }

    public Orquestacion getOrq() {
        return orq;
    }

    public void setOrq(Orquestacion orq) {
        this.orq = orq;
    }

    public UniversoTonal getUniv() {
        return univ;
    }

    public Escala getEscalaAct() {
        return univ.getEscAct();
    }

    public void setEscala(int fund, char tip) {
        univ.setEscalaAct(fund, tip);
//        escala.iniciarEscalas();
    }

    public BancoImagenes getImags() {
        return imags;
    }
}
