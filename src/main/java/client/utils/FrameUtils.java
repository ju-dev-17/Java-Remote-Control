package client.utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class FrameUtils {
    public void convertToFile(BufferedImage bufferedImage) {
        try {
            if (bufferedImage != null) {
                // save the BufferedImage object as an image file
                File outputfile = new File("./image.jpg");
                ImageIO.write(bufferedImage, "jpg", outputfile);
                System.out.println("Image saved successfully.");
            } else {
                System.out.println("Failed to read image.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertToBase64(String path) throws IOException {
        File inputFile = new File(Objects.requireNonNull(getClass().getResource(path)).getFile());

        byte[] fileContent = FileUtils.readFileToByteArray(inputFile);

        return Base64
                .getEncoder()
                .encodeToString(fileContent);
    }

    public void convertToImage(String encodedString) throws IOException {
        File outputFile = new File("/frames/frame.png");

        byte[] decodedBytes = Base64
                .getDecoder()
                .decode(encodedString);
        FileUtils.writeByteArrayToFile(outputFile, decodedBytes);
    }
}
