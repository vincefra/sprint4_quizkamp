package Sprint4_quizkamp_server.Server;

import java.net.Socket;

public class GameServer {

    private static Game gameWaitingForPlayers;

    // Kallas på när en ny klient ansluter.
    public static void clientConnected(Socket newConnection) {


        // Om det inte finns något spel öppet så skapar vi ett.
        if (gameWaitingForPlayers == null) {
            gameWaitingForPlayers = new Game();

            gameWaitingForPlayers.player1 = new Player(newConnection, gameWaitingForPlayers);

            System.out.println("player 1 har anslutit");
            gameWaitingForPlayers.startGame(); // Starta spelet.
        } else if (gameWaitingForPlayers.player2 == null) {
            // Detta får bli Player 2.
            gameWaitingForPlayers.player2 = new Player(newConnection, gameWaitingForPlayers);

            System.out.println("player 2 har anslutit");
        }

        if (gameWaitingForPlayers.isFull()) {
            gameWaitingForPlayers = null;
        }
    }

    public static void objectRecivedFromClient(Object o) {

    }


}
