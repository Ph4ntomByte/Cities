import java.util.Scanner;

public class CitiesInCountry extends CityGame {
    private void displayMenu() {
        System.out.println("Choose country");
        System.out.println("1 All\n2 Spain\n3 United Kingdom\n4 USA\n5 Hungary\n6 Exit");
        System.out.print("Your choice: ");
    }

    public void chooseCountry() {
        CityGame cityGame = new CityGame();
        Scanner scan = new Scanner(System.in);

        int choice;
        displayMenu();
        choice = getValidChoice(scan, 6);
        switch (choice) {
            case 1:
                loadCities("src/Lists/ListOfCities");
                break;
            case 2:
                loadCities("src/Lists/ListOfSpanishCities");
                break;
            case 3:
                loadCities("src/Lists/ListOfEnglishCities");
                break;
            case 4:
                loadCities("src/Lists/ListOfAmericanCities");
                break;
            case 5:
                loadCities("src/Lists/ListOfHungarianCities.txt");
                break;
            case 6:
                System.out.println("EXIT");
                break;
        }

    }


}
