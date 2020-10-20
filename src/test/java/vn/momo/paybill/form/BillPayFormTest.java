package vn.momo.paybill.form;

import org.junit.Assert;
import org.junit.Test;
import vn.momo.paybill.model.Bill;
import vn.momo.paybill.repository.BillRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class BillPayFormTest {
    public class Case {
        public String it;
        public boolean expected;
        public String[] billIds;
        public BillRepositoryInterface repository;

        public Case(String it, boolean expected, String[] billIds, BillRepositoryInterface repository) {
            this.it = it;
            this.expected = expected;
            this.billIds = billIds;
            this.repository = repository;
        }
    }

    public class BillRepositoryFindWithIdsSuccess implements BillRepositoryInterface {
        public List<Bill> FindWithIds(long[] ids) throws Exception {
            List<Bill> result = new ArrayList<>();
            result.add(new Bill());
            result.add(new Bill());
            return result;
        }
    }

    public class BillRepositoryFindWithIdsNotExist implements BillRepositoryInterface {
        public List<Bill> FindWithIds(long[] ids) throws Exception {
            List<Bill> result = new ArrayList<>();
            result.add(new Bill());
            return result;
        }
    }

    @Test
    public void testValid() throws Exception {
        List<BillPayFormTest.Case> cases = new ArrayList();
        cases.add(new BillPayFormTest.Case("when valid returns true",true, new String[]{"1", "2"}, new BillRepositoryFindWithIdsSuccess()));
        cases.add(new BillPayFormTest.Case("when billIds are not invalid returns false",false, new String[]{"1", "abc"}, new BillRepositoryFindWithIdsSuccess()));
        cases.add(new BillPayFormTest.Case("when billIds are not invalid returns false",false, new String[]{"1", "2"}, new BillRepositoryFindWithIdsNotExist()));

        for (BillPayFormTest.Case c : cases) {
            Assert.assertEquals(c.it, c.expected, new BillPayForm(c.billIds, c.repository).Valid());
        }
    }
}
