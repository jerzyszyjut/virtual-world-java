package virtual_world;

import virtual_world.renderer.Renderer;

public class Simulator {
    private Renderer renderer;
    private World world;
    public Simulator() {
        this.world = new World(10, 10);
        this.renderer = new Renderer(this.world);
    }
}
