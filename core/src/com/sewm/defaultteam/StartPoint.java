package com.sewm.defaultteam;


import com.badlogic.gdx.Game;


public class StartPoint extends Game{

    public static StartPoint startPoint_;
    @Override
    public void create() {

        startPoint_ = this;
        setScreen(new GameScreen());
    }

}