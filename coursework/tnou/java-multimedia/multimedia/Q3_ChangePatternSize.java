import java.awt.*;
import javax.swing.*;

public class Q3_ChangePatternSize extends JPanel {
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Original pattern (4x4)
        int[][] originalPattern = {
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1}
        };

        // Draw original pattern
        g2d.drawString("Original 4x4", 50, 20);
        drawPattern(g2d, originalPattern, 50, 30, 10);

        // Resize to 8x8 (double size)
        int[][] resizedPattern = resizePattern(originalPattern, 8, 8);
        g2d.drawString("Resized 8x8", 50, 180);
        drawPattern(g2d, resizedPattern, 50, 190, 10);

        // Resize to 6x6
        int[][] resizedPattern2 = resizePattern(originalPattern, 6, 6);
        g2d.drawString("Resized 6x6", 250, 20);
        drawPattern(g2d, resizedPattern2, 250, 30, 10);
    }

    // Resize pattern using nearest neighbor
    int[][] resizePattern(int[][] pattern, int newW, int newH) {
        int oldW = pattern.length, oldH = pattern[0].length;
        int[][] newPattern = new int[newW][newH];

        for (int i = 0; i < newW; i++) {
            for (int j = 0; j < newH; j++) {
                int oldI = i * oldW / newW;
                int oldJ = j * oldH / newH;
                newPattern[i][j] = pattern[oldI][oldJ];
            }
        }
        return newPattern;
    }

    // Draw pattern
    void drawPattern(Graphics2D g, int[][] pattern, int x, int y, int cellSize) {
        for (int i = 0; i < pattern.length; i++) {
            for (int j = 0; j < pattern[0].length; j++) {
                g.setColor(pattern[i][j] == 1 ? Color.BLACK : Color.WHITE);
                g.fillRect(x + j * cellSize, y + i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(x + j * cellSize, y + i * cellSize, cellSize, cellSize);
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Change Pattern Size");
        f.add(new Q3_ChangePatternSize());
        f.setSize(450, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
