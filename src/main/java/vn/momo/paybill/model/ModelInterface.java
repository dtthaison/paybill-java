package vn.momo.paybill.model;

public interface ModelInterface {
    String table();
    String insertValue();
    boolean isPersisted();
    void setId(long id);
    long getId();
}
