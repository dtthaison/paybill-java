package vn.momo.paybill.form;

import java.util.ArrayList;
import java.util.List;

public class BaseForm {
    List<String> errors;

    public BaseForm() {
        this.init();
    }

    public boolean isError() {
        return errors.size() > 0;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String msg){
        this.errors.add(msg);
    }

    protected void init() {
        this.errors = new ArrayList();
    }

    protected boolean isValid() {
        return this.errors.size() == 0;
    }
}
