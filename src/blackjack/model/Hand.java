package blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Hand {
  private List<Card> cards = new ArrayList<>();

  public void addCard(Card card) {
    cards.add(card);
  }

  public void clear() {
    cards.clear();
  }

  public Set<Integer> getTotalValues() {
    if (cards.isEmpty()) {
      return Set.of();
    }

    Set<Integer> results = new TreeSet<>();
    calculateCombinations(0, 0, results);
    return results;
  }

  private void calculateCombinations(int index, int currentSum, Set<Integer> results) {
    if (index == cards.size()) {
      results.add(currentSum);
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
      if (value <= 21) {
        if (!valueSb.isEmpty()) {
          valueSb.append(" o ");
        }

        valueSb.append(value);
      }
    }

    return String.format("%s (%s)", handSb, valueSb);
  }
}
