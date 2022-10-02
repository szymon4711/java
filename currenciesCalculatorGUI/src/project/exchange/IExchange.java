package project.exchange;

import project.currency.ICurrency;

public interface IExchange {
    double exchange(ICurrency src, ICurrency tgt, double amt); //Oblicza otrzymaną ilość środka pieniężnego po podaniu wartości źródłowej, waluty źródłowej oraz waluty docelowej
}
