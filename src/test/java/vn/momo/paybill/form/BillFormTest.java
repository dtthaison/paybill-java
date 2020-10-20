package vn.momo.paybill.form;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BillFormTest {
    public class Case {
        public String it;
        public boolean expected;
        public String type;
        public String amount;
        public String provider;
        public String dueDate;

        public Case(String it, String type, String amount, String provider, String dueDate, boolean expected) {
            this.it = it;
            this.type = type;
            this.amount = amount;
            this.provider = provider;
            this.dueDate = dueDate;
            this.expected = expected;
        }
    }

    @Test
    public void testValid() throws Exception {
        List<BillFormTest.Case> cases = new ArrayList();
        cases.add(new BillFormTest.Case("when valid returns true", "INTERNET", "10000", "", "", true));
        cases.add(new BillFormTest.Case("when type is ELECTRIC returns true", "ELECTRIC", "10000", "", "", true));
        cases.add(new BillFormTest.Case("when type is INVALID returns false", "INVALID", "10000", "", "", false));
        cases.add(new BillFormTest.Case("when amount is not number returns false", "INTERNET", "ABC", "", "", false));
        cases.add(new BillFormTest.Case("when amount is 0 returns false", "INTERNET", "0", "", "", false));
        cases.add(new BillFormTest.Case("when amount is -10000 returns false", "INTERNET", "-10000", "", "", false));


        for (BillFormTest.Case c : cases) {
            Assert.assertEquals(c.it, c.expected, new BillForm(c.type, c.amount, c.provider, c.dueDate).Valid());
        }
    }
}
