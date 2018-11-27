package Sprint4_quizkamp_server.Client;
import Sprint4_quizkamp_server.Client.GUI.GuiController;
import Sprint4_quizkamp_server.Server.Actions.*;
import Sprint4_quizkamp_server.Server.Question;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        GuiController.init();

        Client.Init();
//        Thread.sleep(2000);
//        ShowQuestionAction action = new ShowQuestionAction();
//        String[] answers = {"William", "Erik", "Waldo", "Xin"};
//        action.question = new Question("Vad heter jag?", answers);
//        GuiController.ShowQuestionWindow(action);
    }
    


}
