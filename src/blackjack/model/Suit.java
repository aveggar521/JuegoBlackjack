package blackjack.model;

public enum Suit {
  HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣"), SPADES("♠");

  private String symbol;

  private Suit(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
