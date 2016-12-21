package gamesoftitalia.bizbong.entity;

import gamesoftitalia.bizbong.entity_interface.DomandaInterface;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */
public class Domanda implements DomandaInterface, Serializable{

    private int punteggio;
    private String tema, domanda, rispostaVera;
    private List<String> listaRisposte;

    public Domanda(int punteggio, String tema, String domanda, String rispostaVera, List<String> listaRisposte) {
        this.punteggio = punteggio;
        this.tema = tema;
        this.domanda = domanda;
        this.rispostaVera = rispostaVera;
        this.listaRisposte = listaRisposte;
    }

    @Override
    public int getPunteggio() {
        return punteggio;
    }

    @Override
    public String getTema() {
        return tema;
    }

    @Override
    public String getDomanda() {
        return domanda;
    }

    @Override
    public List getListaRisposte() {
        return listaRisposte;
    }

    @Override
    public String getRispostaVera() {
        return rispostaVera;
    }

    @Override
    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    @Override
    public void setTema(String tema) {
        this.tema = tema;
    }

    @Override
    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    @Override
    public void setListaRisposte(List<String> listaRisposte) {
        this.listaRisposte = listaRisposte;
    }

    @Override
    public void setRispostaVera(String rispostaVera) {
        this.rispostaVera = rispostaVera;
    }

    @Override
    public String toString(){
        String risposte = "{";
        for(int i = 0; i < listaRisposte.size(); i++){
            risposte += listaRisposte.get(i)+";";
        }
        return "Domande[punteggio:"+punteggio+", tema:"+tema+", domanda:"+domanda+", listaRisposte:"+risposte+"}, rispostaVera:"+rispostaVera+"]";
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;

        if (!(obj instanceof Domanda)) return false;

        Domanda d = (Domanda) obj;
        return ( this.punteggio == d.punteggio && this.tema.equals(d.tema) && this.domanda.equals(d.domanda)
                && this.listaRisposte.equals(d.listaRisposte) && this.rispostaVera.equals(d.rispostaVera));
    }
}
