package vn.momo.paybill.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill extends BaseModel implements ModelScanInterface {
    private String type; // INTERNET|ELECTRIC
    private long amount;
    private String dueDate;
    private String state; // UNPAID|PAID
    private String provider;

    public Bill() { }

    public Bill(String type, long amount, String dueDate, String state, String provider) {
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.state = state;
        this.provider = provider;
    }

    public String getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public Date getDueDate() throws Exception {
        return new SimpleDateFormat("yyyy-mm-dd").parse(dueDate);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getProvider() {
        return provider;
    }

    public String table(){
      return "bill";
    }

    public String insertValue(){
        return type+","+amount+","+dueDate+","+state+","+provider;
    }

    public void print() {
        System.out.println(getId()+","+type+","+amount+","+dueDate+","+state+","+provider);
    }

    public void scan(String[] values) {
        try {
            this.setId(Long.valueOf(values[1]));
            this.type = values[2];
            this.amount = Long.valueOf(values[3]);
            this.dueDate = values[4];
            this.state = values[5];
            this.provider = values[6];
        } catch (ArrayIndexOutOfBoundsException _e){}
    }
}
