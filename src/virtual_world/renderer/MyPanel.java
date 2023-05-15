package virtual_world.renderer;

import virtual_world.*;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyPanel extends JPanel implements ActionListener {
    protected Species chosenSpecies = Species.WOLF;
    protected JComboBox<String> chooseSpecies;
    protected JLabel cooldownInfo;
    World world;
    Graphics g;

    public MyPanel(World world) {
        this.world = world;
        setBorder(BorderFactory.createLineBorder(Color.black));
        initMenu();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setOrganism(e.getX(), e.getY());
            }
        });

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Organism player = world.getPlayer();
                if (player != null) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> player.action(Direction.UP);
                        case KeyEvent.VK_DOWN -> player.action(Direction.DOWN);
                        case KeyEvent.VK_LEFT -> player.action(Direction.LEFT);
                        case KeyEvent.VK_RIGHT -> player.action(Direction.RIGHT);
                        case KeyEvent.VK_SPACE -> ((Human)player).useSpecialAbility();
                    }
                }

                if(e.getKeyCode() != KeyEvent.VK_SPACE)
                {
                    world.nextTurn();
                }
                performRedraw();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    private void initMenu() {
        JButton next = new JButton("Next turn");
        next.setBounds(50, 100, 95, 30);
        next.setActionCommand("next");
        next.addActionListener(this);
        next.setFocusable(false);
        this.add(next);

        JButton save = new JButton("Save");
        save.setBounds(50, 100, 95, 30);
        save.setActionCommand("save");
        save.addActionListener(this);
        save.setFocusable(false);
        this.add(save);

        JButton load = new JButton("Load");
        load.setBounds(50, 100, 95, 30);
        load.setActionCommand("load");
        load.addActionListener(this);
        load.setFocusable(false);
        this.add(load);

        chooseSpecies = new JComboBox<>(getComboboxOptions());
        chooseSpecies.setSelectedIndex(0);
        chooseSpecies.setBounds(50, 100, 95, 30);
        chooseSpecies.setActionCommand("chooseSpecies");
        chooseSpecies.addActionListener(this);
        chooseSpecies.setFocusable(false);
        this.add(chooseSpecies);

        cooldownInfo = new JLabel(getCooldownInfo());
        cooldownInfo.setBounds(50, 100, 95, 30);
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
            ((Human)world.getPlayer()).performSpecialAbility();
            world.nextTurn();
            performRedraw();
        } else if ("chooseSpecies".equals(e.getActionCommand())) {
            chosenSpecies = Species.valueOf((String) chooseSpecies.getSelectedItem());
        }
    }

    private void performRedraw() {
        for (Organism organism : world.getOrganismsList()) {
            Square square = new Square();
            square.setColor(organism.getColor());
            square.setX(organism.getCoordinates().getX() * Config.FIELD_SIZE);
            square.setY(organism.getCoordinates().getY() * Config.FIELD_SIZE);
            repaint();
            renderLogs();
            renderCooldownInfo();
        }
    }

    private void renderLogs() {
        for (String log : world.getLogs()) {
            System.out.println(log);
        }
    }

    private void renderCooldownInfo() {
        cooldownInfo.setText(getCooldownInfo());
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g = g;

        for (Organism organism : world.getOrganismsList()) {
            Square square = new Square();
            square.setColor(organism.getColor());
            square.setX(organism.getCoordinates().getX() * Config.FIELD_SIZE);
            square.setY(organism.getCoordinates().getY() * Config.FIELD_SIZE);
            square.paintSquare(g);
        }
    }

    private void setOrganism(int x, int y) {

        Coordinates pos = new Coordinates(x / Config.FIELD_SIZE, y / Config.FIELD_SIZE);
        if (!world.isInWorld(pos)) {
            return;
        }
        Organism org = null;
        if (chosenSpecies == null) {
            return;
        }
        switch (chosenSpecies) {
            case FOX -> org = new Fox(pos);
            case SHEEP -> org = new Sheep(pos);
            case WOLF -> org = new Wolf(pos);
            case TURTLE -> org = new Turtle(pos);
            case ANTELOPE -> org = new Antelope(pos);
            case GRASS -> org = new Grass(pos);
            case DANDELION -> org = new Dandelion(pos);
            case GUARANA -> org = new Guarana(pos);
            case BELLADONNA -> org = new Belladonna(pos);
            case HERACLEUM_SOSNOWSKYI -> org = new HeracleumSosnowskyi(pos);
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
        Human player = (Human)world.getPlayer();
        if(player == null)
        {
            return "";
        }
        if(player.isSpecialAbilityUsed())
        {
            return "Ability is active for " + player.getSpecialAbilityCooldown() + " turns";
        }
        else if(player.getSpecialAbilityCooldown() == 0) {
            return "Ability is ready";
        }
        else {
            return "Ability is on cooldown for " + player.getSpecialAbilityCooldown() + " turns";
        }
    }
}
