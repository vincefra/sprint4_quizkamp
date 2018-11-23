package Sprint4_quizkamp_server;


import Sprint4_quizkamp_server.Server.Question;
import Sprint4_quizkamp_server.Server.QuestionsHandler;

import java.util.Random;

public class Sprint4_quizkamp_server {
    
    private static Random randomObject = new Random();
    
    public static void main(String[] args) {
        
    }
    
    private static void WriteQuestions(Question[] questions) {
        for (Question q : questions) {
            String[] answers = q.getAnswers(true);
            
            System.out.println("Fråga: " + q.getQuestion());
            System.out.println("Alternativ 1: " + answers[0]);
            System.out.println("Alternativ 2: " + answers[1]);
            System.out.println("Alternativ 3: " + answers[2]);
            System.out.println("Alternativ 4: " + answers[3]);
            System.out.println("Rätt svar är: " + q.getCorrectAnswer());
        }
    }
    
    // Min är inklusive. Max är exklusive.
    public static int GetRandomNum(int min, int max) {
        return randomObject.nextInt(max - min) + min;
    }
    
}
