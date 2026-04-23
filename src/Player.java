import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sprint 2: a Player has a name and a Board, and can be prompted
 * for a shot coordinate from the console.
 */
public class Player {
    private String name;
    private Board board;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
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
                return new int[] { r, c };
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number. Try again.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }
}
