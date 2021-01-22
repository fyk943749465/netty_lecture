package com.shengsiyuan.dp.visitor;

import java.util.LinkedList;
import java.util.List;

// 数据结构，管理了很多的人
public class ObjectStructure {

    private List<Person> persons = new LinkedList<>();

    public void attach(Person person) {
        persons.add(person);
    }

    public void detach(Person person) {
        persons.remove(person);
    }

    public void display(Action action) {
        for (Person p: persons) {
            p.accept(action);
        }
    }
}
