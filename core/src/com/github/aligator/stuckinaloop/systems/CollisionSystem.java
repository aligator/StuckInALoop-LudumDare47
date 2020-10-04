package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.PlayerStartingStats;
import com.github.aligator.stuckinaloop.components.*;

public class CollisionSystem extends IteratingSystem {

    private final PlayerStartingStats startingStats;

    public CollisionSystem(PlayerStartingStats startingStats) {
        super(Family.all(CollisionComponent.class).one(BulletComponent.class, PowerUpComponent.class, PlayerComponent.class).get());
        this.startingStats = startingStats;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent collision = Mapper.collision.get(entity);

        if (collision.collidedEntity != null) {
            // sve collidedEntity before cleanup
            Entity collidedEntity = collision.collidedEntity;

            // clean up collision
            collision.collidedEntity = null;
            CollisionComponent collision2 = Mapper.collision.get(collidedEntity);
            if (collision2 != null) {
                collision2.collidedEntity = null;
            }

            // any of them is a bullet
            if (Mapper.bullet.has(entity)) {
                collideWithBullet(collidedEntity, entity);
                return;
            } else if (Mapper.bullet.has(collidedEntity)) {
                collideWithBullet(entity, collidedEntity);
                return;
            }

            // any of them is a power up and the other the player
            if (Mapper.powerUp.has(entity) && Mapper.player.has(collidedEntity)) {
                collectPowerUp(entity);
                return;
            } else if (Mapper.powerUp.has(collidedEntity) && Mapper.player.has(entity)) {
                collectPowerUp(collidedEntity);
                return;
            }

            // any of them is a player and the other is an enemy
            if (Mapper.player.has(entity) && Mapper.enemy.has(collidedEntity)) {
                collideWithEnemy(entity, collidedEntity);
                return;
            } else if (Mapper.enemy.has(collidedEntity) && Mapper.player.has(entity)) {
                collideWithEnemy(collidedEntity, entity);
                return;
            }
        }
    }


    private void collectPowerUp(Entity powerUpEntity) {
        PowerUpComponent powerUp = Mapper.powerUp.get(powerUpEntity);

        if (startingStats.canCollect(powerUp.type)) {
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
        }

        getEngine().removeEntity(powerUpEntity);
    }


    private void collideWithEnemy(Entity playerEntity, Entity enemyEntity) {
        SpaceShipComponent enemySpaceShip = Mapper.spaceShip.get(enemyEntity);
        SpaceShipComponent playerSpaceShip = Mapper.spaceShip.get(playerEntity);

        --enemySpaceShip.life;
        --playerSpaceShip.life;
    }

    private void collideWithBullet(Entity otherEntity, Entity bulletEntity) {
        BulletComponent bullet = Mapper.bullet.get(bulletEntity);


        if (!Mapper.bullet.has(otherEntity) &&
                (bullet.isFromPlayer && Mapper.enemy.has(otherEntity) ||
                        !bullet.isFromPlayer && Mapper.player.has(otherEntity))
        ) {
            getEngine().removeEntity(bulletEntity);

            if (Mapper.spaceShip.has(otherEntity)) {
                SpaceShipComponent spaceShip = Mapper.spaceShip.get(otherEntity);
                spaceShip.life -= bullet.damage;
                Assets.explosionSound.play();
            }
            return;
        }

        if (Mapper.bullet.has(otherEntity)) {
            BulletComponent bullet2 = Mapper.bullet.get(otherEntity);
            if (bullet.isFromPlayer != bullet2.isFromPlayer) {
                getEngine().removeEntity(otherEntity);
                getEngine().removeEntity(bulletEntity);
                Assets.explosionSound.play();
            }

            return;
        }


    }
}
