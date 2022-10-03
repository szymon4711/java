package view;

import account.Account;
import database.Database;
import transactions.Transactions;

import javax.swing.*;
import java.awt.*;

public class Bankomat {
    private JButton zmienPINButton;
    private JButton wplataButton;
    private JButton wyplataButton;
    private JButton stanKontaButton;
    private JButton przelewButton;
    private JButton homeButton;
    private JPanel mainPanel;
    private JLabel centerLabel;
    private JLabel welcomeLabel;
    private JPasswordField passwordField1;
    private JLabel kontoLabel;
    private JLabel sumaLabel;
    private JTextField textField1;
    private JButton zatwierdzButton;
    private static JFrame mainFrame;
    private int option;
    private int option_2 = 0;

    public Bankomat(Account account, Database database, int index) {
        mainFrame = new JFrame("Konto");
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(500, 400));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - mainFrame.getWidth()) / 2.5);
        int y = (int) ((dimension.getHeight() - mainFrame.getHeight()) / 3);
        mainFrame.setLocation(x, y);
        welcomeLabel.setText("                         Uzytkownik: " + account.getUsername());
        mainFrame.pack();
        mainFrame.setVisible(true);
        passwordField1.setVisible(false);
        sumaLabel.setVisible(false);
        kontoLabel.setVisible(false);
        textField1.setVisible(false);
        zatwierdzButton.setVisible(false);
        wyplataButton.setPreferredSize(new Dimension(40, 40));
        wplataButton.setPreferredSize(new Dimension(40, 40));
        homeButton.setPreferredSize(new Dimension(40, 40));
        przelewButton.setPreferredSize(new Dimension(40, 40));
        stanKontaButton.setPreferredSize(new Dimension(40, 40));
        zmienPINButton.setPreferredSize(new Dimension(40, 40));

