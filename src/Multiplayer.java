import java.util.*;
import java.util.Scanner;


public class Multiplayer extends CityGame {
    private final List<String> playerNames = new ArrayList<>();
    private final Map<String, Integer> scores = new HashMap<>();
    Scanner scanner = new Scanner(System.in);


    public void getNames() {
        System.out.print("Enter number of players: ");
        int count = getValidChoice(scanner, 10);

        for (int i = 1; i <= count; i++) {
            System.out.print("Player " + i + " name: ");
            String playerName = scanner.nextLine();
            playerNames.add(playerName);
            scores.put(playerName, 0);
            countOfStrike.put(playerName, 0);
            playerAttempts.put(playerName, 5);
        }
        startMultiplayerGame(count);
    }


    private void startMultiplayerGame(int count) {
        CitiesInCountry citiesInCountry = new CitiesInCountry();
        loadCities(citiesInCountry.chooseCountry());
        boolean anyPlayerHasAttemptsLeft = true;
        while (anyPlayerHasAttemptsLeft) {
            anyPlayerHasAttemptsLeft = false;

            for (String playerName : playerNames) {
                int attempts = playerAttempts.get(playerName);
                System.out.print(playerName + "'s turn: ");
                String cityInput = scanner.nextLine();
                if (isCityValid(cityInput)) {
                    incrementStrike(playerName);
                    updateScore(playerName);
                } else {
                    attempts--;
                    nullStrike(playerName);
                    playerAttempts.put(playerName, attempts);
                    System.out.println(playerName + " have " + attempts + " attempts left.");
                }
                if (count == 1) {
                    ComputerCity(cityInput.toUpperCase().charAt(cityInput.length() - 1), playerName);
                }
                if (playerAttempts.get(playerName) > 0) {
                    anyPlayerHasAttemptsLeft = true;
                }
                displayScores();
            }
        }
        announceWinner();
    }

    public void incrementStrike(String playerName) {
        int strikes = countOfStrike.getOrDefault(playerName, 0);
        int maxStrikes = 5;
        if (strikes <= maxStrikes) {
            strikes++;
        } else {
            System.out.println(playerName + " reached maximum of strikes");
        }
        countOfStrike.put(playerName, strikes);

    }

    private void updateScore(String playerName) {
        int strike = countOfStrike.get(playerName);
        scores.put(playerName, scores.getOrDefault(playerName, 0) + (10 * strike));
    }

    private void displayScores() {
        System.out.println("Scores:");
        scores.forEach((name, score) -> System.out.println(name + ": " + score));
    }

    private void announceWinner() {
        int highestScore = Collections.max(scores.values());
        List<String> winners = scores.entrySet().stream()
                .filter(entry -> entry.getValue() == highestScore)
                .map(Map.Entry::getKey).toList();
        if (winners.size() == 1) {
            System.out.println("Winner: " + winners.get(0) + " with a score of " + highestScore);
        } else {
            System.out.println("It's a draw between the following players with a score of " + highestScore + ": ");
            winners.forEach(System.out::println);
        }
    }

}
