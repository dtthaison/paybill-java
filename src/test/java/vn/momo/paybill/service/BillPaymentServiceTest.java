package vn.momo.paybill.service;

import org.junit.Assert;
import org.junit.Test;
import vn.momo.paybill.model.Account;
import vn.momo.paybill.model.Bill;
import vn.momo.paybill.model.Payment;
import vn.momo.paybill.repository.PaymentRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class BillPaymentServiceTest {
    public class Expected {
        public long id;
        public long paidAmount;
        public long accountAmount;
        public String billState;
        public int detailLength;

        public Expected(long id, long paidAmount, long accountAmount, String billState, int detailLength) {
            this.id = id;
            this.paidAmount = paidAmount;
            this.accountAmount = accountAmount;
            this.billState = billState;
            this.detailLength = detailLength;
        }
    }
    public class Case {
        public String it;
        public Expected expected;
        public Bill[] bills;
        public PaymentRepositoryInterface repository;
        public long accountAmount;

        public Case(String it, Expected expected, Bill[] bills, PaymentRepositoryInterface repository, long accountAmount) {
            this.it = it;
            this.expected = expected;
            this.bills = bills;
            this.repository = repository;
            this.accountAmount = accountAmount;
        }
    }

    public class PaymentRepositorySaveSuccess implements PaymentRepositoryInterface {
        public void insert(Payment record) throws Exception{
            record.setId(9999);
        }
    }


    @Test
    public void testExecSuccess() throws Exception {
        List<Case> cases = new ArrayList();
        cases.add(new Case(
                "when creates new payment",
                new Expected(9999, 24000, 26000, "PAID", 2),
                new Bill[]{
                        new Bill("INTERNET", 10000, "", "", ""),
                        new Bill("ELECTRIC", 14000, "", "", "")
                },
                new PaymentRepositorySaveSuccess(),
                50000)
        );

        for (Case c : cases) {
            Account.getInstance().setAmount(c.accountAmount);
            BillPaymentService service = new BillPaymentService(c.bills, c.repository);
            Payment actual = service.Exec();

            Assert.assertEquals("expected.id: " + c.it, c.expected.id, actual.getId());
            Assert.assertEquals("expected.paidAmount: " + c.it, c.expected.paidAmount, actual.getPaidAmount());
            Assert.assertEquals("expected.accountAmount: " + c.it, c.expected.accountAmount, Account.getInstance().getAmount());
            Assert.assertEquals("expected.billState: " + c.it, c.expected.billState, c.bills[0].getState());
            Assert.assertEquals("expected.detailLength: " + c.it, c.expected.detailLength, actual.getDetails().length);
        }
    }

    @Test
    public void testExecNotEnoughBalance() throws Exception {
        List<Case> cases = new ArrayList();
        cases.add(new Case(
                "when creates new payment",
                new Expected(9999, 24000, 26000, "PAID", 2),
                new Bill[]{
                        new Bill("INTERNET", 10000, "", "", ""),
                        new Bill("ELECTRIC", 14000, "", "", "")
                },
                new PaymentRepositorySaveSuccess(),
                20000)
        );

        for (Case c : cases) {
            Account.getInstance().setAmount(c.accountAmount);
            BillPaymentService service = new BillPaymentService(c.bills, c.repository);
            Assert.assertThrows(BillPaymentService.BillPaymentException.class, ()-> service.Exec());
        }

    }
}
