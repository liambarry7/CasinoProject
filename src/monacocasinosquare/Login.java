package monacocasinosquare;


public class Login {
    
protected String username;
    protected String password;
    protected int accountNumber;
    protected int money;
    protected int moneyWon;
    protected int moneyLost;
    protected int gamesPlayed;

    public Login(String username, String password, int accountNumber, int money, int moneyWon, int moneyLost, int gamesPlayed) {
        this.username = username;
        this.password = password;
        this.accountNumber = accountNumber;
        this.money = money;
        this.moneyWon = moneyWon;
        this.moneyLost = moneyLost;
        this.gamesPlayed = gamesPlayed;
    }
    
    public String toString () {
        return username + ", " + password + ", " + accountNumber + ", " + money + ", " + moneyWon + ", " + moneyLost + ", " + gamesPlayed;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getMoney() {
        return money;
    }

    public int getMoneyWon() {
        return moneyWon;
    }   
    
    public int getMoneyLost() {
        return moneyLost;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setMoneyWon(int moneyWon) {
        this.moneyWon = moneyWon;
    }
    
    public void setMoneyLost(int moneyLost) {
        this.moneyLost = moneyLost;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
    
    
}
