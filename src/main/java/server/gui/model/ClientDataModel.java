package server.gui.model;

import java.awt.*;

public class ClientDataModel {
    private Point mousePosition;
    private int mouseClick;
    private int pressendKey;

    public ClientDataModel(Point mousePosition, int mouseClick, int pressendKey) {
        this.mousePosition = mousePosition;
        this.mouseClick =  mouseClick;
        this.pressendKey = pressendKey;
    }

    public Point getMousePosition() {
        return mousePosition;
    }

    public void setMousePosition(Point mousePosition) {
        this.mousePosition = mousePosition;
    }

    public int getMouseClick() {
        return mouseClick;
    }

    public void setMouseClick(int mouseClickPosition) {
        this.mouseClick = mouseClickPosition;
    }

    public int getPressendKey() {
        return pressendKey;
    }

    public void setPressendKey(int pressendKey) {
        this.pressendKey = pressendKey;
    }

    @Override
    public String toString() {
        return mousePosition.x + "," + mousePosition.y + ","
                + mouseClick + "," + pressendKey;
    }
}
