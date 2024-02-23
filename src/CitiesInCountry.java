import java.util.Scanner;

public class CitiesInCountry {
    public void chooseCountry() {
        CityGame cityGame = new CityGame();
        Scanner scan = new Scanner(System.in);

        int choice;
        do {
            displayMenu();
            choice = getValidChoice(scan);
            switch (choice) {
                case 1:
                    playGame(cityGame, "France", "src/ListOfFranceCities");
                    break;
                case 2:
                    playGame(cityGame, "Spain", "src/ListOfSpanishCities");
                    break;
                case 3:
                    playGame(cityGame, "United Kingdom", "src/ListOfEnglishCities");
                    break;
                case 4:
                    playGame(cityGame, "USA", "src/ListOfAmericanCities");
                    break;
                case 5:
                    playGame(cityGame, "Hungary", "src/ListOfHungarianCities.txt");
                    break;
                case 6:
                    System.out.println("EXIT");
                    break;
            }
        } while (choice != 6);

        scan.close();
    }

    private void displayMenu() {
        System.out.println("Choose country");
        System.out.println("1 France\n2 Spain\n3 United Kingdom\n4 USA\n5 Hungary\n6 Exit");
        System.out.print("Your choice: ");
    }

    private int getValidChoice(Scanner scanner) {
        int choice;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a number");
                System.out.print("Your choice: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please enter a number between 1 and 6");
                System.out.print("Your choice: ");
            }
        } while (choice < 1 || choice > 6);
        scanner.nextLine();
        return choice;
    }

    private void playGame(CityGame cityGame, String country, String cityFilePath) {
        System.out.println("You chose " + country + "\nYou're going to have to name 5 cities to pass the level");
        cityGame.getCities(cityFilePath);
        cityGame.GetInput();
    }
}
