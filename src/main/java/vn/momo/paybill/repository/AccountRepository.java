package vn.momo.paybill.repository;

public class AccountRepository extends BaseRepository implements AccountRepositoryInterface {
    public long FetchAmount() throws Exception {
        long result = 0;
        for (String[] values : scan("account")) {
            result += Long.valueOf(values[2]);
        }
        return result;
    }
}
