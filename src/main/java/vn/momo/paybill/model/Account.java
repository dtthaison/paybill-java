package vn.momo.paybill.model;

public final class Account extends BaseModel {
    private static Account instance;
    private long amount;

    private Account(){}

    private Account(long amount) {
        this.amount = amount;
    }

    public void addAmount(long amount) {
        this.amount += amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
            instance.setId(1);
        }
        return instance;
    }

    public String table(){
        return "account";
    }

    public String insertValue(){
        return amount+"";
    }
}
