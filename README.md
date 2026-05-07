# Battleship

## Project Title
Battleship — Java Console Game

---

## What This Software Does
Battleship is a turn-based strategy game played on a 10x10 grid. The player fires shots at the computer's board trying to sink all 5 hidden ships before the computer sinks theirs. After every shot, the game displays a stats tracker showing ships remaining, total hits, and all coordinates already tried. The first player to sink all 5 enemy ships wins.

---

## Who It's For
This game is for anyone who wants to play a classic Battleship game in the terminal. It solves the problem of needing a quick, simple game that runs anywhere Java is installed with no extra setup required.

---

## How to Run the Program

1. Open the terminal
2. Navigate to the src folder:
```
cd src
```
3. Compile all Java files:
```
javac *.java
```
4. Run the program:
```
java BattleshipDriver
```
5. When prompted, enter your shot as two numbers separated by a space (row then column, each 0-9):
```
Player, enter shot as 'row col' (0-9): 3 5
```

---

## Technical Overview

### Main Classes
| Class | Description |
|---|---|
| `Ship` | Represents a single ship with a name, size, occupied cells, and hit tracking |
| `Board` | The 10x10 game board — handles ship placement, shot resolution, and rendering |
| `Player` | A human-controlled player that owns a Board and validates user input |
| `ComputerPlayer` | Extends Player and overrides takeTurn to fire random shots automatically |
| `Game` | Controls the full game loop — setup, alternating turns, and win condition |
| `BattleshipDriver` | Driver class with main — creates a Game and starts it |

### Key Data Structures
- `char[][] grid` — a 10x10 2D array in Board that stores the state of every cell (`~` water, `S` ship, `X` hit, `O` miss)
- `ArrayList<Ship> fleet` — stores all ships on a board
- `ArrayList<int[]> coordinates` — stores every cell a ship occupies
- `ArrayList<int[]> hits` — tracks which cells of a ship have been hit
- `ArrayList<String> shotHistory` — tracks every coordinate the player has fired at

### Program Logic
1. Both fleets are placed randomly on their boards at game start
2. The player enters a row and column coordinate to fire a shot
3. The board checks the cell and returns hit, miss, sunk, or repeat
4. Stats are displayed showing ships remaining, total hits, and shot history
5. The computer fires at a random unused cell on the player's board
6. The game loop repeats until all ships on one side are sunk

---

## Class Diagram
See `CLASS_DIAGRAM.txt` in the project folder, or import `battleship_class_diagram_drawio.xml` into draw.io at https://app.diagrams.net for the full visual diagram.

---

## Known Limitations / Future Improvements

**What works well:**
- Full game loop with win and lose conditions
- Stats tracker showing ships remaining, hits, and shot history after every turn
- Input validation handles bad input without crashing
- Enemy ships are hidden from the player during gameplay

**What I would improve with more time:**
- Make the computer smarter — after a hit, target adjacent cells instead of shooting randomly
- Add a two-player mode where two humans play against each other
- Add color to the terminal output to make hits, misses, and ships easier to see
- Allow the player to manually place their own ships instead of random placement



Class Diagram: https://drive.google.com/file/d/1XQKnIc6_-kwM39VQLdhixuICdG3qDC3M/view?usp=sharing
