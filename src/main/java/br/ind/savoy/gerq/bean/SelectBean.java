package br.ind.savoy.gerq.bean;

/**
 * Created by waltenes on 18/09/17.
 */
public class SelectBean {
    private String name;

    private int value;


    public SelectBean(int value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
