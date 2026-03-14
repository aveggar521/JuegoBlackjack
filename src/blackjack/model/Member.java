package blackjack.model;

/**
 * Clase abstracta que representa un miembro del juego de Blackjack. Un miembro tiene un nombre y una mano de cartas. Las subclases (Player, Dealer) deben definir la regla para pedir cartas.
 */
public abstract class Member {

  protected String name;
  protected Hand hand;

  /**
   * Crea un miembro con un nombre dado y mano vacía.
   *
   * @param name Nombre del miembro
   */
  public Member(String name) {
    this.name = name;
    this.hand = new Hand();
  }

  /**
   * Añade una carta a la mano del miembro.
   *
   * @param card Carta a añadir
   */
  public void addCard(Card card) {
    hand.addCard(card);
  }

  /**
   * Devuelve la mano del miembro.
   *
   * @return Mano del miembro
   */
  public Hand getHand() {
    return hand;
  }

  /**
   * Vacía la mano del miembro.
   */
  public void clearHand() {
    hand.clear();
  }

  /**
   * Indica si el miembro puede pedir otra carta.
   *
   * @return true si puede pedir carta, false si no
   */
  public abstract boolean canRequest();

  /**
   * Devuelve una representación en cadena del miembro, incluyendo nombre y mano.
   *
   * @return Cadena con el estado del miembro
   */
  @Override
  public String toString() {
    return String.format("%s: %s", name, hand);
  }
}
