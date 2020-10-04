package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.*;

public class PowerUp {
    public static Entity create(World world, Vector2 velocity, Vector2 position, PowerUpComponent.Type type) {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();
        texture.scale.set(0.5f, 0.5f);

        switch (type) {
            case Life:
                texture.region = Assets.lifePowerUp;
                break;
            case Damage:
                texture.region = Assets.damagePowerUp;
                break;
            case FireRate:
                texture.region = Assets.fireRatePowerUp;
                break;
            default:
        }

        BodyComponent bodyComponent = new BodyComponent();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(texture.widthInMeters() / 2, texture.heightInMeters() / 2));

        bodyComponent.body = world.createBody(bodyDef);

        bodyComponent.body.applyLinearImpulse(velocity, bodyComponent.body.getLocalCenter(), true);
        bodyComponent.body.setTransform(new Vector2(position.x, position.y), 0);

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(texture.widthInMeters() / 2, texture.heightInMeters() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        fixtureDef.isSensor = true;
        bodyComponent.body.setUserData(e);
        bodyComponent.body.createFixture(fixtureDef);
        poly.dispose();

        e.add(new PowerUpComponent(type));
        e.add(bodyComponent);
        e.add(texture);
        e.add(new CollisionComponent());
        e.add(new DiscardingComponent());

        return e;
    }
}
