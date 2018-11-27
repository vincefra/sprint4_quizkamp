package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class WaitingPanel extends JPanel {
    private JLabel waitingLabel = new JLabel("Väntar på motståndare");
    private Icon backIcon= new ImageIcon("src/background.jpg");

    public WaitingPanel() {
        waitingLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        waitingLabel.setForeground(Color.YELLOW);
        waitingLabel.setIcon(backIcon);
        waitingLabel.setHorizontalTextPosition(JLabel.CENTER);
        waitingLabel.setVerticalTextPosition(JLabel.CENTER);
        add(waitingLabel);
    }
}
