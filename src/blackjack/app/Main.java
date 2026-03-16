package blackjack.app;

import blackjack.model.BlackJackGame;
import blackjack.util.Console;

/**
 * Clase principal que actúa como punto de entrada para la aplicación de Blackjack. Gestiona el ciclo de vida del juego, la interacción inicial con los usuarios y el bucle de repetición de rondas.
 */
public class Main {

  /**
   * Método principal que arranca el Casino Java. * @param args Argumentos de la línea de comandos (no utilizados).
   */
  public static void main(String[] args) {
    Console console = new Console();
    showWelcome(console);
    BlackJackGame game = new BlackJackGame(console);

    // 1. REGISTRO DE JUGADORES
    int num = 0;
    while (num <= 0) {
      try {
        console.print("¿Cuántos jugadores vais a ser? ");
        num = Integer.parseInt(console.readLine());
        if (num <= 0) {
          console.println("Debe haber al menos 1 jugador para abrir la mesa.");
        }
      } catch (NumberFormatException e) {
        console.println("Error: Por favor, introduce un número entero válido.");
      }
    }

    for (int i = 1; i <= num; i++) {
      console.print("Nombre del jugador " + i + ": ");
      game.addPlayer(console.readLine());
    }

    // 2. BUCLE PRINCIPAL DE JUEGO (GAME LOOP)
    boolean keepPlaying = true;
    while (keepPlaying) {
      // Inicia el flujo de la ronda: apuestas, reparto, turnos y pagos
      game.start();

      // Gestión de solvencia: elimina a quienes no pueden pagar la apuesta mínima (5)
      game.removeBrokePlayers();

      // Verificación de estado de la mesa
      if (!game.hasPlayers()) {
        console.println("\n[SISTEMA] No quedan jugadores con fondos suficientes. La mesa se cierra.");
        keepPlaying = false;
      } else {
        // Opción de continuar para los jugadores solventes
        console.print("\n¿Deseáis jugar otra ronda? (S/N): ");
        String respuesta = console.readLine().trim().toUpperCase();

        if (respuesta.equals("N")) {
          keepPlaying = false;
        }
      }
    }

    console.println("\n******************************************");
    console.println("* GRACIAS POR VISITAR EL CASINO          *");
    console.println("* ¡HASTA LA PRÓXIMA!                     *");
    console.println("******************************************");
    console.close();
  }

  /**
   * Imprime en la consola un banner decorativo de bienvenida.
   */
  private static void showWelcome(Console console) {
    console.println("******************************************");
    console.println("*                                        *");
    console.println("*        BIENVENIDO A \"EL JANDRO\"        *");
    console.println("*       (apología a la ludopatía)        *");
    console.println("*                                        *");
    console.println("******************************************\n");
  }
}
