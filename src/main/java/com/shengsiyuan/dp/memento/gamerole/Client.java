package com.shengsiyuan.dp.memento.gamerole;

public class Client {

    public static void main(String[] args) {
        GameRole gameRole = new GameRole();
        gameRole.setVit(100);
        gameRole.setDef(100);

        System.out.println("和boss大战前的状态");
        gameRole.display();

        // 保存大战之前的状态
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(gameRole.createMemento());

        System.out.println("和boss大战....");
        gameRole.setVit(30);
        gameRole.setDef(30);

        // 显示大战后的攻击力和防御力
        gameRole.display();

        System.out.println("大战后,使用备忘录对象恢复到战前");

        // 恢复到前一个状态
        gameRole.recoverGameRoleFromMemento(caretaker.getMemento());
        System.out.println("恢复后的状态");
        gameRole.display();
    }
}
