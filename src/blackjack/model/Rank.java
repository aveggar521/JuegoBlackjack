package blackjack.model;

public enum Rank {
  AS("AS", 1, 11), TWO("2", 2), THREE("3", 3), FOUR("4", 4), FIVE("5", 5), SIX("6", 6), SEVEN("7", 7), EIGHT("8", 8),
  NINE("9", 9), J("J", 10), Q("Q", 10), K("K", 10);

  private String symbol;
  private int[] values;

  private Rank(String symbol, int... values) {
    this.symbol = symbol;
    this.values = values;
  }

  public String getSymbol() {
    return symbol;
  }

  public int[] getValues() {
    return values;
  }
}
