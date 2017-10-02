package br.ind.savoy.gerq.bean;

/**
 * Created by waltenes on 18/09/17.
 */
public class SortBean {

    private String field;

    private String direction = "asc";

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
