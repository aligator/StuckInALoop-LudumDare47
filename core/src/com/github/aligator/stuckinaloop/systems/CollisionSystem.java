package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.PlayerStartingStats;
import com.github.aligator.stuckinaloop.components.*;

public class CollisionSystem extends IteratingSystem {

    private final PlayerStartingStats startingStats;

    public CollisionSystem(PlayerStartingStats startingStats) {
        super(Family.all(CollisionComponent.class).one(BulletComponent.class, PowerUpComponent.class).get());
        this.startingStats = startingStats;
    }

    protected void processBulletCollision(Entity entity, float deltaTime) {
        BulletComponent bullet = Mapper.bullet.get(entity);
        CollisionComponent collision = Mapper.collision.get(entity);

        if (collision.collidedEntity != null) {
            if (bullet.isFromPlayer && Mapper.enemy.has(collision.collidedEntity) ||
                    !bullet.isFromPlayer && Mapper.player.has(collision.collidedEntity)
            ) {
                getEngine().removeEntity(entity);

                if (Mapper.spaceShip.has(collision.collidedEntity)) {
                    SpaceShipComponent spaceShip = Mapper.spaceShip.get(collision.collidedEntity);
                    spaceShip.life -= bullet.damage;
                }
            }

            if (Mapper.bullet.has(collision.collidedEntity)) {
                BulletComponent bullet2 = Mapper.bullet.get(collision.collidedEntity);
                if (bullet.isFromPlayer != bullet2.isFromPlayer) {
                    getEngine().removeEntity(collision.collidedEntity);
                    getEngine().removeEntity(entity);
                }
            }

            collision.collidedEntity = null; // collision handled, reset component
        }
    }

    protected void processPowerUpCollision(Entity entity, float deltaTime) {
        PowerUpComponent powerUp = Mapper.powerUp.get(entity);
        CollisionComponent collision = Mapper.collision.get(entity);

        if (collision.collidedEntity != null) {
            if (Mapper.player.has(collision.collidedEntity) && Mapper.spaceShip.has(collision.collidedEntity)) {
                SpaceShipComponent playerSpaceShip = Mapper.spaceShip.get(collision.collidedEntity);

                switch (powerUp.type) {
                    case FireRate:
                        startingStats.firePauseTime -= 0.1f;
                        if (startingStats.firePauseTime < 0.1f) {
                            startingStats.firePauseTime = 0.1f;
                        }
                        break;
                    case Damage:
                        startingStats.damage += 1;
                        break;
                    case Life:
                        startingStats.life += 1;
                        break;
                }

                getEngine().removeEntity(entity);
            }

            collision.collidedEntity = null; // collision handled, reset component
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (Mapper.bullet.has(entity)) {
            processBulletCollision(entity, deltaTime);
            return;
        }
        if (Mapper.powerUp.has(entity)) {
            processPowerUpCollision(entity, deltaTime);
            return;
        }
    }
}
