package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.ExplosionComponent;
import com.github.aligator.stuckinaloop.components.TextureComponent;

public class Explosion {
    public static Entity create(World world, Vector2 position) {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();

        texture.region = Assets.explosion;

        BodyComponent bodyComponent = new BodyComponent();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(texture.widthInMeters() / 2, texture.heightInMeters() / 2));

        bodyComponent.body = world.createBody(bodyDef);

        bodyComponent.body.setTransform(new Vector2(position.x, position.y), 0);

        e.add(new ExplosionComponent());
        e.add(bodyComponent);
        e.add(texture);

        return e;
    }
}
