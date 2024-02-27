import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CityGame {
    public final Map<Character, List<String>> cityMap = new HashMap<>();
    public final Set<String> usedCities = new HashSet<>();
    private final Random random = new Random();
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
            JOptionPane.showMessageDialog(null, "This city has already been used.");
        } else if (!lastCityUsed.isEmpty() && city.toLowerCase().charAt(0) != lastCityUsed.toLowerCase().charAt(lastCityUsed.length() - 1)) {
            JOptionPane.showMessageDialog(null, "The city does not start with the correct letter.");
        } else if (!cityMap.containsKey(Character.toUpperCase(city.charAt(0))) ||
                !cityMap.get(Character.toUpperCase(city.charAt(0))).contains(city.toLowerCase())) {
            System.out.println("This city does not exist.");
            JOptionPane.showMessageDialog(null, "This city does not exist.");

        } else {
            JOptionPane.showMessageDialog(null, "Correct! 🎉");
            markCityAsUsed(city);
            lastCityUsed = city;
            return true;
        }
        return false;
    }


    public String ComputerCity(char letter) {
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
            }
        }

        String chosenCity = cities.get(random.nextInt(cities.size()));
        usedCities.add(chosenCity.toLowerCase());
        lastCityUsed = chosenCity;
        System.out.println("Computer's turn: " + chosenCity);
        return chosenCity;
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
        int numPlayers = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                numPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of players:"));
                if (numPlayers > 0) {
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        }
        return numPlayers;
    }
}
