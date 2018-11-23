package Sprint4_quizkamp_server.Client.GUI;

import javax.swing.*;
import java.awt.*;

public class GuiWindow extends JFrame {

    private JPanel root = new JPanel();
    private JPanel splitBottom = new JPanel();
    private JLabel kampenLabel = new JLabel("Frågekampen");
    private JLabel nameLabel = new JLabel("Ange ditt namn:");
    private JTextField nameTextField = new JTextField();
    private JButton playButton = new JButton("Börja spela");


    public GuiWindow() throws HeadlessException {

    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        setMinimumSize(new Dimension(700, 500));
        setMaximumSize(new Dimension(700, 500));
        root.setLayout(new BorderLayout());
        root.setBackground(new Color(0, 0, 55));
        kampenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        kampenLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        kampenLabel.setFont(new Font("Serif", Font.BOLD, 38));
        splitBottom.setLayout(new GridLayout(0, 3));
        root.add(kampenLabel, BorderLayout.CENTER);
        splitBottom.add(nameLabel);
        splitBottom.add(nameTextField);
        splitBottom.add(playButton);
        root.add(splitBottom, BorderLayout.PAGE_END);
        add(root);
        pack();
        setVisible(true);
    }

}
