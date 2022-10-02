package project.collections;

import project.currency.ICurrency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDataCollection implements IDataCollection {
    List<ICurrency> curr;

    public CurrencyDataCollection() {
        curr = new ArrayList<>();
    }

    @Override
    public String ToString() {
        StringBuilder napis = new StringBuilder("Wszystkie waluty:\n\n");
        for (ICurrency cr : curr) {
            napis.append(cr.getName()).append("  ").append(cr.getCode()).append("  ").append(cr.getFactor()).append(" ").append(cr.getRate()).append("\n");
        }
        return napis.toString();
    }

    @Override
    public List<ICurrency> getCurrencyList() {
        return curr;
    }

    @Override
    public ICurrency getCurrencyByCode(ICurrency currency) {
        for (ICurrency x : curr)
            if (x.equals(currency))
                return x;
        return null;
    }
}
