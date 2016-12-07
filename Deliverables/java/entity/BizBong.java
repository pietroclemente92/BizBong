package entity;

import myInterface.BizBongInterface;

import java.util.List;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */
public class BizBong implements BizBongInterface{
    private int punteggio, tempo;
    private List<Domanda> listaDomande;

    public BizBong(int punteggio, int tempo, List<Domanda> listaDomande) {
        this.punteggio = punteggio;
        this.tempo = tempo;
        this.listaDomande = listaDomande;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public List<Domanda> getListaDomande() {
        return listaDomande;
    }

    public void setListaDomande(List<Domanda> listaDomande) {
        this.listaDomande = listaDomande;
    }

    @Override
    public String toString(){
        String domande = "{";
        for(int i = 0; i < listaDomande.size(); i++){
            domande += listaDomande.get(i)+";";
        }
        return "BizBong[punteggio:"+punteggio+", tempo:"+tempo+", listaDomande:"+domande+"}]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;

        if (!(obj instanceof Profilo)) return false;

        BizBong b = (BizBong) obj;
        return ( this.punteggio == b.punteggio && this.tempo == b.tempo && this.listaDomande.equals(b.listaDomande));
    }
}
