package virtual_world.organisms.animals;

import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Wolf extends Animal {
    public Wolf() {
        super(9, 5, Species.WOLF);
    }

    public void draw() {}
    public Organism clone() { return new Wolf(); }
}
