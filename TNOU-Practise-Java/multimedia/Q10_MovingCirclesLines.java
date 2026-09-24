import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Q10_MovingCirclesLines extends JPanel implements Runnable {
    private ArrayList<Circle> circles = new ArrayList<>();
    private ArrayList<Line> lines = new ArrayList<>();
    private Random rand = new Random();

    class Circle {
        int x, y, dx, dy, size;
        Color color;

        Circle() {
            x = rand.nextInt(400);
            y = rand.nextInt(400);
            dx = rand.nextInt(4) + 1;
            dy = rand.nextInt(4) + 1;
            size = rand.nextInt(30) + 20;
            color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }

        void move() {
            x += dx;
            y += dy;
            if (x <= 0 || x >= 500 - size) dx = -dx;
            if (y <= 0 || y >= 500 - size) dy = -dy;
        }
    }

    class Line {
        int x1, y1, x2, y2;
        Color color;

        Line() {
            x1 = rand.nextInt(500);
            y1 = rand.nextInt(500);
            x2 = rand.nextInt(500);
            y2 = rand.nextInt(500);
            color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        }
    }

    public Q10_MovingCirclesLines() {
        // Create 5 moving circles
        for (int i = 0; i < 5; i++) {
            circles.add(new Circle());
        }

        Thread thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw random lines
        for (Line line : lines) {
            g2d.setColor(line.color);
            g2d.drawLine(line.x1, line.y1, line.x2, line.y2);
        }

        // Draw moving circles
        for (Circle c : circles) {
            g2d.setColor(c.color);
            g2d.fillOval(c.x, c.y, c.size, c.size);
        }
    }

    public void run() {
        while (true) {
            try {
                // Move circles
                for (Circle c : circles) {
                    c.move();
                }

                // Add random line every 10 frames
                if (rand.nextInt(10) == 0 && lines.size() < 50) {
                    lines.add(new Line());
                }

                repaint();
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Moving Circles and Random Lines");
        f.add(new Q10_MovingCirclesLines());
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
