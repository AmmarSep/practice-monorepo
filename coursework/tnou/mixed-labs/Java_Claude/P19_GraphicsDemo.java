// Program 19: Program to display graphical components (Concept: Graphics class)

import javax.swing.*;
import java.awt.*;

class GraphicsPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background
        this.setBackground(Color.WHITE);

        // Draw lines
        g.setColor(Color.BLACK);
        g.drawLine(50, 50, 250, 50);
        g.drawString("Line", 140, 70);

        // Draw rectangle
        g.setColor(Color.BLUE);
        g.drawRect(50, 100, 200, 100);
        g.drawString("Rectangle", 120, 220);

        // Draw filled rectangle
        g.setColor(Color.GREEN);
        g.fillRect(300, 100, 200, 100);
        g.setColor(Color.BLACK);
        g.drawString("Filled Rectangle", 340, 220);

        // Draw circle (oval)
        g.setColor(Color.RED);
        g.drawOval(50, 250, 150, 150);
        g.setColor(Color.BLACK);
        g.drawString("Circle", 100, 420);

        // Draw filled circle
        g.setColor(Color.ORANGE);
        g.fillOval(250, 250, 150, 150);
        g.setColor(Color.BLACK);
        g.drawString("Filled Circle", 290, 420);

        // Draw arc
        g.setColor(Color.MAGENTA);
        g.drawArc(450, 250, 150, 150, 0, 180);
        g.drawString("Arc", 510, 420);

        // Draw polygon
        int[] xPoints = {50, 100, 150, 125, 75};
        int[] yPoints = {500, 450, 500, 550, 550};
        g.setColor(Color.CYAN);
        g.drawPolygon(xPoints, yPoints, 5);
        g.setColor(Color.BLACK);
        g.drawString("Polygon", 80, 570);

        // Draw filled polygon
        int[] xPoints2 = {250, 300, 350, 325, 275};
        int[] yPoints2 = {500, 450, 500, 550, 550};
        g.setColor(new Color(255, 100, 200));
        g.fillPolygon(xPoints2, yPoints2, 5);
        g.setColor(Color.BLACK);
        g.drawString("Filled Polygon", 260, 570);

        // Draw text with different fonts
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Java Graphics Demo", 200, 30);
    }
}

public class P19_GraphicsDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphics Demo");
        GraphicsPanel panel = new GraphicsPanel();

        frame.add(panel);
        frame.setSize(650, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
