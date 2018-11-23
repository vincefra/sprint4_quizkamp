package Sprint4_quizkamp_server.Client;

import Sprint4_quizkamp_server.Client.GUI.GuiController;

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Client.Init();
        GuiController.init();
        Thread.sleep(2000);
        GuiController.ShowCategoriesWindow();

    }
}
