package GUIProgram;

import GUIProgram.GUIInlämning.MyListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUIFormen extends JFrame {
    private JButton läggtill;
    private JPanel panel1;
    private JButton ändraButton;
    private JButton taBortButton;
    private JTextField textField1;
    private JTextField textField2;
    private JList<Movie> list1;
    private JTextField textField3;
    private MyListModel<Movie> myListModel;
    private JMenuBar menuBar;
    private static JFrame frame;

    public GUIFormen() {

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Meny");
        menu.setMnemonic('M');
        menuBar.add(menu);


        JMenuItem save = new JMenuItem("Save");
        menu.add(save);
        JMenuItem load = new JMenuItem("Load");
        menu.add(load);
        JMenuItem exit = new JMenuItem("Exit");
        menu.add(exit);

        // Metodreferens
//        exit.addActionListener(this::quitProgram);

        // Lambda
/*        exit.addActionListener(e -> {
            System.exit(0);
        });
*/

        // Anonym klass
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        myListModel = new MyListModel<>();
        list1.setModel(myListModel);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        läggtill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Movie movie = new Movie(textField1.getText(), textField2.getText()
                        , Integer.parseInt(textField3.getText()));
                myListModel.add(movie);


            }
        });
        taBortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListModel.remove(list1.getSelectedIndex());
            }
        });


        ändraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField1.getText();
                String genre = textField2.getText();
                int year = Integer.parseInt(textField3.getText());
                Movie m = list1.getSelectedValue();
                m.setName(name);
                m.setGenre(genre);
                m.setYear(year);


            }
        });

    }

    public void quitProgram (ActionEvent e) {
        System.exit(0);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            frame = new JFrame("GUIFormen");
            frame.setContentPane(new GUIFormen().panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(800, 600));
            frame.setVisible(true);


        });
    }
}
/*
crud
 */

