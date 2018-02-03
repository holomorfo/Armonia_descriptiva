/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agui;

import polifoniaLib.Escala;

/**
 *
 * @author Cristian
 */
public class Evento {

    private char onOff;//o:on  f:off x: sistema
    private float nota;
    private int veloc;
    private long tiempo;
    private int nombre;

    /**
     *
     * @param onoff
     * @param not
     * @param vel
     * @param temp
     */
    public Evento(char onoff, float not, int vel, long temp) {
        onOff = onoff;
        nota = not;
        veloc = vel;
        tiempo = temp;
    }

    /**
     * 0 - off
     * 1- on
     * @param onoff
     * @param not
     * @param vel
     * @param temp
     */
    public Evento(int onoff, float not, int vel, long temp) {
        switch (onoff) {
            case 0:
                onOff = 'f';
                break;
            case 1:
                onOff = 'o';
                break;
            case -1:
                onOff = 'x';
                break;
        }
//        onOff=onoff;
        nota = not;
        veloc = vel;
        tiempo = temp;
    }


    /**
     *
     * @param onoff
     * @param not
     * @param vel
     * @param temp
     */
    public void setEv(char onoff, int not, int vel, long temp) {
        onOff = onoff;
        nota = not;
        veloc = vel;
        tiempo = temp;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    /**
     * 'o' 'f'
     * @return
     */
    public char getOnOff() {
        return onOff;
    }

    /**
     *
     * @return
     */
    public int getOnOffInt() {
        int reg = 0;
        switch (onOff) {
            case 'f':
                reg = 0;
                break;
            case 'o':
                reg = 1;
                break;
            case 'x':
                reg = -1;
                break;

        }
        return reg;
    }

    /**
     *
     * @param onOff
     */
    public void setOnOff(char onOff) {
        this.onOff = onOff;
    }

    /**
     *
     * @return
     */
    public float getNota() {
        return nota;
    }

    /**
     *
     * @param nota
     */
    public void setNota(float nota) {
        this.nota = nota;
    }

    /**
     *
     * @return
     */
    public long getTiempo() {
        return tiempo;
    }

    /**
     *
     * @param tiempo
     */
    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    /**
     *
     * @return
     */
    public int getVeloc() {
        return veloc;
    }

    /**
     *
     * @param veloc
     */
    public void setVeloc(int veloc) {
        this.veloc = veloc;
    }

    public void transponerCromatica(float not) {
        if (nota > 0 && nota < 108) {
            this.nota = nota + not;
        }
    }

    public void transponerDiatonica(Escala esc, int cuantos) {
        if (getNota() > 0 && getNota() < 108) {
            if (cuantos > 0) {
                this.setNota(esc.siguiente(this.getNota(), cuantos));
            } else {
                this.setNota(esc.anterior(this.getNota(), Math.abs(cuantos)));
            }
        }
    }
}
