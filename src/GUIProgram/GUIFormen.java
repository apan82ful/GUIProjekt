package GUIProgram;

import GUIProgram.GUIInlämning.MyListModel;
import javafx.beans.binding.BooleanBinding;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

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
    OutputStream fileOut;

    public GUIFormen() {

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Meny");
        menu.setMnemonic('M');
        menuBar.add(menu);


        JMenuItem save = new JMenuItem("Save");
        menu.add(save);
        save.getAccessibleContext().setAccessibleDescription("Save to file");
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menu.add(save);

        //save.addActionListener(e -> saveFile());
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        JMenuItem load = new JMenuItem("Load");
        menu.add(load);
        load.getAccessibleContext().setAccessibleDescription("Load to file");
        menu.add(load);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });


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
                list1.repaint();

            }
        });

        //För att tabort knappen skall vara släckt om man inte markerat en film
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list1.getSelectedIndex() == -1) {
                        taBortButton.setEnabled(false);
                    } else
                        taBortButton.setEnabled(true);
                }

            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                keyEnable();

            }
        });
        textField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyEnable();
            }
        });
        textField3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyEnable();
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list1.getSelectedIndex() == -1) {
                        ändraButton.setEnabled(false);
                    } else
                        ändraButton.setEnabled(true);
                }
            }
        });
    }

    private void loadFile() {
        //Movie m = null;

        new Thread(() -> {
        //Skall jag ha med allt i nya tråden?
            try {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                FileInputStream fileIn = new FileInputStream(fc.getSelectedFile());

                ObjectInputStream in = new ObjectInputStream(fileIn);

                myListModel = (MyListModel<Movie>) in.readObject();
                list1.setModel(myListModel);

                in.close();
                fileIn.close();
            }
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("File not found");
            c.printStackTrace();
            return;
        }

        }).start();
    }

    public void saveFile() {
        new Thread(()->{

        try {
            JFileChooser fc = new JFileChooser();

            int returnVal = fc.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {


                FileOutputStream fileOut =
                        new FileOutputStream(fc.getSelectedFile());

                ObjectOutputStream out = new ObjectOutputStream(fileOut);

                out.writeObject(myListModel);

                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in " + fileOut);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();

        }
        }).start();

    }

    public void keyEnable() {
        if (textField1.getText().length() == 0 || textField2.getText().length() == 0 ||
                textField3.getText().length() == 0) {
            läggtill.setEnabled(false);
        } else {
            läggtill.setEnabled(true);
        }

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


