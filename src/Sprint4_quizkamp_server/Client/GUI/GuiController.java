package Sprint4_quizkamp_server.Client.GUI;

import Sprint4_quizkamp_server.Client.GUI.Panels.CategoryPanel;
import Sprint4_quizkamp_server.Client.GUI.Panels.QuestionPanel;
import Sprint4_quizkamp_server.Client.GUI.Panels.ResultPanel;

public class GuiController {

    public static GuiWindow gui = new GuiWindow();

    public static void init() {
        gui.init();
    }

    public static void ShowNameWindow() {
        //Behövs inte?
    }

    public static void ShowCategoriesWindow() {
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new CategoryPanel());
        gui.revalidate();
        gui.repaint();
    }

    public static void ShowQuestionWindow() {
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new QuestionPanel());
        gui.revalidate();
        gui.repaint();
    }

    public static void ShowResultWindow() {
        gui.getContentPane().removeAll();
        gui.getContentPane().add(new ResultPanel());
        gui.revalidate();
        gui.repaint();
    }

}
