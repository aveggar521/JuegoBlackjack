package blackjack.model;

/**
 * 
 * Representa al crupier en el Blackjack. El crupier tiene una mano y sigue reglas automáticas para pedir cartas. Pide carta mientras el valor más alto de su mano sea menor a 17.
 */
public class Dealer extends Member {

  /**
   * Crea un crupier con el nombre "Dealer" y mano vacía.
   */
  public Dealer() {
    super("Dealer");
  }

  /**
   * Indica si el crupier puede pedir carta según su regla automática.
   *
   * @return true si puede pedir carta, false si no
   */
  @Override
  public boolean canRequest() { 
    int valor = hand.getHighestValue();
    // Si valor es -1, significa que ya se pasó de 21, así que debe parar (false)
    return valor != -1 && valor < 17;
  }
}
