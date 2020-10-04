package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.BodyEditorLoader;
import com.github.aligator.stuckinaloop.components.*;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class Enemy {

    public static Entity createBoss(World world, int live) {
        float scale = 1f;

        TextureComponent texture = new TextureComponent();
        texture.scale.set(scale, scale);
        texture.region = Assets.enemyBoss;

        Entity enemy = create(world, texture, -3f, 0.5f, live, 3, scale * 2);

        enemy.add(new BossComponent(live));

        Array<ShootingComponent.Canon> canons = new Array<>();
        canons.add(new ShootingComponent.Canon(new Vector2(-texture.widthInMeters() / 2, 0)));
        canons.add(new ShootingComponent.Canon(new Vector2(-texture.widthInMeters() / 2 + 7, 4.5f)));
        canons.add(new ShootingComponent.Canon(new Vector2(-texture.widthInMeters() / 2 + 7, -4.5f)));
        enemy.add(new ShootingComponent(canons, true, 0.9f));

        return enemy;
    }

    public static Entity create(World world, float force, float yPercentage, float firePauseTime, int live, int damage) {
        float scale = 1f;

        TextureComponent texture = new TextureComponent();
        texture.scale.set(scale, scale);
        switch (live) {
            case 1:
                texture.region = Assets.enemyGreen;
                break;
            case 3:
                texture.region = Assets.enemyYellow;
                break;
            default:
                texture.region = Assets.enemyRed;
                break;
        }

        Entity enemy = create(world, texture, force, yPercentage, live, damage, scale);

        Array<ShootingComponent.Canon> canons = new Array<>();
        canons.add(new ShootingComponent.Canon(new Vector2(-texture.widthInMeters() / 2, 0)));
        enemy.add(new ShootingComponent(canons, true, firePauseTime));

        return enemy;
    }

    public static Entity create(World world, TextureComponent texture, float force, float yPercentage, int live, int damage, float collisionBoxScale) {
        Entity e = new Entity();

        VelocityComponent velocity = new VelocityComponent();

        BodyComponent bodyComponent = new BodyComponent();

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        float screenY = RenderingSystem.getScreenSizeInMeters().y - texture.heightInMeters();

        bodyDef.position.set(new Vector2(RenderingSystem.getScreenSizeInMeters().x + texture.widthInMeters() / 2, texture.heightInMeters() / 2 + screenY * yPercentage));

        bodyComponent.body = world.createBody(bodyDef);

        bodyComponent.body.applyLinearImpulse(new Vector2(force, 0), bodyComponent.body.getLocalCenter(), true);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;

        new BodyEditorLoader(Gdx.files.internal("box2d.json")).attachFixture(bodyComponent.body, "spaceShip", fixtureDef, 5.0625f * collisionBoxScale, 4.8125f * collisionBoxScale, true);

        bodyComponent.body.setUserData(e);
        bodyComponent.body.createFixture(fixtureDef);

        e.add(new EnemyComponent());
        e.add(new DiscardingComponent());
        e.add(new SpaceShipComponent(live, damage));
        e.add(bodyComponent);
        e.add(velocity);
        e.add(texture);

        return e;
    }
}
