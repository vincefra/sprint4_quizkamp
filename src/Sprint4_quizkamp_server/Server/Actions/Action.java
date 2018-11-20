package Sprint4_quizkamp_server.Server.Actions;

import Sprint4_quizkamp_server.Server.Player;
import Sprint4_quizkamp_server.Server.Question;

import java.util.ArrayList;

public class Action {

}

class ShowCategoriesAction {
    public ArrayList<String> categories;
    public int chosenCategory;
}

class ShowQuestionAction {
    public Question question;
    public String pickedAnswer;
}

class ShowResultAction {
    public String pickedAnswer;
    public Player thisPlayer;
    public Player opponent;
}
