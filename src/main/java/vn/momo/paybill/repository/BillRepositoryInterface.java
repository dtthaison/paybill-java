package vn.momo.paybill.repository;

import vn.momo.paybill.model.Bill;

import java.util.List;

public interface BillRepositoryInterface {
    List<Bill> FindWithIds(long[] ids) throws Exception;
}
