package blackjack.model;

/**
 * Enum que representa los palos de una carta.
 */
public enum Suit {
  HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣"), SPADES("♠");

  /**
   * Símbolo del palo.
   */
  private String symbol;

  private Suit(String symbol) {
    this.symbol = symbol;
  }

  /**
   * Obtiene el símbolo del palo.
   * 
   * @return Símbolo del palo
   */
  public String getSymbol() {
    return symbol;
  }
}
