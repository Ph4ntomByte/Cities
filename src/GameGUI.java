import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameGUI extends Multiplayer {
    private JTextArea textArea;
    private final Multiplayer game;
    private final CityGame cityGame = new CityGame();
    public String lastCityUsed = "";
    List<String> names = new ArrayList<>();
    public boolean gameOver = false;


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

        textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        textArea.setForeground(new Color(28, 0, 0));
        textArea.setBackground(new Color(255, 248, 220));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);


        JButton startButton = getStartButton();


        frame.add(startButton, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("OptionPane.messageForeground", new Color(0, 0, 0));
        UIManager.put("Panel.background", new Color(240, 240, 240));
        UIManager.put("Button.background", new Color(200, 200, 200));
        UIManager.put("Button.foreground", new Color(0, 0, 0));

    }

    private JButton getStartButton() {
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 12));
        startButton.setBackground(new Color(30, 144, 255));
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(65, 105, 225));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(30, 144, 255));
            }
        });
        startButton.addActionListener(_ -> promptForPlayerNames());
        return startButton;
    }

    public void promptForPlayerNames() {
        game.resetGameState();
        names.clear();
        gameOver = false;
        textArea.setText("");
        appendText("This is City Game");
        appendText("Every player has 5 streaks\nGame continues till there is one person left");
        int numPlayers = cityGame.getValidChoice();
        for (int i = 0; i < numPlayers; i++) {
            names.add(JOptionPane.showInputDialog(null, STR."Player \{i + 1} name:"));
        }
        game.setPlayerNames(names);
        handlePlayerTurn();
        
    }


    public void handlePlayerTurn() {
        SwingUtilities.invokeLater(() -> {
            while (!gameOver) {
                for (String playerName : game.getPlayerNames()) {
                    String promptMessage = String.format("%s's turn: Enter a city%s", playerName, !lastCityUsed.isEmpty() ? STR." starting with \"\{lastCityUsed.toUpperCase().charAt(lastCityUsed.length() - 1)}\"" : "");
                    String cityInput = JOptionPane.showInputDialog(null, promptMessage);

                    if (cityInput == null || cityInput.trim().isEmpty()) {
                        String leave = JOptionPane.showInputDialog(null, "Do you want to leave the game(yes/no)");
                        if ("no" == leave) {
                            continue;
                        } else {
                            resetGameForNewStart();
                            return;
                        }
                    }
                    if (game.countAttempts(playerName, cityInput)) {
                        game.displayScores();
                        lastCityUsed = cityInput;
                    } else {
                        nullStrike(playerName);
                    }
                }

            }
            JOptionPane.showMessageDialog(null, game.getWinner());
        });
    }

    public void resetGameForNewStart() {
        game.resetGameState();
        gameOver = false;
        lastCityUsed = "";
        textArea.setText("");
    }

    public void updateGameState() {
        if (playerNames.size() <= 1) {
            gameOver = true;
            String winnerMessage = playerNames.size() == 1 ? STR."Winner is \{playerNames.getFirst()}" : "It's a draw!";
            JOptionPane.showMessageDialog(null, winnerMessage);
        }
    }

    public void nullStrike(String playerName) {
        game.countOfStrike.put(playerName, 0);
    }


    public void appendText(String text) {
        SwingUtilities.invokeLater(() -> textArea.append(STR."\{text}\n"));
    }
}
