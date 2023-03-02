package server.gui;

import server.gui.helper.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class RemoteControlFrame extends Frame {
    private final List<JPanel> screens;
    private final JPanel gridPanel;

    public RemoteControlFrame(List<JPanel> screens, String title, String iconName, Dimension size) {
        super(title, iconName, size);

        this.screens = screens;

        this.gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));

        for (JPanel screen : screens) {
            gridPanel.add(screen);
        }

        this.addPanel(gridPanel);
    }

    public void addScreen(JPanel screen) {
        gridPanel.add(screen);
        this.getMainPanel().updateUI();
    }

    public void updateScreen(int index, File newFrame) throws IOException {
        JPanel updatedScreen = new JPanel();
        JLabel frameLabel = new JLabel(new ImageIcon(ImageIO.read(newFrame)));
        updatedScreen.add(frameLabel);
        screens.add(index, updatedScreen);
        this.getMainPanel().updateUI();
    }

    public static void main(String[] args) {
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(RemoteControlFrame.class.getResource("/frames/frame.png"))); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newImg = image.getScaledInstance(1920, 1080,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newImg);  // transform it back

        JLabel frame = new JLabel(imageIcon);

        JPanel screen = new JPanel();
        screen.setSize(500, 500);
        screen.add(frame);

        JPanel screen2 = new JPanel();
        screen2.setSize(500, 500);
        screen2.add(frame);

         new RemoteControlFrame(
                 List.of(screen, screen2),
                "Remote Control - Controller",
                "bug.png",
                new Dimension()
        );
    }
}
