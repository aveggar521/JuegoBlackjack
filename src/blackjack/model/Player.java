package blackjack.model;

public class Player extends Member {
  public Player(String name) {
    super(name);
  }

  @Override
  public boolean canRequest() {
    return hand.getLowestValue() < 21;
  }
}
