package com.company.view;

import com.company.solver.QFormulaSolver;
import com.company.solver.exceptions.SolverException;
import java.util.Scanner;

public class QuadraticEquationSolverConsoleView implements QuadraticEquationSolverView{
    private QFormulaSolver solver;
    private Scanner sc;

    protected double parseWithMessage(String message){
        System.out.println(message);
        String line;
        double res;
        try {
            line = sc.nextLine();
            res = Double.parseDouble(line);
        }
        catch (Exception ex){
            System.err.println("Wprowadzono niepoprawne dane!");
            res = parseWithMessage(message);
        }
        return res;
    }

    protected void parseFactors(){
        double a,b,c;
        a = parseWithMessage("Wprowadz a");
        b = parseWithMessage("Wprowadz b");
        c = parseWithMessage("Wprowadz c");
        this.solver.setInitialParameters(a,b,c);
    }

    protected  void displaySolution(double [] res){
        StringBuilder label = new StringBuilder("Podane rownanie posiada nastepujace rozwiazania w dzidzieni liczb ");
        if(solver.IsComplex()) label.append("zespolomych \n");
        else label.append("rzeczywistych:\n");
        for (int i = 0; i < res.length;i++)
            label.append("x").append(i).append("=").append(res[i]).append(";\t");
        System.out.println(label);
    }

    protected void getSolution(){
        double [] res;
        try {
            res=solver.solve();
            displaySolution(res);
        }
        catch (SolverException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void View() {
        while (true){
            parseFactors();
            getSolution();
        }
    }

    @Override
    public void Init(QFormulaSolver solver) {
        this.solver = solver;
        this.sc = new Scanner(System.in);
    }
}
