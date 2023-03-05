package server.gui;

import server.gui.helper.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Objects;

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
        if (keyCode == 112) { // F1 = UNLOCK
            getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
            setBounds(0, 0, getWidth(), getHeight());
        } else if (keyCode == 113) { // F2 = LOCK
            setBounds(getGraphicsConfiguration().getBounds());
            getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        }
        updateScreenImage();
    }

    private void updateScreenImage() {
        JLabel placeholder = (JLabel) Arrays.stream(getMainPanel().getComponents())
               .filter(component -> component.getName().equals("Placeholder"))
               .toList()
               .get(0);
        placeholder.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/frames/frame.png"))));
        placeholder.updateUI();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
         new RemoteControlFrame();
    }
}
