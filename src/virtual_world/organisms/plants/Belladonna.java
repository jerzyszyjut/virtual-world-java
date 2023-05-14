package virtual_world.organisms.plants;

import virtual_world.Config;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Belladonna extends Plant {
    public Belladonna() { super(Config.BELLADONNA_STRENGTH, Species.BELLADONNA); }
    @Override
    public void draw() {}
    @Override
    public Organism clone() { return new Belladonna(); }
}
