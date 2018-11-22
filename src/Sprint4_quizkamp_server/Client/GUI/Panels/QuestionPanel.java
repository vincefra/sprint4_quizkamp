package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    private JLabel questionLabel = new JLabel("Frågan lyder:");

    public QuestionPanel() {
        setLayout(new BorderLayout());
        questionLabel.setFont(new Font("Serif", Font.BOLD, 38));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.CENTER);
    }

}