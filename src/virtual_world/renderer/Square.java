package virtual_world.renderer;

import virtual_world.Config;

import java.awt.*;

class Square {

    private final int width = Config.FIELD_SIZE;
    private final int height = Config.FIELD_SIZE;
    private final Color borderColor;
    private int x;
    private int y;
    private Color color;

    public Square(int x, int y, Color color, Color borderColor) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.borderColor = borderColor;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void setX(int xPos) {
        this.x = xPos;
    }


    public void setY(int yPos) {
        this.y = yPos;
    }


    public void paintSquare(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(this.borderColor);
        graphics.drawRect(x, y, width, height);
    }
}
