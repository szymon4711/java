package pl.retsuz;

import view.IntegralConsoleView;

public class Main {
    static IntegralConsoleView view;
    public static void main(String[] args) {
         view = new IntegralConsoleView();
         view.init();
         view.view();
    }
}
