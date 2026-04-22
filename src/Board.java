import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a 10x10 Battleship board.
 * Uses a 2D char array to track cell states:
 * '~' = water (empty)
 * 'S' = ship
 * 'X' = hit (NEW in Sprint 2)
 * 'O' = miss (NEW in Sprint 2)
 */
public class Board {
    public static final int SIZE = 10;
    public static final char WATER = '~';
    public static final char SHIP = 'S';
    public static final char HIT = 'X'; // NEW
    public static final char MISS = 'O'; // NEW

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

    /** NEW: process an incoming shot. Returns "hit", "miss", or "repeat". */
    public String receiveShot(int row, int col) {
        char cell = grid[row][col];
        if (cell == HIT || cell == MISS)
            return "repeat";
        if (cell == SHIP) {
            grid[row][col] = HIT;
            for (Ship s : fleet) {
                if (s.registerHit(row, col))
                    break;
            }
            return "hit";
        }
        grid[row][col] = MISS;
        return "miss";
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

    public char getCell(int r, int c) {
        return grid[r][c];
    } // NEW
}
