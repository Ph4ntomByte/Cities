import java.util.*;

public class Multiplayer extends CityGame {
    private final List<String> playerNames = new ArrayList<>();
    private final Map<String, Integer> scores = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void GetNames() {
        System.out.print("Enter number of players: ");
        int count;
        do {
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid choice. Please enter the number");
                scanner.next();
            }
            count = scanner.nextInt();
            scanner.nextLine();
            if (count < 1 || count > 10) {
                System.out.print("Invalid choice. Please enter ratianal number of players ");
            }
        } while (count < 1 || count > 10);


        for (int i = 1; i <= count; i++) {
            System.out.print("Player " + i + " enters name: ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
            scores.put(playerName, 0);
        }
        playGame();
    }

    public void playGame() {
        getCities("src/Lists/ListOfCities");

        while (attempts > 0) {
            for (String playerName : playerNames) {
                System.out.print(playerName + "'s turn. Enter a city name:");
                String cityInput = scanner.nextLine();
                if (IsTheWordCorrect(cityInput)) {
                    updateScore(playerName, 10);
                }
                displayScores();


            }
            if (attempts <= 0) {
                displayScores();
                Map.Entry<String, Integer> maxEntry = Collections.max(scores.entrySet(), Map.Entry.comparingByValue());
                System.out.println("Highest score: " + maxEntry.getKey() + " with " + maxEntry.getValue() + " points.");
                break;
            }

        }

    }

    public void updateScore(String playerName, int points) {
        if (scores.containsKey(playerName)) {
            scores.put(playerName, scores.get(playerName) + points);
        }
    }

    public void displayScores() {
        System.out.println("Current Scores:");
        scores.forEach((name, score) -> System.out.println(name + ": " + score));
    }
}
