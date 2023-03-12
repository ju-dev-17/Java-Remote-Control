package server.gui;

import server.gui.helper.Frame;
import server.gui.model.ClientDataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Objects;

public class RemoteControlFrame extends Frame {
    private final BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

    private final Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
            cursorImg, new Point(0, 0), "blank cursor");

    private Point mousePosition;
    private int mouseClick;
    private int pressendKey;

    public RemoteControlFrame() {
        super("Remote Control - Controller", "bug.png", new Dimension());

        mousePosition = MouseInfo.getPointerInfo().getLocation();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleLockKeys(e.getKeyCode());
                pressendKey = e.getKeyCode();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        getMainPanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mouseClick = e.getButton();
            }
        });

        getMainPanel().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (getMainPanel().contains(e.getPoint())) {
                    // mouse is over the panel, do something
                    mousePosition = e.getPoint();
                }
            }
        });
    }

    private void handleLockKeys(int keyCode) {
        if (keyCode == 112) { // F1 = UNLOCK
            getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
            setBounds(0, 0, getWidth(), getHeight());
            getContentPane().setCursor(Cursor.getDefaultCursor());
        } else if (keyCode == 113) { // F2 = LOCK
            getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
            setBounds(getGraphicsConfiguration().getBounds());
            getContentPane().setCursor(blankCursor);
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

    public ClientDataModel getClientDataModel() {
        return new ClientDataModel(mousePosition, mouseClick, pressendKey);
    }
}
