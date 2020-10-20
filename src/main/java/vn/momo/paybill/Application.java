package vn.momo.paybill;

import vn.momo.paybill.form.BillForm;
import vn.momo.paybill.form.BillPayForm;
import vn.momo.paybill.model.*;
import vn.momo.paybill.repository.*;
import vn.momo.paybill.service.AddAmountService;
import vn.momo.paybill.service.BillPaymentService;

import java.util.List;

public class Application {
    public static void main(String[] args) throws InterruptedException {

        try {
            BillRepository billRepository = new BillRepository();
            PaymentRepository paymentRepository = new PaymentRepository();

            AccountRepository accountRepository = new AccountRepository();
            Account.getInstance().setAmount(accountRepository.FetchAmount());
            System.out.println("Your amount is " + Account.getInstance().getAmount());

            switch (args[0]) {
                case "CLEAR":
                    new BaseRepository().clear();
                    break;
                case "TOPUP":
                    long amount = Long.valueOf(args[1]);
                    if(amount == 0) {
                        System.out.println("Error: You can not topup 0");
                    } else {
                        new AddAmountService(amount, accountRepository).Exec();
                        System.out.println("Your amount is " + Account.getInstance().getAmount());
                    }
                    break;
                case "BILL_ADD":
                    BillForm form = new BillForm(args[1], args[2], "", "");
                    if(form.Valid()) {
                        billRepository.save(form.BuilModel());
                    } else {
                        for(String err : form.getErrors()) {
                            System.out.println("Error: "+err);
                        }
                    }
                    break;
                case "BILL_ALL":
                    List<Bill> collection = billRepository.All();
                    if(collection.size() == 0) {
                        System.out.println("Bills are empty");
                    } else {
                        for(Bill record : collection) {
                            record.print();
                        }
                    }
                    break;
                case "BILL_PAY":
                    BillPayForm billPayForm = new BillPayForm(args[1].split(","), billRepository);
                    if(billPayForm.Valid()) {
                        BillPaymentService billPaymentService = new BillPaymentService(billPayForm.getBills(), paymentRepository);
                        Payment payment = billPaymentService.Exec();
                        System.out.println("Payment#"+payment.getId()+" is created, paid amount is "+payment.getPaidAmount());
                    } else {
                        for(String err : billPayForm.getErrors()) {
                            System.out.println("Error: "+err);
                        }
                    }
                    break;
                default:
                    System.out.println("Does not support command!!! Please try:");
                    System.out.println("1. CLEAR");
                    System.out.println("2. TOPUP <amount>");
                    System.out.println("3. BILL_ALL");
//                    System.out.println("BILL_ADD <type> <amount> <provider> <dueDate:yyyy-mm-dd>");
                    System.out.println("4. BILL_ADD <type> <amount>");
                    System.out.println("5. BILL_PAY <billIds:billId1,billId2,...>");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.toString());
//            e.printStackTrace();
        }
    }
}
