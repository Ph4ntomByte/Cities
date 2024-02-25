import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            playGame(scanner);
            System.out.println("Do you want to play again? (yes/no)");
            String response = scanner.next().trim();
            playAgain = response.equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thank you for playing!");
    }

    private static void playGame(Scanner scanner) {
        CityGame cityGame = new CityGame();
        CitiesInCountry country = new CitiesInCountry();
        Multiplayer multiplayer = new Multiplayer();

        System.out.println("Choose one of the games: ");
        System.out.println("1 AllCities\n2 Cities By Country\n3 Multiplayer\n4 Exit");
        System.out.print("Your choice: ");
        int chooseGame = scanner.nextInt();
        scanner.nextLine();

        switch (chooseGame) {
            case 1:
                System.out.println("Welcome to the City Game!");
                cityGame.GetInput();
                break;
            case 2:
                System.out.println("Welcome to the Cities by Country Game!");
                country.chooseCountry();
                break;
            case 3:
                System.out.println("Welcome to the Multiplayer Game!");
                multiplayer.GetNames();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
        }
    }
}
