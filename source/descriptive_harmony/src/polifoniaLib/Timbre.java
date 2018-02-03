/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import polifoniaLib.defs.DefTimbres;
import polifoniaLib.listas.ListFloat;

/**
 *
 * @author Cristian
 */
public class Timbre extends ListFloat {

    float fund;

    /**
     * Aqui se ponen las amplitudes de cada uno de los timbres posibles.
     */
    public Timbre(float fun) {
        fund = fun;
        for (int i = 0; i < DefTimbres.TimbA.length; i++) {
            add(DefTimbres.TimbA[i]);
        }
    }

    /**
     * Aqui se ponen las amplitudes de cada uno de los timbres posibles.
     */
    public Timbre(float fo, float f1, float f2, float f3, float f4, float f5, float f6) {
        fund = fo;
        add(f1);
        add(f2);
        add(f3);
        add(f4);
        add(f5);
        add(f6);
    }

    public Timbre(double fo, double f1, double f2, double f3, double f4, double f5, double f6) {
        fund = (float) fo;
        add((float) f1);
        add((float) f2);
        add((float) f3);
        add((float) f4);
        add((float) f5);
        add((float) f6);
    }

    public float getFund() {
        return fund;
    }

    public void setFund(float fund) {
        this.fund = fund;
    }
}
