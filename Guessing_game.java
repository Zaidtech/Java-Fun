/*
THIS IS A GUESSING GAME AS PART OF MY GUI-APPLICATION PROJECT IN JAVA USING SWING .
THE GOAL OF THIS GAME IS TO MAKE ALL THE TILES GREEN IN MIN NO OF CHANCES.

PROJECT Requisite:

1. Create GUI of your choice in Java.
2. It should include window and some buttons
3. It should take appropriate action on events.
4. Try to write comments for each components (to explain its purpose and action for the related events)
5. Upload source code and screenshots of your program.

 */

//making the REQ. imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//making a Guessing game class
public class Guessing_game implements ActionListener {
    Random rand = new Random();//creating instance of random class
    //making the labels obj
    JLabel label;
    JLabel label2;
    //making the frame obj
    JFrame frame;
    JPanel panel = new JPanel(new GridLayout(5, 5, 5, 5));
    //making buttons with text "START GAME"
    JButton generate_button = new JButton("START GAME");
    JButton quit = new JButton("QUIT");
    JButton[] btn = new JButton[16]; //initializing a list of buttons where each button will act as a tile associated with a no.
    //initializing var to hold the forms of randomly generated no as int and string
    int random_button_no;
    String guess;
    int chance = 0;

    //    MAKING A CONSTRUCTOR
    public Guessing_game() {
        frame = new JFrame();
        frame.setTitle("GUESSING GAME!"); //setting title of frame
        label = new JLabel("GOAL: MAKE ALL THE TILES GREEN"); //creating a label
        label2 = new JLabel("Chances taken: " + chance);//to hold no of chances taken by a player
        panel.add(label);

        //looping to create button obj and addingActionListener to them
        for (int i = 0; i < 16; i++) {
            btn[i] = new JButton(String.valueOf(i));
            btn[i].setPreferredSize(new Dimension(40, 40));
            btn[i].addActionListener(this); //this refers to the current class
            panel.add(btn[i]); //adding button to the panel
        }

        //adding remaining elements to the panel
        panel.add(generate_button);//this will be an event
        panel.add(quit);
        panel.add(label2);
        frame.setContentPane(panel);
        frame.pack();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generate_button.addActionListener(this);
        quit.addActionListener(this);
        frame.setVisible(true);
        random_button_no = rand.nextInt(16);//this will generate a random no from 1-16
//        System.out.println("Generated no is "+random_button_no); ---> it is for cheating purpose :) !!
    }

    public static void main(String[] args) {
        new Guessing_game();
    }

    //    @Override
    public void actionPerformed(ActionEvent e) {

        Object caller = e.getSource(); //make an caller obj which
        JButton jButton = (JButton) caller; // referencing caller to a jButton obj of type JButton

//        System.out.println("Generated no is " + guess);
//        System.out.println("Button clicked is"+ jButton.getText());

        //resetting the game
        if (jButton == generate_button) {
            generate_button.setText("RESET GAME");
            chance = 0;
            label2.setText("Chances taken:" + chance);
            for (int i = 0; i < 16; i++) {
                btn[i].setBackground(null);
            }
            random_button_no = rand.nextInt(16);
            System.out.println("Generated no is "+random_button_no);
            generate_button.setBackground(null);
        }
        // on a right guess color change to green
        if (jButton == btn[random_button_no]) {
            btn[random_button_no].setBackground(Color.green);
            random_button_no = rand.nextInt(16);
//            System.out.println("Generated no is "+ random_button_no);
            chance++;
        }
        else if ( (jButton.getText() != guess) && (jButton != generate_button) ) {
            chance++;
            jButton.setBackground(Color.red);
            label2.setText("Chances taken :" + chance);
        }
        if(jButton==quit){
            System.exit(0);
        }
    }
}