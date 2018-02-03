/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Cristian
 */
public class Nota implements Serializable {

    int canal;
    float nota;
    float frec;
    float durFig;// duracion en figura.
    int vel;
    LinkedList<Float> armS = new LinkedList<Float>();

    public Nota(float not, int vel, float dur) {
        // dur = duracion figura 0.25 etc
        canal = 0;
        this.nota = not;
        frec = 440 * (float) Math.pow(2.0, (nota - 69) / 12);
        this.durFig = dur;//
        this.vel = vel;
        armS = new LinkedList<Float>();
    }

    public Nota(int can, float not, int vel, float dur) {
        // dur = duracion figura  0.25 etc
        canal = can;
        this.nota = not;
        frec = 440 * (float) Math.pow(2.0, (nota - 69) / 12);
        this.durFig = dur;
        this.vel = vel;
        armS = new LinkedList<Float>();
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public void setArmonicos() {
    }

    public float getDurFig() {
        return durFig;
    }

    public void setDurFig(float dur) {
        this.durFig = dur;
    }

    public float getNotaNum() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }
}
