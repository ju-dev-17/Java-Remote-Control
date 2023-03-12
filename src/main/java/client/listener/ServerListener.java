package client.listener;

import client.utils.FrameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServerListener implements Runnable {
    private final FrameUtils frameUtils = new FrameUtils();

    public final Thread thread = new Thread(this);
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

    private boolean checkWelcomeMessage(String message) {
        return !message.contains(",");
    }

    private void handleMessage(String message) {
        String[] messageContent = message.split(",");
        // process server message content
        if (!messageContent[0].equals("null") && !messageContent[1].equals("null")) {
            robot.mouseMove(Integer.parseInt(messageContent[0]), Integer.parseInt(messageContent[1]));
        }

        if (!messageContent[2].equals("0")) {
            robot.mousePress(Integer.parseInt(messageContent[2]));
            robot.mouseRelease(Integer.parseInt(messageContent[2]));
        }

        if (!messageContent[3].equals("0")) {
            robot.keyPress(Integer.parseInt(messageContent[3]));
            robot.keyRelease(Integer.parseInt(messageContent[3]));
        }
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
        try {
            BufferedReader bf = new BufferedReader(inputStreamReader);

            String serverMessage;

            while ((serverMessage = bf.readLine()) != null) {
                if (checkWelcomeMessage(serverMessage)) {
                    System.out.println(serverMessage);
                } else {
                    handleMessage(serverMessage);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
