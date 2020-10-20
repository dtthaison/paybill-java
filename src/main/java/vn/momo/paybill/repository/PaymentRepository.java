package vn.momo.paybill.repository;

import vn.momo.paybill.model.*;

public class PaymentRepository extends BaseRepository implements PaymentRepositoryInterface {
    public void insert(Payment record) throws Exception {
        save(record);

        for (PaymentDetail detail : record.getDetails()) {
            detail.setPaymentId(record.getId());
            save(detail);
        }

        for (PaymentDetail detail : record.getDetails()) {
            Bill bill = detail.getBill();
            if( bill != null) {
                save(bill);
            }
        }

        Account account = record.getAccount();
        if( account != null) {
            save(account);
        }
    }
}
