package com.shengsiyuan.dp.composite;

public abstract class OrganizationComponent {

    private String name; //名字
    private String des;  //说明

    // 叶子节点和非叶子节点的行为不同，非叶子需要重写该方便，但是对于叶子节点则不需要
    protected void add(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    protected void remove(OrganizationComponent organizationComponent) {
        throw new UnsupportedOperationException();
    }

    public OrganizationComponent(String name, String des) {
        super();
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDes(String des) {
        this.des = des;
    }

    // 方法print, 做成抽象的，不管叶子节点还是非叶子节点
    protected abstract void print();
}
