package gamesoftitalia.bizbong.entity;

/**
 * Created by GameSoftItalia on 09/01/2017.
 */

public class Classificato extends Profilo {

    private int punteggio;

    public Classificato(String nickname, String img_profilo, int punteggio) {
        super(nickname, img_profilo);
        this.punteggio = punteggio;
    }

    public void setPunteggio(int punteggio){
        this.punteggio = punteggio;
    }

    public int getPunteggio(){
        return punteggio;
    }
}
