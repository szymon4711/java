package view;

import algorithm.ShutingYard;
import solution.Solution;

import javax.swing.*;

public class View {
    private JButton zero;
    private JButton plus;
    private JButton kropka;
    private JButton rowna;
    private JButton jeden;
    private JButton dwa;
    private JButton trzy;
    private JButton minus;
    private JButton cztery;
    private JButton razy;
    private JButton szesc;
    private JButton piec;
    private JButton lewy;
    private JButton prawy;
    private JButton back;
    private JButton clr;
    private JTextField textField1;
    private JPanel mainPanel;
    private JButton siedem;
    private JButton osiem;
    private JButton dziewiec;
    private JButton podziel;
    private String str = "";

    public View(){
        zero.addActionListener(e -> {
            str += "0";
            textField1.setText(str);
        });
        jeden.addActionListener(e -> {
            str += "1";
            textField1.setText(str);
        });
        dwa.addActionListener(e -> {
            str += "2";
            textField1.setText(str);
        });
        trzy.addActionListener(e -> {
            str += "3";
            textField1.setText(str);
        });
        cztery.addActionListener(e -> {
            str += "4";
            textField1.setText(str);
        });
        piec.addActionListener(e -> {
            str += "5";
            textField1.setText(str);
        });
        szesc.addActionListener(e -> {
            str += "6";
            textField1.setText(str);
        });
        siedem.addActionListener(e -> {
            str += "7";
            textField1.setText(str);
        });
        osiem.addActionListener(e -> {
            str += "8";
            textField1.setText(str);
        });
        dziewiec.addActionListener(e -> {
            str += "9";
            textField1.setText(str);
        });
        lewy.addActionListener(e -> {
            str += "(";
            textField1.setText(str);
        });
        prawy.addActionListener(e -> {
            str += ")";
            textField1.setText(str);
        });
        kropka.addActionListener(e -> {
            str += ".";
            textField1.setText(str);
        });
        plus.addActionListener(e -> {
            str += "+";
            textField1.setText(str);
        });
        podziel.addActionListener(e -> {
            str += "/";
            textField1.setText(str);
        });
        razy.addActionListener(e -> {
            str += "*";
            textField1.setText(str);
        });
        minus.addActionListener(e -> {
            str += "-";
            textField1.setText(str);
        });
        clr.addActionListener(e -> {
            str = "";
            textField1.setText(str);
        });
        back.addActionListener(e -> {
            if (str.length() > 0)
                str = str.substring(0, str.length() - 1);
            textField1.setText(str);
        });
        rowna.addActionListener(e -> {
            str = String.valueOf(Solution.solution(ShutingYard.shutingYard(str)));
            textField1.setText(str);
        });
    }

    public static void main(String[] args) {
        JFrame main = new JFrame("Kalkulator");
        View form = new View();
        main.setContentPane(form.mainPanel);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.pack();
        main.setVisible(true);
    }
}
