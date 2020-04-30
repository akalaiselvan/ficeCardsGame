public class Card {
    private String name;
    private int value;
    private String symbol;

    public Card(String name, int value, String symbol) {
        this.name = name;
        this.value = value;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }
}
