import java.awt.*;
import javax.swing.*;

public class Q1_EnvironmentMapSphere extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Sphere parameters
        int cx = 200, cy = 200, radius = 100;

        // Environment map colors (simple gradient simulation)
        Color[] envMap = {Color.CYAN, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};

        // Map environment to sphere surface
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                if (x*x + y*y <= radius*radius) {
                    // Calculate sphere surface normal (z-component)
                    double z = Math.sqrt(radius*radius - x*x - y*y);

                    // Map to environment (simple mapping)
                    int mapIndex = (int)((z / radius) * (envMap.length - 1));
                    g2d.setColor(envMap[mapIndex]);
                    g2d.fillRect(cx + x, cy + y, 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Environment Map to Sphere");
        f.add(new Q1_EnvironmentMapSphere());
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
