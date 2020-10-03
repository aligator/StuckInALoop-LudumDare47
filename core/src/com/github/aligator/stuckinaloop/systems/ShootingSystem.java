package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.components.*;
import com.github.aligator.stuckinaloop.entities.Bullet;

public class ShootingSystem extends IteratingSystem {

    private final World world;

    public ShootingSystem(World world) {
        super(Family.all(ShootingComponent.class, BodyComponent.class, SpaceShipComponent.class).one(PlayerComponent.class, EnemyComponent.class).get());
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ShootingComponent shooting = Mapper.shooting.get(entity);
        BodyComponent body = Mapper.body.get(entity);

        // avoid waiting time for the first bullet
        if (shooting.lastShotTime != -1) {
            shooting.lastShotTime += deltaTime;
        }

        if (shooting.isShooting && (shooting.lastShotTime == -1 || shooting.lastShotTime > shooting.firePauseTime)) {
            shooting.lastShotTime = 0;
            System.out.println(body.body.getPosition());
            Entity bullet = Bullet.create(world, 50, body.body.getPosition(), Mapper.player.has(entity), Mapper.spaceShip.get(entity).damage);

            getEngine().addEntity(bullet);
        }

    }
}
