import com.formdev.flatlaf.FlatDarculaLaf;
import com.github.javafaker.Faker;
import models.*;
import models.Event;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.cli.MavenCli;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import utils.Randomizer;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import static javax.swing.BoxLayout.PAGE_AXIS;

public class Main extends JFrame {

    private static int countrySize = 100, eventSize = 700, olympicSize = 200, playerSize = 2000, resultSize = 3000;

    private static ArrayList<Country> countries = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Olympic> olympics = new ArrayList<>();
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<Result> results = new ArrayList<>();

    public Main() {
        super("DB Alenov");

        JButton task1, task2, task3, task4, task5, clearMig, createMig,fillDB, getDB;

        UIManager.put("Button.arc", 20);

        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, PAGE_AXIS));
        add(pane);
        JPanel filler = new JPanel();
        filler.setLayout(new BoxLayout(filler, BoxLayout.LINE_AXIS));
        filler.add(createMig = new JButton("Create Migration"));
        pane.add(filler);

        filler = new JPanel();
        filler.setLayout(new BoxLayout(filler, BoxLayout.LINE_AXIS));
        filler.add(clearMig = new JButton("Clear Migration"));
        pane.add(filler);

        filler = new JPanel();
        filler.setLayout(new BoxLayout(filler, BoxLayout.LINE_AXIS));
        filler.add(fillDB = new JButton("Fill DB"));
        pane.add(filler);

        filler = new JPanel();
        filler.setLayout(new BoxLayout(filler, BoxLayout.LINE_AXIS));
        filler.add(getDB = new JButton("Get Data From DB"));
        pane.add(filler);

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BoxLayout(pane1, BoxLayout.LINE_AXIS));
        pane.add(pane1);
        pane1.add(task1 = new JButton("Task 1"));
        pane1.add(task2 = new JButton("Task 2"));
        pane1.add(task3 = new JButton("Task 3"));
        pane1.add(task4 = new JButton("Task 4"));
        pane1.add(task5 = new JButton("Task 5"));

        createMig.setMaximumSize(new Dimension(10000,100));
        fillDB.setMaximumSize(new Dimension(10000,100));
        getDB.setMaximumSize(new Dimension(10000,100));
        clearMig.setMaximumSize(new Dimension(10000,100));



        task1.addActionListener(e -> {
            getTask1();
        });
        task2.addActionListener(e -> {
            getTask2();
        });
        task3.addActionListener(e -> {
            getTask3();
        });
        task4.addActionListener(e -> {
            getTask4();
        });
        task5.addActionListener(e -> {
            getTask5();
        });
        clearMig.addActionListener(e-> {
            try {
                MavenCli cli = new MavenCli();
                cli.doMain(new String[]{"flyway:clean"}, null, System.out, System.out);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Cant clear migration");
            }
        });
        createMig.addActionListener(e-> {
            try {
                MavenCli cli = new MavenCli();
                cli.doMain(new String[]{"flyway:migrate"}, null, System.out, System.out);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Cant create migration");
            }
        });
        getDB.addActionListener(e -> {
            try {
                getAllTables();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Cant get data from DB");
            }
        });
        fillDB.addActionListener(e -> {
            try {
                infillDB();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Cant fill DB with junk data");
            }
        });
        System.out.println("Created GUI");
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ignored) {
        }

        EventQueue.invokeLater(() -> {
            JFrame frame = new Main();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    private static void getAllTables() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        countries = new ArrayList<Country>(HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Country a").getResultList());
        olympics = new ArrayList<Olympic>(HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Olympic a").getResultList());
        players = new ArrayList<Player>(HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Player a").getResultList());
        events = new ArrayList<Event>(HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Event a").getResultList());
        results = new ArrayList<Result>(HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("SELECT a FROM Result a").getResultList());
//        System.out.println(countries.size() + " " + olympics.size() + " " + players.size() + " " + events.size() + " " + results.size());
        System.out.println("All Tables Loaded Successfully");
        tx1.commit();
        session.close();
    }

    private static void infillDB() {
        Random rnd = new Random();
        Faker faker = new Faker();

        CountryDAO cdao = new CountryDAO();
        Country country;
        for (int i = 0; i < countrySize; i++) {
            country = new Country();
            country.setId(Randomizer.getUniqueID());
            country.setName(StringUtils.left(faker.country().name(), 40));
            country.setAreaSqkm(Randomizer.getRandomInt(100, 10000));
            country.setPopulation(Randomizer.getRandomInt(10000, 1000000));
            cdao.save(country);
            countries.add(country);
        }


        OlympicDAO odao = new OlympicDAO();
        Olympic olympic;
        for (int i = 0; i < olympicSize; i++) {
            olympic = new Olympic();
            olympic.setId(Randomizer.getUniqueID());
            olympic.setCountry(countries.get(rnd.nextInt(countries.size())));
            olympic.setCity(StringUtils.left(faker.country().capital(), 50));
            int year = Randomizer.getRandomInt(1999, 2010);
            olympic.setYear(year);
            olympic.setStartdate(Randomizer.getRandomDate(year, year));
            olympic.setEnddate(Randomizer.getRandomDate(year + 1, year + 2));
            odao.save(olympic);
            olympics.add(olympic);
        }


        EventDAO edao = new EventDAO();
        Event event;
        for (int i = 0; i < eventSize; i++) {
            event = new Event();
            event.setId(Randomizer.getUniqueID());
            event.setName(StringUtils.left(faker.esports().event(), 40));
            event.setEventtype(StringUtils.left(faker.esports().game(), 20));
            event.setOlympic(olympics.get(rnd.nextInt(olympics.size())));
            event.setIsTeamEvent(rnd.nextInt(2));
            event.setNumPlayersInTeam(rnd.nextInt(10));
            event.setResultNotedIn("seconds");
            edao.save(event);
            events.add(event);
        }

        PlayerDAO pdao = new PlayerDAO();
        Player player;
        for (int i = 0; i < playerSize; i++) {
            player = new Player();
            player.setId(Randomizer.getUniqueID());
            player.setName(StringUtils.left(faker.name().fullName(), 40));
            player.setBirthdate(Randomizer.getRandomDate(1980, 2005));
            player.setCountry(countries.get(rnd.nextInt(countries.size())));
            pdao.save(player);
            players.add(player);
        }


        ResultDAO rdao = new ResultDAO();
        Result result;
        ResultId rID;
        for (int i = 0; i < resultSize; i++) {
            try {
                result = new Result();
                rID = new ResultId();
                event = events.get(rnd.nextInt(events.size()));
                result.setEvent(event);
                rID.setEventId(event.getId());

                player = players.get(rnd.nextInt(players.size()));
                result.setPlayer(player);
                rID.setPlayerId(player.getId());

                result.setMedal(Randomizer.getMedal());
                result.setResult(rnd.nextDouble());
                result.setId(rID);

                rdao.save(result);
                results.add(result);
            } catch (Exception ignored) {
            }
        }

        countries.clear();
        events.clear();
        olympics.clear();
        players.clear();
        results.clear();

        System.out.println("Dummy Data For DataBase Was Successfully Created");
    }

    public void getTask1() {
        HashMap<Integer, Tripple> yeTu = new HashMap<>();
        HashSet<Integer> years = new HashSet<>();
        for (Result res : results) {
            if (res.getMedal().equals("GOLD   ") && res.getEvent().getOlympic().getYear() == 2004) {
                if (!yeTu.containsKey(res.getPlayer().getBirthdate().getYear())) {
                    yeTu.put(res.getPlayer().getBirthdate().getYear(), new Tripple());
                }
                yeTu.get(res.getPlayer().getBirthdate().getYear()).players.add(res.getPlayer());
                yeTu.get(res.getPlayer().getBirthdate().getYear()).medals++;
                yeTu.get(res.getPlayer().getBirthdate().getYear()).year = res.getPlayer().getBirthdate().getYear();
                years.add(res.getPlayer().getBirthdate().getYear());
            }
        }

        System.out.println("\n\nFor 2004 olympics:");
        for (int year : years) {
            System.out.println(yeTu.get(year).toString());
        }
    }

    public void getTask2() {
        HashMap<Event, ArrayList<Player>> evPl = new HashMap<>();
        HashSet<Event> evs = new HashSet<>();

        for (Result res : results) {
            if (res.getEvent().getIsTeamEvent() == 0 && res.getMedal().equals("GOLD   ")) {
                if (!evPl.containsKey(res.getEvent())) {
                    evPl.put(res.getEvent(), new ArrayList<>());
                }
                evPl.get(res.getEvent()).add(res.getPlayer());
                evs.add(res.getEvent());
            }
        }
        System.out.println("\n\nDraw Events:");
        for (Event event : evs) {
            if (evPl.get(event).size() > 1) {
                System.out.println("\tEvent " + event.getName() + "\t of " + event.getEventtype() + " that was in " + event.getOlympic().getYear() + " at the city of " + event.getOlympic().getCity() + " which was located in " + event.getOlympic().getCountry().getName());
            }
        }
    }

    public void getTask3() {
        HashMap<Player, ArrayList<Olympic>> plOl = new HashMap<>();
        HashSet<Player> pls = new HashSet<>();
        for (Result res : results) {
            if (!res.getMedal().equals("NONE   ")) {
                if (!plOl.containsKey(res.getPlayer())) {
                    plOl.put(res.getPlayer(), new ArrayList<>());
                }
                plOl.get(res.getPlayer()).add(res.getEvent().getOlympic());
                pls.add(res.getPlayer());
            }
        }
        System.out.println("\n\nPlayers with medals:");
        for (Player player : pls) {
            if (plOl.get(player).size() > 0) {
                System.out.print("\tPlayer " + player.getName() + "\t got medals from ");
                for (Olympic ol : plOl.get(player)) {
                    System.out.print(ol.getCountry().getName().replace(" ", "") + " " + ol.getCity().replace(" ", "") + " (" + ol.getYear() + "),\t");
                }
                System.out.println("\b\b");
            }
        }
    }

    public void getTask4() {
        HashMap<Country, Integer> coIn = new HashMap<>();
        HashSet<Country> couns = new HashSet<>();
        Country maxC = null;
        int max = 0;
        char sym;
        for (Player player : players) {
            sym = player.getName().toLowerCase(Locale.ROOT).charAt(0);
            if (sym == 'a' || sym == 'e' || sym == 'u' || sym == 'o' || sym == 'i' || sym == 'y') {
                if (!coIn.containsKey(player.getCountry())) {
                    coIn.put(player.getCountry(), 0);
                }
                coIn.put(player.getCountry(), coIn.get(player.getCountry()) + 1);
                couns.add(player.getCountry());
                if (maxC == null) {
                    maxC = player.getCountry();
                }
            }
        }


        for (Country country : couns) {
            if (coIn.get(country) > max) {
                maxC = country;
                max = coIn.get(country);
            }
        }

        System.out.println("\n\nCountry with most players with vowel as their first letter in name is:\n\t" + maxC.getName().replace(" ", "") + " (" + max + ")");
    }

    public void getTask5() {
        HashMap<Country, Integer> coIn = new HashMap<>();
        HashSet<Country> couns = new HashSet<>();

        for (Result res : results) {
            if (!res.getMedal().equals("NONE   ") && res.getEvent().getOlympic().getYear() == 2000 && res.getEvent().getIsTeamEvent() == 1) {
                if (!coIn.containsKey(res.getPlayer().getCountry())) {
                    coIn.put(res.getPlayer().getCountry(), 0);
                }
                coIn.put(res.getPlayer().getCountry(), coIn.get(res.getPlayer().getCountry()) + 1);
                couns.add(res.getPlayer().getCountry());
            }
        }
        ArrayList<Tuplee> raw = new ArrayList<>();

        for (Country country : couns) {
            raw.add(new Tuplee(country, coIn.get(country)));
        }
        raw.sort(Comparator.comparingDouble(one -> 1.0 * one.num / one.name.getPopulation()));

        System.out.println("\n\nTop 5 Countries with low win-rate:");
        for (int i = 0; i < Math.min(5, raw.size()); i++) {
            System.out.println("\tCountry " + raw.get(i).name.getName() + " has " + (100 * (1 - 1.0 * raw.get(i).num / raw.get(i).name.getPopulation())) + "% of people what dont know how to earn medals");
        }
    }

}

class Tuplee implements Comparator<Tuplee> {
    Country name;
    int num;

    public Tuplee(Country name, int num) {
        this.name = name;
        this.num = num;
    }


    @Override
    public int compare(Tuplee one, Tuplee two) {
        if (one.num > two.num) {
            return 1;
        }
        if (one.num == two.num) {
            return 0;
        }
        return -1;
    }
}

class Tripple {
    public int year;
    public HashSet<Player> players;
    public int medals;

    public Tripple() {
        year = 0;
        players = new HashSet<>();
        medals = 0;
    }

    @Override
    public String toString() {
        return "\tYear " + year + ": " + players.size() + " players, " + medals + " medals";
    }
}
