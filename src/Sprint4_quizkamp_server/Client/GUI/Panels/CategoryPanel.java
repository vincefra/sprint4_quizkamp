package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoryPanel extends JPanel implements ActionListener {

    private JLabel categoryLabel = new JLabel("Välj en kategori:");
    private JPanel categoryPanel = new JPanel();
    private JButton cat1Button = new JButton("Djur");
    private JButton cat2Button = new JButton("Mat");
    private JButton cat3Button = new JButton("Politik");


    public CategoryPanel() {
        setBackground(new Color(0, 0, 55));
        setLayout(new BorderLayout());
        categoryLabel.setFont(new Font("Serif", Font.BOLD, 38));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setForeground(new Color(255, 255, 255));
        add(categoryLabel, BorderLayout.CENTER);

        cat1Button.setPreferredSize(new Dimension(160, 50));
        cat2Button.setPreferredSize(new Dimension(160, 50));
        cat3Button.setPreferredSize(new Dimension(160, 50));

        cat1Button.addActionListener(this::actionPerformed);
        cat2Button.addActionListener(this::actionPerformed);
        cat3Button.addActionListener(this::actionPerformed);

//        cat1Button.setText((String) cats.get(0));
//        cat2Button.setText((String) cats.get(1));
//        cat3Button.setText((String) cats.get(2));

        categoryPanel.setLayout(new FlowLayout());
        categoryPanel.add(cat1Button);
        categoryPanel.add(cat2Button);
        categoryPanel.add(cat3Button);
        add(categoryPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String pickedCategory = clickedButton.getText();
        //Lägg till pickedCategory i objektet
        //Skicka objektet till server
        System.out.println(pickedCategory);
    }
}
