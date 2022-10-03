package account;

public class Account {
    private String username;
    private String pin;
    private double money;
    private double investment;
    private double totalMoney;
    private String accountNumber;


    public Account(String username, String pin, double money, double investment, String accountNumber) {
        this.username = username;
        this.pin = pin;
        this.money = money;
        this.investment = investment;
        this.accountNumber = accountNumber;
        setTotalMoney();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
        setTotalMoney();
    }

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
        setTotalMoney();
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney() {
        this.totalMoney = this.money + this.investment;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String toString() {
        return username;
    }
}
