import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int attempts = 3;
        Scanner scanner = new Scanner(System.in);
        CityGame cityGame = new CityGame();
        CountryGame country = new CountryGame();
        System.out.println("Choose one of the games: ");
        System.out.println("1 CityGame\n2 CountryGame");
        System.out.print("Your choice: ");
        int chooseGame = Integer.parseInt(scanner.nextLine());
        switch (chooseGame) {
            case 1:
                System.out.println("Welcome to the City Game!");
                cityGame.getCities("src/ListOfCities");
                while (attempts > 0) {
                    System.out.print("Your city: ");
                    String userCity = scanner.nextLine().trim();
                    if (!cityGame.IsTheWordCorrect(userCity)) {
                        attempts--;
                        System.out.println("You have " + attempts + " attempts");
                        continue;
                    }
                    cityGame.getCityStartingWith(userCity.toUpperCase().charAt(userCity.length() - 1));
                }
                break;
            case 2:
                country.ChooseCountry();
        }
    }
}
