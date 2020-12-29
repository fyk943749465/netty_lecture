package com.shengsiyuan.dp.principle.demeter.improve;

import java.util.ArrayList;
import java.util.List;

public class Demeter1 {

    public static void main(String[] args) {
        System.out.println("--使用迪米特法则改进--");
        SchoolManager schoolManager = new SchoolManager();
        schoolManager.printAllEmployee(new CollegeManager());
    }
}

// 学校总部的员工
class Employee {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

// 学院员工类
class CollegeEmployee {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

// 管理学院员工的管理类
class CollegeManager {

    public List<CollegeEmployee> getAllEmployee() {
        List<CollegeEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            CollegeEmployee emp = new CollegeEmployee();
            emp.setId("学院员工id = " + i);
            list.add(emp);
        }
        return list;
    }

    void printCollegeEmployee() {
        // 分析问题：
        // 1. 这里的CollegeEmployee不是SchoolManager的直接朋友
        // 2. CollegeEmployee　是以局部变量的方式出现在SchoolManager
        // 3. 违反了迪米特法则

        // 获取到学院员工
        List<CollegeEmployee> list1 = this.getAllEmployee();
        System.out.println("---------------------学院员工-----------------------");
        for (CollegeEmployee e: list1) {
            System.out.println(e.getId());
        }
    }
}

// 学校管理类
// 分析SchoolManager的直接朋友：　Employee，　CollegeManager
//     非直接朋友　CollegeEmployee　（这里违背了迪米特法则）
class SchoolManager {

    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            Employee emp = new Employee();
            emp.setId("学校总部员工id=" + i);
            list.add(emp);
        }
        return list;
    }

    void printAllEmployee(CollegeManager sub) {

        sub.printCollegeEmployee();
        // 获取到学校总部员工
        List<Employee> list2 = this.getAllEmployee();
        System.out.println("---------------------学校总部员工--------------------");
        for(Employee e: list2) {
            System.out.println(e.getId());
        }
    }
}