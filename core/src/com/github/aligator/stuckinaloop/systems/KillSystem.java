package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.GameScreen;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PowerUpComponent;
import com.github.aligator.stuckinaloop.components.SpaceShipComponent;
import com.github.aligator.stuckinaloop.entities.PowerUp;

import java.util.Random;

public class KillSystem extends IteratingSystem {

    private final static int powerUpProbability = 3;
    private final Random robert;
    private final GameScreen gameScreen;

    public KillSystem(GameScreen gameScreen) {
        super(Family.all(SpaceShipComponent.class).get());
        robert = new Random();
        this.gameScreen = gameScreen;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpaceShipComponent spaceShip = Mapper.spaceShip.get(entity);
        if (spaceShip.life <= 0) {
            // when the player dies, restart game
            if (Mapper.player.has(entity)) {
                gameScreen.restart();
                return;
            }

            // when the enemy dies, drop a powerUp sometimes
            if (Mapper.enemy.has(entity) && Mapper.body.has(entity)) {
                BodyComponent body = Mapper.body.get(entity);

                if (robert.nextInt(powerUpProbability) == 0) {
                    PowerUpComponent.Type type = PowerUpComponent.Type.values()[robert.nextInt(PowerUpComponent.Type.values().length)];

                    getEngine().addEntity(PowerUp.create(body.body.getWorld(), body.body.getLinearVelocity(), body.body.getPosition(), type));
                }
            }

            getEngine().removeEntity(entity);
        }
    }
}
