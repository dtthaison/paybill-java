package vn.momo.paybill.service;

import vn.momo.paybill.model.Account;
import vn.momo.paybill.repository.AccountRepositoryInterface;

public class AddAmountService implements AddAmountServiceInterface {
    private long amount;
    private AccountRepositoryInterface repository;

    public AddAmountService(long amount, AccountRepositoryInterface repository) {
        this.amount = amount;
        this.repository = repository;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void Exec() throws Exception {
        Account.getInstance().addAmount(amount);
        repository.save(Account.getInstance());
    }
}
