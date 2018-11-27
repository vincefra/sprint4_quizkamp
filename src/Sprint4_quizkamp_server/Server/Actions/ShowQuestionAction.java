package Sprint4_quizkamp_server.Server.Actions;

import Sprint4_quizkamp_server.Server.Question;

import java.io.Serializable;

public class ShowQuestionAction extends Action {
    public Question question;
    public int questionNumber;
    public String pickedAnswer;
}
