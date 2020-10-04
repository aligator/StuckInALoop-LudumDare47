package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.PlayerStartingStats;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PowerUpComponent;

public class PowerUpSystem extends IteratingSystem {

    private final PlayerStartingStats startingStats;

    public PowerUpSystem(PlayerStartingStats startingStats) {
        super(Family.all(PowerUpComponent.class).get());

        this.startingStats = startingStats;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PowerUpComponent powerUp = Mapper.powerUp.get(entity);
        if (!startingStats.canCollect(powerUp.type)) {
            getEngine().removeEntity(entity);
        }
    }
}
