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

    public PlayerStartingStats startingStats;

    public GameScreen(SpriteBatch batch, IScreenDispatcher dispatcher) {
        super();
        this.batch = batch;
        this.dispatcher = dispatcher;
        this.startingStats = new PlayerStartingStats();
    }

    private void init() {
        Gdx.app.log("GameScreen", "Initializing");
        Gdx.app.log("GameScreen", startingStats.toString());
        isInitialized = false;

        Box2D.init();

        if (world != null) {
            world.dispose();
        }
        world = new World(new Vector2(0f, 0f), true);

        world.setContactListener(new B2dContactHandler());

        engine = new PooledEngine();

        engine.addEntityListener(new DisposeComponentHandler(world));

        RenderingSystem renderingSystem = new RenderingSystem(batch);

        InputSystem inputSystem = new InputSystem();

        inputHandler = new RawInputHandler(inputSystem);
        Gdx.input.setInputProcessor(inputHandler);

        engine.addEntity(Player.create(world, startingStats));

        engine.addSystem(renderingSystem);
        engine.addSystem(inputSystem);

        engine.addSystem(new ShootingSystem(world));

        engine.addSystem(new MovementSystem());
        engine.addSystem(new CollisionSystem(this.startingStats));
        engine.addSystem(new EnemySpawningSystem(world));
        engine.addSystem(new KillSystem(this));
        engine.addSystem(new DiscardingSystem());

        engine.addSystem(new PhysicsDebugSystem(world, renderingSystem.getCamera()));

        engine.addSystem(new PhysicsSystem(world));

        isInitialized = true;
    }

    private void update(float delta) {
        engine.update(delta);
    }

    public void restart() {
        isInitialized = false;
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
