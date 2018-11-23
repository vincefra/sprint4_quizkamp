package Sprint4_quizkamp_server.Client;
import Sprint4_quizkamp_server.Client.GUI.GuiController;
import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;
import Sprint4_quizkamp_server.Server.Actions.ShowQuestionAction;
import Sprint4_quizkamp_server.Server.Question;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Client.Init();
        GuiController.init();
        Thread.sleep(2000);
        ShowQuestionAction action = new ShowQuestionAction();
        String[] answers = {"William", "Erik", "Waldo", "Xin"};
        action.question = new Question("Vad heter jag?", answers);
        GuiController.ShowQuestionWindow(action);
    }
    


}
