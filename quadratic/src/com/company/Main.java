package com.company;


import com.company.solver.QFormulaSolver;
import com.company.solver.QuadraticFormulaSolver;
import com.company.view.QuadraticEquationSolverConsoleView;
import com.company.view.QuadraticEquationSolverView;

public class Main {
    static QFormulaSolver solver;
    static QuadraticEquationSolverView view;
    public static void main(String[] args) {
        solver = new QuadraticFormulaSolver();
        view = new QuadraticEquationSolverConsoleView();
        view.Init(solver);
        view.View();
    }
}
