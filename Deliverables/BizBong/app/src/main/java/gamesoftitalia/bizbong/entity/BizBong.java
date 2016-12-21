package gamesoftitalia.bizbong.entity;

import gamesoftitalia.bizbong.entity_interface.BizBongInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */

public class BizBong implements BizBongInterface, Serializable {
    private int tempo;
    private List<Domanda> listaDomande;

    public BizBong(int tempo, List<Domanda> listaDomande) {
        this.tempo = tempo;
        this.listaDomande = listaDomande;
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
        return "BizBong[tempo:"+tempo+", listaDomande:"+domande+"}]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;

        if (!(obj instanceof BizBong)) return false;

        BizBong b = (BizBong) obj;
        return ( this.tempo == b.tempo && this.listaDomande.equals(b.listaDomande));
    }
}
