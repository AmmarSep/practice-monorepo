import java.awt.*;
import javax.swing.*;

class GraphicsPanel extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.drawLine(50, 50, 200, 50);
        g.setColor(Color.BLUE);
        g.drawRect(50, 80, 150, 100);
        g.setColor(Color.GREEN);
        g.fillOval(250, 80, 100, 100);
        g.setColor(Color.ORANGE);
        g.fillRect(400, 80, 100, 100);
        g.setColor(Color.BLACK);
        g.drawString("Graphics Demo", 200, 220);
    }
}

public class Q19_GraphicsComponents {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphics Components");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphicsPanel());
        frame.setSize(600, 300);
        frame.setVisible(true);
    }
}
