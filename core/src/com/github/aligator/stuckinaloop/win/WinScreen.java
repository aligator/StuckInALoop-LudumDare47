package com.github.aligator.stuckinaloop.win;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.GameScreen;
import com.github.aligator.stuckinaloop.IScreenDispatcher;

public class WinScreen extends ScreenAdapter {

    OrthographicCamera cam;
    SpriteBatch batch;
    private IScreenDispatcher dispatcher;
    private GameScreen gameScreen;

    private Stage stage;
    private Table table;

    private Label resultLabel1;
    private Label resultLabel2;

    public WinScreen(SpriteBatch batch, IScreenDispatcher dispatcher, GameScreen gameScreen) {
        this.batch = batch;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0f);
        this.dispatcher = dispatcher;
        this.gameScreen = gameScreen;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //table.setDebug(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Assets.font48;

        resultLabel1 = new Label("", labelStyle);
        resultLabel2 = new Label("", labelStyle);
        table.add(resultLabel1);
        table.row();
        table.add(resultLabel2);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        resultLabel1.setText("Finally you WON");

        resultLabel2.setText("... after " + gameScreen.startingStats.restartCounter + " attempts!");

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
