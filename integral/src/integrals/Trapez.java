package integrals;

public class Trapez extends IntegralAlgorithm {

    public Trapez() {
        this.sum = 0.0;
    }

    @Override
    public void calculateIntegral() {
        double step = (b - a) / n;
        double value_a = function.getValue(a), value_b;
        for (int i = 1; i <= n; i++) {
            value_b = function.getValue(a + step * i);
            sum += (value_a + value_b);
            value_a = value_b;
        }
        sum = sum * 0.5 * step;
    }
}
