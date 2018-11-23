package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionPanel extends JPanel implements ActionListener {

    private JPanel answerPanel = new JPanel();
    private JLabel questionLabel = new JLabel("Var är Waldo?Var är Waldo?Var är Waldo?");
    private JButton answer1Button = new JButton("Svar 1");
    private JButton answer2Button = new JButton("Svar 2");
    private JButton answer3Button = new JButton("Svar 3");
    private JButton answer4Button = new JButton("Svar 4");

    public QuestionPanel() {
        setBackground(new Color(0, 0, 55));
        setLayout(new BorderLayout());
        questionLabel.setFont(new Font("Serif", Font.BOLD, 28));
        questionLabel.setForeground(new Color(255, 255, 255));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(questionLabel, BorderLayout.CENTER);
        answer1Button.setPreferredSize(new Dimension(160, 50));
        answer2Button.setPreferredSize(new Dimension(160, 50));
        answer3Button.setPreferredSize(new Dimension(160, 50));
        answer4Button.setPreferredSize(new Dimension(160, 50));
        answer1Button.addActionListener(this::actionPerformed);
        answer2Button.addActionListener(this::actionPerformed);
        answer3Button.addActionListener(this::actionPerformed);
        answer4Button.addActionListener(this::actionPerformed);
        answerPanel.setLayout(new FlowLayout());
        answerPanel.add(answer1Button);
        answerPanel.add(answer2Button);
        answerPanel.add(answer3Button);
        answerPanel.add(answer4Button);
        add(answerPanel, BorderLayout.PAGE_END);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String pickedAnswer = clickedButton.getText();
        //Lägg till pickedAnswer i objektet
        //Skicka objektet till server
        System.out.println(pickedAnswer);
    }
}