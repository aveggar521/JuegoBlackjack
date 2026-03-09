package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
  private List<Card> cards = new ArrayList<>();

  public Deck() {
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new Card(suit, rank);
        cards.add(card);
      }
    }

    shuffleDeck();
  }

  public Card pickCard() {
    return cards.removeFirst();
  }

  private void shuffleDeck() {
    Collections.shuffle(cards);
  }

  @Override
  public String toString() {
    String message = cards.size() == 1 ? "carta restante" : "cartas restantes";
    return String.format("%d %s", cards.size(), message);
  }
}
