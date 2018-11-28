package Sprint4_quizkamp_server.Client;
import Sprint4_quizkamp_server.Client.GUI.GuiController;
import Sprint4_quizkamp_server.Server.Actions.*;
import Sprint4_quizkamp_server.Server.Question;

import java.util.HashMap;


public class Main {



    public static void main(String[] args) throws InterruptedException {
        GuiController.init();
//        ShowResultAction action = new ShowResultAction();
//        action.rondNum = 2;
//        action.name1 = "player 1";
//        action.name2 = "player 2";
//
//        HashMap<Integer,Integer> player1 = new HashMap<>();
//        HashMap<Integer,Integer> player2 = new HashMap<>();
//        player1.put(0, 2);
//        player1.put(1, 2);
//        player2.put(0, 0);
//        player2.put(1, 0);
//        action.player1 = player1;
//        action.player2 = player2;

//        GuiController.ShowResultWindow(action);
        Client.Init();
//        Thread.sleep(2000);
//        ShowQuestionAction action = new ShowQuestionAction();
//        String[] answers = {"William", "Erik", "Waldo", "Xin"};
//        action.question = new Question("Vad heter jag?", answers);
//        GuiController.ShowQuestionWindow(action);
    }
    


}
