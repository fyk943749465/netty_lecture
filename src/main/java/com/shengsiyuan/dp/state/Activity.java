package com.shengsiyuan.dp.state;

public class Activity {

    // 抽奖活动的当前状态
    private State state;

    // 奖品数量
    private int count = 0;

    private NoRaffleState noRaffleState = new NoRaffleState(this);
    private CanRaffleState canRaffleState = new CanRaffleState(this);
    private DispensePrizeState dispensePrizeState = new DispensePrizeState(this);
    private DispenseOutState dispenseOutState = new DispenseOutState(this);


    public Activity(int count) {
        this.state = getNoRafflesState();
        this.count = count;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count--;
    }

    public NoRaffleState getNoRafflesState() {
        return noRaffleState;
    }

    public CanRaffleState getCanRaffleState() {
        return canRaffleState;
    }

    public DispenseOutState getDispenseOutState() {
        return dispenseOutState;
    }

    public DispensePrizeState getDispensePrizeState() {
        return dispensePrizeState;
    }


    public void deductMoney() {
        state.reduceMoney();
    }

    public void raffle() {
        if (state.raffle())
            state.dispensePrize();
    }
}
