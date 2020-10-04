package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StuckInALoop extends Game {
    public AssetManager assetManager;
    private SpriteBatch batch;
    private ScreenDispatcher screenDispatcher;

    @Override
    public void create() {
        assetManager = Assets.load();
        batch = new SpriteBatch();
        screenDispatcher = new ScreenDispatcher();
        Screen splashScreen = new SplashScreen(batch, screenDispatcher);
        GameScreen gameScreen = new GameScreen(batch, screenDispatcher);
        Screen winScreen = new WinScreen(batch, screenDispatcher, gameScreen);
        screenDispatcher.AddScreen(splashScreen);
        // screenDispatcher.AddScreen(gameScreen);
        gameScreen.startingStats.restartCounter = 10;
        screenDispatcher.AddScreen(winScreen);
        setScreen(splashScreen);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Screen nextScreen = screenDispatcher.getNextScreen();
        if (nextScreen != getScreen()) {
            setScreen(nextScreen);
        }

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
        screenDispatcher.dispose();
    }
}
