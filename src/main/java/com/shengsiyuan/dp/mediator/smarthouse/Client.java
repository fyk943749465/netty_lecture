package com.shengsiyuan.dp.mediator.smarthouse;

public class Client {

    public static void main(String[] args) {

        Mediator mediator = new ConcreteMediator();


        Alarm alarm = new Alarm(mediator, "alarm");

        CoffeeMachine coffeeMachine = new CoffeeMachine(mediator,
                "coffeeMachine");


        Curtains curtains = new Curtains(mediator, "curtains");
        TV tV = new TV(mediator, "TV");


        alarm.sendAlarm(0);
        coffeeMachine.FinishCoffee();
        alarm.sendAlarm(1);
    }
}
