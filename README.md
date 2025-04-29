# CityMANIA (Ph4ntomByte)

A Java Swing application for the classic city-name game where players alternately name cities beginning with the last letter of the previous city.

## 📦 Project Structure

```
Ph4ntomByte/
├── .idea/
├── src/
│   ├── CityGame.java
│   ├── CustomInputDialog.java
│   ├── GameGUI.java
│   ├── Main.java
│   └── Multiplayer.java
├── .gitignore
├── CityMANIA.iml
└── README.md
```

- **CityGame.java**: Core game logic for loading cities, validating input, and tracking used cities.
- **CustomInputDialog.java**: Utility class for custom Swing input dialogs.
- **GameGUI.java**: Swing-based graphical interface for single-player gameplay.
- **Multiplayer.java**: Extension handling multiplayer turn order and input.
- **Main.java**: Entry point that launches the game.
- **Lists**: Resource file containing the list of city names (loaded by `CityGame`).

## 🚀 Features

- **City Loading**: Reads city names from a file into a map grouped by first letter.
- **Turn Validation**: Ensures each new city starts with the last letter of the previous city and hasn't been used.
- **User Feedback**: Provides error messages via Swing dialogs for invalid entries.
- **Multiplayer Support**: Handles multiple players and turn rotation.
- **Robust Input**: Validates numeric and string input with user-friendly dialogs.

## 🛠️ Setup & Compilation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/Ph4ntomByte.git
   cd Ph4ntomByte
   ```

2. **Compile** (Java 21)  
   ```bash
   javac -d out src/*.java
   ```

3. **Run**  
   ```bash
   java -cp out Main
   ```

## 🏷️ Commit History

| File                       | Last Commit Message       | Date      |
|----------------------------|---------------------------|-----------|
| Lists                      | feat: added Places        | last year |
| CityGame.java              | feat: added Places        | last year |
| CustomInputDialog.java     | feat: added Places        | last year |
| GameGUI.java               | java 21                   | last year |
| Main.java                  | feat: interface           | last year |
| Multiplayer.java           | feat: multiplayer support | last year |

## 📄 License

This project is for educational purposes. No external license applied.
