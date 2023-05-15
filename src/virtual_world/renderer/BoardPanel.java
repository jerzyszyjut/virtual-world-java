package virtual_world.renderer;

import virtual_world.*;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BoardPanel extends JPanel implements ActionListener {
    protected Species chosenSpecies = Species.WOLF;
    protected JComboBox<String> chooseSpecies;
    protected JLabel cooldownInfo;
    private final World world;
    private Graphics graphics;
    private final LogsPanel logsPanel;

    public BoardPanel(World world, LogsPanel logsPanel) {
        this.world = world;
        this.logsPanel = logsPanel;
        setBorder(BorderFactory.createLineBorder(Color.black));
        initMenu();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setOrganism(e.getX(), e.getY());
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                Organism player = world.getPlayer();
                if (player != null) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> player.action(Direction.UP);
                        case KeyEvent.VK_DOWN -> player.action(Direction.DOWN);
                        case KeyEvent.VK_LEFT -> player.action(Direction.LEFT);
                        case KeyEvent.VK_RIGHT -> player.action(Direction.RIGHT);
                        case KeyEvent.VK_SPACE -> ((Human) player).useSpecialAbility();
                    }
                }

                if (e.getKeyCode() != KeyEvent.VK_SPACE) {
                    world.nextTurn();
                }
                performRedraw();
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    private void initMenu() {
        JButton next = new JButton("Next turn");
        next.setBounds(Config.BOARD_BUTTONS_BOUNDS_X, Config.BOARD_BUTTONS_BOUNDS_Y, Config.BOARD_BUTTONS_BOUNDS_WIDTH, Config.BOARD_BUTTONS_BOUNDS_HEIGHT);
        next.setActionCommand("next");
        next.addActionListener(this);
        next.setFocusable(false);
        this.add(next);

        JButton save = new JButton("Save");
        save.setBounds(Config.BOARD_BUTTONS_BOUNDS_X, Config.BOARD_BUTTONS_BOUNDS_Y, Config.BOARD_BUTTONS_BOUNDS_WIDTH, Config.BOARD_BUTTONS_BOUNDS_HEIGHT);
        save.setActionCommand("save");
        save.addActionListener(this);
        save.setFocusable(false);
        this.add(save);

        JButton load = new JButton("Load");
        load.setBounds(Config.BOARD_BUTTONS_BOUNDS_X, Config.BOARD_BUTTONS_BOUNDS_Y, Config.BOARD_BUTTONS_BOUNDS_WIDTH, Config.BOARD_BUTTONS_BOUNDS_HEIGHT);
        load.setActionCommand("load");
        load.addActionListener(this);
        load.setFocusable(false);
        this.add(load);

        chooseSpecies = new JComboBox<>(getComboboxOptions());
        chooseSpecies.setSelectedIndex(0);
        chooseSpecies.setBounds(Config.BOARD_BUTTONS_BOUNDS_X, Config.BOARD_BUTTONS_BOUNDS_Y, Config.BOARD_BUTTONS_BOUNDS_WIDTH, Config.BOARD_BUTTONS_BOUNDS_HEIGHT);
        chooseSpecies.setActionCommand("chooseSpecies");
        chooseSpecies.addActionListener(this);
        chooseSpecies.setFocusable(false);
        this.add(chooseSpecies);

        cooldownInfo = new JLabel(getCooldownInfo());
        cooldownInfo.setBounds(Config.BOARD_BUTTONS_BOUNDS_X, Config.BOARD_BUTTONS_BOUNDS_Y, Config.BOARD_BUTTONS_BOUNDS_WIDTH, Config.BOARD_BUTTONS_BOUNDS_HEIGHT);
        cooldownInfo.setFocusable(false);
        this.add(cooldownInfo);
    }

    public void actionPerformed(ActionEvent e) {
        if ("save".equals(e.getActionCommand())) {
            world.saveWorldStateToFile("save.txt");
        } else if ("load".equals(e.getActionCommand())) {
            world.loadWorldStateFromFile("save.txt");
            performRedraw();
        } else if ("next".equals(e.getActionCommand())) {
            if (world.getPlayer() != null) {
                world.getPlayer().performSpecialAbility();
            }
            world.nextTurn();
            performRedraw();
        } else if ("chooseSpecies".equals(e.getActionCommand())) {
            chosenSpecies = Species.valueOf((String) chooseSpecies.getSelectedItem());
        }
    }

    private void performRedraw() {
        renderCooldownInfo();
        repaint();
    }

    private void renderCooldownInfo() {
        cooldownInfo.setText(getCooldownInfo());
    }

    private void renderGrid() {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i <= world.getWidth(); i++) {
            graphics.drawLine(i * Config.FIELD_SIZE, Config.Y_OFFSET, i * Config.FIELD_SIZE, world.getHeight() * Config.FIELD_SIZE + Config.Y_OFFSET);
        }
        for (int i = 0; i <= world.getHeight(); i++) {
            graphics.drawLine(0, i * Config.FIELD_SIZE + Config.Y_OFFSET, world.getWidth() * Config.FIELD_SIZE, i * Config.FIELD_SIZE + Config.Y_OFFSET);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(400, 400 + Config.Y_OFFSET);
    }

    public void paintComponent(Graphics graphics) {
        logsPanel.paintComponent(graphics);
        super.paintComponent(graphics);

        this.graphics = graphics;

        renderGrid();
        for (Organism organism : world.getOrganismsList()) {
            paintOrganism(organism);
        }
    }

    public void paintOrganism(Organism organism) {
        Color borderColor = organism instanceof Animal ? Color.BLACK : Color.WHITE;
        Square square = new Square(organism.getCoordinates().getX() * Config.FIELD_SIZE,
                organism.getCoordinates().getY() * Config.FIELD_SIZE + Config.Y_OFFSET,
                organism.getColor(),
                borderColor);
        square.paintSquare(graphics);
    }

    private void setOrganism(int x, int y) {

        Coordinates position = new Coordinates(x / Config.FIELD_SIZE, (y - Config.Y_OFFSET) / Config.FIELD_SIZE);
        if (!world.isInWorld(position)) {
            return;
        }
        Organism org = null;
        if (chosenSpecies == null) {
            return;
        }
        switch (chosenSpecies) {
            case FOX -> org = new Fox(position);
            case SHEEP -> org = new Sheep(position);
            case WOLF -> org = new Wolf(position);
            case TURTLE -> org = new Turtle(position);
            case ANTELOPE -> org = new Antelope(position);
            case GRASS -> org = new Grass(position);
            case DANDELION -> org = new Dandelion(position);
            case GUARANA -> org = new Guarana(position);
            case BELLADONNA -> org = new Belladonna(position);
            case HERACLEUM_SOSNOWSKYI -> org = new HeracleumSosnowskyi(position);
        }
        if (org == null) {
            return;
        }
        world.addOrganism(org);
        performRedraw();
    }

    private String[] getComboboxOptions() {
        Species[] options = Species.allWithoutHuman();
        String[] optionsString = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            optionsString[i] = options[i].name();
        }
        return optionsString;
    }

    private String getCooldownInfo() {
        Human player = world.getPlayer();
        if (player == null) {
            return "";
        }
        if (player.isSpecialAbilityUsed()) {
            return "Ability is active for " + player.getSpecialAbilityCooldown() + " turns";
        } else if (player.getSpecialAbilityCooldown() == 0) {
            return "Ability is ready";
        } else {
            return "Ability is on cooldown for " + player.getSpecialAbilityCooldown() + " turns";
        }
    }
}
