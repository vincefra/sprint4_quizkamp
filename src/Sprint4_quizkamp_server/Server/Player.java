package Sprint4_quizkamp_server.Server;

import java.net.Socket;
import java.util.ArrayList;

public class Player extends Thread {
    
    public Socket socket;
    private Game game;
    
    public Player() {
    
    }
    
    public void setGame(Game g) {
        this.game = g;
    }

    public void showCategoriesWindow() {
        ArrayList<Category> categories = QuestionsHandler.getCategories();
        
        
        
    }

}
