package Sprint4_quizkamp_server.Client.GUI.Panels;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private int rondNum=2;
    private int LabelSize=(rondNum+1)*3;
    private JLabel[] resultLabels= new JLabel[LabelSize];
    
    

    private JButton geUppButton= new JButton("Ge upp");
    private JPanel recordPanel= new JPanel();
    private JPanel buttonPanel= new JPanel();
    private Font font1= new Font("SansSerif", Font.PLAIN, 20);
    private Font font2= new Font("SansSerif", Font.BOLD, 30);
    JButton spelaButton= new MyJButton("SPELA",MyJButton.sky);
    private Icon backIcon= new ImageIcon("src/background.jpg");


    public ResultPanel() {
        setLayout(new BorderLayout());
        add(recordPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        recordPanel.setLayout(new GridLayout((rondNum+1), 3, 4, 4));
        buttonPanel.setLayout(new FlowLayout());
        
        recordPanel.setBackground(new Color(96,165,191));
        buttonPanel.setBackground(new Color(96,165,191));
        
        for(int i=0;i<LabelSize;i++){
            setLabelStyle(resultLabels[i], font1, Color.yellow, JLabel.CENTER);
            recordPanel.add(resultLabels[i]);
            if(i>2&&i%3==1){
                resultLabels[i].setText("ROND "+i/3);
            }
            else
                resultLabels[i].setText("score");
        }
        
        resultLabels[1].setIcon(backIcon);
        resultLabels[1].setHorizontalTextPosition(JLabel.CENTER);
        resultLabels[1].setVerticalTextPosition(JLabel.CENTER);
     
       
        buttonPanel.add(spelaButton);
        buttonPanel.add(geUppButton);
         
        

    }
    
    public void setLabelStyle(JLabel label,Font font,Color color,int alignment){
        label.setFont(font);
        label.setForeground(color);
        label.setHorizontalAlignment(alignment);      
    }

}