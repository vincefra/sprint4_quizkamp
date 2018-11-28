package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Server.Actions.*;
import Sprint4_quizkamp_server.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Properties;

public class Game {
    
    public Player player1 = new Player(this);
    public Player player2 = new Player(this);
    
    private QuestionBox questionBox;
    private ArrayList<Round> rounds;
    private int numQuestions;
    private int numRounds;
    private Player currentPlayer; // Vem som spelar just nu.
    private boolean started = false;
    
    
    public boolean isFull() {
        return player1.isConnected() && player2.isConnected();
    }
    
    public void startGame() {
        started = true;
        
        //Läser in properties för antal rundor/frågor
        Properties p = new Properties();

        try {
            p.load(new FileInputStream(Locations.settingsPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        numQuestions = Integer.parseInt(p.getProperty("numberOfQuestions", "2"));
        numRounds = Integer.parseInt(p.getProperty("numberOfRounds", "2"));
        System.out.println(numQuestions + " " + numRounds);

        System.out.println("SERVER: startar game");
        
        rounds = new ArrayList<>();
        questionBox = new QuestionBox();
        
        Player startingPlayer = null;
        if (player1.isConnected())
            startingPlayer = player1;
        else
            startingPlayer = player2;
        
        startNextRound(startingPlayer);
    }
    
    private void startNextRound(Player playerToStart) {
        showCategoriesSend(playerToStart);
    }
    
    public void messageRecivedFromPlayer(Object message, Player player) 
    {
        System.out.println("SERVER tagit emot meddelande/objekt: " + message + " FROM " + player);
        if (message instanceof NameAction) {
            // Här har vi nu fått in spelarens namn.
            
            // Spara spelarens namn.
            player.name = ((NameAction)message).name;
            
            // Om vi inte har startat så ska vi starta spelet.
            if (!started) {
                // Starta spelet.
                startGame();
            }
            else {
                // Nu har spelare nummer 2 kommit in.
                if (currentPlayer == null) {
                    // Vår tur att spela. Spelare 1 har väntat på att vi ska connecta.
                    opponentsTurn(player);
                }
                else {
                    // Vänta på den andra spelaren.
                    // Visa väntaskärmen.
                    showWaitingScreen(player);
                }
            }
            
        }
        if (message instanceof ShowCategoriesAction)
            showCategoriesReceived((ShowCategoriesAction)message, player); 
        else if (message instanceof ShowQuestionAction)
            showQuestionsReceived((ShowQuestionAction)message, player);
    }
    
    private void showWaitingScreen(Player p) {
        ShowWaitingAction action = new ShowWaitingAction();
        Server.sendObject(p, action);
    }
    
    private void showQuestionsReceived(ShowQuestionAction data, Player player)
    {
        if (data == null) return;
    
        if (player == player1) {
            getCurrentRound().questionsAnsweredByPlayer1++;
        } else if (player == player2) {
            getCurrentRound().questionsAnsweredByPlayer2++;
        }
        
        // Kolla om vi haft rätt.
        if (data.question.getCorrectAnswer().equalsIgnoreCase(data.pickedAnswer)) {
            // Spelaren har haft rätt.
            if (player == player1)
                getCurrentRound().player1Points++;
            else if (player == player2)
                getCurrentRound().player2Points++;
        }
    
        // Kolla om rundan är över för just denna spelaren.
        if (currentPlayer == player1
                && getCurrentRound().questionsAnsweredByPlayer1 == getCurrentRound().totalQuestions()) {
            // Rundan är över för player1.
            onRoundCompleteByPlayer(player1);
        } else if (currentPlayer == player2
                && getCurrentRound().questionsAnsweredByPlayer2 == getCurrentRound().totalQuestions()) {
            // Rundan är över för player2.
            onRoundCompleteByPlayer(player2);
        }
        else {
            // Rundan ska fortsätta för den nuvarande spelaren.
            showNextQuestionForPlayer(player);
        }
    }
    
    private void showNextQuestionForPlayer(Player player) {
        currentPlayer = player;
        ShowQuestionAction action = new ShowQuestionAction();
        action.question = getCurrentRound().getNextQuestionForPlayer(player, this);
        
        Server.sendObject(player, action);
    }
    
    //Ta emot vald kategori från klient
    private void showCategoriesReceived(ShowCategoriesAction data, Player player)
    {
        // Skapa en runda.
        rounds.add(new Round());
        getCurrentRound().playerThatStartedRound = player;
        getCurrentRound().questions = questionBox.getQuestions(data.chosenCategory, numQuestions, true);
        
        showNextQuestionForPlayer(player);
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
        return rounds.get(rounds.size() - 1);
    }
    
    private int getPlayerTotalPoints(Player p) {
        if (p == player1) return getPlayer1TotalPoints();
        else
            return getPlayer2TotalPoints();
    }
    
    private int getPlayer1TotalPoints() {
        int total = 0;
        for (int i = 0; i < rounds.size(); i++) {
            total += rounds.get(i).player1Points;
        }
        
        return total;
    }
    
    private int getPlayer2TotalPoints() {
        int total = 0;
        for (int i = 0; i < rounds.size(); i++) {
            total += rounds.get(i).player2Points;
        }
        
        return total;
    }
    
    private Player getOtherPlayer(Player p) {
        if (p == player1)
            return player2;
        else
            return player1;
    }
    
    // Denna kallas på när en spelare har spelat klart sin del av rundan och
    // turen ska övergå till motståndaren.
    private void opponentsTurn(Player playerThatWillPlay) {
        // Vi ska bara starta nästa runda om den spelaren har connectat.
        if (playerThatWillPlay.isConnected()) {
            showNextQuestionForPlayer(playerThatWillPlay);
        }
        else {
            // Då får vi invänta motståndaren.
            currentPlayer = null;
        }
    }
    
    private void showResultWindowForBoth() {
        ShowResultAction action = new ShowResultAction();
        for (int i = 0; i < rounds.size(); i++) {
            action.player1.put(i, rounds.get(i).player1Points);
            action.player2.put(i, rounds.get(i).player2Points);
        }
        action.name1 = player1.name;
        action.name2 = player2.name;
        action.rondNum = rounds.size();
        
        Server.sendObject(player1, action);
        Server.sendObject(player2, action);
    }
    
    // Kallas på när någon av spelarna har svarat på alla frågor i en round.
    private void onRoundCompleteByPlayer(Player p) {
    
        System.out.println("Rundan för " + p.name + " är slut.");
        System.out.println("Poäng för " + p.name + ": " + getPlayerTotalPoints(p));
        
        // Kolla om både player 1 och player 2 har spelat klart.
        if (getCurrentRound().isRoundOver()) {
            // Ja, denna rundan är färdigspelad för båda spelarna.
            showResultWindowForBoth();
            try
            {
                Thread.sleep(10000);
            } catch (Exception e) {}
            
            // Kolla om spelet är slut.
            if (rounds.size() == numRounds) {
                // Spelet är slut.
                System.out.println("Spelet är slut.");
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (Exception e) { e.printStackTrace(); }
                
            }
            else {
                // Spelet fortsätter med nästa runda.
                // Kolla vem som ska starta nästa runda.
                Player playerToStartNextRound = getOtherPlayer(getCurrentRound().playerThatStartedRound);
                startNextRound(playerToStartNextRound);
                // Se till att väntskärmen visas för den andra spelaren.
                showWaitingScreen(getOtherPlayer(playerToStartNextRound));
            }
        }
        else {
            // Turen för rundan ska gå till den andra spelaren.
            // Visa väntskärmen för denna spelaren.
            showWaitingScreen(currentPlayer);
            Player otherPlayer = getOtherPlayer(currentPlayer); // Hämtar motståndaren.
            opponentsTurn(otherPlayer);
        }
        
        
    }
}
