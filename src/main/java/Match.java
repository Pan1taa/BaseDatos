import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "match")
public class Match {
    @Id
    @Column(name = "code")
    int code;
    @Column(name = "played")
    Date played;
    @ManyToOne
    @JoinColumn(name = "code_home", insertable = false, updatable = false)
    Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "code_away", insertable = false, updatable = false)
    Team awayTeam;

    public Match(){

    }
    public Match(int code, Date played, Team homeTeam, Team awayTeam) {
        this.code = code;
        this.played = played;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getPlayed() {
        return played;
    }

    public void setPlayed(Date played) {
        this.played = played;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public String toString() {
        return "Match{" +
                "code=" + code +
                ", played=" + played +
                ", code_home=" + homeTeam +
                ", code_away=" + awayTeam +
                '}';
    }
}
