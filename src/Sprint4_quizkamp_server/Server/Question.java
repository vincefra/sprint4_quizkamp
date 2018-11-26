package Sprint4_quizkamp_server.Server;

import Sprint4_quizkamp_server.Sprint4_quizkamp_server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable {
    
    private String question;
    private String[] answers;
    private static final long serialVersionUID = 1l;
    
    public String getQuestion() {
        return question;
    }
    
    public String getCorrectAnswer() {
        return answers[0];
    }
    
    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }
    
    public String[] getAnswers(boolean randomize) {
        ArrayList<String> temp1 = new ArrayList<>();
        Collections.addAll(temp1, answers);
        String[] returnArray = new String[answers.length];
    
        for (int i = 0; i < answers.length; i++) {
            int index;
            if (randomize) {
                index = Sprint4_quizkamp_server.GetRandomNum(0, temp1.size());
            }
            else {
                index = i;
            }
            
            returnArray[i] = temp1.get(index);
            // Ta bort från temp1 så vi inte får samma svar igen.
            temp1.remove(index);
        }
        
        return returnArray;
    }
}
