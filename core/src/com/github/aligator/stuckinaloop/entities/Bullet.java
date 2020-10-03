package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.BulletComponent;
import com.github.aligator.stuckinaloop.components.TextureComponent;
import com.github.aligator.stuckinaloop.components.VelocityComponent;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class Bullet {
    public final static float MOVE_SPEED = 150.0f;

    public static Entity create(World world, float force, Vector2 position) {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();
        texture.region = Assets.bullet;

        VelocityComponent velocity = new VelocityComponent();

        velocity.minBounds = new Vector2();
        velocity.maxBounds = new Vector2(RenderingSystem.getScreenSizeInMeters().x, RenderingSystem.getScreenSizeInMeters().y);

        BodyComponent bodyComponent = new BodyComponent();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(texture.widthInMeters() / 2, texture.heightInMeters() / 2));

        bodyComponent.body = world.createBody(bodyDef);
        bodyComponent.body.applyLinearImpulse(new Vector2(force, 0), bodyComponent.body.getLocalCenter(), true);
        bodyComponent.body.setTransform(new Vector2(position.x, position.y), 0);


        e.add(new BulletComponent());
        e.add(bodyComponent);
        e.add(velocity);
        e.add(texture);

        return e;
    }
}
