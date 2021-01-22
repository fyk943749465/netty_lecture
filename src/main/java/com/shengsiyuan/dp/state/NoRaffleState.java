package com.shengsiyuan.dp.state;

public class NoRaffleState implements State {

    private Activity activity;

    public NoRaffleState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void reduceMoney() {
        // 当前状态可扣积分
        System.out.println("[不能抽奖状态]正在扣除积分");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        // 当期状态不能抽奖
        System.out.println("[不能抽奖状态] 扣除积分后才能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("[不能抽奖状态]　抽奖后才能进一步看结果，以决定是否发放奖品");
    }
}
