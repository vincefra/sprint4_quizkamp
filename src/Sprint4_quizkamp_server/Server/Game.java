package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;

import java.util.ArrayList;

public class Game {
    
    public Player player1;
    public Player player2;
    
    public boolean isFull() {
        return player1 != null && player2 != null;
    }
    
    public void startGame() {
        // Prova visa kategorier för spelare 1.
        System.out.println("SERVER: startar game");
        ArrayList<Category> categories = QuestionsHandler.getCategories();
        ArrayList<String> categoriesAsStrings = new ArrayList<>();
        ShowCategoriesAction action = new ShowCategoriesAction();
        
        for (int i = 0; i < categories.size(); i++) {
            categoriesAsStrings.add(categories.get(i).name);
        }
        
        Server.sendObject(player1.outputStream, action);
        
    }
    
    public void messageRecivedFromPlayer(Object message, Player from) {
        System.out.println("SERVER tagit emot meddelande/objekt: " + message + " FROM " + from);
    }
    
}
