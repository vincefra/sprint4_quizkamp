package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;

import java.util.ArrayList;

public class Game {
    
    public Player player1;
    public Player player2;
    private ArrayList<Round> rounds;
    
    public boolean isFull() {
        return player1 != null && player2 != null;
    }
    
    public void startGame() {
        rounds = new ArrayList<>();
        
        
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
        
        if (message instanceof ShowCategoriesAction) {
            // Vi har precis valt kategori. En ny runda ska starta.
            String categoryChosen = ((ShowCategoriesAction)message).chosenCategory;
            Round newRound = new Round();
            newRound.questions = QuestionsHandler.getQuestions(categoryChosen, 2, false);
            
        }
    }
    
}
