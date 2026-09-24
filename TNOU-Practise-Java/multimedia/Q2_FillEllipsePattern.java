import java.awt.*;
import javax.swing.*;

public class Q2_FillEllipsePattern extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Create pattern (diagonal stripes)
        int[] pattern = {0xFF00FF00, 0x00FF00FF}; // Green-Magenta pattern

        // Ellipse parameters
        int cx = 200, cy = 200, rx = 100, ry = 70;

        // Fill ellipse with pattern
        for (int y = cy - ry; y <= cy + ry; y++) {
            for (int x = cx - rx; x <= cx + rx; x++) {
                // Check if point inside ellipse
                double val = Math.pow((x - cx), 2) / (rx * rx) + Math.pow((y - cy), 2) / (ry * ry);
                if (val <= 1) {
                    // Apply pattern
                    int color = pattern[(x + y) % 2];
                    g2d.setColor(new Color(color));
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }

        // Draw ellipse outline
        g2d.setColor(Color.BLACK);
        g2d.drawOval(cx - rx, cy - ry, 2 * rx, 2 * ry);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Fill Ellipse with Pattern");
        f.add(new Q2_FillEllipsePattern());
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
