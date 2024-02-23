import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CityGame cityGame = new CityGame();
        CitiesInCountry country = new CitiesInCountry();
        Multiplayer multiplayer = new Multiplayer();


        System.out.println("Choose one of the games: ");
        System.out.println("1 AllCities\n2 Cities By Country\n3 Multiplayer\n4 Exit");
        System.out.print("Your choice: ");
        int chooseGame;

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a number");
                System.out.print("Your choice: ");
                scanner.next();
            }
            chooseGame = scanner.nextInt();
            scanner.nextLine();
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
                System.out.println("Welcome to the Cities by Country Game!");
                country.chooseCountry();
                break;
            case 3:
                System.out.println("Welcome to the Multiplayer Game!");
                multiplayer.GetNames();
                break;
            case 4:
                System.out.println("EXIT");
                System.exit(0);

        }
    }
}
