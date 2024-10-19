import java.util.Objects;
public class Bet {
    double betAmount = 0;
    String side;
    Coin coin;

    public Bet()
    {
        coin = new Coin();
    }
    public boolean placeBet(double bet)
    {
        betAmount = bet;
        if(betAmount != 0)
        {
           return betLogic();
        }
        else
        {
            //TODO: error message from error class
            return false;
        }
    }
    public boolean betLogic()
    {
        int coinSide = coin.flip();
        if(coinSide == 1 && Objects.equals(side, "heads"))
        {
            return true;
        }
        else if(coinSide == 2 && Objects.equals(side, "tails"))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public void selectSide(String side)
    {
        this.side = side;
    }
}
