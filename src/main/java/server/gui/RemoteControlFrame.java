package server.gui;

import server.gui.helper.Frame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RemoteControlFrame extends Frame {
    public RemoteControlFrame() {
        super("Remote Control - Controller", "bug.png", new Dimension());

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleLockKeys(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void handleLockKeys(int keyCode) {
        if (keyCode == 112) { // F1
            getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
            setBounds(0, 0, getWidth(), getHeight());
        } else if (keyCode == 113) { // F2

            setBounds(getGraphicsConfiguration().getBounds());
            getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        }
    }

    public void refreshScreen() {
        this.getMainPanel().updateUI();
    }

    public static void main(String[] args) {
         new RemoteControlFrame();
    }
}
