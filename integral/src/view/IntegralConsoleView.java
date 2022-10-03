package view;

import functions.Function;
import functions.examples.builder.ExampleBuilder;
import functions.examples.cosineexample.CosineExampleBuilder;
import integrals.IntegralAlgorithm;
import integrals.MonteCarlo;
import integrals.Trapez;

import java.util.Scanner;

public class IntegralConsoleView implements IntegralView {
    private Scanner sc;
    protected static ExampleBuilder functionBuilder;
    protected static Function givenExample;
    protected static IntegralAlgorithm algorithm;
    double a, b;
    int n;

    public void enterValues() {
        int wybor;
        while (true) {
            System.out.println("Ktory algorytm ma sie wykonac?\n[1] - MonteCarlo\n[2] - Trapez");
            wybor = enterInt();
            if (wybor == 1 || wybor == 2)
                break;
            else
                System.err.println("Nie wybrano 1 lub 2");
        }

        System.out.print("Podaj a: ");
        a = enterDouble();

        System.out.println("Podaj b: ");
        b = enterDouble();

        System.out.println("Podaj n: ");
        n = enterInt();

        functionBuilder = new CosineExampleBuilder();
        givenExample = functionBuilder.build();
        if (wybor == 1) {
            algorithm = new MonteCarlo();
        } else {
            algorithm = new Trapez();
        }
        algorithm.setFunction(givenExample);
        algorithm.setA(a);
        algorithm.setB(b);
        algorithm.setN(n);
    }


    public void getSolution() {
        algorithm.calculateIntegral();
        double integral = algorithm.getIntegral();
        double exactIntegral = givenExample.getExactIntegralValue(b) - givenExample.getExactIntegralValue(a);
        double error = Math.abs(integral - exactIntegral);

        System.out.println("Numeryczna\t" + integral);
        System.out.println("Dokładna\t" + exactIntegral);
        System.out.println("Błąd\t\t" + error);
    }

    public int enterInt() {
        int res;
        String x;
        try {
            x = sc.nextLine();
            res = Integer.parseInt(x);
        } catch (Exception exception) {
            System.err.println("Nie wprowadzono Int");
            res = enterInt();
        }
        return res;
    }

    public double enterDouble() {
        double res;
        String x;
        try {
            x = sc.nextLine();
            res = Double.parseDouble(x);
        } catch (Exception exception) {
            System.err.println("Nie wprowadzono Double");
            res = enterDouble();
        }
        return res;
    }

    @Override
    public void view() {
        while (true) {
            enterValues();
            getSolution();
        }
    }

    @Override
    public void init() {
        this.sc = new Scanner(System.in);
    }
}


