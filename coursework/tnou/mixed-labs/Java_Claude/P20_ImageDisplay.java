// Program 20: Program to display an image (Concept: PixelGrabber Class: Getting pixels of an image)

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;

class ImagePanel extends JPanel {
    private BufferedImage image;
    private int[] pixels;
    private int width;
    private int height;

    public ImagePanel(String imagePath) {
        try {
            // Load the image
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                image = ImageIO.read(imageFile);
                width = image.getWidth();
                height = image.getHeight();

                // Use PixelGrabber to get pixel data
                pixels = new int[width * height];
                PixelGrabber pg = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);

                try {
                    pg.grabPixels();
                    System.out.println("Image loaded successfully!");
                    System.out.println("Image dimensions: " + width + " x " + height);
                    System.out.println("Total pixels: " + pixels.length);

                    // Display some pixel information
                    displayPixelInfo();
                } catch (InterruptedException e) {
                    System.out.println("Error grabbing pixels: " + e.getMessage());
                }
            } else {
                System.out.println("Image file not found. Creating a sample image instead.");
                createSampleImage();
            }
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
            createSampleImage();
        }
    }

    private void createSampleImage() {
        // Create a sample gradient image if file not found
        width = 400;
        height = 300;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Create gradient
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLUE, width, height, Color.RED);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);

        // Draw some shapes
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(50, 50, 100, 100);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(250, 150, 100, 100);

        g2d.dispose();

        // Grab pixels from sample image
        pixels = new int[width * height];
        PixelGrabber pg = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
        try {
            pg.grabPixels();
            displayPixelInfo();
        } catch (InterruptedException e) {
            System.out.println("Error grabbing pixels: " + e.getMessage());
        }
    }

    private void displayPixelInfo() {
        // Display information about first few pixels
        System.out.println("\nSample Pixel Data (first 10 pixels):");
        for (int i = 0; i < Math.min(10, pixels.length); i++) {
            int pixel = pixels[i];
            int red = (pixel >> 16) & 0xff;
            int green = (pixel >> 8) & 0xff;
            int blue = pixel & 0xff;
            System.out.println("Pixel " + i + " - R:" + red + " G:" + green + " B:" + blue);
        }

        // Calculate average color
        long totalRed = 0, totalGreen = 0, totalBlue = 0;
        for (int pixel : pixels) {
            totalRed += (pixel >> 16) & 0xff;
            totalGreen += (pixel >> 8) & 0xff;
            totalBlue += pixel & 0xff;
        }
        int avgRed = (int) (totalRed / pixels.length);
        int avgGreen = (int) (totalGreen / pixels.length);
        int avgBlue = (int) (totalBlue / pixels.length);
        System.out.println("\nAverage Color - R:" + avgRed + " G:" + avgGreen + " B:" + avgBlue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Calculate scaling to fit the panel
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            double scale = Math.min((double) panelWidth / width, (double) panelHeight / height);
            int scaledWidth = (int) (width * scale);
            int scaledHeight = (int) (height * scale);
            int x = (panelWidth - scaledWidth) / 2;
            int y = (panelHeight - scaledHeight) / 2;

            g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
        } else {
            g.drawString("No image loaded", 50, 50);
        }
    }
}

public class P20_ImageDisplay {
    public static void main(String[] args) {
        // You can change this path to any image file
        String imagePath = "sample_image.jpg";

        System.out.println("Image Display Program using PixelGrabber");
        System.out.println("==========================================");
        System.out.println("Attempting to load image from: " + imagePath);
        System.out.println("If image not found, a sample image will be created.\n");

        JFrame frame = new JFrame("Image Display - PixelGrabber Demo");
        ImagePanel panel = new ImagePanel(imagePath);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
