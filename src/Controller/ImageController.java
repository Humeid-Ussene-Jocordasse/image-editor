package Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageController {

    public static final int ROTATE_LEFT = 1;
    public static final int ROTATE_RIGHT = -1;

    private String error;
    private boolean isError;

    public ImageController() {
        this.error = null;
        this.isError = false;
    }
    
    
    public BufferedImage rotate(BufferedImage image, int angleInInt) {
        int width = image.getWidth();
        int height = image.getHeight();
        double angleInRadians = Math.toRadians(angleInInt);

        int newWidth = (int) Math.ceil(width * Math.abs(Math.cos(angleInRadians)) + height * Math.abs(Math.sin(angleInRadians)));
        int newHeight = (int) Math.ceil(width * Math.abs(Math.sin(angleInRadians)) + height * Math.abs(Math.cos(angleInRadians)));

        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight,  BufferedImage.TYPE_INT_ARGB);

            // Get the Graphics2D object to perform the rotation
            Graphics2D g2d = rotatedImage.createGraphics();

            // Set the rotation angle
            g2d.rotate(angleInRadians, newWidth / 2, newHeight / 2);

            // Draw the original image onto the rotated image
            g2d.drawImage(image, (newWidth - width) / 2, (newHeight - height) / 2, null);

            // Dispose of the Graphics2D object
            g2d.dispose();

        return rotatedImage;
    }

    public BufferedImage scale(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, width, height, null);
        graphics2D.dispose();
        return scaledImage;
    }

    public BufferedImage translate(BufferedImage originalImage, int x, int y, int width, int height) {

        if (x > width && y > height) {
            this.isError = true;
            this.error = "Os valores de x e y são maiores do que a largura e altura da imagem.";
            return originalImage;
        }

        if (x > width) {
            this.isError = true;
            this.error = "O valor de x é maior que a largura da imagem.";
            return originalImage;
        }

        if (y > height) {
            this.isError = true;
            this.error = "O valor de y é maior que a altura da imagem.";
            return originalImage;
        }
        
        
        
//             Update the position based on the speed
//            x += xSpeed;
//            y += ySpeed;
//
//            // Check for boundary collisions (adjust as needed)
//            if (x < 0 || x + image.getWidth() > getWidth()) {
//                xSpeed *= -1; // Reverse direction on collision
//            }
//            if (y < 0 || y + image.getHeight() > getHeight()) {
//                ySpeed *= -1; // Reverse direction on collision
//            }
        
        

        BufferedImage movedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = movedImage.createGraphics();
        graphics2D.drawImage(originalImage, x, y, null);
        graphics2D.dispose();
        return movedImage;
    }

    public BufferedImage crop(BufferedImage image, int x, int y, int width, int height) {
        if (height >= image.getHeight() && width >= image.getWidth()) {
            this.isError = true;
            this.error = "Os valores da largura e altura são maiores do que a largura e altura da imagem.";
            return image;
        }

        if (height >= image.getHeight()) {
            this.isError = true;
            this.error = "O valor da altura introduzida é maior do que a altura da imagem.";
            return image;
        }

        if (height >= image.getHeight() || width >= image.getWidth()) {
            this.isError = true;
            this.error = "O valor da largura introduzida é maior do que a largura da imagem.";
            return image;
        }

        return image.getSubimage(x, y, width, height);
    }

    public String getError() {
        return this.error;
    }

    public boolean isIsError() {
        return this.isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }
}
