package com.company.solver;

import com.company.solver.exceptions.SolverException;

//sprawdzic
public interface QFormulaSolver {
    double[] solve() throws SolverException;

    void setInitialParameters(double a, double b, double c);

    boolean IsComplex();
}
