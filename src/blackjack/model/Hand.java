package blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Representa la mano de un jugador o crupier.
 */
public class Hand {
  /**
   * Lista de cartas que contiene la mano.
   */
  private List<Card> cards = new ArrayList<>();

  /**
   * Añade una carta a la mano.
   *
   * @param card Carta a añadir
   */
  public void addCard(Card card) {
    cards.add(card);
  }

  /**
   * Elimina todas las cartas de la mano.
   */
  public void clear() {
    cards.clear();
  }

  /**
   * Obtiene todas las combinaciones posibles del total del valor de la mano.
   *
   * @return Valor de la mano
   */
  public Set<Integer> getTotalValues() {
    if (cards.isEmpty()) {
      return Set.of();
    }

    Set<Integer> results = new TreeSet<>();
    calculateCombinations(0, 0, results);
    return results;
  }

  /**
   * Obtiene el valor más pequeño de la mano siempre y cuando sea menor a 21. Si no hay un valor posible, devuelve `-1`.
   *
   * @return Valor más pequeño de la mano
   */
  public int getLowestValue() {
    try {
      return new TreeSet<Integer>(getTotalValues()).first();
    } catch (NoSuchElementException e) {
      return -1;
    }
  }

  /**
   * Obtiene el valor más alto de la mano siempre y cuando sea menor o igual a 21. Si no hay un valor posible, devuelve `-1`.
   *
   * @return Valor más alto de la mano
   */
  public int getHighestValue() {
    try {
      return new TreeSet<Integer>(getTotalValues()).last();
    } catch (NoSuchElementException e) {
      return -1;
    }
  }

  /**
   * Método recursivo auxiliar para poder calcular las combinaciones.
   *
   * @param index      Índice actual de la carta
   * @param currentSum Suma actual de los valores
   * @param results    Conjunto donde se almacenan los resultados
   */
  private void calculateCombinations(int index, int currentSum, Set<Integer> results) {
    if (index == cards.size()) {
      if (currentSum <= 21) {
        results.add(currentSum);
      }

      return;
    }

    for (int value : cards.get(index).getValues()) {
      calculateCombinations(index + 1, currentSum + value, results);
    }
  }

  @Override
  public String toString() {
    if (cards.isEmpty()) {
      return "";
    }

    StringBuilder handSb = new StringBuilder();
    StringBuilder valueSb = new StringBuilder();

    for (Card card : cards) {
      if (!handSb.isEmpty()) {
        handSb.append(" ");
      }

      handSb.append(card);
    }

    for (int value : getTotalValues()) {
      if (!valueSb.isEmpty()) {
        valueSb.append(" o ");
      }

      valueSb.append(value);
    }

    return String.format("%s (%s)", handSb, valueSb);
  }
}
