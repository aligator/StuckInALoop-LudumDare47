package com.github.aligator.stuckinaloop;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.entities.Player;
import com.github.aligator.stuckinaloop.handlers.B2dContactHandler;
import com.github.aligator.stuckinaloop.handlers.DisposeComponentHandler;
import com.github.aligator.stuckinaloop.handlers.RawInputHandler;
import com.github.aligator.stuckinaloop.systems.*;

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
        isInitialized = false;

        Box2D.init();
        world = new World(new Vector2(0f, 0f), true);

        world.setContactListener(new B2dContactHandler());

        engine = new PooledEngine();

        engine.addEntityListener(new DisposeComponentHandler(world));

        RenderingSystem renderingSystem = new RenderingSystem(batch);

        InputSystem inputSystem = new InputSystem();

        inputHandler = new RawInputHandler(inputSystem);
        Gdx.input.setInputProcessor(inputHandler);

        engine.addEntity(Player.create(world));

        engine.addSystem(renderingSystem);
        engine.addSystem(inputSystem);

        engine.addSystem(new ShootingSystem(world));

        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new EnemySpawningSystem(world));
        engine.addSystem(new SpaceShipSystem());
        engine.addSystem(new DiscardingSystem());

        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));

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

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }
}
