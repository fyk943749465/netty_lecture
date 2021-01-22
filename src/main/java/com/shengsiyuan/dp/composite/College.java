package com.shengsiyuan.dp.composite;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {

    // list中存放的department
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public College(String name, String des) {
        super(name, des);
    }

    // 重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        this.organizationComponentList.add(organizationComponent);
    }

    // 重写remove
    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponentList.remove(organizationComponent);
    }

    // 重写get方法
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDes() {
        return super.getDes();
    }


    // print方法，就算输出University包含的学院
    @Override
    protected void print() {
        System.out.println("========================" + this.getName() + "======================");
        // 遍历organizationComponents
        for (OrganizationComponent organizationComponent : organizationComponentList) {
            organizationComponent.print();
        }
    }
}
