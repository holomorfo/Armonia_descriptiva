
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * http://www.java2s.com/Code/Java/Swing-JFC/Createsimpleaboutdialog.htm
 * @author Cristian
 */
public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "About Dialog", true);

        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(new JLabel("  \t\tArmonia descriptiva"));
        b.add(new JLabel("  \t\tHolomorfo"));
        b.add(new JLabel("  \t\tTijuana, B.C. Mexico, 2013"));
        b.add(new JLabel("  \t\tCristian Banuelos"));
        b.add(new JLabel("  \t\twww.holomorfo.com"));
        b.add(Box.createGlue());
        getContentPane().add(b, "Center");

        JPanel p2 = new JPanel();
        JButton ok = new JButton("Ok");
        p2.add(ok);
        getContentPane().add(p2, "South");

        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
            }
        });
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();

        setLocation(dim.width / 2 - 100, dim.height / 2 - 70);
        setSize(250, 250);
        setAlwaysOnTop(true);
    }

//    public static void main(String[] args) {
//        JDialog f = new AboutDialog(new JFrame());
//        f.show();
//    }
}
