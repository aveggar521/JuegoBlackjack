package blackjack.model;

public class Dealer extends Member {
  public Dealer() {
    super("Dealer");
  }

  @Override
  public boolean canRequest() {
    return hand.getHighestValue() < 17;
  }
}
