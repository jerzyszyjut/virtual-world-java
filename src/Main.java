import virtual_world.World;

public class Main {
    public static void main(String[] args) {
        World world = new World(10, 10);
        world.saveWorldStateToFile("world.txt");
        world.loadWorldStateFromFile("world.txt");
    }
}