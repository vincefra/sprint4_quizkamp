package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JLabel resultLabel = new JLabel("Frågan lyder:");

    public ResultPanel() {
        setLayout(new BorderLayout());
        resultLabel.setFont(new Font("Serif", Font.BOLD, 38));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, BorderLayout.CENTER);
    }

}