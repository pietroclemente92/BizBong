package gamesoftitalia.bizbong.entity;

import gamesoftitalia.bizbong.entity_interface.BizBongInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */

public class BizBong implements BizBongInterface, Serializable {
    private String modalita;
    private List<Domanda> listaDomande;

    public BizBong(String modalita, List<Domanda> listaDomande) {
        this.modalita = modalita;
        this.listaDomande = listaDomande;
    }

    public String getModalita() {
        return modalita;
    }

    public void setModalita(String modalita) {
        this.modalita = modalita;
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
        return "BizBong[modalita:"+modalita+", listaDomande:"+domande+"}]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;

        if (!(obj instanceof BizBong)) return false;

        BizBong b = (BizBong) obj;
        return ( this.modalita.equals(b.modalita) && this.listaDomande.equals(b.listaDomande));
    }
}
