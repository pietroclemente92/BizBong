package myInterface;

import entity.Domanda;

import java.util.List;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */
public interface BizBongInterface {

    public int getPunteggio();
    public int getTempo();
    public List getListaDomande();
    public void setPunteggio(int punteggio);
    public void setTempo(int tempo);
    public void setListaDomande(List<Domanda> listaDomande);
}
