package mlutiThreat;

import matrix.IMatrix;

public class MultiThreads implements IMultiThreads {
    private final IMatrix matrixA;
    private final IMatrix matrixB;
    private final IMatrix matrixRes;
    private final int row;

    public MultiThreads(IMatrix A, IMatrix B, IMatrix R, int row) {
        this.matrixA = A;
        this.matrixB = B;
        this.matrixRes = R;
        this.row = row;
    }

    @Override
    public void run() {
        for (int i = 0; i < matrixB.rowCount(); i++) {
            matrixRes.getData()[row][i] = 0;
            for (int j = 0; j < matrixA.getData()[row].length; j++) {
                matrixRes.getData()[row][i] += matrixA.getData()[row][j] * matrixB.getData()[j][i];
            }
        }
    }
}