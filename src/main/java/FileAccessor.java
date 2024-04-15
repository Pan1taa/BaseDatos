import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class FileAccessor {
    ArrayList<Player> llistaPlayers = new ArrayList<>();
    ArrayList<Team> llistaTeams = new ArrayList<>();

    ArrayList<Match> llistaMatches = new ArrayList<>();
    ArrayList<Plays>llistaPlays = new ArrayList<>();

    public FileAccessor() {

    }

    public void readPlayersFile(String filename) throws IOException {
        int id;
        String surname, forename;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            id = Integer.parseInt(str.nextToken());
            surname = str.nextToken();
            forename = str.nextToken();

            llistaPlayers.add(new Player(id, surname, forename));
        }
        br.close();
    }

    public void printPlayers() {
        for (int i = 0; i < llistaPlayers.size(); i++) {
            System.out.println(llistaPlayers.get(i).toString());

        }
    }

    public void printTeams() {
        for (int i = 0; i < llistaTeams.size(); i++) {
            System.out.println(llistaTeams.get(i).toString());

        }
    }

    public void readTeamsFile(String filename) throws IOException {
        int code;
        String name, stadium, city;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            code = Integer.parseInt(str.nextToken());
            name = str.nextToken();
            stadium = str.nextToken();
            city = str.nextToken();

            llistaTeams.add(new Team(code, name, stadium, city));
        }
        br.close();
    }

   public ArrayList<Team> readMatchFile(String filename) throws IOException {
        int code, code_home, code_away;
        Date played;
        DateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            code = Integer.parseInt(str.nextToken());

            code_home = Integer.parseInt(str.nextToken());
            code_away = Integer.parseInt(str.nextToken());
            try {
                played = dateformat.parse(str.nextToken());
                Team team_home = llistaTeams.get(code_home-1);
                Team team_away = llistaTeams.get(code_away-1);
                Match match = new Match(code, played, team_home,team_away);
                team_home.addMatchesHome(match);
                team_away.addMatchesAway(match);
                System.out.println(match);
                llistaMatches.add(match);
            } catch (ParseException e) {
                System.err.print(" errada format data al fitxer");
                e.printStackTrace();
            }

        }
        br.close();

        return llistaTeams;
    }
    public ArrayList<Plays> readPlaysFile(String filename) throws IOException{
    int id_match, id_player, stats, substituted, goals, yellow;
    boolean red;
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String linea= "";
    while ((linea= br.readLine())!=null) {
        StringTokenizer str = new StringTokenizer(linea, ",");
        id_match = Integer.parseInt(str.nextToken());
        id_player = Integer.parseInt(str.nextToken());
        stats = Integer.parseInt(str.nextToken());
        substituted = Integer.parseInt(str.nextToken());
        goals = Integer.parseInt(str.nextToken());
        yellow = Integer.parseInt(str.nextToken());
        red = Boolean.parseBoolean(str.nextToken());
        Match match = llistaMatches.get(id_match-1);
        Player player = llistaPlayers.get(id_player-1);
        Plays plays = new Plays(id_match, id_player,stats,substituted,goals,yellow,red,match,player);
        llistaPlays.add(plays);
    }br.close();
    return llistaPlays;
    }
}

