package Sprint4_quizkamp_server.Server;

import java.net.Socket;

public class GameServer {
    
    private static Game gameWaitingForPlayers;
    
    // Kallas på när en ny klient ansluter.
    public static void clientConnected(Socket newConnection) {
        
        
        // Om det inte finns något spel öppet så skapar vi ett.
        if (gameWaitingForPlayers == null) {
            // Spelare 1 har anslutit.
            gameWaitingForPlayers = new Game();
            gameWaitingForPlayers.player1.connect(newConnection);

            System.out.println("player 1 har anslutit");
        }
        else if (gameWaitingForPlayers.player2 == null) {
            // Spelare 2 har anslutit.
            gameWaitingForPlayers.player2.connect(newConnection);

            System.out.println("player 2 har anslutit");
        }
        
        if (gameWaitingForPlayers.isFull()) {
            gameWaitingForPlayers = null;
        }   
    }
    
    
    
    
}
