package com.shengsiyuan.dp.command;

public interface Command {

    // 执行某个动作或操作
    void execute();
    // 撤销某个动作或操作
    void undo();
}
