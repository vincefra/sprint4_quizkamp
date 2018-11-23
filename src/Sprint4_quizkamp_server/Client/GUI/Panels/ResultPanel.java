package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JLabel resultLabel = new JLabel("Resultat");
    private JLabel player1Label = new JLabel("Player 1: 2/3");
    private JLabel player2Label = new JLabel("Player 2: 0/3");

    public ResultPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 55));
        resultLabel.setFont(new Font("Serif", Font.BOLD, 48));
        resultLabel.setForeground(new Color(255, 255, 255));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        player1Label.setFont(new Font("Serif", Font.BOLD, 28));
        player1Label.setForeground(new Color(255, 255, 255));
        player1Label.setHorizontalAlignment(SwingConstants.CENTER);
        player2Label.setFont(new Font("Serif", Font.BOLD, 28));
        player2Label.setForeground(new Color(255, 255, 255));
        player2Label.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultLabel, BorderLayout.CENTER);
        add(player1Label, BorderLayout.PAGE_START);
        add(player2Label, BorderLayout.PAGE_END);

    }

}