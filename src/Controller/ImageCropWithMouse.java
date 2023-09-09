package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageCropWithMouse {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Crop Image with Mouse");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            CustomPanel customPanel = new CustomPanel();
            frame.add(customPanel);

            frame.setVisible(true);
        });
    }

    static class CustomPanel extends JPanel {
        private BufferedImage originalImage;
        private BufferedImage croppedImage;
        private int startX, startY, endX, endY;
        private boolean isDragging = false;

        public CustomPanel() {
            // Load your BufferedImage (replace this with your image loading code)
            // For this example, we create a simple image with a colored rectangle
            originalImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = originalImage.createGraphics();
            g2d.setColor(Color.BLUE);
            g2d.fillRect(100, 100, 400, 300);
            g2d.dispose();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.out.println("Mouse pressed");
                    startX = e.getX();
                    startY = e.getY();
                    isDragging = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("Mouse Released");
                    endX = e.getX();
                    endY = e.getY();
                    isDragging = false;

                    // Calculate the crop region and create a cropped image
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);

                    if (width > 0 && height > 0) {
                        croppedImage = originalImage.getSubimage(x, y, width, height);
                        repaint(); // Repaint the panel to display the cropped region
                    }
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    System.out.println("Mouse dragging");
                    if (isDragging) {
                        endX = e.getX();
                        endY = e.getY();
                        repaint(); // Repaint the panel to show the selection rectangle
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (originalImage != null) {
                g.drawImage(originalImage, 0, 0, this);
                if (isDragging) {
                    // Draw a selection rectangle while dragging
                    int width = Math.abs(endX - startX);
                    int height = Math.abs(endY - startY);
                    int x = Math.min(startX, endX);
                    int y = Math.min(startY, endY);
                    g.setColor(Color.RED);
                    g.drawRect(x, y, width, height);
                } else if (croppedImage != null) {
                    // Draw the cropped image
                    g.drawImage(croppedImage, 0, 0, this);
                }
            }
        }
    }
}
