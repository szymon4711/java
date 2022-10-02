package pl.retsuz.view;

import pl.retsuz.currency.ICurrency;
import pl.retsuz.collections.IDataCollection;
import pl.retsuz.exchange.IExchange;

public interface ICurrencyView {
    void setExchange(IExchange exchange); //Ustawia referencję do obiektu typu implementującego IExchange

    void setDataCollection(IDataCollection collection); //Ustawia referencję do obiektu typu implementującego IDataCollection

    void ViewAll(IDataCollection coll); //Wyświetla wszystkie waluty

    ICurrency StringToCurrency(String code); //Po podaniu kodu tworzy obiekt Currency z ustawionym kodem, a następnie z jego wykorzystaniem przeszukuje kolekcję typu IDataCollection

    ICurrency ChooseCurrency(String label); //Prosi uytkownika o podanie kodu, a następnie deleguje wybór waluty do metody StringToCurrency

    void exchange(); //Interakcyjnie na bazie metody ChooseCurrency dokonuje przeliczenia walut na bazie IExchange

    void menu(); //Interakcyjnie wyświetla menu
}
