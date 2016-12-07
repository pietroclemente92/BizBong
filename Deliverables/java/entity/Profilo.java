package entity;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */
public class Profilo {
    private String nickname, password, email;
    private Statisitca statisitca;

    public Profilo(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Statisitca getStatisitca() {
        return statisitca;
    }

    public void setStatisitca(Statisitca statisitca) {
        this.statisitca = statisitca;
    }

    @Override
    public String toString() {
        return "Profilo[Nickname:"+nickname+",Password:"+password+",Email:"+email+",Statistica:"+statisitca+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof Profilo)) return false;

        Profilo p = (Profilo) obj;
        return ( this.nickname == p.nickname && this.password == p.password && this.email == p.email && this.statisitca.equals(p.statisitca));
    }
}
