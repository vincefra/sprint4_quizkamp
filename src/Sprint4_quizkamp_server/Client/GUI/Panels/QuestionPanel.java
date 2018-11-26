package Sprint4_quizkamp_server.Client.GUI.Panels;

import Sprint4_quizkamp_server.Client.Client;
import Sprint4_quizkamp_server.Server.Actions.ShowQuestionAction;

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
    private ShowQuestionAction action;
    private String[] answers;

    public QuestionPanel(ShowQuestionAction action) {
        this.action = action;
        this.answers = action.question.getAnswers(true);

        setBackground(new Color(0, 0, 55));
        setLayout(new BorderLayout());
        questionLabel.setText(action.question.getQuestion());
        answer1Button.setText(answers[0]);
        answer2Button.setText(answers[1]);
        answer3Button.setText(answers[2]);
        answer4Button.setText(answers[3]);
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
        action.pickedAnswer = pickedAnswer;
        if (answer1Button.getText().equalsIgnoreCase(action.question.getCorrectAnswer())) {
            clickedButton.setForeground(Color.RED);
            answer1Button.setForeground(Color.GREEN);
        }else if (answer2Button.getText().equalsIgnoreCase(action.question.getCorrectAnswer())) {
            clickedButton.setForeground(Color.RED);
            answer2Button.setForeground(Color.GREEN);
        }else if (answer3Button.getText().equalsIgnoreCase(action.question.getCorrectAnswer())) {
            clickedButton.setForeground(Color.RED);
            answer3Button.setForeground(Color.GREEN);
        }else{
            clickedButton.setForeground(Color.RED);
            answer4Button.setForeground(Color.GREEN);
        }
            Client.sendToServer(action);
        System.out.println(pickedAnswer);
    }
}