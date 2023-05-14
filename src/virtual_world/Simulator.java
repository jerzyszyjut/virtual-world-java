package xyz.kwasniak.Simulator;

import xyz.kwasniak.Renderer.Renderer;
import xyz.kwasniak.World.World;
import xyz.kwasniak.Config;

public class Simulator {
    private Renderer renderer;
    private World world;
    public void init() {

        this.world = new World();
        this.renderer = new Renderer();
        world.init(Config.WORLD_N, Config.WORLD_M);


        connectRendererToWorld();
        renderer.init();


    }

    private void connectRendererToWorld() {
        renderer.setWorld(world);
    }
}
