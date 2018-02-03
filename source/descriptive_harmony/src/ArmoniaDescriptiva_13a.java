    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @autor Cristian
 */
public class ArmoniaDescriptiva_13a extends JPanel {

    public static String currentOs = "";
    public static ArmoniaDescriptiva13a_PA armoniaPA;
    public static JFrame mainJFrm;
//    public static FullScreenJFrame mainJFrmW;
    public static JFrame mainJFrmW;
    public static JFrame menuJF;
    public static JMenuBar menuBar;
    public static JMenu menu, submenu;
    public static JMenuItem menuItem;

    // constructor
    public ArmoniaDescriptiva_13a() {
    }

    public static void frameMain() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        int wi = 550;
        int hei = 730;
        System.out.println("Width of Screen Size is " + dim.width + " pixels");
        System.out.println("Height of Screen Size is " + dim.height + " pixels");
        currentOs = System.getProperty("os.name").toUpperCase();
        armoniaPA = new ArmoniaDescriptiva13a_PA(wi, hei);
        armoniaPA.init();

        if (currentOs.contains("MAC")) {
        } else if (currentOs.contains("WINDOWS")) {
            mainJFrmW = new JFrame("Armonia Descriptiva");
//            mainJFrmW = new FullScreenJFrame("Reconciliacion IM");
            mainJFrmW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainJFrmW.setExtendedState(mainJFrmW.getExtendedState() | mainJFrmW.MAXIMIZED_BOTH);
            // You add things to the contentPane in a JFrame
            mainJFrmW.getContentPane().add(armoniaPA);
            mainJFrmW.setResizable(false);
            mainJFrmW.pack();
            mainJFrmW.setSize(wi, hei);
            mainJFrmW.setLocation(0, 0);
            mainJFrmW.setVisible(true);

        }

    }

    public static void frameMenuArriba() {
        //Create the menu bar.
        menuBar = new JMenuBar();

        //MENU File
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "Files");
        menuBar.add(menu);

                ///////////
        menuItem = new JMenuItem("On Top: "+mainJFrmW.isAlwaysOnTop(),
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "On Top");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                if (mainJFrmW.isAlwaysOnTop()) {
                    mainJFrmW.setAlwaysOnTop(false);
                } else {
                    mainJFrmW.setAlwaysOnTop(true);
                }
            }
        });
        menu.add(menuItem);
        ///////////

        //a group of JMenuItems
        menuItem = new JMenuItem("Acerca de ...",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Cristian B");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                JDialog f = new AboutDialog(new JFrame());
                f.show();
            }
        });
        menu.add(menuItem);


        ///////////
        menuItem = new JMenuItem("Salir",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Exit");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        menu.add(menuItem);
        ///////////




        if (currentOs.contains("MAC")) {
        } else if (currentOs.contains("WINDOWS")) {
            mainJFrmW.setJMenuBar(menuBar);
        }


    }

    private static void createGUI() {
        // GUI
        frameMain();
        frameMenuArriba();
//        JOptionPane.showMessageDialog(mainJFrm, "Please open the PD-patch enclosed\nfor the sound output.");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // threadsafe way to create a Swing GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                createGUI();
            }
        });

    }
}
