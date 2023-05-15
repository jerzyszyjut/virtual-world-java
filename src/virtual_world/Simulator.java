package virtual_world;

import virtual_world.renderer.Renderer;

public class Simulator {
    private final Renderer renderer;
    private final World world;

    public Simulator() {
        this.world = new World(20, 20);
        this.renderer = new Renderer(this.world);
    }
}
