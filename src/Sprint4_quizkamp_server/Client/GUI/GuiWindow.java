package Sprint4_quizkamp_server.Client.GUI;

import Sprint4_quizkamp_server.Client.Client;
import Sprint4_quizkamp_server.Client.GUI.Panels.MyJButton;
import Sprint4_quizkamp_server.Server.Actions.NameAction;

import javax.naming.Name;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiWindow extends JFrame implements ActionListener {

    private JPanel root = new JPanel();
    private JPanel splitBottom = new JPanel();
    private JLabel kampenLabel = new JLabel("Frågekampen");
    private JLabel nameLabel = new JLabel("Ange ditt namn:");
    private JTextField nameTextField = new JTextField();
//    private JButton playButton = new JButton("Börja spela");
    private JButton playButton= new MyJButton("Börja spela", MyJButton.sky);
    private Icon backIcon= new ImageIcon("src/background.jpg");
    private Font font=new Font("SansSerif",Font.PLAIN,25);


    public GuiWindow() throws HeadlessException {

    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 700));
        setMinimumSize(new Dimension(500, 700));
        setMaximumSize(new Dimension(500, 700));
        playButton.addActionListener(this::actionPerformed);
        root.setLayout(new BorderLayout());
        root.setBackground(new Color(0, 0, 55));
        kampenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        kampenLabel.setForeground(Color.yellow);
        kampenLabel.setIcon(backIcon);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        kampenLabel.setFont(new Font("SansSerif", Font.BOLD, 34));
        kampenLabel.setHorizontalTextPosition(JLabel.CENTER);
        kampenLabel.setVerticalTextPosition(JLabel.CENTER);
        splitBottom.setLayout(new GridLayout(0, 3,5,5));
        root.add(kampenLabel, BorderLayout.CENTER);
        nameLabel.setPreferredSize(new Dimension(60, 50));
        nameLabel.setBackground(new Color(96,165,191));
        nameLabel.setOpaque(true);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBackground(new Color(96,165,191));
        nameTextField.setPreferredSize(new Dimension(160, 50));

        splitBottom.add(nameLabel);
        splitBottom.add(nameTextField);
        splitBottom.add(playButton);
        root.add(splitBottom, BorderLayout.PAGE_END);
        add(root);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        Client.Init();
        if (!nameTextField.getText().trim().equalsIgnoreCase("")) {
            System.out.println("Skickar " + nameTextField.getText().trim());
//            Client.sendToServer(new NameAction(nameTextField.getText().trim()));
        } else {
            JOptionPane.showMessageDialog(null,"Ange ett giltigt namn innan du börjar spela.");
        }
    }
}
