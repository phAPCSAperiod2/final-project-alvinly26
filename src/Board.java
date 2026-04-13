import java.util.ArrayList;
import java.util.Random;

/**
 * Sprint 1: the 10x10 board.
 * Owns the 2D char array, supports ship placement, and can print itself.
 * No shot handling yet — that's Sprint 2.
 */
public class Board {
    public static final int SIZE = 10;
    public static final char WATER = '~';
    public static final char SHIP = 'S';

    private char[][] grid;
    private ArrayList<Ship> fleet;

    public Board() {
        grid = new char[SIZE][SIZE];
        fleet = new ArrayList<>();
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = WATER;
            }
        }
    }

    public boolean placeShip(Ship ship, int row, int col, boolean horizontal) {
        if (horizontal) {
            if (col + ship.getSize() > SIZE)
                return false;
        } else {
            if (row + ship.getSize() > SIZE)
                return false;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            if (grid[r][c] != WATER)
                return false;
        }
        for (int i = 0; i < ship.getSize(); i++) {
            int r = horizontal ? row : row + i;
            int c = horizontal ? col + i : col;
            grid[r][c] = SHIP;
            ship.addCoordinate(r, c);
        }
        fleet.add(ship);
        return true;
    }

    public void placeFleetRandomly(ArrayList<Ship> ships) {
        Random rng = new Random();
        for (Ship s : ships) {
            boolean placed = false;
            while (!placed) {
                int r = rng.nextInt(SIZE);
                int c = rng.nextInt(SIZE);
                boolean horiz = rng.nextBoolean();
                placed = placeShip(s, r, c, horiz);
            }
        }
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("   ");
        for (int c = 0; c < SIZE; c++)
            sb.append(c).append(" ");
        sb.append("\n");
        for (int r = 0; r < SIZE; r++) {
            sb.append(r).append("  ");
            for (int c = 0; c < SIZE; c++) {
                sb.append(grid[r][c]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getGrid() {
        return grid;
    }

    public ArrayList<Ship> getFleet() {
        return fleet;
    }
}
