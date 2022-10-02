package project.view;

import project.collections.IDataCollection;
import project.currency.ICurrency;
import project.exchange.IExchange;

import java.util.Scanner;

public class StandardView implements ICurrencyView{
    Scanner sc;
    IExchange exch;
    IDataCollection dataColl;

    @Override
    public void setExchange(IExchange exchange) {
        this.exch = exchange;
    }

    @Override
    public void setDataCollection(IDataCollection collection) {
        this.dataColl = collection;
    }

    @Override
    public void ViewAll(IDataCollection coll) {
        System.out.println(dataColl.ToString());
    }

    @Override
    public ICurrency StringToCurrency(String code) {
        for(ICurrency x : dataColl.getCurrencyList()) {
            if(x.getCode().equals(code)) {
                return x;
            }
        }
        return null;
    }


    @Override
    public ICurrency ChooseCurrency(String label) {
        System.out.print(label);
        String tmp;
        ICurrency res;
        tmp = sc.next();
        res = StringToCurrency(tmp);
        return res;
    }

    @Override
    public void exchange() {
        ICurrency from = ChooseCurrency("Wprowadz kod waluty ktora chcesz wymienic:\n");
        ICurrency to = ChooseCurrency("Wprowadz kod waluty na ktora chcesz wymienic:\n");
        System.out.println("Wprowadz ilosc pieniedzy do wymiany:");
        try {
            String mon = sc.next();
            double money = Double.parseDouble(mon);
            double tmp = exch.exchange(from, to, money);
            System.out.println(tmp);
        }
        catch (Exception exception){
            System.err.println("Wprowadzono zly kod waluty");
        }
    }

    @Override
    public void menu() {
        while (true) {
            System.out.print("[1] - wyswietl waluty\n[2] - przelicz na inna walute\n[3] - exit\n");
            int wybor = myFun();

            if (wybor == 1)
                System.out.println(dataColl.ToString());
            else if (wybor == 2)
                exchange();
            else if (wybor == 3)
                break;
            else
                System.err.println("Wybierz 1, 2 lub 3");
        }
    }

    public int myFun(){
        try {
            String wybor = sc.next();
            return Integer.parseInt(wybor);
        }
        catch (Exception exception){
            return -1;
        }
    }

    public StandardView() {
        this.sc = new Scanner(System.in);
    }
}
