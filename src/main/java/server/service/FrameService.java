package server.service;

import server.service.handler.FrameHandler;
import server.service.socket.CustomServerSocket;

import java.io.IOException;

public class FrameService {
    private final CustomServerSocket customServerSocket;
    private final FrameHandler frameHandler;

    public FrameService(CustomServerSocket customServerSocket, FrameHandler frameHandler) {
        this.customServerSocket = customServerSocket;
        this.frameHandler = frameHandler;
    }

    public void readFrame(String encodedString) throws IOException {
        frameHandler.convertToImage(encodedString);
    }

    public String sendFrame() throws IOException {
        return frameHandler.convertToBase64();
    }
}
