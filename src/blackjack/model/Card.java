package blackjack.model;

import java.util.Set;

/**
 * Representa una carta de la baraja.
 */
public class Card {
  /**
   * Palo de la carta.
   */
  private Suit suit;
  /**
   * Rango de la carta.
   */
  private Rank rank;

  /**
   * Crea una carta con dado palo y rango.
   * 
   * @param suit Palo de la carta.
   * @param rank Rango de la carta.
   */
  public Card(Suit suit, Rank rank) {
    this.suit = suit;
    this.rank = rank;
  }

  /**
   * Obtiene los valores de la carta.
   * 
   * @return Valores de la carta.
   */
  public Set<Integer> getValues() {
    return rank.getValues();
  }

  @Override
  public String toString() {
    return String.format("%s%s", suit.getSymbol(), rank.getSymbol());
  }
}
