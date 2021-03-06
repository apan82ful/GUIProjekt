package GUIProgram;

import GUIProgram.GUIInlämning.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
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
    //private JButton searchMovieButton;
    private JTextField textField4;
    private JLabel JLabel1;
    private JCheckBox startsWithCheckBox;
    private JCheckBox endsWithCheckBox;
    //private JButton SearchButton;
    private IMyListModel myListModel;
    private JMenuBar menuBar;
    private static JFrame frame;
    OutputStream fileOut;

    //
    public GUIFormen(IMyListModel listModel) {

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Meny");
        menu.setMnemonic('M');
        menuBar.add(menu);


        JMenuItem save = new JMenuItem("Save");
        menu.add(save);
        save.getAccessibleContext().setAccessibleDescription("Save to file");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        //menu.add(save);

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
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        //menu.add(load);

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFile();
            }
        });


        JMenuItem exit = new JMenuItem("Exit");
        menu.add(exit);
        exit.getAccessibleContext().setAccessibleDescription("Exit program");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        //menu.add(exit);

        // Anonym klass
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //myListModel = new MyListModel();

        myListModel = listModel;

        list1.setModel(myListModel);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        läggtill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Movie movie = new Movie(textField1.getText(), textField2.getText()
                            , Integer.parseInt(textField3.getText()));
                    myListModel.add(movie);
                    JLabel1.setText("");
                } catch (NumberFormatException ex) {
                    JLabel1.setText("You must type the year as a number! ");
                    textField3.setText("");
                }

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
                String name = textField1.getText().trim();
                String genre = textField2.getText().trim();
                String yearString = textField3.getText().trim();
                try {
                    Movie movie = Factory.createMovie(name, genre, yearString);
                    myListModel.update(list1.getSelectedIndex(), movie);
                }catch (NumberFormatException e1){
                    System.out.println("Must type nr");

                }catch (IllegalArgumentException e1){
                    System.out.println("Empty field");
                }


            }
        });

        //För att tabort knappen skall vara släckt om man inte markerat en film
        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    if (list1.getSelectedIndex() == -1) {
                        taBortButton.setEnabled(false);
                        ändraButton.setEnabled(false);
                    } else {
                        taBortButton.setEnabled(true);
                        ändraButton.setEnabled(true);
                    }
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


        textField4.addKeyListener(new KeyAdapter() {
            private int fieldLength = 0;

            @Override
            public void keyReleased(KeyEvent e) {
                //if (fieldLength > textField4.getText().length()){
                //    list1.setModel(myListModel);


                //}
            }


        });

        textField4.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                myListModel.filter(Factory.createFilter(textField4.getText(),startsWithCheckBox.isSelected(),endsWithCheckBox.isSelected()));

            }
        });

    }


    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            new Thread(() -> {
                try {
                    FileInputStream fileIn = new FileInputStream(fc.getSelectedFile());

                    ObjectInputStream in = new ObjectInputStream(fileIn);

                    myListModel = (MyListModel) in.readObject();
                    //invoke later
                    list1.setModel(myListModel);
                    myListModel.filter(Factory.createShowAll());

                    in.close();
                    fileIn.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }).start();
        }
    }


    public void saveFile() {

        JFileChooser fc = new JFileChooser();

        int returnVal = fc.showSaveDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            new Thread(() -> {

                try {

                    FileOutputStream fileOut =
                            new FileOutputStream(fc.getSelectedFile());

                    ObjectOutputStream out = new ObjectOutputStream(fileOut);

                    out.writeObject(myListModel);

                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in " + fileOut);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }).start();

        }
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
            GUIFormen formen = new GUIFormen(Factory.createListModel());
            frame.setContentPane(formen.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(800, 600));
            frame.setVisible(true);


        });
    }
}


