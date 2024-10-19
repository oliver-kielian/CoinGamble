import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View {
    private JFrame loggedInFrame, loggedOutFrame;
    private JTextField loginUsername, loginPassword, createUsername, createPassword, bet;
    private DefaultListModel<String> listModel;
    private JList leaderBoardList;
    private JButton heads = new JButton("HEADS"), tails = new JButton("TAILS"), loginButton, logoutButton = new JButton("LOGOUT"), createButton;

    View()
    {
        loggedOutView();
    }
    public void loggedOutView()
    {
        loggedOutFrame = new JFrame();
        JTabbedPane jtabs = new JTabbedPane();
        jtabs.add("Login", makeLoginTab());
        jtabs.add("Create Account", makeCreateAccountTab());

        loggedOutFrame.add(jtabs);

        loggedOutFrame.setSize(1000, 1000);
        loggedOutFrame.setVisible(true);
    }
    public void loggedInView()
    {
        loggedOutFrame.setVisible(false);
        loggedInFrame = new JFrame();
        JTabbedPane jtabs = new JTabbedPane();
        listModel = new DefaultListModel<>();
        jtabs.add("Homepage", makeHomePage());
        jtabs.add("Game", makeGameTab());


        loggedInFrame.add(jtabs);

        loggedInFrame.setSize(1000, 1000);
        loggedInFrame.setVisible(true);
    }
    public void setLoggedInViewFalse()
    {
        loggedInFrame.setVisible(false);
    }
    public JPanel makeLoginTab()
    {
        JPanel login = new JPanel(new GridLayout(5, 2));
        loginUsername = new JTextField(15);
        loginPassword = new JTextField(15);

        loginButton = new JButton("LOGIN");

        login.add(new JLabel("Username:"));
        login.add(loginUsername);
        login.add(new JLabel("Password:"));
        login.add(loginPassword);
        login.add(loginButton);

        return login;
    }

    public JPanel makeHomePage()
    {
       JPanel homePage = new JPanel();

       leaderBoardList = new JList<>(listModel);
       homePage.add(leaderBoardList);
       homePage.add(logoutButton);

       return homePage;
    }

    public JPanel makeCreateAccountTab()
    {
        JPanel createAccount = new JPanel(new GridLayout(5, 2));
        createUsername = new JTextField(15);
        createPassword = new JTextField(15);

        createButton = new JButton("CREATE ACCOUNT");

        createAccount.add(new JLabel("Username:"));
        createAccount.add(createUsername);
        createAccount.add(new JLabel("Password:"));
        createAccount.add(createPassword);
        createAccount.add(createButton);
        return createAccount;
    }

    public JPanel makeGameTab()
    {
        JPanel game = new JPanel();

        bet = new JTextField(15);
        logoutButton = new JButton("LOGOUT");
        ImageIcon coinImage = new ImageIcon("coin.gif");
        JLabel coin = new JLabel(coinImage);

        game.add(logoutButton);
        game.add(coin);
        game.add(bet);
        game.add(heads);
        game.add(tails);

        return game;
    }

    public void setCreateButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    public void setHeadsButtonListener(ActionListener listener) { heads.addActionListener(listener); }

   public void setTailsButtonListener(ActionListener listener) {
        tails.addActionListener(listener);
    }
    public void setLoginButtonListener(ActionListener listener) {loginButton.addActionListener(listener);}
    public void setLogoutButtonListener(ActionListener listener) {logoutButton.addActionListener(listener);}

    public String getCreateUsernameTextField()
    {
        return createUsername.getText();
    }
    public String getCreatePasswordTextField()
    {
        return createPassword.getText();
    }
    public String getBetAmount()
    {
        return bet.getText();
    }
    public String getUsername()
    {
        return loginUsername.getText();
    }
    public String getPassword()
    {
        return loginPassword.getText();
    }

    public void displayLeaderboard(ArrayList<String> data)
    {
        listModel.clear();
        for (String value : data) {
            listModel.addElement(value);
        }
    }

}
