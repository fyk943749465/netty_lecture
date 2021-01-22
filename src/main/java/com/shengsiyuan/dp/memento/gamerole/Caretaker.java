package com.shengsiyuan.dp.memento.gamerole;


// 守护对象，保存游戏的状态
public class Caretaker {

    private  Memento memento;

//    // 保存多次状态
//    private ArrayList<Memento> mementos;
//
//    // 对多个游戏角色保存状态
//    private HashMap<String, ArrayList<Memento>> rolesMementos;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
