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
        setSize(getMaximumSize());
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/" + iconName))).getImage());
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        createLayout(mainPanel);

        add(mainPanel);
    }

    private void createLayout(JPanel panel) {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbl.setConstraints(panel, gbc);
        panel.setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel placeholder = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/frames/frame.png"))));
        placeholder.setName("Placeholder");
        panel.add(placeholder, gbc);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
