/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprint4_quizkamp_server.Server;

/**
 *
 * @author work
 */
public class TcpHandler 
{
    public static void handleIncoming(Object data)
    {
        switch (data.split("-")[0])
        {
            case "CHAT":
            {
                if (opponent != null)
                {
                    opponent.sendData(inputLine.split("-")[1]);
                    //opponent.sendToOpponent.writeObject("Player" + this.mark + " skickade följande: " + inputLine.split("-")[1]);
                    System.out.println("Player" + this.mark + " skickade följande: " + inputLine.split("-")[1]);
                    continue;
                }
            }

            case "START":
            {
                if (opponent != null)
                {
                    //SKAPA nytt game-object
                    //SKICKA kategori till playert 1 game.generatecategory()
                    //SKICKA vänta till player 2 game.wait()
                }
            }

            case "CATEGORY":
            {
                //SKICKA game.generatequestions(int catid) till player 1 i sträng
                //
            }

            case "ANSWER":
            {
                //kolla game, vilken stage vi är på i GAME
                //skicka nästa fråga samt svar
            }

            case "SHUTDOWN":
            {
                System.exit(0);
            }

            default:
                System.out.println(inputLine);
        }
    }
}
