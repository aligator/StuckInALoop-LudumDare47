package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.BodyEditorLoader;
import com.github.aligator.stuckinaloop.PlayerStartingStats;
import com.github.aligator.stuckinaloop.components.*;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class Player {
    public final static float MOVE_SPEED = 20f;

    public static Entity create(World world, PlayerStartingStats startingStats) {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();
        texture.region = Assets.player;

        VelocityComponent velocity = new VelocityComponent();

        velocity.minBounds = new Vector2();
        velocity.maxBounds = new Vector2(RenderingSystem.getScreenSizeInMeters().x, RenderingSystem.getScreenSizeInMeters().y);

        BodyComponent bodyComponent = new BodyComponent();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(texture.widthInMeters() / 2, texture.heightInMeters() / 2));

        bodyComponent.body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;

        new BodyEditorLoader(Gdx.files.internal("box2d.json")).attachFixture(bodyComponent.body, "spaceShip", fixtureDef, 5.0625f, 4.8125f, false);

        bodyComponent.body.setUserData(e);
        bodyComponent.body.createFixture(fixtureDef);

        e.add(new PlayerComponent());
        e.add(new CollisionComponent());
        e.add(new ShootingComponent(false, startingStats.firePauseTime));
        e.add(new SpaceShipComponent(startingStats.life, startingStats.damage));
        e.add(bodyComponent);
        e.add(velocity);
        e.add(texture);

        return e;
    }
}
