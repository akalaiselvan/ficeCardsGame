import java.util.*;

public class Player {
    private String name;

    public List<Card> getCardInHand() {
        return cardInHand;
    }

    private List<Card> cardInHand;
    Scanner scanner=new Scanner(System.in);

    public Player(String name, List<Card> cardInHand) {
        this.name = name;
        this.cardInHand = cardInHand;
    }

    public void getCard(Card card){
        cardInHand.add(card);
    }

    public void deCard(Card card){
        cardInHand.remove(card);
    }

    public void printCards(){
        int n=0;
        System.out.println("s- for Show");
        for (Card card:cardInHand){
            System.out.println(n+"."+card.getName()+"("+card.getSymbol()+")");
            n++;
        }
    }

    public String getName() {
        return name;
    }



    public List<Card> discard() {
        List<Card> disCards=new ArrayList<>();
        System.out.println("Please Discard ..");
        String dc=scanner.nextLine();
        if (dc.equalsIgnoreCase("s")){
            disCards.add(new Card("Show",0,"S"));
            return disCards;
        }
        String[] splitDc=dc.split(" ");
        if (!areSame(splitDc,this.cardInHand)){
            System.out.println("Cards are not same , So u cant discard these cards");
            return discard();
        }else {
            int count=0;
            for (String disc:splitDc){
                Card card=this.cardInHand.get(Integer.parseInt(disc)-count);
                disCards.add(card);
                System.out.println(card.getName()+" discarded ");
                this.cardInHand.remove(card);
                count++;
            }
        }
        return disCards;
    }


    private static boolean areSame(String[] arr, List<Card> cardInHand) {
        String[] card=new String[arr.length];
        for (int i=0;i<=arr.length-1;i++){
            card[i]= String.valueOf(cardInHand.get(Integer.parseInt(arr[i])).getValue());
        }
        Set<String> s = new HashSet<>(Arrays.asList(card));
        return (s.size() == 1);
    }
}
