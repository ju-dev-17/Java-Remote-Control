package server.controller;

import server.service.FrameService;

import java.io.IOException;

public class FrameController {
    private final FrameService service;

    public FrameController(FrameService service) {
        this.service = service;
    }

    public String getFrame() {
        try {
            return service.sendFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void postFrame(String encodedString) {
        try {
            service.readFrame(encodedString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
