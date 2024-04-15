import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.List;

public class ManageAll {
    private static SessionFactory factory;

    public static void main(String[] args) throws IOException {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.print("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }


        ManageAll MA = new ManageAll();
        FileAccessor fa = new FileAccessor();

        fa.readPlayersFile("player.txt");
        fa.readTeamsFile("team.txt");
        fa.readMatchFile("matchInfo.txt");
        fa.readPlaysFile("plays.txt");
        System.out.println("Dades llegides amb exits");


        for (int i = 0; i < fa.llistaPlayers.size(); i++) {
            System.out.println(fa.llistaPlayers.get(i).toString());
            MA.addPlayers(fa.llistaPlayers.get(i));
        }
        for (int i = 0; i < fa.llistaTeams.size(); i++) {
            System.out.println(fa.llistaTeams.get(i).toString());
            MA.addTeams(fa.llistaTeams.get(i));

        }
        /*for (int i = 0; i <fa.llistaMatches.size(); i++) {
            System.out.println(fa.llistaMatches.get(i).toString());
            MA.addMatches(fa.llistaMatches.get(i));

        }*/

        for (int i = 0; i < fa.llistaPlays.size(); i++) {
            System.out.println(fa.llistaPlays.get(i).toString());
            MA.addPlays(fa.llistaPlays.get(i));

        }

        System.out.println("Dades llegides de la base de dades");
        MA.listPlayers();
        MA.listTeams();
        MA.listMatches();
        MA.listPlays();

    }
        //_-------------------------------------------------

        public void addPlayers(Player player){
          Session session = factory.openSession();
            Transaction tx = null;
            Integer id = null;
            try {
                tx = session.beginTransaction();
                id = (Integer) session.save(player);
                tx.commit();
            }catch (HibernateException e){
                if (tx!= null)
                    tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();}
        }

        public void addTeams(Team team){
            Session session = factory.openSession();
            Transaction tx = null;
            Integer code = null;

            try {
                tx = session.beginTransaction();
                code = (Integer) session.save(team);
                tx.commit();
            }catch (HibernateException e ){
                if (tx!=null)
                    tx.rollback();
                e.printStackTrace();
            }finally {
                session.close();
            }
        }
    public void addMatches(Match match){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer code = null;

        try {
            tx = session.beginTransaction();
            code = (Integer) session.save(match);
            tx.commit();
        }catch (HibernateException e ){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public void addPlays(Plays plays){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer id_match = null;
        Integer id_player = null;
        try {
            tx = session.beginTransaction();
            id_match = (Integer) session.save(plays.getMatch()); // Asigna el ID del partido relacionado
            id_player = (Integer) session.save(plays.getPlayer()); // Asigna el ID del jugador relacionado
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void listPlayers(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List players = session.createQuery("FROM Player").list();
            for(Iterator iterator = players.iterator(); iterator.hasNext();){
                Player player = (Player) iterator.next();
                System.out.println(player.toString());
            }
            tx.commit();
        }catch (HibernateException e){
            if (tx !=null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void listTeams(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List teams = session.createQuery("FROM Team").list();
            for(Iterator iterator = teams.iterator(); iterator.hasNext();){
                Team team = (Team) iterator.next();
                System.out.println(team.toString());
            }
            tx.commit();
        }catch (HibernateException e){
            if (tx !=null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public void listMatches(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List matches = session.createQuery("FROM Match").list();
            for(Iterator iterator = matches.iterator(); iterator.hasNext();){
                Match match = (Match) iterator.next();
                System.out.println(match.toString());
            }
            tx.commit();
        }catch (HibernateException e){
            if (tx !=null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public void listPlays(){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List plays1 = session.createQuery("From Plays").list();
            for(Iterator iterator = plays1.iterator(); iterator.hasNext();){
                Plays plays = (Plays) iterator.next();
                System.out.println(plays.toString());
            }tx.commit();
        }catch (HibernateException e){
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

}

