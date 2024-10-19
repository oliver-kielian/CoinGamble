import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller {
    private View view;
    private Model model;
    private Bet betClass;
    public Controller(){
        view = new View();
        model = new Model();
        betClass = new Bet();

        view.setCreateButtonListener(new createActionListener());
        view.setHeadsButtonListener(new headsActionListener());
        view.setTailsButtonListener(new tailsActionListener());
        view.setLoginButtonListener(new loginActionListener());
        view.setLogoutButtonListener(new logOutActionListener());
    }

    private class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            model.open();
            String username = view.getCreateUsernameTextField();
            String password = view.getCreatePasswordTextField();
            if(Objects.equals(username, "") || Objects.equals(password, ""))
            {
                System.out.println("Please ensure all fields are filled in");
                //TODO: Call error from errors class
            }
            else {
                model.add(username, password, 500);
                model.logInUser(username, password);
                //view.displayLeaderboard(model.leaderboard.getLeaderBoard());
                model.close();
            }
            model.close();
        }
    }

    private class headsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.open();
            String betAmount = view.getBetAmount();
            if(Objects.equals(betAmount, ""))
            {
                System.out.println("Please ensure all fields are filled in");
                //TODO: Call error from errors class
            }
            else {
                boolean letters = betAmount.matches(".*[a-zA-Z].*");
                if (!letters) {
                    double bet = Double.parseDouble(betAmount);
                    if (bet < 0) {
                        System.out.println("Please enter a valid bet");
                    } else {
                        betClass.selectSide("heads");
                        boolean win = betClass.placeBet(bet);
                        if (win) {
                            model.updateWin(view.getUsername(), bet);
                            model.setLeaderboard();
                        } else {
                            model.updateLose(view.getUsername(), bet);
                            model.setLeaderboard();
                        }
                        view.displayLeaderboard(model.leaderboard.getLeaderBoard());
                        model.close();
                    }
                }
            }
        }
    }
    private class tailsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.open();
            String betAmount = view.getBetAmount();
            if(Objects.equals(betAmount, ""))
            {
                System.out.println("Please ensure all fields are filled in");
                //TODO: Call error from errors class
            }
            else {
                boolean letters = betAmount.matches(".*[a-zA-Z].*");
                if (!letters) {
                    double bet = Double.parseDouble(betAmount);
                    if (bet < 0) {
                        System.out.println("Please enter a valid bet");
                    }
                    else
                    {
                        betClass.selectSide("tails");
                        boolean win = betClass.placeBet(bet);
                        if(win)
                        {
                            model.updateWin(view.getUsername(), bet);
                            model.setLeaderboard();
                        }
                        else
                        {
                            model.updateLose(view.getUsername(), bet);
                            model.setLeaderboard();
                        }
                        view.displayLeaderboard(model.leaderboard.getLeaderBoard());
                        model.close();
                    }
                }
            }
        }
    }
    private class loginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            model.open();
            String username = view.getUsername();
            String password = view.getPassword();
            boolean login = model.logInUser(username, password);
            if(login)
            {
                view.loggedInView();
                //view.displayLeaderboard(model.leaderboard.getLeaderBoard());
                //TODO: leaderboard fuckin broken
                model.user.startTimer();
                model.close();
            }
            else
            {
                //TODO: error message from error class
                model.close();
            }
            model.close();
        }
    }

    private class logOutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            view.loggedOutView();
            view.setLoggedInViewFalse();
        }
    }
}
