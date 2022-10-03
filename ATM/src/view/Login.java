package view;

import account.Account;
import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Login {
    private JButton okButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel LoginLabel;
    private JLabel pinLabel;
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private Bankomat bankomat;
    private static JFrame mainFrame;


    public Login() {
        Database database = new Database();
        database.createDatabase();
        AtomicInteger counter = new AtomicInteger();

        okButton.addActionListener(e -> {
            String accNr = textField1.getText();
            String password = passwordField1.getText();
            if (counter.get() < 2) {
                for (Account currentAccount : Database.accounts) {
                    if (currentAccount.getAccountNumber().equals(accNr))
                        if (currentAccount.getPin().equals(password)) {
                            System.out.println("acces");
                            int index = Database.accounts.indexOf(currentAccount);
                            bankomat = new Bankomat(currentAccount, database, index);
                            mainFrame.dispose();
                            break;
                        }
                }
                welcomeLabel.setText("                     Wprowadzono niepoprawne dane, sprobuj ponownie");
                counter.getAndIncrement();
            } else {
                welcomeLabel.setText("                                           " +
                        "Osiagnieto limit 3 prób!");
            }
        });


    }


    public static void main(String[] args) {
        mainFrame = new JFrame("Bankomat");
        Login login = new Login();
        mainFrame.setContentPane(login.mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(400, 300));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2.5);
        int y = (int) ((dimension.getHeight() - mainFrame.getHeight()) / 3);
        mainFrame.setLocation(x, y);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
