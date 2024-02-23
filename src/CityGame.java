import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CityGame {
    private final Map<Character, List<String>> cityMap = new HashMap<>();
    private final Random random = new Random();
    private final Set<String> usedCities = new HashSet<>();
    public boolean gameOver;
    private int attempts = 2;


    public void getCities(String path) {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String cityName = reader.nextLine();
                char firstLetter = Character.toUpperCase(cityName.charAt(0));

                if (!cityMap.containsKey(firstLetter)) {
                    cityMap.put(firstLetter, new ArrayList<>());
                }
                cityMap.get(firstLetter).add(cityName);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void GetInput() {
        getCities("src/ListOfCities");
        Scanner scanner = new Scanner(System.in);
        while (attempts > 0) {
            System.out.print("Your city: ");
            String userCity = scanner.nextLine().trim();
            if (IsTheWordCorrect(userCity)) {
                getCityStartingWith(userCity.toUpperCase().charAt(userCity.length() - 1));
            }
        }
    }

    public String GetInputForMultiple(String player) {
        getCities("src/ListOfCities");
        Scanner scanner = new Scanner(System.in);
        System.out.print(player + " enter your city: ");
        String userCity = scanner.nextLine().trim();
        IsTheWordCorrect(userCity);
        if (attempts == 0) {
            gameOver = true;
        }
        return userCity;
    }

    public void getCityStartingWith(char letter) {
        List<String> citiesStartingWithLetter = cityMap.getOrDefault(Character.toUpperCase(letter), Collections.emptyList());
        List<String> availableCities = new ArrayList<>();

        for (String city : citiesStartingWithLetter) {
            if (!usedCities.contains(city)) {
                availableCities.add(city);
            }
        }
        String chosenCity = availableCities.get(random.nextInt(availableCities.size()));
        if (!availableCities.isEmpty()) {
            usedCities.add(chosenCity);
            System.out.println("Computer's city: " + chosenCity);
        } else {
            System.out.println("There is no more cities");
            gameOver = true;
        }
    }

    public boolean IsTheWordCorrect(String word) {
        if (usedCities.contains(word.toLowerCase())) {
            System.out.println("This word has already been taken");
            attempts--;
            System.out.println("You have " + attempts + " attempts");
            return false;
        } else if (!cityMap.containsKey(Character.toUpperCase(word.charAt(0))) ||
                !cityMap.get(Character.toUpperCase(word.charAt(0))).contains(word.toLowerCase())) {
            System.out.println("There is no such city");
            attempts--;
            System.out.println("You have " + attempts + " attempts");
            return false;
        } else {
            markCityAsUsed(word);
//            score += 10;
//            System.out.println("Your score is " + score);
        }
        return true;
    }

    public void promptNewGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to start a new game? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Starting a new game...");
            Multiplayer multiplayer = new Multiplayer();
            multiplayer.GetNames();
        } else {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
    }

    public void markCityAsUsed(String city) {
        usedCities.add(city);
    }
}
