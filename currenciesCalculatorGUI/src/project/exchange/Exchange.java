package project.exchange;

import project.currency.ICurrency;

public class Exchange implements IExchange {
    @Override
    public double exchange(ICurrency src, ICurrency tgt, double amt) {
        return src.getRate() * amt / tgt.getRate();
    }
}
