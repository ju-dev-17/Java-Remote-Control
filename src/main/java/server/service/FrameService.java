package server.service;

import server.service.socket.CustomServerSocket;
import server.service.socket.CustomSocket;

import java.io.IOException;

public class FrameService {
    private final CustomServerSocket customServerSocket;
    private final CustomSocket customSocket;

    public FrameService(CustomServerSocket customServerSocket, CustomSocket customSocket) {
        this.customServerSocket = customServerSocket;
        this.customSocket = customSocket;
    }

    public void readFrame(String encodedString) throws IOException {
        customSocket.convertToImage(encodedString);
    }

    public String sendFrame() throws IOException {
        return customSocket.convertToBase64();
    }
}
