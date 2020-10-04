package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WinScreen extends ScreenAdapter {

    OrthographicCamera cam;
    SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private IScreenDispatcher dispatcher;
    private GameScreen gameScreen;

    public WinScreen(SpriteBatch batch, IScreenDispatcher dispatcher, GameScreen gameScreen) {
        this.batch = batch;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0f);
        this.dispatcher = dispatcher;
        this.shapeRenderer = new ShapeRenderer();
        this.gameScreen = gameScreen;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        Assets.font24.draw(batch, "You needed " + gameScreen.startingStats.restartCounter + " retries", 10, cam.viewportHeight);

        batch.end();
    }
}
