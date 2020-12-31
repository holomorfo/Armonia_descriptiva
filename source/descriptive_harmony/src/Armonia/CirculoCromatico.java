/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armonia;

import agui.Boton;
import java.util.LinkedList;
import polifoniaLib.Armonia;
import polifoniaLib.CoorTon;
import polifoniaLib.Escala;
import polifoniaLib.defs.Oper;
import polifoniaLib.listas.ListFloat;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class CirculoCromatico {

    PApplet p;
    Armonia elAcorde;
    ListFloat notas;
    ListFloat notasGuia;
    LinkedList<Float> xs;
    LinkedList<Float> ys;
    LinkedList<Boton> significadosMayBt;
    LinkedList<Boton> significadosMenBt;
    LinkedList<String> listaStr;
    Datos datos;
    private int px;
    private int py;
    private int diam;
    private int radioAcorde;
    private int radioMay;
    private int radioMen;
    int btLar = 60;
    int aCirculitos = 13;

    public CirculoCromatico(PApplet pa, Datos dt, int x, int y, int dia) {
        p = pa;
        elAcorde = new Armonia();
        datos = dt;
        notas = elAcorde.getVoces();
        listaStr = new LinkedList<String>();
        px = x;
        py = y;
        diam = dia;
        radioAcorde = diam / 2;
        radioMen = radioAcorde + 50;
        radioMay = radioAcorde + 2 * 50;
        xs = new LinkedList<Float>();
        ys = new LinkedList<Float>();
        /////
        significadosMayBt = new LinkedList<Boton>();
        significadosMenBt = new LinkedList<Boton>();

        for (int i = 0; i < 12; i++) {
            significadosMayBt.add(new Boton(p, px - 200 + btLar * (i % 12), 500 + btLar * (i / 12), btLar));
            significadosMayBt.getLast().setRectVF(false);
            significadosMayBt.getLast().setCornerVF(false);
            significadosMayBt.getLast().setColorSuelto(p.color(0, 0, 255, 85));

        }

        for (int i = 12; i < 24; i++) {
            significadosMenBt.add(new Boton(p, px - 200 + btLar * (i % 12), 500 + btLar * (i / 12), btLar));
            significadosMenBt.getLast().setRectVF(false);
            significadosMenBt.getLast().setCornerVF(false);
            significadosMenBt.getLast().setColorSuelto(p.color(0, 0, 255, 85));

        }

    }

    public void setDiam(int diam) {
        this.diam = diam;
    }

    public void setPos(int pox, int poy) {
        px = pox;
        py = poy;
    }

    public void actualizarArmGuia(ListFloat an) {
        if (an.size() > 1) {
            ListFloat a = new ListFloat();
            try {
                a.addAll(an);
            } catch (Exception e) {
            }
            elAcorde = new Armonia(a);
            notasGuia = elAcorde.getSimplificado();
            notas = notasGuia;
        } else {
            notasGuia = new ListFloat();
            notas = new ListFloat();
        }
    }

    public void actualizarNotasPiano(ListFloat an) {
        ListFloat a = new ListFloat();
        try {
            a.addAll(an);
            if (notasGuia.size() > 0) {
                a.addAll(notasGuia);
            }
        } catch (Exception e) {
        }
        elAcorde = new Armonia(a);
        notas = elAcorde.getSimplificado();
    }

    public void calcularListaArmonia() {
        String lista = "";
        listaStr = new LinkedList<String>();
        CoorTon cor = datos.getUniv().gradoArmSecundEscAct(new Armonia(notas));
        Armonia arm = elAcorde;
        int gradM = 0;
        int gradm = 0;
        int coorM = 0;
        int coorm = 0;

        String etq = "";

        LinkedList<String> parent1Mayor = new LinkedList<String>();
        LinkedList<String> parent2Mayor = new LinkedList<String>();
        LinkedList<String> parent3Mayor = new LinkedList<String>();
        LinkedList<String> parent4Mayor = new LinkedList<String>();

        LinkedList<String> parent1Menor = new LinkedList<String>();
        LinkedList<String> parent2Menor = new LinkedList<String>();
        LinkedList<String> parent3Menor = new LinkedList<String>();
        LinkedList<String> parent4Menor = new LinkedList<String>();

        parent1Mayor.add("00M");
        parent1Mayor.add("07M");
        parent1Mayor.add("05M");
        parent1Mayor.add("02m");
        parent1Mayor.add("04m");
        parent1Mayor.add("05m");
        parent1Mayor.add("09m");

        parent2Mayor.add("10M");
        parent2Mayor.add("02M");
        parent2Mayor.add("11m");
        parent2Mayor.add("07m");

        parent3Mayor.add("08M");
        parent3Mayor.add("09M");
        parent3Mayor.add("11M");
        parent3Mayor.add("01M");
        parent3Mayor.add("03M");
        parent3Mayor.add("04M");
        parent3Mayor.add("00m");
        parent3Mayor.add("10m");

        parent4Mayor.add("06M");
        parent4Mayor.add("01m");
        parent4Mayor.add("03m");
        parent4Mayor.add("06m");
        parent4Mayor.add("08m");

        //__________________________________________________
        parent1Menor.add("00m");
        parent1Menor.add("07m");
        parent1Menor.add("05m");
        parent1Menor.add("10M");
        parent1Menor.add("08M");
        parent1Menor.add("07M");
        parent1Menor.add("03M");

        parent2Menor.add("10m");
        parent2Menor.add("02m");
        parent2Menor.add("01M");
        parent2Menor.add("05M");

        parent3Menor.add("08m");
        parent3Menor.add("09m");
        parent3Menor.add("11m");
        parent3Menor.add("01m");
        parent3Menor.add("03m");
        parent3Menor.add("04m");
        parent3Menor.add("00M");
        parent3Menor.add("02M");

        parent4Menor.add("06m");
        parent4Menor.add("11M");
        parent4Menor.add("09M");
        parent4Menor.add("06M");
        parent4Menor.add("04M");


        // Quiero que recorra las 24 tonalidades y revise el significado sencillo
        // en todas.
        //Mayor
        //        for (int k = 0; k < 12; k++) {
        //            int idx = (int) Oper.mod(k + 7, 12);
        int baseEsc = (int) datos.getUniv().getEscAct().getBaseEsc();

        int escalaNum = 0;
        char escTip = 'x';
        Escala escTemp;
        LinkedList<String> listTemp;
        int baseActual = (int) datos.getUniv().getEscAct().getBaseEsc();

        if (datos.getUniv().getEscAct().getTipoEsc() == 'M') {
            listaStr.addLast("1er Grado");
            //listaStr.add(datos.getUniv().gradoAcordeRomSecEscAct(elAcorde));
//            gradM = datos.getUniv().gradoArmSecund(arm, datos.getUniv().getUniversoMayor().get(idx)).getGrado();
            listTemp = parent1Mayor;
//            parentescoCalcular();
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += " " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ":  " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }
            listaStr.addLast("\n2do Grado");
            listTemp = parent2Mayor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += " " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }
            listaStr.addLast("\n3er Grado");
            listTemp = parent3Mayor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += "  " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }
            listaStr.addLast("\n4to Grado");
            listTemp = parent4Mayor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += "  " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }

        } else {
            listaStr.addLast("1er Grado");
            //listaStr.add(datos.getUniv().gradoAcordeRomSecEscAct(elAcorde));

            listTemp = parent1Menor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += "  " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }
            listaStr.addLast("\n2do Grado");
            listTemp = parent2Menor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += "  " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }
            listaStr.addLast("\n3er Grado");
            listTemp = parent3Menor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += "  " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }

            listaStr.addLast("\n4to Grado");
            listTemp = parent4Menor;
            for (int i = 0; i < listTemp.size(); i++) {
                lista = "";
                escalaNum = (int) (Integer.parseInt(listTemp.get(i).substring(0, 2)) + baseActual) % 12;
                escTip = listTemp.get(i).charAt(2);
                escTemp = datos.getUniv().getEscala(escalaNum, escTip);
                gradM = datos.getUniv().gradoArmSens(arm, escTemp);
                if (gradM > 0) {
                    // Aquí va la descripción de la interpretación
                    // Tonalidad actual en formato Nota, tipo. y e interpetacion romana
                    lista += "" + escTemp.getNombreEsc();
                    lista += "  " + Oper.numRomano((int) datos.getUniv().getEscAct().gradoNota(escalaNum % 12)) + "";
                    lista += ": " + datos.getUniv().gradoAcordeRomSec(arm, escTemp);
                    listaStr.addLast(lista);
                }
            }

        }

    }

    public void paint() {
        calcularListaArmonia();
        xs.clear();
        p.strokeWeight(3);
        ys.clear();
        p.fill(255);
        p.ellipseMode(p.CENTER);
        // Circulo grande acordes
        p.ellipse(px, py, diam, diam);
        float x, y;
        // Circulos pequenios notas
        float ang = -2 * p.PI / 12;
        for (int i = 0; i < 12; i++) {
            x = px + radioAcorde * p.cos(i * ang + p.PI / 2);
            y = py - radioAcorde * p.sin(i * ang + p.PI / 2);

            p.fill(255);// Fondo blanco
            p.ellipse(x, y, aCirculitos, aCirculitos);
            if (datos.getUniv().getEscAct().perteneceNota(i)) {
                p.fill(0, 0, 255, 85);// Fondo de color
            } else {
                p.fill(255);
            }
            p.ellipse(x, y, aCirculitos, aCirculitos);
        }

        // Pintar las notas nuevas.
        float nt;
        for (int j = 0; j < notas.size(); j++) {
            nt = p.map(notas.get(j) % 12, 0, 12, 2 * p.PI, 0);
            x = px + radioAcorde * p.cos(nt + p.PI / 2);
            y = py - radioAcorde * p.sin(nt + p.PI / 2);
            xs.add(x);
            ys.add(y);
            p.fill(200, 0, 0);
            p.ellipse(x, y, 6, 6);
        }


        // Dibujar lineas.
        float dis = 0;
        for (int i = 0; i < notas.size(); i++) {
            for (int j = i; j < notas.size(); j++) {
                if (i != j) {
                    dis = Oper.mod(p.abs(notas.get(i) - notas.get(j)), 12);
                    colorDisStroke(dis);// cambia color stroke
                    try {
                        p.line(xs.get(i), ys.get(i), xs.get(j), ys.get(j));
                    } catch (Exception e) {
                        p.println("Exception Circulocrom.pain dibujar lineas");
                    }
                    p.stroke(0); // importante stroke negro
                }
            }
        }


        ///////////////////
        // Circulo grande Tonalidades 
        p.noFill();
        p.fill(0);
        // Circulos pequenios
        for (int i = 0; i < 12; i++) {
            x = px + radioMen * p.cos(i * ang + p.PI / 2);
            y = py - radioMen * p.sin(i * ang + p.PI / 2);
            p.fill(255);// Fondo blanco
            significadosMenBt.get(i).setPosXY((int) x, (int) y);
//            p.ellipse(x, y, aCiculitos, aCiculitos);
        }
        p.noFill();
//        p.ellipse(px, py, radioMay * 2, radioMay * 2);
        p.fill(0);



        String str = datos.getUniv().gradoAcordeRomSecEscAct(elAcorde);
        str += "\n" + datos.getUniv().getEscAct().nombreAcordeAbsoluto(elAcorde);
        p.ellipseMode(p.CENTER);
        p.fill(255);
        p.noStroke();
        p.ellipse(px, py, 60, 60);
        p.stroke(3);
        p.fill(0);
        String str2 = "";
        for (int i = 0; i < listaStr.size(); i++) {
            str2 += listaStr.get(i) + "\n";
        }
        p.textFont(p.createFont("AGaramondPro-Bold", 20));
        p.text(str2, 370, 130);
        p.text(str, px - 7, py - 7);
        p.rectMode(p.CORNER);

        p.strokeWeight(1);
        p.fill(255);
        p.textFont(datos.getColoresFonts().getLetrasA());

    }

    public void colorDisStroke(float inter) {
        // Pinta la linea de acuerdo a la consonancia, disonancia
//        System.out.println("Inter: " + inter % 1);
        float num = inter - (float) Math.floor(inter);
//        p.println("Inter: " + inter);
//        p.println("Num: " + num);
        if (num == 0) {
            switch ((int) inter) {
                case 0:// Octava azul
                    p.stroke(0, 0, 255);
                    break;
                case 1:// 2da menor rojo
                    p.stroke(255, 0, 0);
                    break;
                case 2:// 2daMayor rojo
                    p.stroke(223, 0, 255);
                    break;
                case 3:// 3ram verde
                    p.stroke(0, 255, 0);
                    break;
                case 4:// 3raM v
                    p.stroke(0, 255, 0);
                    break;
                case 5:// Cuarta azul
                    p.stroke(0, 0, 255);
                    break;
                case 6:// tritono rojo
                    p.stroke(255, 0, 0);
                    break;
                case 7:// Quinta azul
                    p.stroke(0, 0, 255);
                    break;
                case 8:// 6m verde
                    p.stroke(0, 255, 0);
                    break;
                case 9:// 6M verde
                    p.stroke(0, 255, 0);
                    break;
                case 10:// 7m
                    p.stroke(223, 0, 255);
                    break;
                case 11:// 7M
                    p.stroke(255, 0, 0);
                    break;
            }
        }
        // AGUASS!
//        p.stroke(0, 255, 0);
    }
}
