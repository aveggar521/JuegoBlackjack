package blackjack.model;

public class Card {
  private Suit suit;
  private Rank rank;

  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

  public int[] getValues() {
    return rank.getValues();
  }

  @Override
  public String toString() {
    return String.format("%s%s", suit.getSymbol(), rank.getSymbol());
  }
}
