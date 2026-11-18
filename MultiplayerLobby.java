import java.util.Scanner;

public class MultiplayerLobby {


// all features
    static class Player {
        int id;
        String name;
        int score;
        boolean isReady;
        Player next;  // DLL
        Player prev;

        Player(int id, String name, int score) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.isReady = false;
            this.next = null;
            this.prev = null;
        }
    }

    static Player head = null;   // first player
    static int totalPlayers = 0;

    // add new player
    public static void addPlayer(int id, String name, int score) {
        Player newPlayer = new Player(id, name, score);

        if (head == null) {   // if lobby empty
            head = newPlayer;
            head.next = head;
            head.prev = head;
        } else {
            Player tail = head.prev;
            tail.next = newPlayer;
            newPlayer.prev = tail;
            newPlayer.next = head;
            head.prev = newPlayer;
        }
        totalPlayers++;
        System.out.println( name + " joined the lobby.");
    }

    // remove player
    public static void removePlayer(int id) {
        if (head == null) {
            System.out.println("Lobby is empty.");
            return;
        }

        Player current = head;
        for (int i = 0; i < totalPlayers; i++) {
            if (current.id == id) {
                if (totalPlayers == 1) {
                    head = null;
                } else {   // if multiple players exist
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    if (current == head) {
                        head = current.next;
                    }
                }
                totalPlayers--;
                System.out.println( current.name + " removed from lobby.");
                return;
            }
            current = current.next;
        }
        System.out.println("Player ID " + id + " not found.");
    }

    // ready
    public static void toggleReady(int id) {
        if (head == null) {
            System.out.println("Lobby is empty.");
            return;
        }

        Player current = head;
        for (int i = 0; i < totalPlayers; i++) {
            if (current.id == id) {
                current.isReady = !current.isReady;  // boolean true false
                System.out.println(current.name + " is now " + (current.isReady ? "READY" : "NOT READY"));
                return;
            }
            current = current.next;
        }
        System.out.println("Player ID " + id + " not found.");
    }

    // display players , their details and status
    public static void displayForward() {
        if (head == null) {
            System.out.println("Lobby is empty.");
            return;
        }

        System.out.println("\n--- Lobby Roster (Forward order) ---");
        Player current = head;
        for (int i = 0; i < totalPlayers; i++) {
            String role = (current == head) ? " [HOST]" : "";
            String status = current.isReady ? "Ready" : "Waiting";
            System.out.println("ID: " + current.id + " | Name: " + current.name + role + " | Score: " + current.score + " | Status: " + status);
            current = current.next;     // move forward
        }
    }

    public static void displayBackward() {
        if (head == null) {
            System.out.println("Lobby is empty.");
            return;
        }

        System.out.println("\n--- Lobby Roster (Reverse Order) ---");
        Player current = head.prev;     // goes to last player
        for (int i = 0; i < totalPlayers; i++) {
            System.out.println("ID: " + current.id + " | Name: " + current.name + " | Score: " + current.score);
            current = current.prev;     // moves backward
        }
    }

    // start
    public static void startMatch() {
        if (totalPlayers < 2) {     // less players exception
            System.out.println("Cannot start match: Need at least 2 players.");
            return;
        }

        Player current = head;
        for (int i = 0; i < totalPlayers; i++) {
            if (!current.isReady) {     // not ready exception
                System.out.println("Cannot start match: " + current.name + " is not ready!");
                return;
            }
            current = current.next;
        }
        System.out.println(" MATCH STARTING NOW! ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        addPlayer(101, "Rajas", 1500);
        addPlayer(102, "Ansh", 1420);
        addPlayer(103, "Kabir", 1600);

        do {
            System.out.println("\n=== MULTIPLAYER GAME LOBBY ===");
            System.out.println("1. Add Player");
            System.out.println("2. Remove Player");
            System.out.println("3. Toggle Player Ready Status");
            System.out.println("4. View Lobby (Forward)");
            System.out.println("5. View Lobby (Backward)");
            System.out.println("6. Start Match");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Player ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Player Name: ");
                    String name = sc.next();
                    System.out.print("Enter Player Score: ");
                    int score = sc.nextInt();
                    addPlayer(id, name, score);
                    break;

                case 2:
                    System.out.print("Enter Player ID to remove: ");
                    int removeId = sc.nextInt();
                    removePlayer(removeId);
                    break;

                case 3:
                    System.out.print("Enter Player ID to toggle Ready: ");
                    int readyId = sc.nextInt();
                    toggleReady(readyId);
                    break;

                case 4:
                    displayForward();
                    break;

                case 5:
                    displayBackward();
                    break;

                case 6:
                    startMatch();
                    break;

                case 0:
                    System.out.println("Closing lobby...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);

        sc.close();
    }
}