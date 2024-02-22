import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int attempts = 3;
        Scanner scanner = new Scanner(System.in);
        CityGame cityGame = new CityGame();

        System.out.println("Welcome to the City Game!");
        while (attempts > 0) {
            System.out.print("Your city: ");
            String userCity = scanner.nextLine().trim();

            if (!cityGame.IsTheWordCorrect(userCity)) {
                attempts--;
                System.out.println("You have " + attempts + " attempts");
                continue;
            }

            char lastLetter = userCity.toUpperCase().charAt(userCity.length() - 1);
            String computerCity = cityGame.getCityStartingWith(lastLetter);

            System.out.println("Computer's city: " + computerCity);
            cityGame.markCityAsUsed(computerCity);
        }
    }
}
