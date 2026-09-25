import java.awt.*;
import javax.swing.*;

public class Q4_ThickLinesCaps extends JPanel {
    private int capStyle = BasicStroke.CAP_BUTT;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set thick stroke with current cap style
        g2d.setStroke(new BasicStroke(20, capStyle, BasicStroke.JOIN_MITER));

        // Draw line
        g2d.setColor(Color.BLUE);
        g2d.drawLine(100, 150, 300, 150);

        // Draw reference markers
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.RED);
        g2d.drawLine(100, 100, 100, 200); // Start marker
        g2d.drawLine(300, 100, 300, 200); // End marker

        // Display current cap style
        g2d.setColor(Color.BLACK);
        String capName = capStyle == BasicStroke.CAP_BUTT ? "BUTT CAPS" :
                        capStyle == BasicStroke.CAP_ROUND ? "ROUND CAPS" : "SQUARE CAPS";
        g2d.drawString("Cap Style: " + capName, 150, 50);
        g2d.drawString("Click to change cap style", 130, 250);
    }

    public static void main(String[] args) {
        Q4_ThickLinesCaps panel = new Q4_ThickLinesCaps();
        JFrame f = new JFrame("Thick Lines with Caps");

        // Menu for cap styles
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Cap Style");

        JMenuItem buttCap = new JMenuItem("Butt Cap");
        buttCap.addActionListener(e -> {
            panel.capStyle = BasicStroke.CAP_BUTT;
            panel.repaint();
        });

        JMenuItem roundCap = new JMenuItem("Round Cap");
        roundCap.addActionListener(e -> {
            panel.capStyle = BasicStroke.CAP_ROUND;
            panel.repaint();
        });

        JMenuItem squareCap = new JMenuItem("Square Cap");
        squareCap.addActionListener(e -> {
            panel.capStyle = BasicStroke.CAP_SQUARE;
            panel.repaint();
        });

        menu.add(buttCap);
        menu.add(roundCap);
        menu.add(squareCap);
        menuBar.add(menu);

        f.setJMenuBar(menuBar);
        f.add(panel);
        f.setSize(400, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
