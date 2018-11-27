package Sprint4_quizkamp_server.Server.Actions;

import java.util.HashMap;

public class ShowResultAction extends Action {

    //int score, String namn
    public HashMap<Integer,Integer> player1 = new HashMap<>();  
    public HashMap<Integer,Integer> player2 = new HashMap<>(); 
    public String name1;
    public String name2;
    
    /*
    (0,1) = round 0, 1 score
    (1,1) = round 1, 1 score
    (2,0) = round 2, 0 score
    */
}
