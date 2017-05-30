import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.List;
import java.util.ArrayList;
public class Screen extends JFrame{
    
    JPanel menu;
    JComboBox [] boxes = new JComboBox[3];
    JTextArea text;
    Color bgColor = Color.decode("#44C5F4");
    
    private String [] questionChoices = {"Multiple Choice", "Fill in the Blank", "Objective Based"};
    private String [] difficulties = {"1","2","3"};
    private String [] amounts = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25", "26","27","28","29","30"};
    public String[] output = new String[3];
    
    public Screen(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        Toolkit tool = Toolkit.getDefaultToolkit();
        this.setSize((int)tool.getScreenSize().getWidth(),(int)tool.getScreenSize().getHeight());
        setLocationRelativeTo(null);
        setResizable(true);
        

        initiate();
        setVisible(true);
    }
    
    public void initiate(){
        menu = new JPanel();
        menu.setLayout(new BorderLayout());
        menu.setBackground(bgColor);
        
        JPanel west = new JPanel(new GridLayout(10,1));
        west.setPreferredSize(new Dimension(getWidth()/5,200));
        west.setBackground(bgColor);
        
        JPanel north = new JPanel();
        north.setBackground(Color.decode("#3493E5"));
        
        JPanel east = new JPanel();
        east.setPreferredSize(new Dimension(4*(getWidth())/5, getHeight()));
        east.setBackground(bgColor);
        
        
        // Add welcome to north panel
        JLabel welcome = new JLabel("Education Innovators");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setForeground(Color.black);
        welcome.setFont(new Font("Bell MT", Font.BOLD, 48));
        north.add(welcome);

        
        // Set West Panel
        JButton add = new JButton("Add");
        add.setPreferredSize(new Dimension(150,150));
        add.addActionListener(new ActionListener() { 
              public void actionPerformed(ActionEvent e) { 
                addButtonPressed();
              } 
            } );
        west.add(add);
        
        JLabel choiceLabel = new JLabel("Type Of Question");
        choiceLabel.setVerticalAlignment(JLabel.BOTTOM);
        west.add(choiceLabel);
        boxes[0] = new JComboBox(questionChoices);
        west.add(boxes[0]);
        
        JLabel difficultyLabel = new JLabel("Difficulty of Question");
        difficultyLabel.setVerticalAlignment(JLabel.BOTTOM);
        west.add(difficultyLabel);
        boxes[1] = new JComboBox(difficulties);
        west.add(boxes[1]);
        
        JLabel amountLabel = new JLabel("Number of Question");
        amountLabel.setVerticalAlignment(JLabel.BOTTOM);
        west.add(amountLabel);
        boxes[2] = new JComboBox(amounts);
        west.add(boxes[2]);
        
        JButton finish = new JButton("Finish");
        finish.setPreferredSize(new Dimension(150,150));
        finish.addActionListener(new ActionListener() { 
              public void actionPerformed(ActionEvent e) { 
                finishButtonPressed();
              } 
            } );
        west.add(finish);
        
        
        // Set East Panel
        text = new JTextArea();
        text.setFont(text.getFont().deriveFont(15f));
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(getWidth()*7/10,getHeight()-200));
        
        east.setBorder(BorderFactory.createEmptyBorder(getHeight()/20, 0, getHeight()/10, getWidth()/20));
        west.setBorder(BorderFactory.createEmptyBorder(getHeight()/20, getWidth()/20, getHeight()/10, getWidth()/20));
        
        east.add(scrollPane);
        
        
        menu.add(east, BorderLayout.EAST);
        menu.add(west, BorderLayout.WEST);
        menu.add(north, BorderLayout.NORTH);
        
        add(menu);
        
    }
    
    public void finishButtonPressed(){
        //blank for Owen
    }
    
    public void addButtonPressed(){
        if(boxes[0].getSelectedItem().equals("Multiple Choice"))
            output[0] = "0";
        else if(boxes[0].getSelectedItem().equals("Fill in the Blank"))
            output[0] = "1";
        else if(boxes[0].getSelectedItem().equals("Objective Based"))
            output[0] = "2";
        output[1] = boxes[1].getSelectedItem().toString();
        output[2] = boxes[2].getSelectedItem().toString();
        
        
        ArrayList<Question> selected = new ArrayList<Question>();
        selected = QuestionBank.qBank;
        int n = 0;
        while (n < selected.size())
        {
            if(selected.get(n).getDifficulty() != Integer.parseInt(output[1]))
                selected.remove(n);
            else if(selected.get(n).getQuestionType() != Integer.parseInt(output[0]))
                selected.remove(n);
            else
                n++;
        }
        for (n = 0; n < Integer.parseInt(output[2]); n++)
            text.append(selected.get(n).getQuestionText());
        
    }
    
    
    public void waitFor(int i ){
        
            try {
                TimeUnit.MICROSECONDS.sleep(i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }
    public static void main(String [] args){
        Screen screen = new Screen();
        QuestionBank questionbank = new QuestionBank();
    }
}
