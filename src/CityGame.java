import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CityGame {
    private final Map<Character, List<String>> cityMap = new HashMap<>();
    private final Random random = new Random();
    private final Set<String> usedCities = new HashSet<>();
    public boolean gameOver;
    private int attempts = 5;


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
            String userCity = scanner.nextLine().trim().toLowerCase();
            if (!IsTheWordCorrect(userCity)) {
                attempts--;
                System.out.println("You have " + attempts + " attempts");
                continue;
            }
            getCityStartingWith(userCity.toUpperCase().charAt(userCity.length() - 1));
        }
    }

    public void GetInputs() {
        getCities("src/ListOfCities");
        Scanner scanner = new Scanner(System.in);
        while (attempts > 0) {
            System.out.print("Your city: ");
            String userCity = scanner.nextLine().trim().toLowerCase();
            if (!IsTheWordCorrect(userCity)) {
                attempts--;
                System.out.println("You have " + attempts + " attempts");
                continue;
            }
        }
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
        if (usedCities.contains(word)) {
            System.out.println("This word has already been taken");
            return false;
        } else if (!cityMap.containsKey(Character.toUpperCase(word.charAt(0))) ||
                !cityMap.get(Character.toUpperCase(word.charAt(0))).contains(word)) {
            System.out.println("There is no such city");
            return false;
        } else {
            markCityAsUsed(word);
//            score += 10;
//            System.out.println("Your score is " + score);
        }
        return true;
    }

    public void markCityAsUsed(String city) {
        usedCities.add(city);
    }

}
