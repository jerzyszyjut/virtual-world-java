package virtual_world.renderer;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {
    private final int x;
    private final int y;
    private final int size;
    private final Color color;

    public Square(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }
}