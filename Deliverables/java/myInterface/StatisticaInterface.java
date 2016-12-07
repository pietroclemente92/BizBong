package myInterface;

import java.util.List;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */
public interface StatisticaInterface {

    public List getListaTemi();
    public List getListaPunteggi();
    public void setListaTemi(List<String> listaTemi);
    public void setListaPunteggi(List<Integer> listaPunteggi);
    public String toString();
    public boolean equals(Object obj);
}
