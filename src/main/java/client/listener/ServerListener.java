package client.listener;

import client.utils.FrameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class ServerListener implements Runnable {
    private final Thread thread = new Thread(this);
    private final FrameUtils frameUtils = new FrameUtils();
    private final Robot robot = new Robot();

    private final PrintWriter printWriter;
    private final InputStreamReader inputStreamReader;

    public ServerListener(PrintWriter printWriter, InputStreamReader inputStreamReader) throws AWTException {
        this.printWriter = printWriter;
        this.inputStreamReader = inputStreamReader;
    }

    private void sendMessage(String message) {
        printWriter.println(message);
        printWriter.flush();
    }

    private void handleMessage(String message) {
        // Process raw message
        String[] messageContent = message.split(",");
        // 1: int, 2: int, 3: boolean || String, 4: String || boolean
        if (messageContent[2].equals("true") || messageContent[2].equals("false")) {

        } else if (messageContent[2].equals("t")) {

        }
        robot.mouseMove(Integer.parseInt(messageContent[0]), Integer.parseInt(messageContent[1]));
    }

    private void createScreenshot() {
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        frameUtils.convertToFile(screenCapture);
    }

    private void sendScreenshot() throws IOException {
        sendMessage(frameUtils.convertToBase64("/frames/frame.png"));
    }

    @Override
    public void run() {
        thread.start();
        try {
            BufferedReader bf = new BufferedReader(inputStreamReader);

            String serverMessage;

            while ((serverMessage = bf.readLine()) != null) {
                handleMessage(serverMessage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
