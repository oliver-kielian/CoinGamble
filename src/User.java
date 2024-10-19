public class User {
    protected double timer;
    protected String username;
    public void startTimer()
    {
        timer = 1;
    }
    public void setusername(String username)
    {
        this.username = username;
    }
    public double getTimer()
    {
        return timer;
    }
}
