package com.shengsiyuan.dp.composite;

import java.util.ArrayList;
import java.util.List;

// Univercity 就算Composite，可以管理College
public class University extends OrganizationComponent {

    // list中存放的是college
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public University(String name, String des) {
        super(name, des);
    }

    // 重写add
    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponentList.add(organizationComponent);
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
