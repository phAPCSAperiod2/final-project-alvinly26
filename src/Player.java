import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Player owns a Board and can take turns firing shots.
 * Tracks shot history so the player can see coordinates already tried.
 */
public class Player {
    private String name;
    private Board board;
    private ArrayList<String> shotHistory;  // tracks coords already tried

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.shotHistory = new ArrayList<>();
    }

    public ArrayList<Ship> buildFleet() {
        ArrayList<Ship> fleet = new ArrayList<>();
        fleet.add(new Ship("Carrier", 5));
        fleet.add(new Ship("Battleship", 4));
        fleet.add(new Ship("Cruiser", 3));
        fleet.add(new Ship("Submarine", 3));
        fleet.add(new Ship("Destroyer", 2));
        return fleet;
    }

    public int[] takeTurn(Scanner scanner, Board opponentBoard) {
        while (true) {
            System.out.print(name + ", enter shot as 'row col' (0-9): ");
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Please enter two numbers separated by a space.");
                continue;
            }
            try {
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                if (r < 0 || r >= Board.SIZE || c < 0 || c >= Board.SIZE) {
                    System.out.println("Out of bounds. Use 0-9.");
                    continue;
                }
                char cell = opponentBoard.getCell(r, c);
                if (cell == Board.HIT || cell == Board.MISS) {
                    System.out.println("You already shot there. Try again.");
                    continue;
                }
                shotHistory.add(r + "," + c);
                return new int[]{r, c};
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number. Try again.");
            }
        }
    }

    /** Print a stats summary for this player's shots against the opponent. */
    public void printStats(Board opponentBoard) {
        System.out.println("--- Your Stats ---");
        System.out.println("Ships remaining: " + opponentBoard.shipsRemaining());
        System.out.println("Total hits: " + opponentBoard.totalHits());
        System.out.print("Shots tried: ");
        if (shotHistory.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(String.join("  ", shotHistory));
        }
        System.out.println("------------------");
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Board getBoard() { return board; }
    public ArrayList<String> getShotHistory() { return shotHistory; }
}
