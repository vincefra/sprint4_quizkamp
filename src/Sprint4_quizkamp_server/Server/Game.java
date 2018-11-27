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
    private Player startingPlayer; // Vem det är som startar rundan.
    private Player currentPlayer; // Vem som spelar just nu.
    
    public boolean isFull() {
        return player1 != null && player2 != null;
    }
    
    public void startGame() {
        //Läser in properties för antal rundor/frågor
        Properties p = new Properties();

        try {
            p.load(new FileInputStream("C:\\Users\\Asd\\Dropbox\\Nackademin\\Kurs Objektorienterad programmering och Java\\Quizkampen\\sprint4_quizkamp\\src\\Sprint4_quizkamp_server\\Server\\GameProperties.properties"));
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
        startingPlayer = player1;
        
        startNextRound();
    }
    
    private void startNextRound() {
        player1.currentQuestionIndex = 0;
        player2.currentQuestionIndex = 0;
        showCategoriesSend(startingPlayer);
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
        if (data == null) return;
    
        getCurrentRound().totalNumberOfAnswersRecived++;
    
        // Kolla om vi haft rätt.
        if (data.question.getCorrectAnswer().equalsIgnoreCase(data.pickedAnswer)) {
            // Spelaren har haft rätt.
            if (player == player1)
                getCurrentRound().player1Points++;
            else
                getCurrentRound().player2Points++;
        }
    
        // Kolla om rundan är över.
        if (getCurrentRound().totalNumberOfAnswersRecived == numQuestions * 2) {
            // Rundan är över.
            System.out.println("yay, skicka resultat!");
            currentRound++;
        
            //skicka resultatfönster till p1 och p2
            // Kolla om spelet är slut.
            if (currentRound >= numRounds) {
                // Spelet är slut.
            }
            else {
                // Spelet fortsätter med nästa runda.
                // Kolla vem som ska starta nästa runda.
                startingPlayer = getOtherPlayer(startingPlayer);
            
                startNextRound();
            }
        }
        else {
            // Rundan är inte över.
            // Kolla om spelet ska gå över till andra spelaren.
            if (getCurrentRound().totalNumberOfAnswersRecived == numQuestions) {
            
            }
            else {
                // Spelet ska fortsätta för denna spelaren.
                ShowQuestionAction action = new ShowQuestionAction();
                action.question = getCurrentRound().questions[currentPlayer.currentQuestionIndex];
                showQuestionsSend(action, currentPlayer);
            }
            
        }
    
        data.questionNumber = 0;
        player = player.game.player2;
        showQuestionsSend(data, player);
    }
    
    private void showQuestionsSend(ShowQuestionAction data, Player player)
    {
        currentPlayer = player;
        ShowQuestionAction action = data;
        action.question = getCurrentRound().questions[action.questionNumber];
        
        Server.sendObject(player, action);
        currentPlayer.currentQuestionIndex++;
    }
    
    //Ta emot vald kategori från klient
    private void showCategoriesReceived(ShowCategoriesAction data, Player player)
    {
        // Skapa en runda.
        rounds[currentRound] = new Round();
        rounds[currentRound].questions = questionBox.getQuestions(data.chosenCategory, numQuestions, true);
        
        
        ShowQuestionAction q = new ShowQuestionAction();
        q.question = rounds[currentRound].questions[0];
        
        Server.sendObject(player, q);
    }
    
    //Skicka kategorier till klient
    private void showCategoriesSend(Player p)
    {
        currentPlayer = p;
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
    
    private int getPlayer1TotalPoints() {
        int total = 0;
        for (int i = 0; i < rounds.length; i++) {
            total += rounds[i].player1Points;
        }
        
        return total;
    }
    
    private int getPlayer2TotalPoints() {
        int total = 0;
        for (int i = 0; i < rounds.length; i++) {
            total += rounds[i].player2Points;
        }
        
        return total;
    }
    
    private Player getOtherPlayer(Player p) {
        if (p == player1)
            return player2;
        else
            return player1;
    }
}
