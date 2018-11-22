package Sprint4_quizkamp_server.Server;

public class Game {

    public Player player1;
    public Player player2;

    public boolean isFull() {
        return player1 != null && player2 != null;
    }


}
