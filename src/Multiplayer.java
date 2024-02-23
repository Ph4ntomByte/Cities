import java.util.Scanner;

public class Multiplayer {
    CityGame cityGame = new CityGame();
    CityGame firstPlayer = new CityGame();
    CityGame secondPlayer = new CityGame();

    String firstPlayerName = "";
    String secondPlayerName = "";

    public void GetNames() {
        Scanner scan = new Scanner(System.in);
        System.out.print("First player enters name: ");
        firstPlayerName = scan.nextLine();
        System.out.print("Second player enters name: ");
        secondPlayerName = scan.nextLine();
        playGame();
    }

    public void playGame() {
        boolean gameOver = false;
        while (!gameOver) {
            firstPlayer.GetInputForMultiple(firstPlayerName);
            if (firstPlayer.gameOver) {
                System.out.println(secondPlayerName + " won");
                cityGame.promptNewGame();
                gameOver = true;
                break;

            }
            secondPlayer.GetInputForMultiple(secondPlayerName);
            if (secondPlayer.gameOver) {
                System.out.println(firstPlayerName + " won");
                cityGame.promptNewGame();
                gameOver = true;
            }
        }
    }

}
