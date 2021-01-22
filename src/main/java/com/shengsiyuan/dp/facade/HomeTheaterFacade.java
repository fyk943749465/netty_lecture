package com.shengsiyuan.dp.facade;

public class HomeTheaterFacade {

    // 定义各个子系统的对象
    private TheaterLight theaterLight;
    private Popcorn popcorn;
    private Projector projector;
    private Screen screen;
    private Stereo stereo;
    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade() {
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.stereo = Stereo.getInstance();
        this.dvdPlayer = DVDPlayer.getInstance();
    }

    public void ready() {
        theaterLight.on();
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }

    public void play() {
        dvdPlayer.play();
    }

    public void pause() {
        dvdPlayer.pause();
    }

    public void end() {
        theaterLight.bright();
        dvdPlayer.off();
        projector.off();
        screen.up();
        stereo.off();
        theaterLight.off();

    }

}
