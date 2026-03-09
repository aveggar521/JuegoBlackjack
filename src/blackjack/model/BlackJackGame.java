package blackjack.model;

import java.util.Scanner;

/**
 * Clase principal que controla la lógica y el flujo de una partida de BlackJack. Coordina la interacción entre la baraja, el jugador y el crupier (dealer).
 */
public class BlackJackGame {
  /** Baraja de cartas utilizada en el juego. */
  private Deck deck;
  /** Jugador humano. */
  private Player player;
  /** Crupier (Dealer) del casino. */
  private Dealer dealer;
  /** Scanner para la entrada de datos por consola. */
  private Scanner scanner;

  /**
   * Crea una nueva instancia del juego de BlackJack.
   * 
   * @param playerName Nombre del jugador que va a participar.
   */
  public BlackJackGame(String playerName) {
    this.deck = new Deck();
    this.player = new Player(playerName);
    this.dealer = new Dealer();
    this.scanner = new Scanner(System.in);
  }

  /**
   * Inicia el flujo principal del juego: reparto inicial, turnos y resolución.
   */
  public void iniciar() {
    System.out.println("--- Inicio de la partida de BlackJack ---");

    // 1. Reparto inicial: 2 cartas para cada uno
    player.addCard(deck.pickCard());
    dealer.addCard(deck.pickCard());
    player.addCard(deck.pickCard());
    dealer.addCard(deck.pickCard());

    // 2. Turno del Jugador
    ejecutarTurnoJugador();

    // 3. Turno del Dealer (solo si el jugador no se ha pasado de 21)
    if (player.getHand().getHighestValue() != -1) {
      ejecutarTurnoDealer();
    }

    // 4. Mostrar resultado final
    comprobarGanador();
  }

  /**
   * Gestiona el turno del jugador permitiéndole pedir cartas hasta que decida plantarse o se pase de 21 puntos.
   */
  private void ejecutarTurnoJugador() {
    boolean plantado = false;

    while (!plantado && player.canRequest()) {
      System.out.println("\n" + player.toString());
      System.out.print("¿Deseas pedir carta (P) o plantarte (S)? ");
      String decision = scanner.nextLine().trim().toUpperCase();

      if (decision.equals("P")) {
        player.addCard(deck.pickCard());
        if (player.getHand().getHighestValue() == -1) {
          System.out.println("\n" + player.toString());
          System.out.println("¡Te has pasado de 21!");
          plantado = true;
        }
      } else {
        plantado = true;
      }
    }
  }

  /**
   * Gestiona el turno del Dealer. Según la lógica de Dealer, pedirá carta mientras su valor más alto sea inferior a 17.
   */
  private void ejecutarTurnoDealer() {
    System.out.println("\n--- Turno del Dealer ---");
    while (dealer.canRequest()) {
      System.out.println("El Dealer pide carta...");
      dealer.addCard(deck.pickCard());
    }
    System.out.println(dealer.toString());
  }

  /**
   * Compara las manos del jugador y el dealer para determinar quién ha ganado la partida e imprime el resultado por consola.
   */
  public void comprobarGanador() {
    int puntosJugador = player.getHand().getHighestValue();
    int puntosDealer = dealer.getHand().getHighestValue();

    System.out.println("\n--- RESULTADO FINAL ---");
    System.out.println("Puntos Jugador: " + (puntosJugador == -1 ? "Bust (>21)" : puntosJugador));
    System.out.println("Puntos Dealer: " + (puntosDealer == -1 ? "Bust (>21)" : puntosDealer));

    if (puntosJugador == -1) {
      System.out.println("Resultado: Gana la Casa (Jugador se pasó).");
    } else if (puntosDealer == -1) {
      System.out.println("Resultado: ¡GANASTE! (Dealer se pasó).");
    } else if (puntosJugador > puntosDealer) {
      System.out.println("Resultado: ¡GANASTE!");
    } else if (puntosJugador < puntosDealer) {
      System.out.println("Resultado: Gana la Casa.");
    } else {
      System.out.println("Resultado: Empate (Push).");
    }
  }
}
