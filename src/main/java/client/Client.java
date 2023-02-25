package client;

import server.service.socket.utils.FrameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static Scanner scanner;

    public static Socket client;
    public static PrintWriter printWriter;

    public static FrameUtils frameUtils = new FrameUtils();

    public static String getHostAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createScreenshot() throws AWTException, IOException {
        BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        frameUtils.convertToFile(screenCapture);
    }

    public static void sendScreenshot() throws IOException {
        sendMessage(frameUtils.convertToBase64());
    }

    public static void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    public static void startClient() {
        try {
            System.out.println("Trying to connect...");

            client = new Socket(getHostAddress(), 1234);

            printWriter = new PrintWriter(client.getOutputStream());

            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            scanner = new Scanner(System.in);

            String modifiedSentence = bf.readLine();

            while (modifiedSentence != null) {
                System.out.println("SERVER -> " + modifiedSentence);
                modifiedSentence = bf.readLine();

                String messageToSend = scanner.nextLine();
                sendMessage(messageToSend);
            }
        } catch (Exception e) {
            startClient();
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
