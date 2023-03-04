package client.listener;

import client.utils.FrameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static client.Client.sendMessage;

public class ServerListener implements Runnable {
    public Thread thread;
    private final FrameUtils frameUtils;
    private final InputStream clientInputStream;

    public ServerListener(InputStream inputStream) {
        this.thread = new Thread(this);
        this.frameUtils = new FrameUtils();
        this.clientInputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            InputStreamReader in = new InputStreamReader(clientInputStream);
            BufferedReader bf = new BufferedReader(in);

            String serverMessage;

            while ((serverMessage = bf.readLine()) != null) {
                if (!serverMessage.isEmpty() || !serverMessage.isBlank()) {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createScreenshot() throws AWTException {
        BufferedImage screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        frameUtils.convertToFile(screenCapture);
    }

    private void sendScreenshot() throws IOException {
        sendMessage(frameUtils.convertToBase64("/frames/frame.png"));
    }
}
