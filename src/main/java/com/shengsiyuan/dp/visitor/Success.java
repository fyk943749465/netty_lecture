package com.shengsiyuan.dp.visitor;

public class Success extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给该歌手的评价是很成功");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给该歌手的评价是很成功");
    }
}
