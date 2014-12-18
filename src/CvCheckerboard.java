
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/*
 * CvCheckerboard.java: Canvas that draws an 8x8 checkerboard.
 *
 * checker and initgr methods copied from Sections 1.2 and 1.3 of
 *    Ammeraal, L. and K. Zhang (2007). Computer Graphics for Java Programmers, 2nd Edition,
 *       Chichester: John Wiley.
 */
public class CvCheckerboard extends Canvas {

    /**
     * ratio of starting (biggest) triangle side to Math.min(maxX, maxY)
     * (bounding square's side)
     */
    final private static float DRAWING_TO_CANVAS_RATIO = 0.90F;

    private int n;
    
    int maxX, maxY, minMaxXY;

    /**
     * logical coordinate for vertex of square
     */
    private float xA, yA, xB, yB, xC, yC, xD, yD;

    /**
     * Initializes a newly created CvCheckerboard with 8 squares on a side.
     */
    public CvCheckerboard() {
        this(8);
    }

    /**
     * Initializes a newly created CvCheckerboard
     *
     * @param n number of squares on a side.
     * @throws IllegalArgumentException if n &le; 0
     */
    public CvCheckerboard(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "Illegal number of squares on a side: " + n);
        }
        this.n = n;
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        maxX = d.width - 1;
        maxY = d.height - 1;
        int xCenter = maxX / 2;
        int yCenter = maxY / 2;
        float side = DRAWING_TO_CANVAS_RATIO * Math.min(maxX, maxY);
        checker(g, xCenter - side / 2, yCenter - side / 2, side / n);
    }

    void checker(Graphics g, float x, float y, float w) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g.setColor((i + n - j) % 2 == 0
                        ? Color.yellow : Color.black);
                setVerticesForNextBoardSquare(x, y, i, j, w);
                drawBoardSquare(g);
            }
        }
    }

    public void drawBoardSquare(Graphics g) {
        g.drawLine(iX(xA), iY(yA), iX(xB), iY(yB));
        g.drawLine(iX(xB), iY(yB), iX(xC), iY(yC));
        g.drawLine(iX(xC), iY(yC), iX(xD), iY(yD));
        g.drawLine(iX(xD), iY(yD), iX(xA), iY(yA));
    }

    public void setVerticesForNextBoardSquare(float x, float y, int i, int j, float w) {
        xA = xD = x + i * w;
        xB = xC = x + i * w + w - 1;
        yA = yB = y + j * w;
        yC = yD = y + j * w + w - 1;
    }
    /**
     * Converts x from a logical coordinate to a device coordinate.
     *
     * @param x a logical x coordinate
     * @return the corresponding device x coordinate
     */
    private int iX(float x) {
        return Math.round(x);
    }

    /**
     * Converts y from a logical coordinate to a device coordinate.
     *
     * @param y a logical y coordinate
     * @return the corresponding device y coordinate
     */
    private int iY(float y) {
        return maxY - Math.round(y);
    }
}
