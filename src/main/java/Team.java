
import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @Column(name = "code")
    int code;
    @Column(name = "name")
    String name;
    @Column(name = "stadium")
    String stadium;
    @Column(name = "city")
    String city;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private Set<Match> listMatchesHome;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private Set<Match> listMatchesAway;

   public Team(){

   }
    public Team(int code, String name, String stadium, String city) {
        this.code = code;
        this.name = name;
        this.stadium = stadium;
        this.city = city;
        this.listMatchesHome= new HashSet<>();
        this.listMatchesAway= new HashSet<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public Match getMatchesHome(int i){
        Iterator<Match> it = listMatchesHome.iterator();
        for (int j = 0; j < i; j++)it.next();
        return it.next();
    }

    public Set<Match> getListMatchesHome() {
        return listMatchesHome;
    }
    public void addMatchesHome(Match match){
        listMatchesHome.add(match);
    }
    public Match getMatchesAway(int i){
        Iterator<Match> it = listMatchesAway.iterator();
        for (int j = 0; j < i; j++)it.next();
        return it.next();
    }

    public Set<Match> getListMatchesAway() {
        return listMatchesAway;
    }
    public void addMatchesAway(Match match){
        listMatchesAway.add(match);
    }


    @Override
    public String toString() {
        return "Team{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", stadium='" + stadium + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
