package blackjack.model;

import java.util.ArrayList;
import java.util.List;

import blackjack.util.Console;

/**
 * Maneja turnos, apuestas y estados.
 */
public class BlackJackGame {
  private Console console;
  private Deck deck;
  private List<Player> players;
  private Dealer dealer;
  private boolean dealerTurnStarted;

  public BlackJackGame(Console console) {
    this.console = console;
    this.deck = new Deck();
    this.players = new ArrayList<>();
    this.dealer = new Dealer();
    this.dealerTurnStarted = false;
  }

  /**
   * Registra un jugador.
   * 
   * @param name Nombre del usuario.
   */
  public void addPlayer(String name) {
    players.add(new Player(name));
  }

  private void resetRound() {
    dealer.getHand().clear();
    for (Player p : players)
      p.getHand().clear();
    dealerTurnStarted = false;
  }

  private void showTable() {
    console.println("\n--- MESA ---");
    if (!dealerTurnStarted) {
      String mano = dealer.getHand().toString();
      String carta1 = mano.contains(" ") ? mano.split(" ")[0] : "??";
      console.println(" CRUPIER: [" + carta1 + "] [HIDDEN]");
    } else {
      console.println(" CRUPIER: " + dealer.getHand().toString());
    }
    for (Player p : players)
      console.println(" " + p.toString());
  }

  /**
   * Ejecuta una ronda completa.
   */
  public void start() {
    if (players.isEmpty())
      return;
    resetRound();

    // APUESTAS
    for (Player p : players) {
      boolean ok = false;
      while (!ok) {
        try {
          console.print(p.toString().split(":")[0] + " (" + p.getBalance() + "€). Apuesta: ");
          p.placeBet(Integer.parseInt(console.readLine()));
          ok = true;
        } catch (Exception e) {
          console.println("Error: " + e.getMessage());
        }
      }
    }

    // REPARTO
    for (int i = 0; i < 2; i++) {
      for (Player p : players)
        p.addCard(deck.pickCard());
      dealer.addCard(deck.pickCard());
    }

    // TURNOS JUGADORES
    for (Player p : players)
      playerTurn(p);

    // TURNO CRUPIER
    dealerTurnStarted = true;
    if (players.stream().anyMatch(p -> p.getHand().getHighestValue() != -1)) {
      dealerTurn();
    } else {
      showTable();
    }

    checkWinners();
  }

  private void playerTurn(Player p) {
    String nom = p.toString().split(":")[0];
    while (p.canRequest()) {
      showTable();
      console.print(nom + " ¿(P)edir o (S)lantarse? ");
      if (console.readLine().trim().toUpperCase().equals("P")) {
        p.addCard(deck.pickCard());
      } else
        break;
    }
  }

  private void dealerTurn() {
    while (dealer.canRequest()) {
      showTable();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      dealer.addCard(deck.pickCard());
    }
    showTable();
  }

  /**
   * Compara puntos y ajusta balances.
   */
  public void checkWinners() {
    int d = dealer.getHand().getHighestValue();
    console.println("\n--- RESULTADOS ---");
    for (Player p : players) {
      int pts = p.getHand().getHighestValue();
      console.print(p.toString().split(":")[0] + ": ");
      if (pts == -1) {
        console.print("BUST. ");
        p.loseBet();
      } else if (d == -1 || pts > d) {
        console.print("GANA. ");
        p.winBet();
      } else if (pts < d) {
        console.print("PIERDE. ");
        p.loseBet();
      } else {
        console.print("PUSH. ");
        p.pushBet();
      }
      console.println("Saldo: " + p.getBalance() + "€");
    }
  }

  /**
   * Echa a los que tengan menos de 5€.
   */
  public void removeBrokePlayers() {
    players.removeIf(p -> p.getBalance() < 5);
  }

  /**
   * Mira si queda alguien.
   * 
   * @return true si hay jugadores.
   */
  public boolean hasPlayers() {
    return !players.isEmpty();
  }
}
