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
    
    private QuestionBox questionBox;
    private Round[] rounds;
    private int numQuestions;
    private int numRounds;
    private int currentRound;
    
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
        
        currentRound = 0;
        rounds = new Round[numRounds];
        questionBox = new QuestionBox();
        
        showCategoriesSend(player1);
    }
    
    public void messageRecivedFromPlayer(Object message, Player player) 
    {
        System.out.println("SERVER tagit emot meddelande/objekt: " + message + " FROM " + player);

        if (message instanceof ShowCategoriesAction)
            showCategoriesReceived((ShowCategoriesAction)message, player); 
        else if (message instanceof ShowQuestionAction)
            showQuestionsReceived((ShowQuestionAction)message, player);
    }
    
    private void showQuestionsReceived(ShowQuestionAction data, Player player)
    {
        if (data != null)
        {
            if (data.question.getCorrectAnswer().equalsIgnoreCase(data.pickedAnswer))
                //player.roundScore.add(currentRound, ++1);
            
            //player har svarat på sista frågan
            if (data.questionNumber >= numQuestions)
            {
                //player är player 2, ska till nästa stadie
                if (player == player2)
                {
                    currentRound++;
                    
                    System.out.println("yay, skicka resultat!");
                    //skicka resultatfönster till p1 och p2
                }
                data.questionNumber = 0;
                player = player.game.player2;
            }
            showQuestionsSend(data, player);
        }
    }
    
    private void showQuestionsSend(ShowQuestionAction data, Player player)
    {
        ShowQuestionAction action = data;
        action.question = getCurrentRound().questions[action.questionNumber];
        
        Server.sendObject(player, action);
    }
    
    //Ta emot vald kategori från klient
    private void showCategoriesReceived(ShowCategoriesAction data, Player player)
    {
        // Skapa en runda.
        
        
        
        ShowQuestionAction q = new ShowQuestionAction();
        Question[] currentQuestions = getCurrentRound().questions;
        currentQuestions =
                QuestionsHandler.GetQuestions(QuestionsHandler.GetCategoryNum(data.chosenCategory), numQuestions, true);
        q.question = currentQuestions[q.questionNumber];
        
        Server.sendObject(player, q);
    }
    
    //Skicka kategorier till klient
    private void showCategoriesSend(Player p)
    {
        ArrayList<Category> categories = questionBox.getCategories();
        ShowCategoriesAction c = new ShowCategoriesAction();
        c.categories = new ArrayList();
        
        categories.forEach((temp) -> {
            c.categories.add(temp.name);
        });
        
        Server.sendObject(p, c);
    }
    
    private Round getCurrentRound() {
        return rounds[currentRound];
    }
}
