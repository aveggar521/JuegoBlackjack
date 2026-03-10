package blackjack.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJackGame {
  private Deck deck;
  private List<Player> players;
  private Dealer dealer;
  private Scanner scanner;
  private boolean dealerTurnStarted; // Controla si mostramos la carta oculta

  public BlackJackGame() {
    this.deck = new Deck();
    this.players = new ArrayList<>();
    this.dealer = new Dealer();
    this.scanner = new Scanner(System.in);
    this.dealerTurnStarted = false;
  }

  /**
   * Añade jugadores a la partida antes de empezar.
   */
  public void addPlayer(String name) {
    players.add(new Player(name));
  }

  /**
   * Muestra el estado de la mesa. Si el turno del dealer no ha empezado, ocultamos su puntuación real y solo vemos su primera carta.
   */
  private void showTable() {
    System.out.println("\n========================================");
    if (!dealerTurnStarted) {
      // Solo se ve la primera carta del Crupier al principio
      System.out.println(" CRUPIER: [Carta Oculta] y " + dealer.getHand().toString().split(" ")[0]);
    } else {
      System.out.println(" CRUPIER: " + dealer.getHand().toString());
    }

    for (Player p : players) {
      System.out.println(" " + p.toString());
    }
    System.out.println("========================================");
  }

  public void start() {
    if (players.isEmpty()) {
      System.out.println("No hay jugadores para empezar.");
      return;
    }

    // 1. REPARTO INICIAL (2 cartas a cada uno)
    for (int i = 0; i < 2; i++) {
      for (Player p : players)
        p.addCard(deck.pickCard());
      dealer.addCard(deck.pickCard());
    }

    // 2. TURNOS DE LOS JUGADORES
    for (Player p : players) {
      playerTurn(p);
    }

    // 3. TURNO DEL CRUPIER
    // Solo juega si al menos un jugador no se ha pasado
    boolean anyPlayerAlive = players.stream().anyMatch(p -> p.getHand().getHighestValue() != -1);

    dealerTurnStarted = true; // Ahora revelamos la mano del Crupier
    if (anyPlayerAlive) {
      dealerTurn();
    } else {
      System.out.println("\nTodos los jugadores se pasaron. El Crupier no necesita jugar.");
      showTable();
    }

    // 4. RESULTADOS
    checkWinners();
  }

  private void playerTurn(Player p) {
    boolean plant = false;
    System.out.println("\n>>> TURNO DE: " + p.toString().split(":")[0]);

    while (!plant && p.canRequest()) {
      showTable();
      System.out.print(p.toString().split(":")[0] + ", ¿(P)Pedir o (S)Plantarse? ");
      String decision = scanner.nextLine().trim().toUpperCase();

      if (decision.equals("P")) {
        p.addCard(deck.pickCard());
        if (p.getHand().getHighestValue() == -1) {
          showTable();
          System.out.println("¡" + p.toString().split(":")[0] + " SE HA PASADO!");
          plant = true;
        }
      } else {
        plant = true;
      }
    }
  }

  private void dealerTurn() {
    System.out.println("\n--- Turno del Crupier ---");
    showTable();

    while (dealer.getHand().getHighestValue() != -1 && dealer.canRequest()) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      System.out.println("El Crupier pide carta...");
      dealer.addCard(deck.pickCard());
      showTable();
    }
  }

  public void checkWinners() {
    int dPoints = dealer.getHand().getHighestValue();
    System.out.println("\n--- RESULTADOS FINALES ---");

    for (Player p : players) {
      int pPoints = p.getHand().getHighestValue();
      String pName = p.toString().split(":")[0];

      System.out.print(pName + ": ");
      if (pPoints == -1) {
        System.out.println("Pierde (Bust)");
      } else if (dPoints == -1) {
        System.out.println("¡GANA! (Crupier se pasó)");
      } else if (pPoints > dPoints) {
        System.out.println("¡GANA! (" + pPoints + " vs " + dPoints + ")");
      } else if (pPoints < dPoints) {
        System.out.println("Pierde (" + dPoints + " vs " + pPoints + ")");
      } else {
        System.out.println("Empate (Push)");
      }
    }
  }
}
