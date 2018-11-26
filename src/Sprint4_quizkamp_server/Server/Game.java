package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Game {
    
    public Player player1;
    public Player player2;
    
    public boolean isFull() {
        return player1 != null && player2 != null;
    }
    
    public void startGame() {

        //Läser in properties för antal rundor/frågor
        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src\\Sprint4_quizkamp_server\\Server\\GameProperties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numQuestions = Integer.parseInt(p.getProperty("numberOfQuestions", "2"));
        int numRounds = Integer.parseInt(p.getProperty("numberOfRounds", "2"));
        System.out.println(numQuestions + " " + numRounds);

        // Prova visa kategorier för spelare 1.
        System.out.println("SERVER: startar game");
        ArrayList<Category> categories = QuestionsHandler.getCategories();
        ArrayList<String> categoriesAsStrings = new ArrayList<>();
        ShowCategoriesAction action = new ShowCategoriesAction();
        
        for (int i = 0; i < categories.size(); i++) {
            categoriesAsStrings.add(categories.get(i).name);
        }
        action.categories = categoriesAsStrings;
        
        Server.sendObject(player1.outputStream, action);
        System.out.println("Server: Skickat categoryobject");
    }
    
    public void messageRecivedFromPlayer(Object message, Player from) {
        System.out.println("SERVER tagit emot meddelande/objekt: " + message + " FROM " + from);
    }
    
}
