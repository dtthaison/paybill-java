package vn.momo.paybill.repository;

import vn.momo.paybill.model.Payment;

public interface PaymentRepositoryInterface {
    void insert(Payment record) throws Exception;
}
