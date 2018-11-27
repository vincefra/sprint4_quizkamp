package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Game {
    
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    
    private Question[] currentQuestions;
    
    int numQuestions, numRounds, currentRound;
    
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
    }
    
    public void messageRecivedFromPlayer(Object message, Player player) throws InterruptedException 
    {
        System.out.println("SERVER tagit emot meddelande/objekt: " + message + " FROM " + player);

        if (message != null && player != null)
        {
            if (message instanceof NameAction)
                showUsernameReceived((NameAction)message, player);
            else if (message instanceof ShowCategoriesAction)
                showCategoriesReceived((ShowCategoriesAction)message, player); 
            else if (message instanceof ShowQuestionAction)
                showQuestionsReceived((ShowQuestionAction)message, player);
        }
    }
    
    /*
    private void swapPlayer(Player player1, Player player2)
    {
        Player temp = player1;
        player1 = player2;
        player2 = temp;
    }*/
    
    private ShowResultAction setResultAction(Game game)
    {
        ShowResultAction a = new ShowResultAction();
        a.name1 = game.player1.name;
        a.name2 = game.player2.name;
        a.player1 = game.player1.roundScore;
        a.player2 = game.player2.roundScore;
        
        return a;
    }
    
    private void setPlayerScore(ShowQuestionAction data, Player player)
    {
        if (!player.roundScore.containsKey(currentRound))
            player.roundScore.put(currentRound, 0);
        
        if (data.question.getCorrectAnswer().equalsIgnoreCase(data.pickedAnswer))
            player.roundScore.put(currentRound, player.roundScore.get(currentRound)+1);
    }
    
    //0,1,2,3 fråga 1
    //0,1,2,3 fråga 2
    
    //index 0 to 1 = 1 round
    
    //1, 2 = 1 rond, 2 right
    //1 , 1 = 1 rond, 1 right
    //
    
    //rond 1
    
    //0,1,2,3 fråga 1
    //0,1,2,3 fråga 2
    
    //rond 2
    
    private void showQuestionsReceived(ShowQuestionAction data, Player player) throws InterruptedException
    {
        //sätt dit score på spelare
        setPlayerScore(data, player);
        
        //player har svarat på sista frågan
        if (data.questionNumber >= numQuestions)
        {
            //player är player 2, ska till nästa stadie
            if (player == player2)
            {
                //runda 1 klar för p1 och p2
                currentRound++;
                
                ShowResultAction a = setResultAction(this);
                showResultWindowToSend(a, player1);
                showResultWindowToSend(a, player2);
                
                Thread.sleep(5000);
                
                //checka antal spelade ronder också!!!!!
                //swapPlayer(player1, player2);
                
                System.out.println("yay, skicka resultat!");
                //skicka resultatfönster till p1 och p2
            }
            
            data.questionNumber = 0;
            showWaitingWindowToSend(player);
            
            if (player2 == null)
            {
                //skicka att vi väntar på player 2
                return;
            }
            
            player = player.game.player2;
        }
        showQuestionsToSend(data, player);
    }
    
    private void showUsernameReceived(NameAction data, Player player)
    {
        player.name = data.name;
        
        if (player1 == player)
            showCategoriesToSend(player);
        else
            //visa player 2 att vänta
            showWaitingWindowToSend(player);
    }
    
    private void showWaitingWindowToSend(Player player)
    {
        Server.sendObject(player, new ShowWaitingAction());
    }
    
    private void showQuestionsToSend(ShowQuestionAction data, Player player)
    {
        System.out.println(data.questionNumber);
        ShowQuestionAction action = data;
        action.question = currentQuestions[action.questionNumber];
        
        Server.sendObject(player, action);
    }
    
    //Ta emot vald kategori från klient
    private void showCategoriesReceived(ShowCategoriesAction data, Player player)
    {
        ShowQuestionAction q = new ShowQuestionAction();
        currentQuestions = QuestionsHandler.GetQuestions(QuestionsHandler.GetCategoryNum(data.chosenCategory), numQuestions, true);
        q.question = currentQuestions[q.questionNumber];
        
        Server.sendObject(player, q);
    }
    
    //Skicka kategorier till klient
    private void showCategoriesToSend(Player p)
    {
        ArrayList<Category> categories = QuestionsHandler.getCategories();
        ShowCategoriesAction c = new ShowCategoriesAction();
        c.categories = new ArrayList();
        
        categories.forEach((temp) -> {
            c.categories.add(temp.name);
        });
        
        Server.sendObject(p, c);
    }
    
    private void showResultWindowToSend(ShowResultAction data, Player player)
    {
        ShowResultAction r = data;
        
        Server.sendObject(player, r);
    }
}
