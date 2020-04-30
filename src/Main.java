import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Player>players=createRoom(2);
        List<Card> pack=generatePack();
        shuffleCard(players,pack);
        playGame(players,pack);
    }

    private static void playGame(List<Player> players, List<Card> pack){
        List<Card> discardedList=new ArrayList<>();
        Player playertoRemove=null;
        boolean shown=false;
        while (!shown){
            for (Player player:players){
                System.out.println(player.getName()+"'s turn : cards held");
                player.printCards();
                List<Card> discard=player.discard();
                if (discard.get(0).getValue()==0){
                    System.out.println(player.getName()+"Shown his card");
                    if (showByPlayer(player)){
                        System.out.println("Game Ended. Show by "+player.getName());
                        playertoRemove=player;
                        shown=true;
                        break;
                    }
                }
                discardedList.addAll(discard);
                player.getCard(pack.get(getRandomNumber(0,pack.size())));
            }
        }
        players.remove(playertoRemove);
        calculatePoints(players);
    }

    private static void calculatePoints(List<Player> players) {
        for (Player player:players){
            int points=0;
            for (Card card:player.getCardInHand()){
                points=points+card.getValue();
            }
            System.out.println("Point for "+player.getName()+" is "+points);
        }
    }

    private static boolean showByPlayer(Player player) {
        int n=0;
        for (Card card:player.getCardInHand()){
            n=n+card.getValue();
        }
        if (n<=9){
            System.out.println("Show success");
            return true;
        }else {
            System.out.println("Point greater than 9, u can't show");
        }
        return false;
    }


    private static List<Card> generatePack(){
        List<Card> pack=new ArrayList<>();
        String[] symbols={"heart","spade","diamond","flower"};
        String[] names={"A","2","3","4","5","6","7","8","9","10","J","Q","k"};
        for (String symbol : symbols) {
            for (int i = 0; i < names.length; i++) {
                pack.add(new Card(names[i], i + 1, symbol));
            }
        }
        return pack;
    }

    private static List<Player> createRoom(int playerCount){
        List<Player> players=new ArrayList<>();
        for (int i=1;i<=playerCount;i++){
            players.add(new Player("player"+i,new ArrayList<>()));
        }
        return players;
    }

    private static void shuffleCard(List<Player> room,List<Card> pack){
        for (Player player:room){
            for (int i=1;i<=5;i++){
                int toShuffle=getRandomNumber(0,pack.size()-1);
                player.getCard(pack.get(toShuffle));
                pack.remove(toShuffle);
            }
        }
    }

    private static int getRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
