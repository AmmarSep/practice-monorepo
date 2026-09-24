import java.awt.*;
import javax.swing.*;

public class Q8_Animate2DObject extends JPanel implements Runnable {
    private int x = 0, y = 150;
    private int dx = 2;
    private Thread thread;

    public Q8_Animate2DObject() {
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw animated car
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, 80, 30);           // Car body
        g2d.fillRect(x + 15, y - 20, 50, 20); // Car top

        // Wheels
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + 10, y + 25, 20, 20);
        g2d.fillOval(x + 50, y + 25, 20, 20);

        // Windows
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x + 20, y - 15, 20, 12);
        g2d.fillRect(x + 45, y - 15, 15, 12);
    }

    public void run() {
        while (true) {
            try {
                x += dx;
                // Reverse direction at boundaries
                if (x > 400 || x < 0) dx = -dx;

                repaint();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Animate 2D Object - Moving Car");
        f.add(new Q8_Animate2DObject());
        f.setSize(500, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
