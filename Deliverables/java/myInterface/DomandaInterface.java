package myInterface;

import java.util.List;

/**
 * Created by Raffaella on 07/12/2016.
 */
public interface DomandaInterface {

    public int getPunteggio();
    public String getTema();
    public String getDomanda();
    public List getListaRisposte();
    public String getRispostaVera();
    public void setPunteggio(int punteggio);
    public void setTema(String tema);
    public void setDomanda(String domanda);
    public void setListaRisposte(List<String> listaRisposte);
    public void setRispostaVera(String rispostaVera);
    public String toString();
    public boolean equals(Object obj);
}
