package blackjack.app;

import blackjack.model.BlackJackGame;
import java.util.Scanner;

/**
 * Clase principal que actúa como punto de entrada para la aplicación de Blackjack.
 * Gestiona el ciclo de vida del juego, la interacción inicial con los usuarios
 * y el bucle de repetición de rondas.
 */
public class Main {

    /**
     * Método principal que arranca el Casino Java.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        showWelcome();
        Scanner sc = new Scanner(System.in);
        BlackJackGame game = new BlackJackGame();

        // 1. REGISTRO DE JUGADORES
        int num = 0;
        while (num <= 0) {
            try {
                System.out.print("¿Cuántos jugadores vais a ser? ");
                num = Integer.parseInt(sc.nextLine());
                if (num <= 0) {
                    System.out.println("Debe haber al menos 1 jugador para abrir la mesa.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número entero válido.");
            }
        }

        for (int i = 1; i <= num; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            game.addPlayer(sc.nextLine());
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
                System.out.println("\n[SISTEMA] No quedan jugadores con fondos suficientes. La mesa se cierra.");
                keepPlaying = false;
            } else {
                // Opción de continuar para los jugadores solventes
                System.out.print("\n¿Deseáis jugar otra ronda? (S/N): ");
                String respuesta = sc.nextLine().trim().toUpperCase();
                
                if (respuesta.equals("N")) {
                    keepPlaying = false;
                }
            }
        }

        System.out.println("\n******************************************");
        System.out.println("* GRACIAS POR VISITAR EL CASINO     *");
        System.out.println("* ¡HASTA LA PRÓXIMA!           *");
        System.out.println("******************************************");
        sc.close();
    }

    /**
     * Imprime en la consola un banner decorativo de bienvenida.
     */
    private static void showWelcome() {
        System.out.println("******************************************");
        System.out.println("*                                        *");
        System.out.println("*        BIENVENIDO A \"EL JANDRO\"        *");
        System.out.println("*       (apología a la ludopatía)        *");
        System.out.println("*                                        *");
        System.out.println("******************************************\n");
    }
}