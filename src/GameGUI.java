import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameGUI {
    private JTextArea textArea;
    private final Multiplayer game;
    private String lastCityUsed = "";
    List<String> names = new ArrayList<>();


    public GameGUI() {
        this.game = new Multiplayer();
        game.setGUI(this);
        initialize();
    }

    private void initialize() {
        JFrame frame = new JFrame("City Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 200);
        frame.getBackground();
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(_ -> promptForPlayerNames());
        frame.add(startButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setVisible(true);
    }


    public void promptForPlayerNames() {
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter number of players:"));
        for (int i = 0; i < numPlayers; i++) {
            names.add(JOptionPane.showInputDialog(null, STR."Player \{i + 1} name:"));
        }
        game.setPlayerNames(names);
    }

    public void handlePlayerTurn() {
        SwingUtilities.invokeLater(() -> {
            boolean gameOver = false;
            while (!gameOver) {
                for (String playerName : game.getPlayerNames()) {
                    String cityInput;
                    if (!game.isGameOver(names)) {
                        gameOver = true;
                    }
                    if (!lastCityUsed.isEmpty()) {
                        cityInput = JOptionPane.showInputDialog(null, STR."\{playerName}'s turn: Enter a city starting with \"\{lastCityUsed.toUpperCase().charAt(lastCityUsed.length() - 1)}\"");
                    } else {
                        cityInput = JOptionPane.showInputDialog(null, STR."\{playerName}'s turn:");
                    }
                    if (cityInput != null && !cityInput.trim().isEmpty()) {
                        if (game.countAttempts(playerName, cityInput)) {
                            game.displayScores();
                            lastCityUsed = cityInput;
                        } else {
                            nullStrike(playerName);
                        }
                    } else {
                        gameOver = true;
                    }
                }
            }
            JOptionPane.showMessageDialog(null, game.getWinner());
        });
    }

    public void nullStrike(String playerName) {
        game.countOfStrike.put(playerName, 0);
    }


    public void appendText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(STR."\{text}\n"));
    }
}
