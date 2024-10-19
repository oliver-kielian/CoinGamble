import java.util.ArrayList;

public class Leaderboard {
    protected ArrayList<String> leaderBoard = new ArrayList<>();
    public void updateLeaderboard(ArrayList<String> leaderBoard)
    {
        this.leaderBoard = leaderBoard;
    }
    public ArrayList<String> getLeaderBoard()
    {
        return leaderBoard;
    }
}
