/*
 * Checkerboard.java: This program draws an 8x8 checkerboard.
 *
 * checker and initgr methods copied from Sections 1.2 and 1.3 of
 *    Ammeraal, L. and K. Zhang (2007). Computer Graphics for Java Programmers, 2nd Edition,
 *       Chichester: John Wiley.
 */
import java.awt.*;
import java.awt.event.*;


public class Checkerboard extends Frame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Checkerboard();
    }

    Checkerboard() {
        super("Checker: A checkerboard");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(600, 400);
        add("Center", new CvCheckerboard());
        setVisible(true);
    }
}
