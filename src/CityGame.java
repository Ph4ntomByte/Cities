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

    public boolean isCityValid(String city) {
        if (usedCities.contains(city.toLowerCase())) {
            System.out.println("This city has already been used.");
        } else if (!cityMap.containsKey(Character.toUpperCase(city.charAt(0))) ||
                !cityMap.get(Character.toUpperCase(city.charAt(0))).contains(city.toLowerCase())) {
            System.out.println("This city does not exist.");
        } else if (!lastCityUsed.isEmpty() && city.toLowerCase().charAt(0) != lastCityUsed.toLowerCase().charAt(lastCityUsed.length() - 1)) {
            System.out.println("The city does not start with the correct letter.");
        } else {
            System.out.println("Correct! 🎉");
            markCityAsUsed(city);
            lastCityUsed = city;
            return true;
        }
        return false;
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

    public void nullStrike(String playerName) {
        countOfStrike.put(playerName, 0);
    }


    public int getValidChoice(Scanner scanner, int max) {
        int choice;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Enter a number: ");
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
