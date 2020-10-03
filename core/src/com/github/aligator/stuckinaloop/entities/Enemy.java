package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.*;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class Enemy {
    public final static float MOVE_SPEED = 20f;

    public static Entity create(World world, float force) {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();
        texture.region = Assets.enemy;

        VelocityComponent velocity = new VelocityComponent();

        BodyComponent bodyComponent = new BodyComponent();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(RenderingSystem.getScreenSizeInMeters().x + texture.widthInMeters() / 2, texture.heightInMeters() / 2));

        bodyComponent.body = world.createBody(bodyDef);

        bodyComponent.body.applyLinearImpulse(new Vector2(force, 0), bodyComponent.body.getLocalCenter(), true);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(texture.widthInMeters() / 2, texture.heightInMeters() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.isSensor = true;
        bodyComponent.body.setUserData(e);
        bodyComponent.body.createFixture(fixtureDef);
        poly.dispose();

        e.add(new EnemyComponent());
        e.add(new DiscardingComponent());
        e.add(new ShootingComponent(true, 2f));
        e.add(bodyComponent);
        e.add(velocity);
        e.add(texture);

        return e;
    }
}
