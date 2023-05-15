package virtual_world.renderer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LogsPanel extends JPanel {
    protected ArrayList<String> logs;

    public LogsPanel() {
        this.logs = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (String log : logs) {
            this.add(new JLabel(log));
        }
    }

    public void addLog(String log) {
        this.logs.add(log);
    }

    public void clearLogs() {
        this.logs.clear();
    }

    public void renderLogs() {
        this.removeAll();
        for (String log : logs) {
            this.add(new JLabel(log));
        }
        this.revalidate();
        this.repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 400);
    }
}
