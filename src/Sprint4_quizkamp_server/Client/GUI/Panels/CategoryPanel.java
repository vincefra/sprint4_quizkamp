package Sprint4_quizkamp_server.Client.GUI.Panels;

import Sprint4_quizkamp_server.Client.Client;
import Sprint4_quizkamp_server.Server.Actions.ShowCategoriesAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryPanel extends JPanel implements ActionListener {

    private JLabel categoryLabel = new JLabel("Välj en kategori:");
    private JPanel categoryPanel = new JPanel();
    private JButton cat1Button = new MyJButton("Djur",MyJButton.pink);
    private JButton cat2Button = new MyJButton("Mat", MyJButton.pink);
    private JButton cat3Button = new MyJButton("Politik",MyJButton.pink);
    private ShowCategoriesAction action; 
    private Font font= new Font("SansSerif", Font.BOLD, 28);
    private Icon backIcon= new ImageIcon("src/background.jpg");


    public CategoryPanel(ShowCategoriesAction action) {
        this.action = action;
        setBackground(new Color(0, 0, 55));
        
        categoryLabel.setFont(font);
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setIcon(backIcon);

        cat1Button.addActionListener(this::actionPerformed);
        cat2Button.addActionListener(this::actionPerformed);
        cat3Button.addActionListener(this::actionPerformed);

        cat1Button.setText(action.categories.get(0));
        cat2Button.setText(action.categories.get(1));
        cat3Button.setText(action.categories.get(2));
        categoryPanel.setLayout(new GridLayout(3,1,3,5));
        add(categoryLabel,BorderLayout.NORTH);
        
        categoryPanel.add(cat1Button);
        categoryPanel.add(cat2Button);
        categoryPanel.add(cat3Button);
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
