import java.util.Scanner;

public class CountryGame {
    private int attempts = 5;

    public void ChooseCountry() {
        CityGame cityGame = new CityGame();
        Scanner scan = new Scanner(System.in);

        System.out.println("Choose country");
        System.out.println("1 France\n2 Spain\n3 United Kingdom\n4 USA\n5 Hungary");
        System.out.print("Your choice: ");
        int res = scan.nextInt();
        scan.nextLine();

        switch (res) {
            case 1:
                System.out.println("You chose France\nYou gonna have to name 5 cities to pass the level");
                cityGame.getCities("src/ListOfFranceCities");
                break;
            case 2:
                System.out.println("You chose Spain\nYou gonna have to name 5 cities to pass the level");
                cityGame.getCities("src/ListOfSpanishCities");
                break;
            case 3:
                System.out.println("You chose United Kingdom\nYou gonna have to name 5 cities to pass the level");
                cityGame.getCities("src/ListOfEnglishCities");
                break;
            case 4:
                System.out.println("You chose USA\nYou gonna have to name 5 cities to pass the level");
                cityGame.getCities("src/ListOfAmericanCities");
                break;
            case 5:
                System.out.println("You chose Hungary\nYou gonna have to name 5 cities to pass the level");
                cityGame.getCities("src/ListOfHungarianCities.txt");
                break;
            default:
                System.out.println("Invalid choice");
                cityGame.getCities("src/ListOfCities");
                return;
        }

        while (attempts > 0) {
            System.out.print("Your city: ");
            String userCity = scan.nextLine().trim();
            if (!cityGame.IsTheWordCorrect(userCity)) {
                attempts--;
                System.out.println("You have " + attempts + " attempts");
            } else {
                cityGame.getCityStartingWith(userCity.toUpperCase().charAt(userCity.length() - 1));
            }
        }

        scan.close();
    }
}
