package server.service.socket;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class CustomSocket {
    public static List<Socket> sockets = new ArrayList<>();

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

    private String getHostAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
