package virtual_world.renderer;

import virtual_world.Config;
import virtual_world.World;

import javax.swing.*;
import java.awt.*;

public class LogsPanel extends JPanel {
    private final JTextArea textArea;
    private final JLabel turnCounter;
    private final World world;
    private int previousTurn = 0;

    public LogsPanel(World world) {
        this.world = world;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.turnCounter = new JLabel("Turn: " + world.getTurn());
        this.add(this.turnCounter);

        this.textArea = new JTextArea(10, 20);
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(this.textArea);
        this.add(scrollPane);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (previousTurn != world.getTurn()) {
            this.previousTurn = world.getTurn();
        } else {
            return;
        }
        this.turnCounter.setText("Turn: " + world.getTurn());
        this.clearLogs();

        this.addLog("Turn: " + world.getTurn());
        this.world.getLogs().forEach(this::addLog);
        this.world.clearLogs();
    }

    public void addLog(String log) {
        this.textArea.append(log + "\n");
    }

    public void clearLogs() {
        this.textArea.setText("");
    }

    public Dimension getPreferredSize() {
        return new Dimension(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
    }
}
