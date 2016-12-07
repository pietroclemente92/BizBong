package entity;

import myInterface.ProfiloInterface;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */
public class ProfiloClassificato extends Profilo {

    public ProfiloClassificato(String nickname, String imgProfilo, Statisitca statisitca) {
        super(nickname, imgProfilo, statisitca);
    }

    @Override
    public String getNickname() {
        return super.getNickname();
    }

    @Override
    public String getImgProfilo() {
        return super.getImgProfilo();
    }

    @Override
    public Statisitca getStatisitca() {
        return super.getStatisitca();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
