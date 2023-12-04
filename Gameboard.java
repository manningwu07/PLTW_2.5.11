import java.lang.Math;

public class Gameboard {
    private static int numPaperClips;
    public Gameboard(){
        numPaperClips = (int)(Math.floor(Math.floor(Math.random() * 40) + 10));
    }

    public int getNumPaperClips(){
        return numPaperClips;
    }

    public void takeNumPaperClips(int subAmount){
        numPaperClips -= subAmount;
    }

    public static boolean verify(int subAmount){
        if(numPaperClips == 1 && subAmount == 1){
            return true;
        }
        if(subAmount >= 1 && subAmount <= Math.floor(numPaperClips/2)){
            return true;
        }
        System.out.println("Please take between 1 and " + (numPaperClips == 1 ? 1 : (int) Math.floor(numPaperClips/2)) + " clips");
        return false;
    }
}
