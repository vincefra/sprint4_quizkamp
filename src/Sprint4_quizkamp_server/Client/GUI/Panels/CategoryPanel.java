package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {

    private JLabel categoryLabel = new JLabel("Välj en kategori:");

    public CategoryPanel() {
        setLayout(new BorderLayout());
        categoryLabel.setFont(new Font("Serif", Font.BOLD, 38));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(categoryLabel, BorderLayout.CENTER);
    }

}
