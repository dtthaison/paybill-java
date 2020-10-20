package vn.momo.paybill.form;

import vn.momo.paybill.model.Bill;
import vn.momo.paybill.repository.BillRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class BillPayForm extends BaseForm {
    private String[] billIds;
    private Bill[] bills;
    private int billLen;
    private BillRepositoryInterface repository;

    public BillPayForm(String[] billIds, BillRepositoryInterface repository) {
        this.billIds = billIds;
        this.billLen = billIds.length;
        this.repository = repository;
        this.init();
    }

    public Bill[] getBills() {
        return bills;
    }

    public boolean Valid() {
        long[] convertedIds;
        try {
            convertedIds = convertBillIdsToLong();
        } catch (Exception _e) {
            convertedIds = new long[0];
        }
        if(convertedIds.length < billLen) {
            addError("Invalid bill ids");
        }

        try {
            List<Bill> billList = repository.FindWithIds(convertedIds);
            int billListSize = billList.size();
            this.bills = new Bill[billListSize];
            for (int i = 0; i < billListSize; i++) {
                this.bills[i] = billList.get(i);
            }
        } catch (Exception _e) {
            this.bills = new Bill[0];
        }
        if(bills.length < billLen) {
            addError("Invalid bill ids");
        }

        return isValid();
    }

    private long[] convertBillIdsToLong() throws Exception {
        long[] result = new long[billLen];
        for (int i = 0; i < billLen; i++) {
            result[i] = Long.valueOf(billIds[i].trim());
        }

        return result;
    }
}
