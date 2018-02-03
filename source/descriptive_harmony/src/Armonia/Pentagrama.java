/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armonia;

import agui.BtnArmonia;
import aguiListas.ListBotnArm;
import polifoniaLib.Armonia;
import polifoniaLib.Escala;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class Pentagrama {

    PApplet p;
    ListFloat notasActual;
    ListFloat notasPlay;
    ListFloat notasMapa;
    Armonia arm;
//    ListFloats secuenciaLista;
    int inicC;
    private int largoX = 400;
    private int anchoY = 300;
    int posX, posY;
    Datos datos;
    //PImage b;
    // Images must be in the "data" directory to load correctly
    String progStr = "";
    int idMn = 18;// Indices de notas min y max
    int idMx = 50;
    int margenNot;
    int margenArmadura;
    int sepX = 50;
    int tamX = (15) * 2;// Tamano nota redonda
    int tamY = (11) * 2;// Tamanio nota redonda
    Escala doM = new Escala(0, 'M');
    int mi, sol, si, re, fa, la, fa2, re2, si2, sol2;

    public Pentagrama(PApplet pa, Datos mL, int px, int py, int largo) {
        p = pa;
        datos = mL;
        this.posX = px;
        this.posY = py;
        this.largoX = largo;
        inicC = 10;
        margenNot = largo - 200;
        margenArmadura = 50;

        mi = (int) p.map(doM.indiceNota(64), idMn, idMx, posY + anchoY, posY);
        sol = (int) p.map(doM.indiceNota(67), idMn, idMx, posY + anchoY, posY);
        si = (int) p.map(doM.indiceNota(71), idMn, idMx, posY + anchoY, posY);
        re = (int) p.map(doM.indiceNota(74), idMn, idMx, posY + anchoY, posY);
        fa = (int) p.map(doM.indiceNota(77), idMn, idMx, posY + anchoY, posY);
        la = (int) p.map(doM.indiceNota(57), idMn, idMx, posY + anchoY, posY);
        fa2 = (int) p.map(doM.indiceNota(53), idMn, idMx, posY + anchoY, posY);
        re2 = (int) p.map(doM.indiceNota(50), idMn, idMx, posY + anchoY, posY);
        si2 = (int) p.map(doM.indiceNota(47), idMn, idMx, posY + anchoY, posY);
        sol2 = (int) p.map(doM.indiceNota(43), idMn, idMx, posY + anchoY, posY);

//        p.println(mi + " " + sol + " " + si + " " + sol2);
    }

    public void colocarArmadura() {// CORREGIR
        p.imageMode(p.CORNER);
        if (datos.getEscalaAct().getTipoEsc() == 'M') {
            switch ((int) datos.getEscalaAct().getBaseEsc()) {
                case 7:
                    p.image(datos.getImags().getArmSost().get(0), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(0), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 2:
                    p.image(datos.getImags().getArmSost().get(1), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(1), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 9:
                    p.image(datos.getImags().getArmSost().get(2), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(2), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 4:
                    p.image(datos.getImags().getArmSost().get(3), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(3), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 11:
                    p.image(datos.getImags().getArmSost().get(4), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(4), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                // Mayores con bemoles
                case 5:
                    p.image(datos.getImags().getArmBem().get(0), posX + margenArmadura, fa - 25, (int) (1.5 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(0), posX + margenArmadura, la - 25, (int) (1.5 * 121), (int) (1 * 131));
                    break;
                case 10:
                    p.image(datos.getImags().getArmBem().get(1), posX + margenArmadura, fa - 25, (int) (1.5 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(1), posX + margenArmadura, la - 25, (int) (1.5 * 121), (int) (1 * 131));
                    break;
                case 3:
                    p.image(datos.getImags().getArmBem().get(2), posX + margenArmadura, fa - 25, (int) (1.5 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(2), posX + margenArmadura, la - 25, (int) (1.5 * 121), (int) (1 * 131));
                    break;
                case 8:
                    p.image(datos.getImags().getArmBem().get(3), posX + margenArmadura, fa - 25, (int) (1.5 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(3), posX + margenArmadura, la - 25, (int) (1.5 * 121), (int) (1 * 131));
                    break;
                case 1:
                    p.image(datos.getImags().getArmBem().get(4), posX + margenArmadura, fa - 25, (int) (1.5 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(4), posX + margenArmadura, la - 25, (int) (1.5 * 121), (int) (1 * 131));
                    break;
                case 6:
                    p.image(datos.getImags().getArmBem().get(5), posX + margenArmadura, fa - 25, (int) (1.5 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(5), posX + margenArmadura, la - 25, (int) (1.5 * 121), (int) (1 * 131));
                    break;
            }

        } else if (datos.getEscalaAct().getTipoEsc() == 'm') {
            switch ((int) datos.getEscalaAct().getBaseEsc()) {
                case 2:
                    p.image(datos.getImags().getArmBem().get(0), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(0), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 7:
                    p.image(datos.getImags().getArmBem().get(1), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(1), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 0:
                    p.image(datos.getImags().getArmBem().get(2), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(2), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 5:
                    p.image(datos.getImags().getArmBem().get(3), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(3), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 10:
                    p.image(datos.getImags().getArmBem().get(4), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(4), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 3:
                    p.image(datos.getImags().getArmBem().get(5), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmBem().get(5), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                // Menores con sostenidos
                case 4:
                    p.image(datos.getImags().getArmSost().get(0), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(0), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 11:
                    p.image(datos.getImags().getArmSost().get(1), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(1), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 6:
                    p.image(datos.getImags().getArmSost().get(2), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(2), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 1:
                    p.image(datos.getImags().getArmSost().get(3), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(3), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
                case 8:
                    p.image(datos.getImags().getArmSost().get(4), posX + margenArmadura, fa - 45, (int) (1 * 121), (int) (1 * 131));
                    p.image(datos.getImags().getArmSost().get(4), posX + margenArmadura, la - 45, (int) (1 * 121), (int) (1 * 131));
                    break;
            }
        }
    }

    public void mousePressed() {
//        p.println("pent.mousePressed: " + datos.getIdxArmActual());
    }

    /**
     * Pinta con las imagenes de Pepy El estado puede ser m-melodia, o a-armonia
     */
    public void paint() {
        p.imageMode(p.CENTER);
        p.fill(255);
//        p.rect(posX, posY, largoX, anchoY);
        int notaX;
        int notaY;
        int midiNm;
        Escala doM = new Escala(0, 'M');
        Escala dom = new Escala(0, 'M');
        int temp = 0;
        if (datos.getEscalaAct().getTipoEsc() == 'M') {
            temp = doM.indiceNota(datos.getEscalaAct().getBaseEsc()) % 7;
        } else if (datos.getEscalaAct().getTipoEsc() == 'm') {
            temp = dom.indiceNota(datos.getEscalaAct().getBaseEsc()) % 7;
        }
        p.image(datos.getImags().getClaveFa(), posX + 35, fa2 + 10, (int) (1 * 74), (int) (1 * 77));
        p.image(datos.getImags().getClaveSol(), posX + 35, si, 112 / 1, 187 / 1);
        colocarArmadura();

        // Pintar lineas de pentagrama
        p.strokeWeight(2);
        p.line(posX, mi, posX + largoX, mi); // mi
        p.line(posX, sol, posX + largoX, sol); // sol
        p.line(posX, si, posX + largoX, si); // si
        p.line(posX, re, posX + largoX, re);
        p.line(posX, fa, posX + largoX, fa);

        p.line(posX, la, posX + largoX, la); // mi
        p.line(posX, fa2, posX + largoX, fa2); // sol
        p.line(posX, re2, posX + largoX, re2); // si
        p.line(posX, si2, posX + largoX, si2);
        p.line(posX, sol2, posX + largoX, sol2);

        p.fill(0);
        p.ellipseMode(p.CENTER);



//        // Pinta Secuencia, en caso de que haya una serie de acordes
        // Creo que debo limpiar estos métodos para que sólo haya uno.

        int inic = 0;
        if (datos.getListBotnArm().size() > 0) {
            if (datos.getListBotnArm().size() > inicC) {
                inic = datos.getListBotnArm().size() - inicC;
            }
            for (int i = inic; i < datos.getListBotnArm().size(); i++) {
                // Pintar el acorde
                notaX = posX + margenNot + i * sepX - (sepX * datos.getListBotnArm().size());
                arm = new Armonia(datos.getListBotnArm().get(i).getArm());
//                p.text(datos.getUniv().gradoAcordeRomano(arm), notaX, posY + anchoY - 40);
                datos.getListBotnArm().get(i).setPosXY(notaX, posY + anchoY - 40);
                //btnTmp = new BtnArmonia(p, arm, notaX, posY + anchoY - 40, 33);
//                btnTmp.setTexto(datos.getUniv().gradoAcordeRomano(arm));
//                 datos.getListBotnArm().add(btnTmp);
                datos.getListBotnArm().get(i).paint();

                for (int voz = 0; voz < datos.getListBotnArm().get(i).getArm().getVoces().size(); voz++) {
                    if (datos.getListArm().get(i).getVoces().get(voz).intValue() < 12) {
                        break;
                    }
                    midiNm = datos.getListBotnArm().get(i).getArm().getVoces().get(voz).intValue();
                    notaY = (int) p.map(doM.indiceNota(datos.getEscalaAct().getBaseEsc()) + datos.getEscalaAct().indiceNota(midiNm), idMn, idMx, posY + anchoY, posY);
                    if (midiNm != 0) {
                        if (!datos.getEscalaAct().perteneceNota(midiNm)) {
                            if (datos.getEscalaAct().sostenido == true) {
                                p.image(datos.getImags().getNotaSost(), notaX - 30, notaY - 30, (int) (1 * 30), (int) (1 * 69));
                                p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                            } else {
                                p.image(datos.getImags().getNotaBemol(), notaX - 30, notaY - 35, (int) (1 * 27), (int) (1 * 61));
                                p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                            }
                        } else {
                            p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                        }
                    }
                }
            }
        }

        notasActual = datos.getNotasActuales();
        notasMapa = datos.getNotasMapa();
        notasPlay = datos.getOrq().getFloatActivos();

        // PINTAR ACORDE play
        // Pinta acorde piano, el acorde que se toca en tiempo real
        if (notasPlay != null) {
            notaX = posX + margenNot + 60 * 2;

            for (int i = 0; i < notasPlay.size(); i++) {
                if (notasPlay.get(i).intValue() < 12) {
                    break;
                }
                midiNm = notasPlay.get(i).intValue();
                notaY = (int) p.map(doM.indiceNota(datos.getEscalaAct().getBaseEsc()) + datos.getEscalaAct().indiceNota(midiNm), idMn, idMx, posY + anchoY, posY);
                if (midiNm != 0) {
                    if (!datos.getEscalaAct().perteneceNota(midiNm)) {
                        if (datos.getEscalaAct().sostenido == true) {
                            p.image(datos.getImags().getNotaSost(), notaX - 30, notaY - 30, (int) (1 * 30), (int) (1 * 69));
                            p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                        } else {
                            p.image(datos.getImags().getNotaBemol(), notaX - 30, notaY - 35, (int) (1 * 27), (int) (1 * 61));
                            p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                        }
                    } else {
                        p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                    }
                }
            }
            Armonia arm = new Armonia(notasPlay);

            //p.textFont(datos.getColoresFonts().getLetrasC());
            //p.text(datos.getUniv().getEscAct().nombreAcordeAbsoluto(arm), notaX+40, posY + anchoY - 265);
            //p.text(datos.getUniv().gradoAcordeRomSecEscAct(arm), notaX+40, posY + anchoY - 20);
            //p.textFont(datos.getColoresFonts().getLetrasA());

        }
        // PINTAR Explorando
        // Pinta acorde temporal, cuando se mueve el mouse por el mapa
        if (notasMapa != null) {
            for (int i = 0; i < notasMapa.size(); i++) {
                if (notasMapa.get(i).intValue() < 12) {
                    break;
                }
                midiNm = notasMapa.get(i).intValue();
                notaX = posX + margenNot + 60 * 1;
                notaY = (int) p.map(doM.indiceNota(datos.getEscalaAct().getBaseEsc()) + datos.getEscalaAct().indiceNota(midiNm), idMn, idMx, posY + anchoY, posY);
                if (midiNm != 0) {
                    if (!datos.getEscalaAct().perteneceNota(midiNm)) {
                        if (datos.getEscalaAct().sostenido == true) {
                            p.image(datos.getImags().getNotaSost(), notaX - 30, notaY - 30, (int) (1 * 30), (int) (1 * 69));
                            p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                        } else {
                            p.image(datos.getImags().getNotaBemol(), notaX - 30, notaY - 35, (int) (1 * 27), (int) (1 * 61));
                            p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                        }
                    } else {
                        p.image(datos.getImags().getNotaRedonda(), notaX, notaY - 12, tamX, tamY);
                    }
                }
            }
        }
        p.strokeWeight(0);
    }
}
