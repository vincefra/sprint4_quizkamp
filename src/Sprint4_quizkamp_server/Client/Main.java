package Sprint4_quizkamp_server.Client;
import Sprint4_quizkamp_server.Client.GUI.GuiController;
import Sprint4_quizkamp_server.Server.Actions.*;
import Sprint4_quizkamp_server.Server.Question;


public class Main {
    


    public static void main(String[] args) throws InterruptedException {
        GuiController.init();
        Client.Init();
        /*
        ShowResultAction action = new ShowResultAction();
        action.player1.put(0, 1);
        action.player2.put(0, 2);
    
        action.player1.put(1, 1);
        action.player2.put(1, 2);
        action.rondNum = 2;
        action.name1 = "adam";
        action.name2 = "markus";
        
        
        GuiController.ShowResultWindow(action);
        */
        
        
        
//        Thread.sleep(2000);
//        ShowQuestionAction action = new ShowQuestionAction();
//        String[] answers = {"William", "Erik", "Waldo", "Xin"};
//        action.question = new Question("Vad heter jag?", answers);
//        GuiController.ShowQuestionWindow(action);
    }
    


}
