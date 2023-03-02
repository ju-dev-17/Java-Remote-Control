package client;

import client.listener.Messanger;
import client.utils.FrameUtils;
import client.utils.HostUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Client {
    static Socket client;
    static PrintWriter printWriter;

    static final FrameUtils frameUtils = new FrameUtils();
    static final HostUtils hostUtils = new HostUtils();
    static final Messanger messanger = new Messanger();

    static Thread messageListener = new Thread(() -> {
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
            client = new Socket(hostUtils.getHostAddress(), 1234);
            printWriter = new PrintWriter(client.getOutputStream());

            messanger.thread.start();
            messageListener.start();
        } catch (Exception e) {
            startClient();
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
