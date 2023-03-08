package server.gui.model;

import java.awt.*;

public class ClientDataModel {
    private Point mousePosition;
    private String pressendKey;

    public ClientDataModel(Point mousePosition, String pressendKey) {
        this.mousePosition = mousePosition;
        this.pressendKey = pressendKey;
    }

    public Point getMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public String getPressendKey() {
        return pressendKey;
    }

    public void setPressendKey(String pressendKey) {
        this.pressendKey = pressendKey;
    }
}
