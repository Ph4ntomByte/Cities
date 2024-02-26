import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playAgain = playGame(scanner);
        } while (playAgain);

        System.out.println("Thank you for playing!");
    }

    private static boolean playGame(Scanner scanner) {
        System.out.println("Welcome to the Multiplayer Game!");

        Multiplayer multiplayer = new Multiplayer();
        multiplayer.getNames();

        System.out.println("Play again? (yes/no)");
        String response = scanner.nextLine().trim();
        return response.equalsIgnoreCase("yes");
    }
}
