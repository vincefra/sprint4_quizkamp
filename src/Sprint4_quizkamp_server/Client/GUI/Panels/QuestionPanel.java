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
    private Color color= new Color(96,165,191);
    
    private Font font= new Font("SansSerif", Font.PLAIN, 28);
    private Icon backIcon= new ImageIcon("src/background.jpg");
    private Dimension dimenstion= new Dimension(160,50);
    
    
    public QuestionPanel(ShowQuestionAction action) {
        this.action = action;
        this.answers = action.question.getAnswers(true);

        setBackground(new Color(0, 0, 55));
        setLayout(new BorderLayout());
        questionLabel.setPreferredSize(new Dimension(500,350));
        questionLabel.setIcon(backIcon);
        questionLabel.setText(action.question.getQuestion());
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        questionLabel.setForeground(Color.YELLOW);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setHorizontalTextPosition(JLabel.CENTER);
        questionLabel.setVerticalTextPosition(JLabel.CENTER);
        add(questionLabel, BorderLayout.NORTH);
        
        answer1Button.setText(answers[0]);
        answer2Button.setText(answers[1]);
        answer3Button.setText(answers[2]);
        answer4Button.setText(answers[3]);
        
        
        answer1Button.addActionListener(this::actionPerformed);
        answer2Button.addActionListener(this::actionPerformed);
        answer3Button.addActionListener(this::actionPerformed);
        answer4Button.addActionListener(this::actionPerformed);
        answerPanel.setLayout(new GridLayout(2,2,10,10));
        answerPanel.add(answer1Button);
        answerPanel.add(answer2Button);
        answerPanel.add(answer3Button);
        answerPanel.add(answer4Button);
        setJButtonFont(answer1Button, font, color,Color.WHITE,dimenstion);
        setJButtonFont(answer2Button, font, color,Color.WHITE,dimenstion);
        setJButtonFont(answer3Button, font, color,Color.WHITE,dimenstion);
        setJButtonFont(answer4Button, font, color,Color.WHITE,dimenstion);

        add(answerPanel, BorderLayout.CENTER);

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
        action.questionNumber += 1;
        Client.sendToServer(action);
        System.out.println(pickedAnswer);
    }
    
    public void setJButtonFont(JButton button,Font font,Color color,Color color1,Dimension dimension){
        button.setPreferredSize(dimension);
        button.setFont(font);
        button.setForeground(color1);
        button.setBackground(color);
        button.setBorderPainted(false);
        button.setOpaque(true);
    }
}