package virtual_world;

import virtual_world.renderer.Renderer;

public class Simulator {
    private final Renderer renderer;
    private final World world;

    public Simulator() {
        this.world = new World(Config.MAP_WIDTH, Config.MAP_HEIGHT);
        this.renderer = new Renderer(this.world);
    }
}
