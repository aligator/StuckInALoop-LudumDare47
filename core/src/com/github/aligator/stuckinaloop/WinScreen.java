package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WinScreen extends ScreenAdapter {

    OrthographicCamera cam;
    SpriteBatch batch;
    private IScreenDispatcher dispatcher;
    private final GameScreen gs;

    private Stage stage;
    private Table table;

    private Label resultLabel1;
    private Label resultLabel2;
    private TextButton continueButton;

    public WinScreen(SpriteBatch batch, final IScreenDispatcher dispatcher, GameScreen gameScreen) {
        this.batch = batch;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0f);
        this.dispatcher = dispatcher;
        this.gs = gameScreen;

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //table.setDebug(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Assets.font48;

        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        skin.add("default", Assets.font48);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        resultLabel1 = new Label("", labelStyle);
        resultLabel2 = new Label("", labelStyle);
        continueButton = new TextButton("Play endless (random)", skin);

        table.add(resultLabel1);
        table.row();
        table.add(resultLabel2);
        table.row();
        table.add(continueButton);

        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gs.restart();
                dispatcher.endCurrentScreen();
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);

        resultLabel1.setText("Finally you WON");

        resultLabel2.setText("... after " + gs.startingStats.restartCounter + " attempts!");

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
