package virtual_world;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE;

    public static Direction randomDirection()
    {
        Direction[] directions = Direction.values();
        directions = java.util.Arrays.copyOf(directions, directions.length - 1);
        return directions[(int)(Math.random() * directions.length)];
    }
}
