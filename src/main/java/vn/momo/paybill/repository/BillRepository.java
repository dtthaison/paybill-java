package vn.momo.paybill.repository;

import vn.momo.paybill.model.Bill;

import java.util.ArrayList;
import java.util.List;


public class BillRepository extends BaseRepository implements BillRepositoryInterface {
    public List<Bill> All() throws Exception {
        List<Bill> result = new ArrayList<>();
        for (String[] values : scan("bill")) {
            Bill bill = new Bill();
            bill.scan(values);
            result.add(bill);
        }

        return result;
    }

    public List<Bill> FindWithIds(long[] ids) throws Exception {
        List<Bill> result = new ArrayList<>();
        for (String[] values : scan("bill")) {
            Bill bill = new Bill();
            bill.scan(values);
            for(long id : ids) {
                if(id == bill.getId()) {
                    result.add(bill);
                    break;
                }
            }
        }
        return result;
    }
}
