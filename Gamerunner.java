public class Gamerunner {
    public static void main(String[] args) {
        Board.populate();

        welcomeMessage();

        Game nim = new Game();
        nim.play();
    }

    public static void welcomeMessage() {
        System.out.println(
                "Hello, and welcome to the Game of Nim.\n\nGame Rules:\nThere is a stack of clips between two people\nYou must remove at leat 1 clip but no more than half during your turn\nYou and your opponenet will alternate turns\nLast person to take a clip loses\n\nGood Luck and Have Fun!!\n");
    }
}
