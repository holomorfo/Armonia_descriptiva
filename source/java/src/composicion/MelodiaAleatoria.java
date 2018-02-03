/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composicion;

import agui.Orquestacion;
import java.util.LinkedList;
import polifoniaLib.Nota;
import polifoniaLib.UniversoTonal;
import processing.core.PApplet;

/**
 *
 * @author Cristian
 */
public class MelodiaAleatoria {

    PApplet p;
    Orquestacion orq;
    UniversoTonal uni;
    boolean play = true;
    Nota nota;
    PlayMelodia playMel = new PlayMelodia();
    Thread playMelTh;
    LinkedList<PlayMelodia> playMelList;

    public MelodiaAleatoria(PApplet pa, Orquestacion o, UniversoTonal u) {
        p = pa;
        orq = o;
        uni = u;
        playMelList = new LinkedList<PlayMelodia>();
    }

    public void playRand() {
        float not = 60;
        int vel = 100;
        float dur = 1;
        while (play) {
            not = uni.getEscAct().siguiente(not, (int) p.random(-3, 2));
            vel = (int) p.random(40, 120);
            dur = p.random(0, 1);

            nota = new Nota(not, vel, dur);
            orq.gMnInst().playNota(nota);
            p.redraw();
//            p.println("Id "+playMelTh.);
        }
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void togglePlay() {
        play = !play;
    }

    /**
     *
     * @param nota
     * @param vel
     * @param durMs
     */
    public void playMelodiaTh() {
        playMel = new PlayMelodia(this);
        playMelList.add(playMel);
        playMelTh = new Thread(playMel);
        playMelTh.start();

        if (playMelList.size() > 4) {
            for (int i = 0; i < playMelList.size(); i++) {
                playMelList.get(i).termina();
            }
            playMelList = new LinkedList<PlayMelodia>();
        }
//        if (playMel.isLoop() == false) {
//            playMel = new PlayMelodia(this);
//            playMelTh = new Thread(playMel);
//            playMelTh.start();
//        }
    }

    /**
     *
     */
    public static class PlayMelodia implements Runnable {

        MelodiaAleatoria mel;
        boolean loop = false;

        public PlayMelodia(MelodiaAleatoria ml) {
            mel = ml;
        }

        public PlayMelodia() {
        }

        public void run() {
            loop = true;
//            while (loop) {
//            }
            mel.playRand();
            loop = false;
        }

        public boolean isLoop() {
            return loop;
        }

        public void termina() {
            mel.setPlay(false);
        }

        public void setLoop(boolean lp) {
            this.loop = lp;
        }
    }
}

