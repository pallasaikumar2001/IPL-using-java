import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEGBYE_RUNS = 12;
    public static final int NOBALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int BATSMAN_RUNS = 15;
    public static final int EXTRA_RUNS = 16;
    public static final int TOTAL_RUNS = 17;
    public static final int PLAYER_DISMISSED = 18;
    public static final int DISMISSAL_KIND = 19;
    public static final int FIELDER = 20;

    public static final int ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUNS = 11;
    public static final int WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 = 16;
    public static final int UMPIRE3 = 17;

    public static void main(String[] args) {
        
        List<Delivery> deliveries = setDeliveriesData();
        List<Match> matches = setMatchesData();
        wintossandmatch(matches);
        mostpop(matches);
        winsperseason(matches);
        bowlerruns(deliveries, matches);
        bowlernoballsbyseason(deliveries, matches);
        teamextras(deliveries);
        
        
    }
    

    private static List<Match> setMatchesData() {
        List<Match> matches = new ArrayList<>();
        String matchesPath = "/home/saikumar-palla/Desktop/java/ipl/data/matches.csv";

        try (BufferedReader matchesReader = new BufferedReader(new FileReader(matchesPath))) {
            String nextLine;
            matchesReader.readLine();

            while ((nextLine = matchesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);

                Match match = new Match();

                match.setId(Integer.parseInt(nextRecord[ID]));
                match.setSeason(Integer.parseInt(nextRecord[SEASON]));
                match.setCity(nextRecord[CITY]);
                match.setDate(LocalDate.parse(nextRecord[DATE]));
                match.setTeam1(nextRecord[TEAM1]);
                match.setTeam2(nextRecord[TEAM2]);
                match.setTossWinner(nextRecord[TOSS_WINNER]);
                match.setTossDecision(nextRecord[TOSS_DECISION]);
                match.setResult(nextRecord[RESULT]);
                match.setDlApplied(Boolean.parseBoolean(nextRecord[DL_APPLIED]));
                match.setWinner(nextRecord[WINNER]);
                match.setWinByRuns(Integer.parseInt(nextRecord[WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(nextRecord[WIN_BY_WICKETS]));
                match.setPlayerOfMatch(nextRecord[PLAYER_OF_MATCH]);
                match.setVenue(nextRecord[VENUE]);
                match.setUmpire1(nextRecord[UMPIRE1]);
                match.setUmpire2(nextRecord[UMPIRE2]);
                match.setUmpire3(nextRecord[UMPIRE3]);

                matches.add(match);
            }
            // System.out.println(matches);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return matches;
    }

    private static List<Delivery> setDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();
        String deliveriessPath = "/home/saikumar-palla/Desktop/java/ipl/data/deliveries.csv";
    
        try (BufferedReader deliveriesReader = new BufferedReader(new FileReader(deliveriessPath))) {
            String nextLine;
    
            deliveriesReader.readLine(); 
    
            while ((nextLine = deliveriesReader.readLine()) != null) {
                if (nextLine.trim().isEmpty()) {  
                    continue;
                }
    
                String[] nextRecord = nextLine.split(",", -1);
    
                if (nextRecord.length <= 1) {
                    continue;
                }
    
                Delivery delivery = new Delivery();
    
                delivery.setMatchId(parseIntSafe(nextRecord[MATCH_ID]));
                delivery.setInning(Integer.parseInt(nextRecord[INNING]));
                delivery.setBattingTeam(nextRecord[BATTING_TEAM]);
                delivery.setBowlingTeam(nextRecord[BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(nextRecord[OVER]));
                delivery.setBall(Integer.parseInt(nextRecord[BALL]));
                delivery.setBatsman(nextRecord[BATSMAN]);
                delivery.setNonStriker(nextRecord[NON_STRIKER]);
                delivery.setBowler(nextRecord[BOWLER]);
                delivery.setSuperOver(Boolean.parseBoolean(nextRecord[IS_SUPER_OVER]));
                delivery.setWideRuns(Integer.parseInt(nextRecord[WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(nextRecord[BYE_RUNS]));
                delivery.setLegbyeRuns(Integer.parseInt(nextRecord[LEGBYE_RUNS]));
                delivery.setNoballRuns(Integer.parseInt(nextRecord[NOBALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(nextRecord[PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(nextRecord[BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(nextRecord[EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(nextRecord[TOTAL_RUNS]));
                delivery.setPlayerDismissed(nextRecord[PLAYER_DISMISSED]);
                delivery.setDismissalKind(nextRecord[DISMISSAL_KIND]);
                delivery.setFielder(nextRecord[FIELDER]);
    
                deliveries.add(delivery);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        // System.out.println(deliveries);
    
        return deliveries;
    }
    
    private static int parseIntSafe(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + str);
            return 0;
        }
    }




    private static void mostpop(List<Match> matches) {

        Map<Integer, Map<String, Integer>> seasonmom = new HashMap<>();
    
        for (Match match : matches) {
            int season = match.getSeason();
            String player = match.getPlayerOfMatch();
    
            seasonmom.putIfAbsent(season, new HashMap<>());
    
            Map<String, Integer> playersInSeason = seasonmom.get(season);
            playersInSeason.put(player, playersInSeason.getOrDefault(player, 0) + 1);
        }
    
        for (Map.Entry<Integer, Map<String, Integer>> entry : seasonmom.entrySet()) {
            int season = entry.getKey();
            Map<String, Integer> playersInSeason = entry.getValue();

            List<Map.Entry<String, Integer>> sortedPlayers = new ArrayList<>(playersInSeason.entrySet());
            sortedPlayers.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

    
            System.out.println("Season " + season + ":");
            for (int i = 0; i < Math.min(10, sortedPlayers.size()); i++) {
                Map.Entry<String, Integer> playerEntry = sortedPlayers.get(i);
                String player = playerEntry.getKey();
                int count = playerEntry.getValue();
                System.out.println("  " + player + ": " + count);
            }
            System.out.println();
        }
    }
    



    private static void winsperseason(List<Match> matches){
        Map<Integer,Map<String,Integer>> teamwins=new HashMap<>();
        for (Match match:matches){
            int season=match.getSeason();
            String team=match.getWinner();
            teamwins.putIfAbsent(season, new HashMap<>());
            Map<String,Integer>teamwinner=teamwins.get(season);
            teamwinner.put(team,teamwinner.getOrDefault(team, 0)+1);
            
        }
        System.out.println(teamwins);
        for (Map.Entry<Integer,Map<String,Integer>> entry:teamwins.entrySet()){
            System.out.println(entry);
            System.out.println();
        }

        
    }

    private static void bowlerruns(List<Delivery>deliveries,List<Match>matches){
        Map<Integer,Map<String,Integer>>bruns=new HashMap<>();
        Map<Integer,Integer>matchseasonmap=new HashMap<>();
        for (Match match:matches){
            matchseasonmap.put(match.getId(), match.getSeason());
        }
        // System.out.println(matchseasonmap);

        for (Delivery del:deliveries){
            String player=del.getBowler();
            Integer matchid=del.getMatchId();
            Integer season=matchseasonmap.get(matchid);
            Integer truns=del.getTotalRuns();
            bruns.putIfAbsent(season, new HashMap<>());
            Map<String,Integer>seasonbowlerruns=bruns.get(season);
            seasonbowlerruns.put(player,seasonbowlerruns.getOrDefault(player, 0)+truns);
            // bruns.put(player,bruns.getOrDefault(player, truns)+truns);
        }
        // System.out.println(bruns);

        for (Map.Entry<Integer,Map<String,Integer>> seasonEntry:bruns.entrySet()){
            System.out.println(seasonEntry);
            System.out.println();
        }
    }
    private static void bowlernoballsbyseason(List<Delivery>deliveries,List<Match>matches){
        Map<Integer,Map<String,Integer>>seasonwisenoballs=new HashMap<>();
        Map<Integer,Integer>matseason=new HashMap<>();
        for (Match match:matches){
            matseason.put(match.getId(), match.getSeason());
        }
        // System.out.println(matseason);
        for (Delivery del:deliveries){
            String player=del.getBowler();
            Integer matchid=del.getMatchId();
            Integer season=matseason.get(matchid);
            Integer nbruns=del.getNoballRuns();
            seasonwisenoballs.putIfAbsent(season, new HashMap<>());
            Map<String,Integer>bowlernoballs=seasonwisenoballs.get(season);
            bowlernoballs.put(player,bowlernoballs.getOrDefault(player,0)+nbruns);

        }
        for (Map.Entry<Integer,Map<String,Integer>>seasonEntry:seasonwisenoballs.entrySet()){
            // System.out.println(seasoEntry);
            Integer season=seasonEntry.getKey();
            Map<String,Integer> bowlernoballs=seasonEntry.getValue();
            System.out.println("Season : "+season+" [");
            bowlernoballs.entrySet().stream().sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))
            .limit(10)
            .forEach(entry -> 
                System.out.println(entry.getKey() + "=" + entry.getValue()));
            System.out.println(" ]");
        }
    }






    private static void teamextras(List<Delivery>deliveries){
        Map<String,Integer>extras=new HashMap<>();
        for (Delivery del:deliveries){
            String team=del.getBowlingTeam();
            Integer eruns=del.getExtraRuns();
            extras.put(team,extras.getOrDefault(team, 0)+eruns);
        }
        System.out.println(extras);
    }




    private static void wintossandmatch(List<Match> matches){
        Map<Integer,List<String[]>> tossandmatch=new TreeMap<>();
        // Map<String, Intetger> teamWins = new HashMap<>();
        for (Match match:matches){
            int season=match.getSeason();
            String twinner=match.getTossWinner();
            String matchWinner=match.getWinner();
            tossandmatch.putIfAbsent(season, new ArrayList<>());
            tossandmatch
            .get(season)
            .add(new String[]{twinner,matchWinner});
        }
        // System.out.println(tossandmatch);
        for (Map.Entry<Integer,List<String[]>> entry:tossandmatch.entrySet()){
            // int season=entry.getKey();
            // System.out.println(season);
            Map<String, Integer> teamWins = new HashMap<>();
            System.out.println(entry.getKey());
            for (String[] result:entry.getValue()){
                String tosswinner=result[0];
                String matchwinner=result[1];
                // System.out.println(tosswinner+":"+matchwinner);
                if (tosswinner.equals(matchwinner)){
                    // System.out.println(matchwinner);
                    teamWins.put(tosswinner, teamWins.getOrDefault(tosswinner, 0) + 1);
                }
            }
            System.out.println(teamWins);            
            System.out.println();
        }
    }
    

    
    
}