//        account.setMoney(111);
//        Database.accounts.set(index, account);
//        database.updateDatabase();

        /* 0 - home
           1 - zmien PIN
           2 - stan konta
           3 - wplata
           4 - wyplata
           5 - przelew
        */
        zmienPINButton.addActionListener(e -> {
            if (option == 0) {
                zmienPINButton.setText("");
                wyplataButton.setText("");
                stanKontaButton.setText("");
                przelewButton.setText("");
                wplataButton.setText("");
                passwordField1.setVisible(true);
                zatwierdzButton.setVisible(true);
                option = 1;
            }
            if (option == 3) {
                textField1.setText("20");
            }
            if (option == 4) {
                textField1.setText("20");
            }
            if (option == 5 && option_2 == 0) {
                zmienPINButton.setText("");
                wyplataButton.setText("");
                stanKontaButton.setText("");
                przelewButton.setText("");
                wplataButton.setText("");
                textField1.setVisible(true);
                passwordField1.setVisible(true);
                zatwierdzButton.setVisible(true);
                textField1.setText("Podaj kwote");
                option_2 = 1;
            }

        });

        wyplataButton.addActionListener(e -> {
            if (option == 0) {
                zmienPINButton.setText("20");
                wplataButton.setText("50");
                stanKontaButton.setText("100");
                przelewButton.setText("200");
                wyplataButton.setText("");
                textField1.setVisible(true);
                zatwierdzButton.setVisible(true);
                option = 4;
            }
            if (option == 3) {
                textField1.setText("50");
            }
            if (option == 5 && option_2 == 0) {
                zmienPINButton.setText("");
                wyplataButton.setText("");
                stanKontaButton.setText("");
                przelewButton.setText("");
                wplataButton.setText("");
                textField1.setVisible(true);
                zatwierdzButton.setVisible(true);
                textField1.setText("Podaj kwote");
                option_2 = 2;
            }

        });

        wplataButton.addActionListener(e -> {
            if (option == 0) {
                zmienPINButton.setText("20");
                wyplataButton.setText("50");
                stanKontaButton.setText("100");
                przelewButton.setText("200");
                wplataButton.setText("");
                textField1.setVisible(true);
                zatwierdzButton.setVisible(true);
                option = 3;
            }
            if (option == 4) {
                textField1.setText("50");
            }
        });

        stanKontaButton.addActionListener(e -> {
            if (option == 0) {
                zmienPINButton.setText("");
                wyplataButton.setText("");
                stanKontaButton.setText("");
                przelewButton.setText("");
                wplataButton.setText("");
                kontoLabel.setVisible(true);
                sumaLabel.setVisible(true);
                kontoLabel.setText("                         Stan konta: " + account.getMoney());
                centerLabel.setText("                         Stan lokaty: " + account.getInvestment());
                sumaLabel.setText("                         Suma srodkow: " + account.getTotalMoney());
                option = 2;
            }
            if (option == 3) {
                textField1.setText("100");
            }
            if (option == 4) {
                textField1.setText("100");
            }
            if (option == 5 && option_2 == 0) {
                zmienPINButton.setText("");
                wyplataButton.setText("");
                stanKontaButton.setText("");
                przelewButton.setText("");
                wplataButton.setText("");
                textField1.setVisible(true);
                zatwierdzButton.setVisible(true);
                textField1.setText("Podaj kwote");
                option_2 = 3;
            }
        });

        przelewButton.addActionListener(e -> {
            if (option == 0) {
                zmienPINButton.setText("Numer konta");
                wyplataButton.setText("Glowne -> Lokata");
                stanKontaButton.setText("Lokata -> Glowne");
                przelewButton.setText("");
                wplataButton.setText("");
                option = 5;
            }
            if (option == 3) {
                textField1.setText("200");
            }
            if (option == 4) {
                textField1.setText("200");
            }

        });

        zatwierdzButton.addActionListener(e -> {
            if (option == 1) { //zmien pin
                String pin = passwordField1.getText();
                account.setPin(pin);
                homeButton.doClick();
            }
            if (option == 3) {
                try {
                    String text = textField1.getText();
                    double value = Double.parseDouble(text);
                    Transactions.wplata(account, value);
                    JOptionPane.showMessageDialog(null, "Wplata udana!");
                    homeButton.doClick();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Zly format liczby!");
                }
            }
            if (option == 4) {
                try {
                    String text = textField1.getText();
                    double value = Double.parseDouble(text);
                    if (Transactions.wyplata(account, value))
                        JOptionPane.showMessageDialog(null, "Wyplata udana!");
                    else
                        JOptionPane.showMessageDialog(null, "Nie posiadasz tyle pieniedzy!");
                    homeButton.doClick();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Zly format liczby!");
                }
            }
            if (option == 5 && option_2 == 1) {
                try {
                    String text = textField1.getText();
                    double value = Double.parseDouble(text);
                    String accNr = passwordField1.getText();
                    if (Transactions.przelew(account, accNr, value))
                        JOptionPane.showMessageDialog(null, "Przelew udany!");
                    else
                        JOptionPane.showMessageDialog(null, "Nie posiadasz tyle pieniedzy lub nieznany nr konta!");
                    homeButton.doClick();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Zly format liczby!");
                }
            }

            if (option == 5 && option_2 == 2) {
                try {
                    String text = textField1.getText();
                    double value = Double.parseDouble(text);
                    if (Transactions.przelewWewnetrzny(account, value, 0))
                        JOptionPane.showMessageDialog(null, "Przelew udany!");
                    else
                        JOptionPane.showMessageDialog(null, "Nie posiadasz tyle pieniedzy!");
                    homeButton.doClick();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Zly format liczby!");
                }
            }

            if (option == 5 && option_2 == 3) {
                try {
                    String text = textField1.getText();
                    double value = Double.parseDouble(text);
                    if (Transactions.przelewWewnetrzny(account, value, 1))
                        JOptionPane.showMessageDialog(null, "Przelew udany!");
                    else
                        JOptionPane.showMessageDialog(null, "Nie posiadasz tyle pieniedzy!");
                    homeButton.doClick();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Zly format liczby!");
                }
            }

        });

        homeButton.addActionListener(e -> {
            zmienPINButton.setText("Zmien Pin");
            wyplataButton.setText("Wyplata");
            stanKontaButton.setText("Stan konta");
            przelewButton.setText("Przelew");
            wplataButton.setText("Wplata");
            centerLabel.setText("                                 Wybierz opcje");
            option = 0;
            option_2 = 0;
            Database.accounts.set(index, account);
            database.updateDatabase();
            passwordField1.setVisible(false);
            sumaLabel.setVisible(false);
            kontoLabel.setVisible(false);
            textField1.setVisible(false);
            zatwierdzButton.setVisible(false);
        });


    }
}
