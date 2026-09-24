import java.awt.*;
import javax.swing.*;

public class Q5_BlinkingHighlight extends JPanel implements Runnable {
    private boolean highlight = true;
    private Thread thread;

    public Q5_BlinkingHighlight() {
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw text
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.BLACK);
        g2d.drawString("HIGHLIGHTED TEXT", 100, 100);

        // Draw rectangle
        g2d.drawRect(80, 150, 150, 80);
        g2d.drawString("BOX", 130, 195);

        // Apply blinking highlight
        if (highlight) {
            g2d.setColor(new Color(255, 255, 0, 150)); // Semi-transparent yellow
            g2d.fillRect(95, 80, 210, 30);
            g2d.fillRect(80, 150, 150, 80);
        }
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(500); // Blink every 500ms
                highlight = !highlight;
                repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Blinking Highlight");
        f.add(new Q5_BlinkingHighlight());
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
