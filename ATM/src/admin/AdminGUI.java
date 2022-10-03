package admin;

import account.Account;
import database.Database;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminGUI {
    private JButton zatwierdzButton;
    private JComboBox uzytkownicyBox;
    private JButton zmienPINButton;
    private JButton informacjeButton;
    private JPanel mainPanel;
    private JPasswordField passwordField1;
    private JTextArea textArea1;
    private static JFrame mainFrame;
    private Account account;
    private int option = 0;

    public AdminGUI() {
        Database database = new Database();
        database.createDatabase();
        initForm(Database.accounts, uzytkownicyBox);
        passwordField1.setVisible(false);


        zmienPINButton.addActionListener(e -> {
            option = 1;
            passwordField1.setVisible(true);
            textArea1.append("Wprowadz nowy PIN");

        });

        informacjeButton.addActionListener(e -> {
            account = (Account) uzytkownicyBox.getSelectedItem();
            textArea1.setText("");
            textArea1.append("Stan konta: " + account.getMoney() +
                    "\nStan lokat: " + account.getInvestment() +
                    "\nSuma srodkow: " + account.getTotalMoney());

        });

        zatwierdzButton.addActionListener(e -> {
            if (option == 1) {
                account = (Account) uzytkownicyBox.getSelectedItem();
                String pin = passwordField1.getText();
                account.setPin(pin);
            }

            textArea1.setText("");
            database.updateDatabase();
            passwordField1.setVisible(false);
            option = 0;
        });

    }


    public static void initForm(ArrayList<Account> accounts, JComboBox uzytkownicyBox) {
        for (int i = 0; i < accounts.size(); i++) {
            uzytkownicyBox.addItem(accounts.get(i));
        }
    }


    public static void main(String[] args) {
        mainFrame = new JFrame("Admin");
        AdminGUI adminGUI = new AdminGUI();
        mainFrame.setContentPane(adminGUI.mainPanel);
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
