package virtual_world;

import virtual_world.organisms.Organism;
import virtual_world.organisms.OrganismComparator;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class World {
    private final Organism[][] organisms;
    private int turn;
    private int width;
    private int height;
    private Organism player;
    private final ArrayList<String> logs = new ArrayList<>();

    public World(int width, int height) {
        this.turn = 0;
        this.width = width;
        this.height = height;
        this.organisms = new Organism[width][height];
        this.player = new Human();
        addOrganism(this.player);
        addOrganism(new Wolf(new Coordinates(5, 5)));
        addOrganism(new Sheep(new Coordinates(3, 3)));
    }

    public ArrayList<Organism> getOrganismsList() {
        ArrayList<Organism> organisms = new ArrayList<>();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.organisms[i][j] != null && this.organisms[i][j].isAlive()) {
                    organisms.add(this.organisms[i][j]);
                }
            }
        }
        return organisms;
    }

    public void nextTurn() {
        ArrayList<Organism> organisms = getOrganismsList();

        organisms.sort(new OrganismComparator());

        for (Organism organism : organisms) {
            if(organism.isAlive())
            {
                if(organism.getSpecies() != Species.HUMAN)
                {
                    organism.action();
                }
                organism.incrementAge();
            }
        }
        removeDeadOrganisms();
    }

    public void addOrganism(Organism organism) {
        organism.setWorld(this);
        Coordinates coordinates = organism.getCoordinates();
        this.organisms[coordinates.getX()][coordinates.getY()] = organism;
    }

    public void removeOrganism(Organism organism) {
        Coordinates coordinates = organism.getCoordinates();
        if(organism.getSpecies() == Species.HUMAN)
        {
            this.player = null;
        }
        this.organisms[coordinates.getX()][coordinates.getY()] = null;
    }


    public Organism getOrganism(Coordinates coordinates) {
        return this.getOrganism(coordinates.getX(), coordinates.getY());
    }

    public Organism getOrganism(int x, int y) {
        if (!this.isInWorld(x, y))
            return null;
        return this.organisms[x][y];
    }

    public void removeDeadOrganisms() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.organisms[i][j] != null && !this.organisms[i][j].isAlive()) {
                    this.removeOrganism(this.organisms[i][j]);
                }
            }
        }
    }

    public boolean isInWorld(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < this.width && coordinates.getY() >= 0 && coordinates.getY() < this.height;
    }

    public boolean isInWorld(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public void moveOrganism(Organism organism, Coordinates coordinates) {
        if(!this.isInWorld(coordinates))
            return;
        Coordinates oldCoordinates = organism.getCoordinates();
        this.organisms[oldCoordinates.getX()][oldCoordinates.getY()] = null;
        this.organisms[coordinates.getX()][coordinates.getY()] = organism;
        organism.setCoordinates(coordinates);
    }

    public void saveWorldStateToFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(this.turn + "\n");
            fileWriter.write(this.width + "\n");
            fileWriter.write(this.height + "\n");
            if (player != null) {
                fileWriter.write(player + "\n");
            } else {
                fileWriter.write("null\n");
            }

            ArrayList<Organism> organisms = getOrganismsList();
            for (Organism organism : organisms) {
                if(organism.getSpecies() != Species.HUMAN)
                {
                    fileWriter.write(organism.toString() + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearOrganisms()
    {
        for(int i = 0; i < this.width; i++)
        {
            for(int j = 0; j < this.height; j++)
            {
                this.organisms[i][j] = null;
            }
        }
    }

    public void loadWorldStateFromFile(String filename) {
        try {
            clearOrganisms();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            this.turn = Integer.parseInt(bufferedReader.readLine());
            this.width = Integer.parseInt(bufferedReader.readLine());
            this.height = Integer.parseInt(bufferedReader.readLine());
            String playerString = bufferedReader.readLine();
            if (playerString.equals("null")) {
                this.player = null;
            } else {
                this.player = new Human();
                this.player.fromString(playerString);
                addOrganism(this.player);
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] organismString = line.split("\t");
                Organism organism = null;
                Species species = Species.valueOf(organismString[0]);
                switch (species)
                {
                    case WOLF:
                        organism = new Wolf();
                        break;
                    case SHEEP:
                        organism = new Sheep();
                        break;
                    case FOX:
                        organism = new Fox();
                        break;
                    case TURTLE:
                        organism = new Turtle();
                        break;
                    case ANTELOPE:
                        organism = new Antelope();
                        break;
                    case GRASS:
                        organism = new Grass();
                        break;
                    case DANDELION:
                        organism = new Dandelion();
                        break;
                    case GUARANA:
                        organism = new Guarana();
                        break;
                    case BELLADONNA:
                        organism = new Belladonna();
                        break;
                    case HERACLEUM_SOSNOWSKYI:
                        organism = new HeracleumSosnowskyi();
                        break;
                }
                organism.fromString(line);
                this.addOrganism(organism);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTurn() {
        return turn;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Organism[][] getOrganisms() {
        return organisms;
    }

    public Organism getPlayer() {
        return player;
    }

    public void setPlayer(Organism player) {
        this.player = player;
    }

    public void addLog(String log) {
        this.logs.add(log);
    }

    public ArrayList<String> getLogs() {
        return this.logs;
    }

    public void clearLogs() {
        this.logs.clear();
    }
}
