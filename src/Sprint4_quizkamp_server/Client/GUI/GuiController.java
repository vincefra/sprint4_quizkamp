package Sprint4_quizkamp_server.Client.GUI;

import Sprint4_quizkamp_server.Client.GUI.Panels.*;
import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;
import Sprint4_quizkamp_server.Server.Actions.ShowQuestionAction;
import Sprint4_quizkamp_server.Server.Actions.ShowResultAction;
import Sprint4_quizkamp_server.Server.Actions.ShowWaitingAction;

public class GuiController {

    public static GuiWindow gui = new GuiWindow();

    public static void init() {
        gui.init();
    }

    public static void ShowNameWindow() {
        //Behövs inte?
    }

    public static void ShowCategoriesWindow(ShowCategoriesAction action) { //Skickar in objektet i denna metod
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new CategoryPanel(action)); //Objektet skickas sedan in som parameter i konstruktorn, för att datan ska nå knappar etc?
        gui.revalidate();
        gui.repaint();
    }

    public static void ShowWaitingWindow(ShowWaitingAction action) { //Skickar in objektet i denna metod
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new WaitingPanel()); //Objektet skickas sedan in som parameter i konstruktorn, för att datan ska nå knappar etc?
        gui.revalidate();
        gui.repaint();
    }

    public static void ShowQuestionWindow(ShowQuestionAction action) {
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new QuestionPanel(action));
        gui.revalidate();
        gui.repaint();
    }

    public static void ShowResultWindow(ShowResultAction action) {
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new ResultPanel(action));
        gui.revalidate();
        gui.repaint();
    }

}
