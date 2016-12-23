package gamesoftitalia.bizbong.entity;

/**
 * Created by gamesoftitalia on 21/12/2016.
 */

public class FineSudoBizBong {
    private String modalita;
    private int mosse;
    private String tempo;
    private int punteggio;


    public FineSudoBizBong(String modalita,int mosse,String tempo){
        this.modalita=modalita;
        this.mosse=mosse;
        this.tempo=tempo;
        this.punteggio=calcolaPunteggio();

    }

    public int calcolaPunteggio(){
        int p=0;
        String minuti=tempo.substring(0, tempo.indexOf(":"));
        String secondi=tempo.substring(tempo.indexOf(":")+1, tempo.length());
        int mm= Integer.parseInt(String.valueOf(minuti));
        int sec= Integer.parseInt(String.valueOf(secondi));
        p=mm*100+sec*10+mosse;
        return p;
    }

    public String getModalita() {
        return modalita;
    }

    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    public int getMosse() {
        return mosse;
    }

    public void setMosse(int mosse) {
        this.mosse = mosse;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
}