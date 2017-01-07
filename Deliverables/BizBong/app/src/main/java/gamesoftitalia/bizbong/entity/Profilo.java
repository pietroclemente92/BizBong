package gamesoftitalia.bizbong.entity;

/**
 * Created by GameSoftItalia on 01/12/2016.
 */
public class Profilo {
    private String nickname, password, email, img_profilo;

    public Profilo(String nickname, String img_profilo){
        this.nickname = nickname;
        this.img_profilo = img_profilo;
    }

    public Profilo(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.img_profilo = "img_base.png";
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

    public String getImgProfilo() { return img_profilo; }

    public void setImgProfilo(String img_profilo) { this.img_profilo = img_profilo; }

    @Override
    public String toString() {
        return "Profilo[Nickname:"+nickname+",Password:"+password+",Email:"+email+",ImmagineProfilo:"+img_profilo+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!(obj instanceof Profilo)) return false;

        Profilo p = (Profilo) obj;
        return ( this.nickname == p.nickname && this.password == p.password && this.email == p.email
                 && this.img_profilo.equals(p.img_profilo));
    }
}
