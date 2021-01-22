package com.shengsiyuan.dp.state;

public class DispenseOutState implements State {

    // 抽奖活动对象
    private Activity activity;

    public DispenseOutState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void reduceMoney() {
        // 当前状态不允许扣除积分
        System.out.println("【奖品发完状态】活动已结束，请下次再来参加");
    }

    @Override
    public boolean raffle() {
        // 当前状态不允许抽奖
        System.out.println("【奖品发完状态】活动已结束，请下次再来参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        // 当前状态不允许分发奖品
        System.out.println("【奖品发完状态】活动已结束，请下次再来参加");
    }

    public Activity getActivity() {
        return activity;
    }
}
