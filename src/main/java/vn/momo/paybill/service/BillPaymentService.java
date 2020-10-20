package vn.momo.paybill.service;

import vn.momo.paybill.model.Account;
import vn.momo.paybill.model.Bill;
import vn.momo.paybill.model.Payment;
import vn.momo.paybill.model.PaymentDetail;
import vn.momo.paybill.repository.PaymentRepositoryInterface;

public class BillPaymentService {
    class BillPaymentException extends Exception {
        public BillPaymentException(String message) {
            super(message);
        }
    }

    private Bill []bills;
    private PaymentRepositoryInterface repository;

    public BillPaymentService(Bill[] bills, PaymentRepositoryInterface repository) {
        this.bills = bills;
        this.repository = repository;
    }

    public Payment Exec() throws Exception {
        long totalPaidAmount = 0;
        int billLen = bills.length;
        PaymentDetail[] details = new PaymentDetail[billLen];

        for (int i = 0; i < billLen; i++) {
            bills[i].setState("PAID");
            totalPaidAmount += bills[i].getAmount();
            details[i] = new PaymentDetail(bills[i]);
        }

        if (Account.getInstance().getAmount() < totalPaidAmount) {
            throw new BillPaymentException("NotEnoughBalance");
        }

        Payment payment = new Payment("COMPLETED", totalPaidAmount);
        payment.setDetails(details);

        Account account = Account.getInstance();
        account.addAmount(-totalPaidAmount);
        payment.setAccount(account);

        repository.insert(payment);

        return payment;
    }
}
