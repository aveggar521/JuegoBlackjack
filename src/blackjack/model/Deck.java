package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa una baraja con todas las cartas. Se inicializa con todas las cartas y se baraja automáticamente al crear.
 */
public class Deck {
  private List<Card> cards = new ArrayList<>();

  /**
   * Crea la baraja con las cartas ya barajadas.
   */
  public Deck() {
    fill();
    shuffle();
  }

  /**
   * Obten la siguiente carta de la baraja. Si la baraja se queda sin cartas, se volverá a llenar y a barajar automáticamente.
   * 
   * @return Siguiente carta de la baraja
   */
  public Card pickCard() {
    if (cards.isEmpty()) {
      fill();
      shuffle();
    }

    return cards.removeFirst();
  }

  /**
   * Llena la baraja de cartas nuevas.
   */
  private void fill() {
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new Card(suit, rank);
        cards.add(card);
      }
    }
  }

  /**
   * Desordena las cartas de la baraja.
   */
  private void shuffle() {
    Collections.shuffle(cards);
  }

  @Override
  public String toString() {
    String message = cards.size() == 1 ? "carta restante" : "cartas restantes";
    return String.format("%d %s", cards.size(), message);
  }
}
