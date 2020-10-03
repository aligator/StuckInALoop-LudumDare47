package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PowerUpComponent;
import com.github.aligator.stuckinaloop.components.SpaceShipComponent;
import com.github.aligator.stuckinaloop.entities.PowerUp;

public class SpaceShipSystem extends IteratingSystem {

    public SpaceShipSystem() {
        super(Family.all(SpaceShipComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpaceShipComponent spaceShip = Mapper.spaceShip.get(entity);
        if (spaceShip.life <= 0) {
            if (Mapper.enemy.has(entity) && Mapper.body.has(entity)) {
                BodyComponent body = Mapper.body.get(entity);
                getEngine().addEntity(PowerUp.create(body.body.getWorld(), body.body.getLinearVelocity(), body.body.getPosition(), PowerUpComponent.Type.Damage));
            }

            getEngine().removeEntity(entity);
        }
    }
}
