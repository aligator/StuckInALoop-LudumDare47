package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.Screen;

public interface IScreenDispatcher {
    void endCurrentScreen();

    Screen getNextScreen();
}
