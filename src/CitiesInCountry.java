import java.util.Scanner;

public class CitiesInCountry extends CityGame {
    private void displayMenu() {
        System.out.println("Choose country");
        System.out.println("1 All Cities\n2 Countries\n3 United Kingdom\n4 USA\n5 Hungary\n6 Spain\n7 Exit");
        System.out.print("Your choice: ");
    }

    public String chooseCountry() {
        Scanner scan = new Scanner(System.in);

        displayMenu();
        int choice = getValidChoice(scan, 7);
        switch (choice) {
            case 1:
                return ("src/Lists/ListOfCities");
            case 2:
                return ("src/Lists/ListOfCountries");
            case 3:
                return ("src/Lists/ListOfEnglishCities");
            case 4:
                return ("src/Lists/ListOfAmericanCities");
            case 5:
                return ("src/Lists/ListOfHungarianCities");
            case 6:
                return ("src/Lists/ListOfSpanishCities");
            case 7:
                System.out.println("EXIT");
                break;
        }
        return "";
    }


}
