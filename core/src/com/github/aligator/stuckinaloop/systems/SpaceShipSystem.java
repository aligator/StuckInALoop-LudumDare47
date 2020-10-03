package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.SpaceShipComponent;

public class SpaceShipSystem extends IteratingSystem {

    public SpaceShipSystem() {
        super(Family.all(SpaceShipComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpaceShipComponent spaceShip = Mapper.spaceShip.get(entity);
        if (spaceShip.live <= 0) {
            getEngine().removeEntity(entity);
        }
    }
}
