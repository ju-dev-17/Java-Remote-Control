package server.gui;

import server.gui.helper.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoteControlFrame extends Frame {
    public static List<JPanel> screens = new ArrayList<>();
    private final JPanel gridPanel;

    public RemoteControlFrame(String title, String iconName, Dimension size) {
        super(title, iconName, size);

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
         new RemoteControlFrame(
                "Remote Control - Controller",
                "bug.png",
                new Dimension()
        );
    }
}
