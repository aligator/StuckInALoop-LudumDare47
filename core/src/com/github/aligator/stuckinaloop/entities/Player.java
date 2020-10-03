package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.PlayerComponent;
import com.github.aligator.stuckinaloop.components.TextureComponent;
import com.github.aligator.stuckinaloop.components.VelocityComponent;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class Player {
    public final static float MOVE_SPEED = 20f;

    public static Entity create(World world) {
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

        e.add(new PlayerComponent());
        e.add(bodyComponent);
        e.add(velocity);
        e.add(texture);

        return e;
    }
}
