package client.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

public class FrameUtils {
    public String convertToBase64() throws IOException {
        File inputFile = new File(Objects.requireNonNull(getClass().getResource("/frames/frame.png")).getFile());

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
