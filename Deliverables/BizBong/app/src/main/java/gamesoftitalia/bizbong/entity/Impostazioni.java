package gamesoftitalia.bizbong.entity;

import java.io.Serializable;

/**
 * Created by gamesoftitalia on 26/12/2016.
 */

@SuppressWarnings("serial")
public class Impostazioni implements Serializable {


    private String lingua;
    private boolean audio;
    private boolean effetti;
    private boolean vibrazione;



    public Impostazioni(String lingua,boolean audio,boolean effetti,boolean vibrazione){
        this.lingua=lingua;
        this.audio=audio;
        this.effetti=effetti;
        this.vibrazione=vibrazione;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public boolean getAudio() {
        return audio;
    }

    public void setAudio(boolean audio) {
        this.audio = audio;
    }

    public boolean getEffetti() {
        return effetti;
    }

    public void setEffetti(boolean effetti) {
        this.effetti = effetti;
    }

    public boolean getVibrazione() {
        return vibrazione;
    }

    public void setVibrazione(boolean vibrazione) {
        this.vibrazione = vibrazione;
    }
}
