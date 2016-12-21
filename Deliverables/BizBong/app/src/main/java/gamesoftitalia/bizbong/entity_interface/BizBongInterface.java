package gamesoftitalia.bizbong.entity_interface;

import gamesoftitalia.bizbong.entity.Domanda;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */
public interface BizBongInterface {

    public int getTempo();
    public List getListaDomande();
    public void setTempo(int tempo);
    public void setListaDomande(List<Domanda> listaDomande);
}
