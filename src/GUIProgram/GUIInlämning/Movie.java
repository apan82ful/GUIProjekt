package GUIProgram.GUIInlämning;

import GUIProgram.GUIFormen;

import javax.swing.*;
import java.awt.*;

public class Movie extends JFrame {
    private JList list1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton ändraButton;
    private JButton läggTillButton;
    private JButton taBortButton;

    public Movie(){

    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("GUIMovie");
            frame.setContentPane(new Movie().rootPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(800,600));
            frame.setVisible(true);



        });
    }
}