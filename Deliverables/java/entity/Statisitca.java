package entity;

import myInterface.StatisticaInterface;

import java.util.List;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */
public class Statisitca implements StatisticaInterface{
    List<String> listTemi;
    List<Integer> listPunteggi;

    public Statisitca(List<String> listTemi, List<Integer> listPunteggi) {
        this.listTemi = listTemi;
        this.listPunteggi = listPunteggi;
    }

    public List<String> getListaTemi() {
        return listTemi;
    }

    public void setListaTemi(List<String> listaTemi) {
        this.listTemi = listTemi;
    }

    public List<Integer> getListaPunteggi() {
        return listPunteggi;
    }

    public void setListaPunteggi(List<Integer> listaPunteggi) {
        this.listPunteggi = listPunteggi;
    }

    @Override
    public String toString() {
        return "Statistica{ListaTemi:"+listTemi+",ListaPunteggi:"+listPunteggi+"}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof Statisitca)) return false;

        Statisitca s = (Statisitca) obj;
        return ( this.listTemi.equals(s.listTemi) && this.listPunteggi.equals(s.listPunteggi));
    }
}
