package virtual_world.organisms.plants;

import virtual_world.Config;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Grass extends Plant {
    public Grass() { super(Config.GRASS_STRENGTH, Species.GRASS); }
    @Override
    public void draw() {}
    @Override
    public Organism clone() {return new Grass();}
}
