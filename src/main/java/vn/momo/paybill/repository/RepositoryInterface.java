package vn.momo.paybill.repository;

import vn.momo.paybill.model.ModelInterface;

public interface RepositoryInterface {
    void save(ModelInterface record) throws Exception;
}
