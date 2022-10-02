package pl.retsuz.currency;

public interface ICurrency {
    void setName(String name);

    void setCode(String code);

    void setRate(double rate);

    void setFactor(double factor);

    String getName();

    String getCode();

    double getFactor();

    double getRate();
}
