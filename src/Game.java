import java.util.Scanner;

/**
 * Controls the overall game flow: setup, turn loop, win check.
 * Uses ComputerPlayer (extends Player) for the computer opponent,
 * demonstrating polymorphism — same takeTurn call, different behavior.
 */
public class Game {
    private Player human;
    private Player computer;
    private Scanner scanner;

    public Game() {
        this.human = new Player("Player");
        this.computer = new ComputerPlayer("Computer");
        this.scanner = new Scanner(System.in);
    }

    /** Place both fleets randomly. */
    public void setup() {
        human.getBoard().placeFleetRandomly(human.buildFleet());
        computer.getBoard().placeFleetRandomly(computer.buildFleet());
    }

    /** Main game loop — alternates turns until someone wins. */
    public void play() {
        System.out.println("=== BATTLESHIP ===");
        System.out.println("Sink all 5 enemy ships to win!");
        System.out.println("Fleet: Carrier(5) Battleship(4) Cruiser(3) Submarine(3) Destroyer(2)\n");

        while (true) {
            // Show enemy board with ships hidden
            System.out.println("Enemy waters:");
            System.out.println(computer.getBoard().render(true));

            // Show your own board with ships visible
            System.out.println("Your fleet:");
            System.out.println(human.getBoard().render(false));

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

            // Computer turn
            int[] cshot = computer.takeTurn(scanner, human.getBoard());
            String cresult = human.getBoard().receiveShot(cshot[0], cshot[1]);
            System.out.println("Computer: " + cresult.toUpperCase() + "\n");

            if (human.getBoard().allSunk()) {
                System.out.println("\nYou lose. Your fleet is destroyed.");
                break;
            }
        }
    }

    public Player getHuman() { return human; }
    public Player getComputer() { return computer; }
}
