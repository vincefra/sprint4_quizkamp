package Sprint4_quizkamp_server.Client.GUI.Panels;

import Sprint4_quizkamp_server.Client.Client;
import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel implements ActionListener {

    private JLabel categoryLabel = new JLabel("Välj en kategori:");
    private JPanel categoryPanel = new JPanel();
    private JButton cat1Button = new MyJButton("Djur", MyJButton.sky);
    private JButton cat2Button = new MyJButton("Mat", MyJButton.sky);
    private JButton cat3Button = new MyJButton("Politik", MyJButton.sky);
    private ShowCategoriesAction action;
    private Font font = new Font("SansSerif", Font.BOLD, 28);
    private Icon backIcon = new ImageIcon("src/background.jpg");
    private Color color = new Color(96, 165, 191);


    public CategoryPanel(ShowCategoriesAction action) {
        this.action = action;
        setBackground(new Color(0, 0, 55));
        setLayout(new BorderLayout());

        categoryLabel.setPreferredSize(new Dimension(500, 400));
        categoryLabel.setIcon(backIcon);
        categoryLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        categoryLabel.setForeground(Color.YELLOW);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setHorizontalTextPosition(JLabel.CENTER);
        categoryLabel.setVerticalTextPosition(JLabel.CENTER);
        add(categoryLabel, BorderLayout.NORTH);

        cat1Button.addActionListener(this::actionPerformed);
        cat2Button.addActionListener(this::actionPerformed);
        cat3Button.addActionListener(this::actionPerformed);


        cat1Button.setText(action.categories.get(0));
        cat2Button.setText(action.categories.get(1));
        cat3Button.setText(action.categories.get(2));
        categoryPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 1;
        gbc.gridx = 3;

        categoryPanel.add(cat1Button, gbc);
        categoryPanel.add(cat2Button, gbc);
        categoryPanel.add(cat3Button, gbc);

        categoryPanel.setBackground(color);
        add(categoryPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String pickedCategory = clickedButton.getText();
        action.chosenCategory = clickedButton.getText();
        Client.sendToServer(action);
        System.out.println(pickedCategory);
    }
}
