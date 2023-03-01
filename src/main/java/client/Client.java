package client;

import client.utils.FrameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static Socket client;
    public static PrintWriter printWriter;

    public static FrameUtils frameUtils = new FrameUtils();

    public static Thread messanger = new Thread(() -> {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String messageToSend = scanner.nextLine();
            sendMessage(messageToSend);
        }
    });

    public static Thread messageListener = new Thread(() -> {
        try {
            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String modifiedSentence = bf.readLine();

            while (modifiedSentence != null) {
                System.out.println("SERVER >> " + modifiedSentence);
                modifiedSentence = bf.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    public static InputListener inputListener = new InputListener();
    public static MouseClickListener mouseClickListener = new MouseClickListener();

    public static String getHostAddress() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createScreenshot() throws AWTException {
        BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        frameUtils.convertToFile(screenCapture);
    }

    public static void sendScreenshot() throws IOException {
        sendMessage(frameUtils.convertToBase64("/frames/frame.png"));
    }

    public static void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    public static void startClient() {
        try {
            client = new Socket(getHostAddress(), 1234);
            printWriter = new PrintWriter(client.getOutputStream());

            messanger.start();
            messageListener.start();
            inputListener.thread.start();
            mouseClickListener.thread.start();
        } catch (Exception e) {
            startClient();
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
