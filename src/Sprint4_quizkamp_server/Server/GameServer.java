package Sprint4_quizkamp_server.Server;

import java.net.Socket;

public class GameServer {
    
    private static Game gameWaitingForPlayers;
    
    // Kallas på när en ny klient ansluter.
    public static void clientConnected(Socket newConnection) {
    
        System.out.println(newConnection);
        Player newPlayer = new Player();
        
        
        // Om det inte finns något spel öppet så skapar vi ett.
        if (gameWaitingForPlayers == null) {
            gameWaitingForPlayers = new Game();
        }
        
        if (gameWaitingForPlayers.player1 == null) {
            // Detta får bli Player 1.
            gameWaitingForPlayers.player1 = newPlayer;
        }
        else if (gameWaitingForPlayers.player2 == null) {
            // Detta får bli Player 2.
            gameWaitingForPlayers.player2 = newPlayer;
        }
        
        
        
        
    }
    
    public static void objectRecivedFromClient(Object o) {
    
    }
    
    
    
}
