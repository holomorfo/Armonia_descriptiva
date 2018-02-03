/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib.defs;

import java.util.LinkedList;

public class DefEnlace {

//    String[] strCorto={
//
//    };
//    String[] strLargo;
    LinkedList<String> strCortoL;
    LinkedList<String> strLargoL;

    public DefEnlace() {
//        strCorto = new  String[];
//        strLargo = new  String[];
        
        strCortoL = new LinkedList<String>();
        strLargoL = new LinkedList<String>();


        //
        strCortoL.add("58Par");
        strLargoL.add("Paralelas prohibidas");
        //
        strCortoL.add("Arm");
        strLargoL.add("Armonico");
        //
        strCortoL.add("Mel");
        strLargoL.add("Melodico");
        //
        strCortoL.add("Tdir");
        strLargoL.add("Traslado directo");
        //
        strCortoL.add("Tcon");
        strLargoL.add("Traslado contrario");
        //
        strCortoL.add("Tobl");
        strLargoL.add("Traslado oblicuo");
        //
        strCortoL.add("Sop3");
        strLargoL.add("Salto de terceras \nen Soprano");
        //
        strCortoL.add("Ten3");
        strLargoL.add("Salto de terceras \nen Tenor");
        //
        strCortoL.add("PCad");
        strLargoL.add("Prepara \nAcorde cadencial K64");
        //
        strCortoL.add("RCad");
        strLargoL.add("Resuelve \nAcorde cadencial K64");
        //
        strCortoL.add("Arm6");
        strLargoL.add("Armonico, inversion");
        //
        strCortoL.add("Tra6");
        strLargoL.add("Traslado, inversion");
        //
        strCortoL.add("Slt6");
        strLargoL.add("Salto, inversion");
        //
        strCortoL.add("6-6");
        strLargoL.add("Enlace de 6-6");
        //
        strCortoL.add("6-6b");
        strLargoL.add("Enlace de 6-6 \ncorrelacion segunda");
        //
        strCortoL.add("6-6c");
        strLargoL.add("Enlace de 6-6 \nmenor t6-D6, D6-t6");
        //
        strCortoL.add("6-6d");
        strLargoL.add("Enlace de 6-6 \nmenor S6-D6");
        //
        strCortoL.add("6-6d");
        strLargoL.add("Enlace de 6-6 \nmenor S6-D6");
        //
        strCortoL.add("6-6d");
        strLargoL.add("Enlace de 6-6 \nmenor S6-D6");
        //
        strCortoL.add("6,4Pas");
        strLargoL.add("Enlace de 6,4 \ncorde de paso");
        //
        strCortoL.add("D7Prep");
        strLargoL.add("Prepara D7 por salto\n o paso");
        //
        strCortoL.add("D7PrepB");
        strLargoL.add("Prepara D7 por nota\ncomun");
        //
        strCortoL.add("S-D7*");
        strLargoL.add("Preparacion de D7\nincompletopor medio de S");
        //
        strCortoL.add("D7-T*5");
        strLargoL.add("Resolucion de D7\na tonica sin quinta");
        //
        strCortoL.add("D7-T");
        strLargoL.add("Resolucion de D7\na tonica completa");
        //
        strCortoL.add("D7*5-T");
        strLargoL.add("Resolucion de D7\nincompleto a tonica completa");
        //
        strCortoL.add("D7i-T");
        strLargoL.add("Resolucion de D7\ninversion a tonica completa");
        //
        strCortoL.add("Slt5");
        strLargoL.add("Salto de 5tas\nde D2 a T6");
        //
        strCortoL.add("Slt5-1");
        strLargoL.add("Salto de 5tas\ny 1ras de D2 a T6");
        //
        strCortoL.add("VII7-T");
        strLargoL.add("VII7 a Tonica 3ra duplicada");
        //
        strCortoL.add("VII7-D7");
        strLargoL.add("VII7 a D7 e inversiones");
        //
        strCortoL.add("VII7-D7b");//261
        strLargoL.add("VII7 a D7 e inversiones");
        //
        strCortoL.add("Pr-SII6");
        strLargoL.add("Preparacion SII6");
        //
        strCortoL.add("SII6-Rs");
        strLargoL.add("Resolucion de SII6");
        //
        strCortoL.add("Pr-SII");
        strLargoL.add("Preparacion de SII");
        //
        strCortoL.add("SII-Rs");
        strLargoL.add("Resolucion de SII");
        //
        strCortoL.add("V-VI");
        strLargoL.add("Giro interrumpido");
        //
        strCortoL.add("TSVI Esl");
        strLargoL.add("Eslabon intermedio");
        //
        strCortoL.add("TSVI sub");
        strLargoL.add("TSVI Funcion Subdominante");
        //
        strCortoL.add("Pr-SII7");
        strLargoL.add("Preparacion SII7");
        //
        strCortoL.add("SII7-V");
        strLargoL.add("Resolucion de SII7 a V ");
        //
        strCortoL.add("SII7-K");
        strLargoL.add("Resolucion de SII7 a K63 ");
        //
        strCortoL.add("SII7-T");
        strLargoL.add("Resolucion de SII7 a T");
        //
        strCortoL.add("SII7-D7");
        strLargoL.add("Resolucion de SII7 a D7 e inv ");
        //
        strCortoL.add("SII-SII2");
        strLargoL.add("SII2 de paso");
        //
        strCortoL.add("III-mel");
        strLargoL.add("Movimiento de paso en III");
        //
        strCortoL.add("Cant");
        strLargoL.add("Enlace vocal");
//
        strCortoL.add("Cool");
        strLargoL.add("Enlace Cool ;)");
        //
        strCortoL.add("x");
        strLargoL.add("N/D");


    }

    public LinkedList<String> getStrCortoL() {
        return strCortoL;
    }

    public LinkedList<String> getStrLargoL() {
        return strLargoL;
    }



    public int gIdx(String sr) {
//        int idx = -1;
//        for (int i = 0; i < strCorto.size(); i++) {
//            if (strCorto.get(i).equals(sr)) {
//                idx = i;
//            }
//        }
        return strCortoL.indexOf(sr);
    }
}
