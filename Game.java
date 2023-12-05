import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Scanner sc = new Scanner(System.in);

    private boolean play = true;
    private int playerturn = Math.random() > 0.5 ? 1 : 0;
    private String playerTurnName = "";

    public Game() {
        System.out.println(
                "_______________________________________________\n\nPlayer 1, what would you like to be called?");
        String namevar = sc.next();
        player1 = new Player(namevar);
        playerTurnName = namevar;
        System.out
                .println(
                        "\nPlayer 2, what would you like to be called? Enter a number if you want Player 2 to be a computer.");
        String temp = sc.next();
        try {
            player2 = new Player(Integer.parseInt(temp));
        } catch (NumberFormatException ex) {
            player2 = new Player(temp);
        }

    }

    public void play() {

        System.out.println("_______________________________________________");
        while (play) {

            // switchPlayerTurn();

            System.out.println("\nThere are " + Board.getNumClips() + " clips left to take!");

            if (player2.getComputerStatus() && playerturn % 2 == 1) {
                playComputerMove();
                playerTurnName = player1.getPlayerName();
                playerturn++;
            }

            while (true) {
                try {
                    System.out.println(playerTurnName + ", how many clips would you like to take?");
                    int takeAmount = Integer.parseInt(sc.next());
                    while (verify(takeAmount)) {
                        takeAmount = sc.nextInt();
                    }
                    Board.subNumClips(takeAmount);
                    break;

                } catch (NumberFormatException e) {
                    System.out.println("\nINVALID: Please enter a number between 1 and "
                            + (Math.floor(Board.getNumClips() / 2) > 1 ? (int) Math.floor(Board.getNumClips() / 2) : 1)
                            + " clips.");
                }
            }
            playerturn++;
            switchPlayerTurn();
            hasGameEnded();
        }
    }

    public boolean verify(int takeAmount) {
        if (takeAmount > 1 && takeAmount <= Math.floor(Board.getNumClips() / 2) || takeAmount == 1) {
            return false;
        } else {
            System.out.println("\nINVALID: Please enter a number between 1 and "
                    + (Math.floor(Board.getNumClips() / 2) > 1 ? (int) Math.floor(Board.getNumClips() / 2) : 1)
                    + " clips.");
            System.out.println(playerTurnName + ", how many clips would you like to take?");
            return true;
        }
    }

    /**
     * Method used to determine if Game has ended
     * Precondition:
     * Postcondition: player playing switches
     */
    public void switchPlayerTurn() {
        if (playerturn % 2 == 0) {
            playerTurnName = player1.getPlayerName();
        }else {
            playerTurnName = player2.getPlayerName();
        }
    }

    /**
     * Method used to determine if Game has ended
     * Precondition: play is true
     * Postcondition: Game will end at if the number of clips is equal to 0,
     * otherwise, the game keeps running
     */
    public void hasGameEnded() {
        if (Board.getNumClips() == 0) {

            System.out.println("\n" + playerTurnName + " has won the game!");
            if (playAgain()) {
                Board.populate();
            } else {
                play = false;
            }
        }
    }

    public boolean playAgain() {
        System.out.println("Would you like to play again? (Yes or No)");
        String response = sc.next();
        if (response.toLowerCase().equals("yes")) {
            switchPlayerTurn();
            return true;
        }
        return false;
    }

    public void playComputerMove() {
        int[] winningMoves = { 1, 3, 7, 15, 31 };
        if (winningPosition(winningMoves)) {
            makeWinningPosition(winningMoves);
        } else {
            Board.subNumClips(
                    (Math.floor(Board.getNumClips() / 2) > 1 ? (int) Math.floor(Board.getNumClips() / 2) : 1));
            System.out.println("\nComputer has taken "
                    + (Math.floor(Board.getNumClips() / 2) > 1 ? (int) Math.floor(Board.getNumClips() / 2) : 1)
                    + " clips");
            ;
        }

        System.out.println("\nThere are " + Board.getNumClips() + " clips left to take!");
    }

    public boolean winningPosition(int[] winningMoves) {
        for (int i : winningMoves) {
            if (Board.getNumClips() == i) {
                return false;
            }
        }
        return true;
    }

    public void makeWinningPosition(int[] winningMoves) {
        int lowerbound = (int) Math.ceil(Board.getNumClips() / 2);
        for (int i : winningMoves) {
            if (i >= lowerbound && i <= Board.getNumClips()) {
                int clipsTaken = Board.getNumClips() - i;
                Board.subNumClips(clipsTaken);
                System.out.println("\nComputer has taken " + (clipsTaken) + " clips");
            }
        }

    }
}
