package Sprint4_quizkamp_server.Client.GUI.Panels;

import Sprint4_quizkamp_server.Server.Actions.ShowResultAction;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private int rondNum;
    private int LabelSize;
    private JLabel[] resultLabels;
    private JPanel recordPanel = new JPanel();
    private Font font1 = new Font("SansSerif", Font.PLAIN, 20);
    private Font font2 = new Font("SansSerif", Font.BOLD, 30);
    private Icon backIcon = new ImageIcon("src/background.jpg");
    private ShowResultAction action;


    public ResultPanel(ShowResultAction action) {
        this.action = action;
        rondNum = this.action.rondNum;
        LabelSize = (rondNum + 1) * 3;
        resultLabels = new JLabel[LabelSize];
        System.out.println("LabelSize: " + LabelSize);
        System.out.println("resultLabels: " + resultLabels.length);

        setLayout(new BorderLayout());
        add(recordPanel, BorderLayout.CENTER);
        recordPanel.setLayout(new GridLayout((rondNum + 1), 3, 4, 4));
        recordPanel.setBackground(new Color(96, 165, 191));

        for (int i = 0; i < LabelSize; i++) {
            resultLabels[i] = new JLabel();
            setLabelStyle(resultLabels[i], font1, Color.WHITE, JLabel.CENTER);
            recordPanel.add(resultLabels[i]);

            if (i == 0) {
                resultLabels[i].setText(this.action.name1);
            } else if (i == 2) {
                resultLabels[i].setText(this.action.name2);
            } else if (i == 1) {
                resultLabels[i].setText("RESULT");
                resultLabels[i].setIcon(backIcon);
                setLabelStyle(resultLabels[i], font2, Color.yellow, JLabel.CENTER);
                resultLabels[i].setHorizontalTextPosition(JLabel.CENTER);
                resultLabels[i].setVerticalTextPosition(JLabel.CENTER);
            } else if (i % 3 == 1) {
                resultLabels[i].setText("ROND " + (i / 3));
                setLabelStyle(resultLabels[i], font1, Color.yellow, JLabel.CENTER);

            } else if (i % 3 == 0) {
                if (this.action.player1.containsKey((i / 3) - 1))
                    resultLabels[i].setText(Integer.toString(this.action.player1.get((i / 3) - 1)));
            } else {
                System.out.print("- ");
                System.out.println((i / 3) - 1);
                if (this.action.player1.containsKey((i / 3) - 1))
                    resultLabels[i].setText(Integer.toString(this.action.player2.get((i / 3) - 1)));
            }
        }

    }

    public void setLabelStyle(JLabel label, Font font, Color color, int alignment) {
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);
    }

}