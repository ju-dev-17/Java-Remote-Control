package client.listener;

import client.utils.FrameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static client.Client.sendMessage;

public class ServerListener implements Runnable {
    public Thread thread;
    private final FrameUtils frameUtils;
    private final Socket clientSocket;

    private final Robot robot;

    public ServerListener(Socket clientSocket) throws AWTException {
        this.thread = new Thread(this);
        this.frameUtils = new FrameUtils();
        this.clientSocket = clientSocket;
        this.robot = new Robot();
    }

    @Override
    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String serverMessage;

            while ((serverMessage = bf.readLine()) != null) {
                String[] message = serverMessage.split(",");
                System.out.println(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleServerResponse(int x, int y) {
        robot.mouseMove(x, y);
    }

    private void createScreenshot() {
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        frameUtils.convertToFile(screenCapture);
    }

    private void sendScreenshot() throws IOException {
        sendMessage(frameUtils.convertToBase64("/frames/frame.png"));
    }
}
