package blackjack.model;

import java.util.Set;

/**
 * Enum que representa un rango de una carta.
 */
public enum Rank {
  AS("AS", Set.of(1, 11)), TWO("2", Set.of(2)), THREE("3", Set.of(3)), FOUR("4", Set.of(4)), FIVE("5", Set.of(5)),
  SIX("6", Set.of(6)), SEVEN("7", Set.of(7)), EIGHT("8", Set.of(8)), NINE("9", Set.of(9)), J("J", Set.of(10)),
  Q("Q", Set.of(10)), K("K", Set.of(10));

  private String symbol;
  private Set<Integer> values;

  private Rank(String symbol, Set<Integer> values) {
    this.symbol = symbol;
    this.values = values;
  }

  /**
   * Obtiene el símbolo del rango.
   * 
   * @return Símbolo del rango
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Obtiene los valores del rango.
   * 
   * @return Valores del rango
   */
  public Set<Integer> getValues() {
    return values;
  }
}
