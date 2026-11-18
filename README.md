# Multiplayer Game Lobby (Java)

A console-based Java application that simulates a multiplayer game
lobby. It lets users manage players, update their ready status, view the
lobby in both directions, and start a match when the requirements are
met.

## Features

-   **Add players** with a player ID, name, and score.
-   **Remove players** using their player ID.
-   **Toggle ready status** between `READY` and `NOT READY`.
-   **Display the lobby forward**, starting from the host.
-   **Display the lobby backward**, starting from the last player.
-   **Start a match** only when at least two players are in the lobby
    and every player is ready.

## Data Structure Used

The project uses a **circular doubly linked list** to maintain the
lobby.

Each `Player` node stores:

  Field       Description
  ----------- -------------------------------------------
  `id`        Unique identifier intended for the player
  `name`      Player's name
  `score`     Player's score
  `isReady`   Whether the player is ready to play
  `next`      Reference to the next player
  `prev`      Reference to the previous player

The list is circular: the last player's `next` reference points to the
first player (`head`), and the first player's `prev` reference points to
the last player. This allows traversal in both directions.

## Requirements

-   Java Development Kit (JDK)
-   A terminal or command prompt

## How to Run

1.  Save `MultiplayerLobby.java` in your project directory.

2.  Open a terminal in that directory.

3.  Compile the program:

    ``` bash
    javac MultiplayerLobby.java
    ```

4.  Run the program:

    ``` bash
    java MultiplayerLobby
    ```

## Initial Players

The program automatically adds these three players when it starts:

    Player ID Name      Score
  ----------- ------- -------
          101 Rajas      1500
          102 Ansh       1420
          103 Kabir      1600

All players initially have their ready status set to `false`.

## Menu Options

    Option Operation
  -------- -------------------------------------
       `1` Add a player
       `2` Remove a player
       `3` Toggle a player's ready status
       `4` Display the lobby in forward order
       `5` Display the lobby in backward order
       `6` Attempt to start the match
       `0` Exit the application

## Match-start Conditions

A match can start only if:

1.  At least **two players** are in the lobby.
2.  **Every player** has their ready status set to `READY`.

If either condition is not satisfied, the program displays a message
explaining why the match cannot start.

## Project Structure

``` text
.
├── MultiplayerLobby.java
└── README.md
```

## Concepts Demonstrated

-   Java classes and objects
-   Constructors
-   Static fields and methods
-   Circular doubly linked lists
-   Insertion and deletion of nodes
-   Forward and backward traversal
-   Conditional logic and loops
-   `Scanner` for console input
-   Menu-driven programming

------------------------------------------------------------------------

**Note:** Player names are read as a single word because the program
uses `Scanner.next()`.
