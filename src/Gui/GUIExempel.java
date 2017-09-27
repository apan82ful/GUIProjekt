/*
package Gui;

import GUIProgram.GUIInlämning.MyListModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GUIExempel {
    private JPanel root;
    private JTextField textField1;
    private JTextField textField2;
    private JButton läggTillButton;
    private JButton taBortButton;
    private JList list1;
    private MyListModel myListModel;
    private JMenuBar menuBar;
    private static JFrame frame;

    public GUIExempel() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Meny");
        menu.setMnemonic('M');
        menuBar.add(menu);


        //Keyevent is used for navigating with alt + underscored character in windows
        JMenuItem menuItem = new JMenuItem("A text-only menu item",
                KeyEvent.VK_T);


        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));

        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);

        menuItem.addActionListener( (e) -> {
            myListModel.add(textField1.getText());
        });

        myListModel = new MyListModel();
        list1.setModel(myListModel);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        läggTillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListModel.add(textField1.getText());
            }
        });
        taBortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListModel.remove(list1.getSelectedIndex());
            }
        });
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if( e.getValueIsAdjusting() == false){
                    //Selection/Deselection is done
                    if( list1.getSelectedIndex() == -1){
                        taBortButton.setEnabled(false);
                    }
                    else
                        taBortButton.setEnabled(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            frame = new JFrame("GUIExempel");
            frame.setContentPane(new GUIExempel().root);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(800,600));
            frame.setVisible(true);
        });
    }
}
*/