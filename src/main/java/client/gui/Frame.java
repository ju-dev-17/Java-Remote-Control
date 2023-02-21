package client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Frame extends JFrame {
    boolean fullscreen = false;

    String title;
    String iconName;
    Dimension size;

    JPanel mainPanel;

    public Frame(String title, String iconName, Dimension size){
        this.title = title;
        this.iconName = iconName;
        this.size = size;

        initFrame();
        initMainPanel();

        this.setVisible(true);
    }

    private void initFrame() {
        setTitle(title);
        if (fullscreen) {
            setExtendedState(MAXIMIZED_BOTH);
            setResizable(true);
        } else {
            setSize(size);
            setResizable(false);
        }
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/" + iconName))).getImage());
        setLocationRelativeTo(null);
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        add(mainPanel);
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        initFrame();
    }

    public void addPanel(JPanel panel, String layout) {
        mainPanel.add(panel, layout);
        mainPanel.updateUI();
    }
}
