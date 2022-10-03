package view;

import matrix.IMatrix;
import matrix.Matrix;
import matrix.generators.DefaultGenerator;
import matrixThreads.MatrixThreads;
import java.util.Date;
import java.util.Scanner;

public class View implements IView {
    private final Scanner sc = new Scanner(System.in);

    public void solution() {
        IMatrix a;
        IMatrix b;
        IMatrix c;
        IMatrix d;
        int m;

        System.out.println("Wprowadź rozmiar macierzy MxM:");
        m = enterInt();

        a = DefaultGenerator.generateRandomMatrix(m, m, 0, 3);
        b = DefaultGenerator.generateRandomMatrix(m, m, 0, 3);

        System.out.println("Mnożę macierze klasycznie...");
        Date start = new Date();
        c = IMatrix.multiply(a, b);
        Date end = new Date();
        System.out.println("Czas mnożenia w milisekundach: " + (end.getTime() - start.getTime()));


        d = new Matrix(m,m);
        System.out.println("\nMnożę macierze na watkach...");
        start = new Date();
        MatrixThreads.multiply(a,b,d);
        end = new Date();
        System.out.println("Czas mnożenia w milisekundach: " + (end.getTime() - start.getTime()));
    }

    @Override
    public void view() {
        int wybor;
        while (true) {
            System.out.println("[1] - Program\n[2] - Koniec");
            wybor = enterInt();
            if (wybor == 1)
                solution();
            else if (wybor == 2)
                break;
            else
                System.out.println("Wybierz 1 lub 2!\n");
        }
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
}