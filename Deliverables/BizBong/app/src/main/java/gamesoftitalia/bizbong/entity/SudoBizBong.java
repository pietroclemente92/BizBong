package gamesoftitalia.bizbong.entity;

import java.io.Serializable;

/**
 * Created by gamesoftitalia on 21/12/2016.
 */

public class SudoBizBong implements Serializable {

    private String modalita;
    private int tempo;
    private int mosse;
    private int dimensione;
    private String traccia;

    public SudoBizBong(String modalita, int tempo, int mosse, int dimensione, String traccia) {
        this.modalita = modalita;
        this.tempo = tempo;
        this.mosse = mosse;
        this.dimensione = dimensione;
        this.traccia=traccia;
    }

    public String getModalita() {
        return modalita;
    }

    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getMosse() {
        return mosse;
    }

    public void setMosse(int mosse) {
        this.mosse = mosse;
    }

    public int getDimensione() {
        return dimensione;
    }

    public void setDimensione(int max) {
        this.dimensione = max;
    }

    public String getTraccia() {
        return traccia;
    }

    public void setTraccia(String traccia) {
        this.traccia = traccia;
    }
}