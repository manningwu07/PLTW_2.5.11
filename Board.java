import java.lang.Math;

public class Board {
    private static int numClips;

    public static void populate() {
        numClips = (int) (Math.random() * 40 + 10);
    }

    public static void subNumClips(int subAmount) {
        numClips -= subAmount;
    }

    public static int getNumClips() {
        return numClips;
    }
}