package client.gui.remote;

import client.gui.Frame;

import java.awt.*;
import java.io.IOException;

public class RemoteControlFrame extends Frame {
    public RemoteControlFrame(String title, String iconName, Dimension size) {
        super(title, iconName, size);
    }

    public static void main(String[] args)  {
        RemoteControlFrame remoteControlFrame = new RemoteControlFrame(
                "Remote Control - Controller",
                "bug.png",
                new Dimension()
        );
        remoteControlFrame.setFullscreen(true);
    }
}
