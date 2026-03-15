package blackjack.app;

import java.util.Scanner;

import blackjack.model.BlackJackGame;

/**
 * Clase de entrada al programa para probar el funcionamiento del BlackJack.
 */
public class Main {

  public static void main(String[] args) {
    showWelcome();
    Scanner sc = new Scanner(System.in);
    BlackJackGame game = new BlackJackGame();

    System.out.print("¿Cuántos jugadores vais a ser? ");
    int num = Integer.parseInt(sc.nextLine());

    for (int i = 1; i <= num; i++) {
      System.out.print("Nombre del jugador " + i + ": ");
      game.addPlayer(sc.nextLine());
    }

    game.start();
  }

  /**
   * Muestra un banner de bienvenida por consola.
   */
  private static void showWelcome() {
    System.out.println("******************************************");
    System.out.println("*                                        *");
    System.out.println("*       BIENVENIDO AL CASINO JAVA        *");
    System.out.println("*            JUEGO: BLACKJACK            *");
    System.out.println("*                                        *");
    System.out.println("******************************************\n");
  }
}

/**
 * TODO: mostrar un mensaje con las fichas actuales del jugador y preguntarle si quiere jugar otra vez
 */
