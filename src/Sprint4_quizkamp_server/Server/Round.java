package Sprint4_quizkamp_server.Server;

import java.io.UncheckedIOException;
import java.util.ArrayList;

public class Round {
    public Question[] questions;
    public int player1Points = 0;
    public int player2Points = 0;
    public int questionsAnsweredByPlayer1 = 0;
    public int questionsAnsweredByPlayer2 = 0;
    public Player playerThatStartedRound = null;
    
    public int getTotalNumberOfAnswersRecived() {
        return questionsAnsweredByPlayer1 + questionsAnsweredByPlayer2;
    }
    
    public int totalQuestions() {
        return questions.length;
    }
    
    public boolean isRoundOver() {
        return getTotalNumberOfAnswersRecived() == totalQuestions() * 2;
    }
    
    public int currentQuestionPlayer1() {
        return questionsAnsweredByPlayer1;
    }
    
    public int currentQuestionPlayer2() {
        return questionsAnsweredByPlayer2;
    }
    
    public Question getNextQuestionForPlayer(Player player, Game game) {
        if (player == game.player1)
            return questions[currentQuestionPlayer1()];
        else if (player == game.player2)
            return questions[currentQuestionPlayer2()];
        else
            return null; // Allvarligt fel om vi kommit hit ;)
    }
    
    
}
