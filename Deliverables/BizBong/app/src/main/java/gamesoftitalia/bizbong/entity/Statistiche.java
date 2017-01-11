package gamesoftitalia.bizbong.entity;

import java.util.List;

/**
 * Created by GameSoftItalia on 09/01/2017.
 */

public class Statistiche {

    private String[] modalitaList;
    private int[] punteggiList;

    public Statistiche(String[] modalitaList, int[] punteggiList){
        this.modalitaList = modalitaList;
        this.punteggiList = punteggiList;
    }

    public String[] getModalitaList() {
        return modalitaList;
    }

    public void setModalitaList(String[] modalitaList) {
        this.modalitaList = modalitaList;
    }

    public int[] getPunteggiList() {
        return punteggiList;
    }

    public void setPunteggiList(int[] punteggiList) {
        this.punteggiList = punteggiList;
    }
}
