/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package polifoniaLib;

/**
 *
 * @author Cristian
 */
public class CoorTon {

    // Los grados van del 1 al 7
    int escala = 0;
    int grado = 0;
    char tipo = 'm';
    String etq = "-";

    public CoorTon() {
        escala = 0;
        grado = 0;
        tipo = 'M';
    }

    public CoorTon(int esc, char tp, int gra) {
        escala = esc;
        grado = gra;
        tipo = tp;
    }

    public int getEscala() {
        return escala;
    }

    public void setEscala(int escala) {
        this.escala = escala;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public void setReg(int esc, char tp, int gra) {
        this.escala = esc;
        this.grado = gra;
        this.tipo = tp;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getEtq() {
        return etq;
    }

    public void setEtq(String etq) {
        this.etq = etq;
    }

    public void print() {
        System.out.println("Escala " + escala + " " + tipo + " grado: " + grado + " Etq: " + etq);
    }
}


