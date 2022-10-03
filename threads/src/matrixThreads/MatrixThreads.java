package matrixThreads;
import matrix.IMatrix;
import mlutiThreat.MultiThreads;

import java.util.ArrayList;
import java.util.List;

public class MatrixThreads {
    public static void multiply(IMatrix a, IMatrix b, IMatrix r) {
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < a.rowCount();i++) {
            MultiThreads task = new MultiThreads(r, a, b, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);

            if(threads.size() == Runtime.getRuntime().availableProcessors()) {
                waitThr(threads);
            }
        }
    }

    private static void waitThr(List<Thread> threads) {
        for(Thread t : threads) {
            try {
                t.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
