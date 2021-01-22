package com.shengsiyuan.dp.memento;

import java.util.ArrayList;
import java.util.List;

// 守护者对象，负责保存多个备忘录对象，使用集合管理，提高效率
public class Caretaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento memento) {
        mementoList.add(memento);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
