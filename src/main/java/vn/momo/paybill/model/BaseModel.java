package vn.momo.paybill.model;

abstract class BaseModel implements ModelInterface {
    private long id;

    public abstract String table();
    public abstract String insertValue();

    public boolean isPersisted() {
        return id > 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
