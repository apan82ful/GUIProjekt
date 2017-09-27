package GUIProgram.GUIInlämning;

import javax.swing.*;
import java.awt.event.*;

public class AddMovie extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private MyListModel<Movie> list;

    public AddMovie(MyListModel list) {
        this.list = list;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here





        //list.add(movie);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /*
    public static void main(String[] args) {
        AddMovie dialog = new AddMovie(myListModel);
        dialog.pack();
        dialog.setVisible(true);
    }
    */
}
