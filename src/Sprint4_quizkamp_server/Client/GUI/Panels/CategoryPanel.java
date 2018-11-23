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


    public CategoryPanel(List cats) {
//        Category categoryObject = (Category) o;
        setLayout(new BorderLayout());
        categoryLabel.setFont(new Font("Serif", Font.BOLD, 38));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(categoryLabel, BorderLayout.PAGE_START);
        cat1Button.addActionListener(this::actionPerformed);
        cat2Button.addActionListener(this::actionPerformed);
        cat3Button.addActionListener(this::actionPerformed);
        cat1Button.setText((String) cats.get(0));
        cat2Button.setText((String) cats.get(1));
        cat3Button.setText((String) cats.get(2));

        categoryPanel.setLayout(new GridLayout());
        categoryPanel.add(cat1Button);
        categoryPanel.add(cat2Button);
        categoryPanel.add(cat3Button);
        add(categoryPanel, BorderLayout.CENTER);
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
