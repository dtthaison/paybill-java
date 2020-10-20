package vn.momo.paybill.model;

public class PaymentDetail extends BaseModel {
    private long paymentId;
    private long billId;
    private long amount;
    private Bill bill;

    public PaymentDetail(Bill bill) {
        this.bill = bill;
        this.billId = bill.getId();
        this.amount = bill.getAmount();
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Bill getBill() {
        return bill;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String table(){
        return "payment_detail";
    }

    public String insertValue(){
        return billId+","+paymentId+","+amount;
    }
}
