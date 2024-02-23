import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CityGame cityGame = new CityGame();
        CountryGame country = new CountryGame();

        System.out.println("Choose one of the games: ");
        System.out.println("1 CityGame\n2 CountryGame\n3 Multiplayer\n4 Exit");
        System.out.print("Your choice: ");
        int chooseGame;

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a number");
                System.out.print("Your choice: ");
                scanner.next();
            }
            chooseGame = Integer.parseInt(scanner.nextLine());
            if (chooseGame < 1 || chooseGame > 4) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4");
                System.out.print("Your choice: ");
            }
        } while (chooseGame < 1 || chooseGame > 4);

        switch (chooseGame) {
            case 1:
                System.out.println("Welcome to the City Game!");
                cityGame.GetInput();
                break;
            case 2:
                System.out.println("Welcome to the Country Game!");
                country.ChooseCountry();
                break;
            case 3:
                System.out.println("Welcome to the Multiplayer Game!");
                break;
            case 4:
                System.out.println("EXIT");
                System.exit(0);

        }
    }
}
