package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.github.aligator.stuckinaloop.GameScreen;
import com.github.aligator.stuckinaloop.components.*;
import com.github.aligator.stuckinaloop.entities.PowerUp;

import java.util.Random;

public class KillSystem extends IteratingSystem {

    private final static int powerUpProbability = 3;
    private final Random robert;
    private final GameScreen gameScreen;
    private Entity player;

    public KillSystem(GameScreen gameScreen) {
        super(Family.all(SpaceShipComponent.class).get());

        robert = new Random();
        this.gameScreen = gameScreen;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get());
        if (entities.size() > 0) {
            player = entities.first();
        } else {
            player = null;
        }
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
                Mapper.player.get(player).kills++;

                if (robert.nextInt(powerUpProbability) == 0) {
                    PowerUpComponent.Type type = PowerUpComponent.Type.values()[robert.nextInt(PowerUpComponent.Type.values().length)];

                    boolean addPowerUp = gameScreen.startingStats.canCollect(type);

                    if (addPowerUp) {
                        getEngine().addEntity(PowerUp.create(body.body.getWorld(), body.body.getLinearVelocity(), body.body.getPosition(), type));
                    }
                }
            }

            getEngine().removeEntity(entity);
        }
    }
}
