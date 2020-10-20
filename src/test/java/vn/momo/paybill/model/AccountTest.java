package vn.momo.paybill.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountTest {
    public class Case {
        public String it;
        public int amount;
        public int expected;

        public Case(String it, int amount, int expected) {
            this.it = it;
            this.amount = amount;
            this.expected = expected;
        }
    }

    @Test
    public void testAddAmount() throws Exception {
        List<Case> cases = new ArrayList();
        cases.add(new Case("when add 10000 returns 100000", 10000, 10000));
        cases.add(new Case("when add 5000 returns 150000", 5000, 15000));
        cases.add(new Case("when add -3000 returns 120000", -3000, 12000));

        for (Case c : cases) {
            Account.getInstance().addAmount(c.amount);
            Assert.assertEquals(c.it, c.expected, Account.getInstance().getAmount());
        }
    }
}