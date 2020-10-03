package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.components.BulletComponent;
import com.github.aligator.stuckinaloop.components.CollisionComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PlayerComponent;

public class CollisionSystem extends IteratingSystem {

    public CollisionSystem() {
        super(Family.all(CollisionComponent.class).one(PlayerComponent.class, BulletComponent.class).get());
    }

    protected void processBulletCollision(Entity entity, float deltaTime) {
        BulletComponent bullet = Mapper.bullet.get(entity);
        CollisionComponent collision = Mapper.collision.get(entity);

        if (collision.collidedEntity != null) {
            if (Mapper.enemy.has(collision.collidedEntity)) {
                getEngine().removeEntity(collision.collidedEntity);
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
    }
}
