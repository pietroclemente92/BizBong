package myInterface;

import entity.Statisitca;

/**
 * Created by GameSoftItalia on 07/12/2016.
 */
public interface ProfiloInterface {

    public String getNickname();
    public String getPassword();
    public String getEmail();
    public String getImgProfilo();
    public Statisitca getStatistica();
    public void setNickname(String nickname);
    public void setPassword(String password);
    public void setEmail(String email);
    public void setImgProfilo(String imgProfilo);
    public void setStatistica(Statisitca statistica);
    public String toString();
    public boolean equals(Object obj);
}
