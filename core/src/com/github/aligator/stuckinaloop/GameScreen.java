package com.github.aligator.stuckinaloop;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.entities.Player;
import com.github.aligator.stuckinaloop.handlers.RawInputHandler;
import com.github.aligator.stuckinaloop.systems.InputSystem;
import com.github.aligator.stuckinaloop.systems.MovementSystem;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class GameScreen extends ScreenAdapter {

    private RawInputHandler inputHandler;

    private boolean isInitialized = false;
    private World world;
    private Engine engine;

    private SpriteBatch batch;
    private IScreenDispatcher dispatcher;

    public GameScreen(SpriteBatch batch, IScreenDispatcher dispatcher) {
        super();
        this.batch = batch;
        this.dispatcher = dispatcher;
    }

    private void init() {
        Gdx.app.log("GameScreen", "Initializing");
        isInitialized = true;

        world = new World(new Vector2(0f, -9.8f), true);

        engine = new PooledEngine();

        RenderingSystem renderingSystem = new RenderingSystem(batch);

        InputSystem inputSystem = new InputSystem();

        inputHandler = new RawInputHandler(inputSystem);
        Gdx.input.setInputProcessor(inputHandler);

        Entity player = Player.create();
        engine.addEntity(player);

        engine.addSystem(renderingSystem);
        engine.addSystem(inputSystem);
        engine.addSystem(new MovementSystem());

/*        engine.addSystem(new AnimationSystem());
        engine.addSystem(renderingSystem);
        engine.addSystem(new PhysicsSystem(world));

        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));
        engine.addSystem(new UselessStateSwapSystem());

        Entity e = buildPuffin(world);
        engine.addEntity(e);
        engine.addEntity(buildFloorEntity(world));
*/
        isInitialized = true;
    }

    private void update(float delta) {
        engine.update(delta);
        /*
        elapsedTime += delta;
        if(elapsedTime/1000f > secondsToSplash){
            world.dispose();
            dispatcher.endCurrentScreen();
        }*/
    }

    @Override
    public void render(float delta) {
        if (isInitialized) {
            update(delta);
        } else {
            init();
        }
    }
}
