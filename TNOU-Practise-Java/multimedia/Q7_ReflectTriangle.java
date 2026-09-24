import java.awt.*;
import javax.swing.*;

public class Q7_ReflectTriangle extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Original triangle vertices
        int[] xPoints = {150, 250, 200};
        int[] yPoints = {100, 100, 50};

        // Draw X-axis (reflection axis)
        g2d.setColor(Color.RED);
        g2d.drawLine(0, 200, 400, 200);
        g2d.drawString("X-Axis", 350, 195);

        // Draw original triangle
        g2d.setColor(Color.BLUE);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Original", 190, 80);

        // Reflect triangle w.r.t X-axis
        int[] xReflected = xPoints.clone();
        int[] yReflected = new int[yPoints.length];

        int xAxis = 200; // X-axis position
        for (int i = 0; i < yPoints.length; i++) {
            yReflected[i] = 2 * xAxis - yPoints[i]; // Reflection formula: y' = 2*axis - y
        }

        // Draw reflected triangle
        g2d.setColor(Color.GREEN);
        g2d.fillPolygon(xReflected, yReflected, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Reflected", 185, 330);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Reflect Triangle w.r.t X-Axis");
        f.add(new Q7_ReflectTriangle());
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
