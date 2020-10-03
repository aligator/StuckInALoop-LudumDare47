package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.PlayerComponent;
import com.github.aligator.stuckinaloop.components.TextureComponent;
import com.github.aligator.stuckinaloop.components.TransformComponent;
import com.github.aligator.stuckinaloop.components.VelocityComponent;

public class Player {
    public final static float MOVE_SPEED = 150.0f;

    public static Entity create() {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();
        texture.region = Assets.player;
        TransformComponent transform = new TransformComponent();

        transform.position.set(new Vector3(texture.region.getRegionWidth() / 2, texture.region.getRegionHeight() / 2, 0));

        VelocityComponent velocity = new VelocityComponent();

        velocity.minBounds = new Vector2();
        velocity.maxBounds = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        e.add(new PlayerComponent());
        e.add(velocity);
        e.add(transform);
        e.add(texture);

        return e;
    }
}
