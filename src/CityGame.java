import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CityGame {
    private final Map<Character, List<String>> cityMap = new HashMap<>();
    private final Set<String> usedCities = new HashSet<>();
    private final Random random = new Random();
    public Map<String, Integer> playerAttempts = new HashMap<>();
    public Map<String, Integer> countOfStrike = new HashMap<>();
    private String lastCityUsed = "";
    private int MaxStrikes = 5;

    Scanner scanner = new Scanner(System.in);

    public void loadCities(String path) {
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String cityName = reader.nextLine();
                char firstLetter = Character.toUpperCase(cityName.charAt(0));
                cityMap.putIfAbsent(firstLetter, new ArrayList<>());
                cityMap.get(firstLetter).add(cityName.toLowerCase());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public void startGame(String playerName) {
        loadCities("src/Lists/ListOfCities");
        playerAttempts.put(playerName, 2);
        int attempts = playerAttempts.get(playerName);
        while (attempts > 0) {
            System.out.print(playerName + "'s turn: ");
            String userCity = scanner.nextLine().trim();
            if (isCityValid(userCity, playerName)) {
                ComputerCity(userCity.toUpperCase().charAt(userCity.length() - 1), playerName);
            }
            attempts = playerAttempts.get(playerName);
        }
    }

    public boolean isCityValid(String city, String playerName) {
        int attempts = playerAttempts.get(playerName);
        int strike = countOfStrike.get(playerName);
        if (usedCities.contains(city.toLowerCase())) {
            System.out.println("This city has already been used.");
            attempts--;
            nullStrike(playerName);
        } else if (!cityMap.containsKey(Character.toUpperCase(city.charAt(0))) ||
                !cityMap.get(Character.toUpperCase(city.charAt(0))).contains(city.toLowerCase())) {
            System.out.println("This city does not exist.");
            attempts--;
            nullStrike(playerName);
        } else if (!lastCityUsed.isEmpty() && city.toLowerCase().charAt(0) != lastCityUsed.toLowerCase().charAt(lastCityUsed.length() - 1)) {
            System.out.println("The city does not start with the correct letter.");
            attempts--;
            nullStrike(playerName);
        } else {
            System.out.println("Correct! 🎉");
            markCityAsUsed(city);
            lastCityUsed = city;
            incrementStrike(playerName);
            return true;
        }
        countOfStrike.put(playerName, strike);

        System.out.println("You have " + attempts + " attempts left.");
        playerAttempts.put(playerName, attempts);
        return false;
    }

    public void incrementStrike(String playerName) {
        int strikes = countOfStrike.getOrDefault(playerName, 0);
        if (strikes <= MaxStrikes) {
            strikes++;
        } else {
            System.out.println(playerName + " reached maximum of strikes");
        }
        countOfStrike.put(playerName, strikes);

    }

    public void nullStrike(String playerName) {
        countOfStrike.put(playerName, 1);
    }


    public void ComputerCity(char letter, String playerName) {
        List<String> cities = cityMap.getOrDefault(Character.toUpperCase(letter), Collections.emptyList());
        cities.removeAll(usedCities);

        if (cities.isEmpty()) {
            System.out.println("No more cities available for the letter: " + letter);

            Optional<Character> newLetter = findNewLetter();

            if (newLetter.isPresent()) {
                System.out.println("Switching to a new letter: " + newLetter.get());
                cities = cityMap.get(newLetter.get());
                cities.removeAll(usedCities);
            } else {
                System.out.println("No more cities available in the game.");
                return;
            }
        }

        String chosenCity = cities.get(random.nextInt(cities.size()));
        usedCities.add(chosenCity.toLowerCase());
        lastCityUsed = chosenCity;
        System.out.println("Computer's turn: " + chosenCity);
    }

    private Optional<Character> findNewLetter() {
        for (Map.Entry<Character, List<String>> entry : cityMap.entrySet()) {
            List<String> availableCities = new ArrayList<>(entry.getValue());
            availableCities.removeAll(usedCities);
            if (!availableCities.isEmpty()) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }


    private void markCityAsUsed(String city) {
        usedCities.add(city.toLowerCase());
    }

    public int getValidChoice(Scanner scanner, int max) {
        int choice;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a number");
                System.out.print("Your choice: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            if (choice < 1 || choice > max) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + max);
                System.out.print("Your choice: ");
            }
        } while (choice < 1 || choice > max);
        scanner.nextLine();
        return choice;
    }
}
