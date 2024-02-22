import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CityGame {
    private Map<Character, List<String>> cityMap = new HashMap<>();
    private Random random = new Random();
    private Set<String> usedCities = new HashSet<>();
    public boolean gameOver;

    public CityGame() {
        getCities();
    }

    public void getCities() {
        try {
            File file = new File("/Users/raufsuleymanov/IdeaProjects/CityMANIA/src/ListOfCities");
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

    public String getCityStartingWith(char letter) {
        List<String> citiesStartingWithLetter = cityMap.getOrDefault(Character.toUpperCase(letter), Collections.emptyList());
        List<String> availableCities = new ArrayList<>();

        for (String city : citiesStartingWithLetter) {
            if (!usedCities.contains(city)) {
                availableCities.add(city);
            }
        }

        if (!availableCities.isEmpty()) {
            String chosenCity = availableCities.get(random.nextInt(availableCities.size()));
            usedCities.add(chosenCity);
            return chosenCity;
        } else {
            gameOver = true;
            return "There is no more words with '" + letter + "'";
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
        }
        return true;
    }


    public void markCityAsUsed(String city) {
        usedCities.add(city);
    }

    public boolean Checkcity() {
        return gameOver;
    }
}
