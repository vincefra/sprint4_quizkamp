package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class WaitingPanel extends JPanel {
    private JLabel waitingLabel = new JLabel("Väntar på motståndare");

    public WaitingPanel() {
        waitingLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        waitingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        waitingLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(waitingLabel);
    }
}
