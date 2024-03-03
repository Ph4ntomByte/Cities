import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;


public class Multiplayer extends CityGame {
    public final List<String> playerNames = new ArrayList<>();
    public final Map<String, Integer> scores = new HashMap<>();
    public Map<String, Integer> playerAttempts = new HashMap<>();
    private GameGUI gui;

    public void setGUI(GameGUI gui) {
        this.gui = gui;
    }

    public void setPlayerNames(List<String> names) {
        playerNames.clear();
        scores.clear();
        for (String name : names) {
            playerNames.add(name);
            scores.put(name, 0);
            countOfStrike.put(name, 0);
            playerAttempts.put(name, 5);
        }
        String path = chooseCountry();
        loadCities(path);
        gui.handlePlayerTurn();

    }

    public void resetGameState() {
        playerNames.clear();
        scores.clear();
        playerAttempts.clear();
        countOfStrike.clear();
        usedCities.clear(); // Ensure all cities are considered unused for the new game
        gui.lastCityUsed = "";
    }

    private String chooseCountry() {
        String[] options = {"All Cities", "Places", "United Kingdom", "USA", "Hungary", "Countries"};
        int choice = JOptionPane.showOptionDialog(null, "Choose a country for the game:",
                "Country Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
        return switch (choice) {
            case 1 -> "src/Lists/Places";
            case 2 -> "src/Lists/ListOfEnglishCities";
            case 3 -> "src/Lists/ListOfAmericanCities";
            case 4 -> "src/Lists/ListOfHungarianCities";
            case 5 -> "src/Lists/ListOfCountries";
            default -> "src/Lists/ListOfCities";
        };
    }


    public boolean countAttempts(String playerName, String cityInput) {
        if (!isCityValid(cityInput)) {
            int attemptsLeft = playerAttempts.get(playerName) - 1;
            playerAttempts.put(playerName, attemptsLeft);
            gui.appendText(STR."\{playerName} has \{attemptsLeft} attempts left.");

            if (attemptsLeft <= 0) {
                gui.appendText(STR."\{playerName}  is out of the game.");
                eliminatePlayer(playerName);
            }
            return false;
        } else {
            incrementStreaks(playerName);
            return true;
        }

    }

    private void eliminatePlayer(String playerName) {
        playerNames.remove(playerName);
        playerAttempts.remove(playerName);
        scores.remove(playerName);
        gui.updateGameState();
    }

    private void updateScore(String playerName) {
        int currentScore = scores.getOrDefault(playerName, 0);
        scores.put(playerName, currentScore + countOfStrike.get(playerName));
        gui.appendText(STR."\{playerName}'s new score: \{countOfStrike.get(playerName)}");
    }

    private void incrementStreaks(String playerName) {
        int currentStrikes = countOfStrike.getOrDefault(playerName, 0);
        if (currentStrikes < 5) {
            countOfStrike.put(playerName, currentStrikes + 1);
            gui.appendText(STR."\{playerName} has \{currentStrikes + 1} streaks.");
        } else {
            gui.appendText(STR."\{playerName} has reached the maximum number of streaks.");
        }
        updateScore(playerName);

    }


    public List<String> getPlayerNames() {
        return new ArrayList<>(playerNames);
    }

    public void displayScores() {
        StringBuilder scoresText = new StringBuilder("Scores:\n");
        scores.forEach((name, score) -> scoresText.append(name).append(": ").append(score).append("\n"));
        gui.appendText(scoresText.toString());
    }

    public String getWinner() {
        int highestScore = Collections.max(scores.values());
        List<String> winners = scores.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), highestScore))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (winners.size() == 1) {
            return STR."Winner: \{winners.getFirst()} with a score of \{highestScore}";
        } else {
            return STR."It's a draw between: \{String.join(", ", winners)}. Score: \{highestScore}";
        }
    }


}
