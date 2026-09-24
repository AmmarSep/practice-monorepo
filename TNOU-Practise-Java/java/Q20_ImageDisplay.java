import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

class ImagePanel extends JPanel {
    Image img;
    int[] pixels;
    int width, height;
    
    ImagePanel() {
        try {
            img = ImageIO.read(new File("sample.jpg"));
            width = img.getWidth(this);
            height = img.getHeight(this);
            pixels = new int[width * height];
            PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixels, 0, width);
            pg.grabPixels();
            System.out.println("Pixels grabbed: " + pixels.length);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 50, 50, this);
        }
    }
}

public class Q20_ImageDisplay {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ImagePanel());
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
