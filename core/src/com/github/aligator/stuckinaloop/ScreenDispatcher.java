package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.Screen;

import java.util.ArrayList;
import java.util.List;

public class ScreenDispatcher implements IScreenDispatcher {

    public List<Screen> screens;
    private boolean isCurrenScreenEnded = false;
    private int currentIndex = 0;

    ScreenDispatcher() {
        screens = new ArrayList<>();
    }

    public void AddScreen(Screen screen) {
        screens.add(screen);
    }


    @Override
    public void endCurrentScreen() {
        isCurrenScreenEnded = true;
    }

    @Override
    public Screen getNextScreen() {
        if (isCurrenScreenEnded) {
            isCurrenScreenEnded = false;
            //Do logic to pick the next screen
            currentIndex++;
        }

        if (screens.size() > currentIndex) {
            return screens.get(currentIndex);
        } else {
            return screens.get(0);
        }
    }

    public void dispose() {
        for (Screen screen : screens) {
            screen.dispose();
        }
    }
}
