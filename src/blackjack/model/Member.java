package blackjack.model;

public abstract class Member {
  protected String name;
  protected Hand hand;

  public Member(String name) {
    this.name = name;
    this.hand = new Hand();
  }

  public void addCard(Card card) {
    hand.addCard(card);
  }

  public Hand getHand() {
    return hand;
  }

  public void clearHand() {
    hand.clear();
  }

  public abstract boolean canRequest();

  @Override
  public String toString() {
    return String.format("%s: %s", name, hand);
  }
}
