package server.gui.helper;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Frame extends JFrame {
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
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(true);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/" + iconName))).getImage());
        setLocationRelativeTo(null);
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        add(mainPanel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addPanel(JPanel panel) {
        mainPanel.add(panel);
        mainPanel.updateUI();
    }
}
