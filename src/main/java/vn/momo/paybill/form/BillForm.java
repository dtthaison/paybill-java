package vn.momo.paybill.form;

import vn.momo.paybill.model.Bill;

public class BillForm extends BaseForm {
    private String type;
    private String amount;
    private String provider;
    private String dueDate;

    public BillForm(String type, String amount, String provider, String dueDate) {
        this.type = type;
        this.amount = amount;
        this.provider = provider;
        this.dueDate = dueDate;
        this.init();
    }

    public Bill BuilModel() {
        return new Bill(type, Long.valueOf(amount), dueDate, "UNPAID", provider);
    }

    public boolean Valid() {
        if(!type.equals("INTERNET") && !type.equals("ELECTRIC")) {
            addError("type is invalid, only allow INTERNET|ELECTRIC");
        }

        try {
            if(Long.valueOf(amount) <= 0) {
                addError("amount must be greater than 0");
            }
        } catch (NumberFormatException _e) {
            addError("amount is not a number");
        }

        return isValid();
    }
}
