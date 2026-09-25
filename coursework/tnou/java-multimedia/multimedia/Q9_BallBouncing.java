import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class Q9_BallBouncing extends JPanel implements Runnable {
    private int x, y, dx, dy;
    private int ballSize = 30;
    private Random rand = new Random();
    private Color ballColor;

    public Q9_BallBouncing() {
        // Random initial position and velocity
        x = rand.nextInt(300) + 50;
        y = rand.nextInt(200) + 50;
        dx = rand.nextInt(5) + 2; // Random speed 2-6
        dy = rand.nextInt(5) + 2;
        ballColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        Thread thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw ball
        g2d.setColor(ballColor);
        g2d.fillOval(x, y, ballSize, ballSize);

        // Draw shadow
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillOval(x + 5, y + 5, ballSize, ballSize);
    }

    public void run() {
        while (true) {
            try {
                x += dx;
                y += dy;

                // Bounce off walls
                if (x <= 0 || x >= getWidth() - ballSize) {
                    dx = -dx;
                    ballColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                }
                if (y <= 0 || y >= getHeight() - ballSize) {
                    dy = -dy;
                    ballColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                }

                repaint();
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Ball Bouncing - Random Colors");
        f.add(new Q9_BallBouncing());
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
