/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author humeidjocordasse
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageRotationExample {

    public static void main(String[] args) {
        try {
            // Load the original image
            BufferedImage originalImage = ImageIO.read(new File("input.jpg"));

            // Specify the custom rotation angle in radians (e.g., 45 degrees)
            double angleInRadians = Math.toRadians(45);

            // Calculate the size of the rotated image
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            int newWidth = (int) Math.ceil(width * Math.abs(Math.cos(angleInRadians)) + height * Math.abs(Math.sin(angleInRadians)));
            int newHeight = (int) Math.ceil(width * Math.abs(Math.sin(angleInRadians)) + height * Math.abs(Math.cos(angleInRadians)));

            // Create a new BufferedImage for the rotated image
            BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

            // Get the Graphics2D object to perform the rotation
            Graphics2D g2d = rotatedImage.createGraphics();

            // Set the rotation angle
            g2d.rotate(angleInRadians, newWidth / 2, newHeight / 2);

            // Draw the original image onto the rotated image
            g2d.drawImage(originalImage, (newWidth - width) / 2, (newHeight - height) / 2, null);

            // Dispose of the Graphics2D object
            g2d.dispose();

            // Save the rotated image to a file
            File outputImageFile = new File("output.jpg");
            ImageIO.write(rotatedImage, "jpg", outputImageFile);

            System.out.println("Image rotated and saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
