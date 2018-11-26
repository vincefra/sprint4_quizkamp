package Sprint4_quizkamp_server.Server.Actions;

import Sprint4_quizkamp_server.Server.Player;

import java.io.Serializable;

public class ShowResultAction extends Action {
    public String pickedAnswer;
    public Player thisPlayer;
    public Player opponent;
}
