package virtual_world;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE;

    public static Direction randomDirection() {
        Direction[] directions = Direction.values();
        directions = java.util.Arrays.copyOf(directions, directions.length - 1);
        return directions[(int) (Math.random() * directions.length)];
    }

    public static Direction fromOffset(int x, int y) {
        if (x == 0 && y == -1) {
            return UP;
        } else if (x == 0 && y == 1) {
            return DOWN;
        } else if (x == -1 && y == 0) {
            return LEFT;
        } else if (x == 1 && y == 0) {
            return RIGHT;
        } else {
            return NONE;
        }
    }
}
