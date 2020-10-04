package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.Assets;
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


        // burst mode enabled
        if (shooting.firePauseTime <= shooting.burstFirePauseTime) {

            shooting.currentBurstCoolDown -= deltaTime;

            if (shooting.currentBurstCoolDown <= 0) {
                shooting.currentBurstCoolDown = shooting.burstCoolDown;
                shooting.burstShootCount = 0;
            }

            if (shooting.burstShootCount > shooting.maxShotsInBurstTime) {
                if (shooting.lastShotTime <= shooting.burstFirePauseTime) {
                    return;
                }
            }
        }

        if (shooting.isShooting && (shooting.lastShotTime == -1 || shooting.lastShotTime > shooting.firePauseTime)) {
            shooting.burstShootCount++;

            shooting.lastShotTime = 0;

            for (ShootingComponent.Canon cannon : shooting.cannons) {
                Vector2 position = body.body.getPosition();

                position.add(cannon.positionOffset);

                Entity bullet = Bullet.create(world, 50, position, Mapper.player.has(entity), Mapper.spaceShip.get(entity).damage);
                getEngine().addEntity(bullet);
            }

            Assets.shotSound.play();
        }
    }
}
