package blackjack.model;

/**
 * Representa a un jugador de Blackjack. Cada jugador tiene un nombre, una mano de cartas, un balance de fichas y una apuesta para la ronda actual.
 * 
 */
public class Player extends Member {

  /** Dinero disponible del jugador */
  private int balance;

  /** Apuesta de la ronda actual */
  private int bet;

  /**
   * Crea un jugador con un nombre dado y un balance inicial de 1000.
   *
   * @param name Nombre del jugador
   */
  public Player(String name) {
    super(name);
    this.balance = 1000;
    this.bet = 0;
  }

  /**
   * Coloca la apuesta del jugador para la ronda actual.
   *
   * @param amount Cantidad a apostar (mínimo 5)
   * @throws IllegalArgumentException si la apuesta es menor a 5 o mayor que el balance disponible
   */
  public void placeBet(int amount) {
    if (amount < 5) {
      throw new IllegalArgumentException("La apuesta mínima es 5");
    }
    if (amount > balance) {
      throw new IllegalArgumentException("No tienes suficiente dinero");
    }
    this.bet = amount;
  }

  /**
   * Devuelve la apuesta actual del jugador.
   *
   * @return Apuesta de la ronda
   */
  public int getBet() {
    return bet;
  }

  /**
   * Ajusta el balance del jugador si gana la apuesta.
   */
  public void winBet() {
    balance += bet;
    bet = 0;
  }

  /**
   * Ajusta el balance del jugador si pierde la apuesta.
   */
  public void loseBet() {
    balance -= bet;
    bet = 0;
  }

  /**
   * Ajusta la apuesta si hay empate (push). El balance no cambia.
   */
  public void pushBet() {
    bet = 0;
  }

  /**
   * Devuelve el balance actual del jugador.
   *
   * @return Balance de fichas disponibles
   */
  public int getBalance() {
    return balance;
  }

  /**
   * Determina si el jugador puede pedir otra carta.El jugador puede pedir carta mientras el valor mínimo de su mano sea menor a 21.
   *
   * @return true si puede pedir carta, false si se pasa o llega a 21
   */
  @Override
  public boolean canRequest() {
    return hand.getLowestValue() < 21;
  }

  /**
   * Devuelve una representación en cadena del jugador, incluyendo nombre, mano y balance.
   *
   * @return Cadena con el estado del jugador
   */
  @Override
  public String toString() {
    return String.format("%s: %s (Balance: %d)", name, hand, balance);
  }
}
