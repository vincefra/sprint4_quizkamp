package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;
import Sprint4_quizkamp_server.Server.Actions.ShowQuestionAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Game {
    
    public Player player1;
    public Player player2;
    
    private Question[] currentQuestions;
    
    int numQuestions, numRounds;
    
    public boolean isFull() {
        return player1 != null && player2 != null;
    }
    
    public void startGame() {
        //Läser in properties för antal rundor/frågor
        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src/Sprint4_quizkamp_server/Server/GameProperties.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        numQuestions = Integer.parseInt(p.getProperty("numberOfQuestions", "2"));
        numRounds = Integer.parseInt(p.getProperty("numberOfRounds", "2"));
        System.out.println(numQuestions + " " + numRounds);

        System.out.println("SERVER: startar game");
        
        showCategoriesSend(player1);
    }
    
    public void messageRecivedFromPlayer(Object message, Player player) 
    {
        System.out.println("SERVER tagit emot meddelande/objekt: " + message + " FROM " + player);

        if (message instanceof ShowCategoriesAction)
            showCategoriesReceived((ShowCategoriesAction)message, player); 
    }
    
    private void showCategoriesReceived(ShowCategoriesAction data, Player player)
    {
        ShowQuestionAction q = new ShowQuestionAction();
        currentQuestions = QuestionsHandler.GetQuestions(QuestionsHandler.GetCategoryNum(data.chosenCategory), numQuestions, true);
        q.question = currentQuestions[q.questionNumber];
        
        Server.sendObject(player, q);
    }
    
    private void showCategoriesSend(Player p)
    {
        ArrayList<Category> categories = QuestionsHandler.getCategories();
        ShowCategoriesAction c = new ShowCategoriesAction();
        c.categories = new ArrayList();
        
        categories.forEach((temp) -> {
            c.categories.add(temp.name);
        });
        
        Server.sendObject(p, c);
    }
}
