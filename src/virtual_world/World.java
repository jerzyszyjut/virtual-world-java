package virtual_world;

import virtual_world.organisms.Organism;

public class World {
    private final int turn, width, height;
    private final Organism[][] organisms;
    private Organism player;

    public World(int width, int height) {
        this.turn = 0;
        this.width = width;
        this.height = height;
        this.organisms = new Organism[width][height];
        this.player = null;
    }

    public void print() {}
    public void nextTurn() {}
    public void addOrganism(Organism organism) {
        organism.setWorld(this);
        Coordinates coordinates = organism.getCoordinates();
        this.organisms[coordinates.getX()][coordinates.getY()] = organism;
    }

    public void removeOrganism(Organism organism) {
        Coordinates coordinates = organism.getCoordinates();
        this.organisms[coordinates.getX()][coordinates.getY()] = null;
    }

    public Organism getOrganism(Coordinates coordinates) {
        return this.organisms[coordinates.getX()][coordinates.getY()];
    }

    public void setPlayer(Organism player) {
        this.player = player;
    }

    public void removeDeadOrganisms() {
        for(int i=0; i<this.width; i++) {
            for(int j=0; j<this.height; j++) {
                if(this.organisms[i][j] != null && !this.organisms[i][j].isAlive()) {
                    this.removeOrganism(this.organisms[i][j]);
                }
            }
        }
    }

    public boolean isInWorld(Coordinates coordinates) {
        return coordinates.getX() >= 0 && coordinates.getX() < this.width && coordinates.getY() >= 0 && coordinates.getY() < this.height;
    }

    public void moveOrganism(Organism organism, Coordinates coordinates) {
        Coordinates oldCoordinates = organism.getCoordinates();
        this.organisms[oldCoordinates.getX()][oldCoordinates.getY()] = null;
        this.organisms[coordinates.getX()][coordinates.getY()] = organism;
        organism.setCoordinates(coordinates);
    }

    public void saveWorldStateToFile(String filename) {
        // TODO
    }

    public void loadWorldStateFromFile(String filename) {
        // TODO
    }

    public int getTurn() { return turn; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Organism[][] getOrganisms() { return organisms; }
    public Organism getPlayer() { return player; }
}
