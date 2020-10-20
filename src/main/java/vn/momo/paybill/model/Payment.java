package vn.momo.paybill.model;

public class Payment extends BaseModel {
    private String state; // PENDING, RUNNING, COMPLETED
    private long paidAmount;
    private PaymentDetail []details;
    private Account account;

    public Payment() {
    }

    public Payment(String state, long paidAmount) {
        this.state = state;
        this.paidAmount = paidAmount;
    }

    public void setDetails(PaymentDetail[] details) {
        this.details = details;
    }

    public PaymentDetail[] getDetails() {
        return details;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String table(){
        return "payment";
    }

    public String insertValue(){
        return state+","+paidAmount;
    }
}
