import java.awt.*;
import javax.swing.*;

public class Q6_ScanConvertEllipse extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Ellipse parameters
        int cx = 200, cy = 200, rx = 100, ry = 70;
        Color fillColor = Color.BLUE;

        // Midpoint Ellipse Algorithm for scan conversion
        scanConvertEllipse(g2d, cx, cy, rx, ry, fillColor);
    }

    void scanConvertEllipse(Graphics2D g, int cx, int cy, int rx, int ry, Color color) {
        // Region 1: Slope < -1
        int x = 0, y = ry;
        double d1 = ry * ry - rx * rx * ry + 0.25 * rx * rx;

        while (rx * rx * y > ry * ry * x) {
            drawScanLine(g, cx, cy, x, y, color);
            x++;
            if (d1 < 0)
                d1 += ry * ry * (2 * x + 3);
            else {
                y--;
                d1 += ry * ry * (2 * x + 3) + rx * rx * (-2 * y + 2);
            }
        }

        // Region 2: Slope >= -1
        double d2 = ry * ry * (x + 0.5) * (x + 0.5) + rx * rx * (y - 1) * (y - 1) - rx * rx * ry * ry;
        while (y >= 0) {
            drawScanLine(g, cx, cy, x, y, color);
            y--;
            if (d2 > 0)
                d2 += rx * rx * (-2 * y + 3);
            else {
                x++;
                d2 += ry * ry * (2 * x + 2) + rx * rx * (-2 * y + 3);
            }
        }
    }

    void drawScanLine(Graphics2D g, int cx, int cy, int x, int y, Color color) {
        g.setColor(color);
        // Draw horizontal scan lines for all 4 quadrants
        g.drawLine(cx - x, cy + y, cx + x, cy + y); // Bottom
        g.drawLine(cx - x, cy - y, cx + x, cy - y); // Top
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Scan Convert Ellipse");
        f.add(new Q6_ScanConvertEllipse());
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
