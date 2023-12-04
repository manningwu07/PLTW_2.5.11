public class Player {
    private String playerName;
    private boolean computer;

    public Player(String playerName) {
        this.playerName = playerName;
        computer = false;
    }

    public Player(int computer) {
        playerName = "Computer";
        this.computer = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getComputerStatus() {
        return computer;
    }
}
