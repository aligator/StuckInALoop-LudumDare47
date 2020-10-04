package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.github.aligator.stuckinaloop.components.ExplosionComponent;
import com.github.aligator.stuckinaloop.components.Mapper;

public class ExplosionSystem extends EntitySystem implements EntityListener {
    ImmutableArray<Entity> entities;

    public ExplosionSystem() {

    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = getEngine().getEntitiesFor(Family.all(ExplosionComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = null;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (entities == null) {
            return;
        }

        for (Entity entity : entities) {
            ExplosionComponent explosion = Mapper.explosion.get(entity);
            explosion.liveTime += deltaTime;

            if (explosion.liveTime > explosion.maxTime) {
                getEngine().removeEntity(entity);
            }
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        addedToEngine(getEngine());
    }

    @Override
    public void entityRemoved(Entity entity) {
        addedToEngine(getEngine());
    }
}
