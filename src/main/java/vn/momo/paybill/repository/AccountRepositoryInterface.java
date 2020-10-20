package vn.momo.paybill.repository;

import vn.momo.paybill.model.ModelInterface;

public interface AccountRepositoryInterface {
    void save(ModelInterface record) throws Exception;
}
