package com.shengsiyuan.dp.state;

public interface State {

    /**
     * 扣除积分，准备抽奖
     */
    void reduceMoney();


    /**
     * 能否抽奖
     * @return
     */
    boolean raffle();

    /**
     * 抽完奖且中奖，分发奖品
     */
    void dispensePrize();
}
