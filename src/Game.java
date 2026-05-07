import java.util.Scanner;

/**
 * Game controls the full turn loop.
 * After each shot it prints a stats summary showing ships remaining,
 * total hits, and coordinates the player has already tried.
 */
public class Game {
    private Player human;
    private Player computer;
    private Scanner scanner;

    public Game() {
        this.human = new Player("Player");
        this.computer = new Player("Computer");
        this.scanner = new Scanner(System.in);
    }

    public void setup() {
        human.getBoard().placeFleetRandomly(human.buildFleet());
        computer.getBoard().placeFleetRandomly(computer.buildFleet());
    }

    public void play() {
        System.out.println("=== BATTLESHIP ===");
        System.out.println("Sink all 5 enemy ships to win!");
        System.out.println("Fleet: Carrier(5) Battleship(4) Cruiser(3) Submarine(3) Destroyer(2)\n");

        while (true) {
            // Show enemy board (ships hidden)
            System.out.println("Enemy waters:");
            System.out.println(computer.getBoard().render());

            // Show player's own board
            System.out.println("Your fleet:");
            System.out.println(human.getBoard().render());

            // Human turn
            int[] shot = human.takeTurn(scanner, computer.getBoard());
            String result = computer.getBoard().receiveShot(shot[0], shot[1]);
            System.out.println("-> " + result.toUpperCase());

            // Print stats after human shot
            human.printStats(computer.getBoard());

            if (computer.getBoard().allSunk()) {
                System.out.println("\nYou win! All enemy ships sunk.");
                break;
            }

            // Computer turn (random)
            int[] cshot = getComputerShot();
            String cresult = human.getBoard().receiveShot(cshot[0], cshot[1]);
            System.out.println("Computer fired at " + cshot[0] + " " + cshot[1] + " -> " + cresult.toUpperCase() + "\n");

            if (human.getBoard().allSunk()) {
                System.out.println("\nYou lose. Your fleet is destroyed.");
                break;
            }
        }
    }

    /** Computer picks a random unused cell. */
    private int[] getComputerShot() {
        java.util.Random rng = new java.util.Random();
        while (true) {
            int r = rng.nextInt(Board.SIZE);
            int c = rng.nextInt(Board.SIZE);
            if (human.getBoard().getCell(r, c) != Board.HIT &&
                human.getBoard().getCell(r, c) != Board.MISS) {
                return new int[]{r, c};
            }
        }
    }

    public Player getHuman() { return human; }
    public Player getComputer() { return computer; }
}
//hi
