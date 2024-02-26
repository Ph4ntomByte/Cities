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
            playerAttempts.put(playerName, 5);
        }
        if (count == 1) {

            startGame(playerNames.get(0));
        } else {
            startMultiplayerGame();
        }
    }


    private void startMultiplayerGame() {
        loadCities("src/Lists/ListOfCities");

        boolean anyPlayerHasAttemptsLeft = true;
        while (anyPlayerHasAttemptsLeft) {
            anyPlayerHasAttemptsLeft = false;

            for (String playerName : playerNames) {
                if (playerAttempts.getOrDefault(playerName, 5) > 0) {
                    System.out.print(playerName + "'s turn: ");
                    String cityInput = scanner.nextLine();
                    if (isCityValid(cityInput, playerName)) {
                        updateScore(playerName, 10);
//                        ComputerCity(cityInput.toUpperCase().charAt(cityInput.length() - 1), playerName);
                    }

                    if (playerAttempts.get(playerName) > 0) {
                        anyPlayerHasAttemptsLeft = true;
                    }
                }
            }
        }

        displayScores();
        announceWinner();
    }


    private void updateScore(String playerName, int points) {
        scores.put(playerName, scores.getOrDefault(playerName, 0) + points);
    }

    private void displayScores() {
        System.out.println("Scores:");
        scores.forEach((name, score) -> System.out.println(name + ": " + score));
    }

    private void announceWinner() {
        String winner = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Winner: " + winner + " with " + scores.get(winner) + " points.");
    }
}
