package server.service.socket.utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class FrameUtils {
    public void convertToFile(BufferedImage bufferedImage) throws IOException {
        File outputfile = new File("/frames/frame.png");
        ImageIO.write(bufferedImage, "png", outputfile);
    }

    public String convertToBase64() throws IOException {
        File inputFile = new File(Objects.requireNonNull(getClass().getResource("/frames/frame.png")).getFile());

        byte[] fileContent = FileUtils.readFileToByteArray(inputFile);

        return Base64
                .getEncoder()
                .encodeToString(fileContent);
    }

    public void convertToImage(String encodedString) throws IOException {
        byte[] decodedBytes = Base64
                .getDecoder()
                .decode(encodedString);

        try {
            BufferedImage image;
            if (decodedBytes != null && decodedBytes.length > 0) {
                // read the decoded bytes and load them into the BufferedImage object
                image = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                if (image != null) {
                    // save the BufferedImage object as an image file
                    File outputfile = new File("./image.jpg");
                    ImageIO.write(image, "jpg", outputfile);
                    System.out.println("Image saved successfully.");
                } else {
                    System.out.println("Failed to read image.");
                }
            } else {
                System.out.println("No decoded bytes found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
