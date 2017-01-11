package gamesoftitalia.bizbong.entity;

import java.util.List;

/**
 * Created by GameSoftItalia on 09/01/2017.
 */

public class Classifica {
    private List<Classificato> classificatiList;

    public Classifica(List<Classificato> classificatiList) {
        this.classificatiList = classificatiList;
    }

    public List<Classificato> getClassificatiList() {
        return classificatiList;
    }

    public void setClassificatiList(List<Classificato> classificatiList) {
        this.classificatiList = classificatiList;
    }
}
