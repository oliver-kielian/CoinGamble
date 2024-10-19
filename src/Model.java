import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Model {
    private Connection connection = null;
    protected User user;
    protected Leaderboard leaderboard;

    Model()
    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            String cmd = "CREATE TABLE IF NOT EXISTS users (" +
                    "username STRING PRIMARY KEY," +
                    "password STRING," +
                    "earnings Double);";
            connection.createStatement().executeUpdate(cmd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user = new User();
    }
    public void open()
    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void setLeaderboard()
    {
        ArrayList<String> leaderBoard = new ArrayList<>();
        try{
            Statement stm = connection.createStatement();

            String cmd = "SELECT earnings, username from users ORDER BY earnings DESC LIMIT 3 ;";

            ResultSet rs = stm.executeQuery(cmd);
            while(rs.next()) {
                double earnings = rs.getDouble("earnings");
                String username = rs.getString("username");
                String s = String.format("%s %.2f", username, earnings);
                leaderBoard.add(s);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        leaderboard.updateLeaderboard(leaderBoard);
    }

    public boolean logInUser(String username, String password) {
        System.out.println(password);
        String passwordVar = null;
        try {

            Statement stm = connection.createStatement();

            String cmd = "SELECT password FROM users WHERE username = '" + username + "'";
            System.out.println(cmd);

            ResultSet rs = stm.executeQuery(cmd);
            while(rs.next()) {
                passwordVar = rs.getString("password");
            }
            System.out.println(passwordVar);
            if(Objects.equals(passwordVar, password))
            {
                user.setusername(username);
                return true;
            }
            else
            {
                return false;
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public void add(String username, String password, int earnings)
    {
        try {

            String insert = "INSERT INTO users(username, password, earnings) VALUES(?, ?, ?);";
            PreparedStatement prepstm = connection.prepareStatement(insert);
            prepstm.setString(1, username);
            prepstm.setString(2, password);
            prepstm.setInt(3, earnings);
            prepstm.executeUpdate();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateWin(String username, double earnings) {
        try {
            Statement stm = connection.createStatement();

            String cmd = "SELECT earnings FROM users WHERE username = '" + username + "'";
            double intEarning = 0;

            ResultSet rs = stm.executeQuery(cmd);
            while (rs.next()) {
                intEarning = rs.getDouble("earnings");
            }
                String update = "UPDATE users SET earnings = ? WHERE username = ?";
                PreparedStatement prepstm = connection.prepareStatement(update);
                prepstm.setString(2, username);
                prepstm.setDouble(1, intEarning+earnings);
                prepstm.executeUpdate();
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
    }
        public void updateLose(String username, double earnings)
        {
            try {
            Statement stm = connection.createStatement();

            String cmd = "SELECT earnings FROM users WHERE username = ?;";
            double intEarning = 0;

            ResultSet rs = stm.executeQuery(cmd);
            while (rs.next()) {
                intEarning = rs.getInt("earnings");
            }

            String update = "UPDATE users SET earnings = ? WHERE username = ?";
            PreparedStatement prepstm = connection.prepareStatement(update);
            prepstm.setString(2, username);
            prepstm.setDouble(1, intEarning-earnings);
            prepstm.executeUpdate();
            } catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    public void close()
    {
        try {
            connection.close();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}
