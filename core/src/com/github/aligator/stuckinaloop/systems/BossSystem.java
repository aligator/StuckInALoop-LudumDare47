package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.components.*;

public class BossSystem extends IteratingSystem {

    public BossSystem() {
        super(Family.all(ShootingComponent.class, BodyComponent.class, SpaceShipComponent.class, BossComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BossComponent boss = Mapper.boss.get(entity);
        BodyComponent body = Mapper.body.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);

        // restrict movement in x and instead move up and down
        if (!boss.isInFightingPosition && body.body.getPosition().x <= RenderingSystem.getScreenSizeInMeters().x - texture.widthInMeters() / 2 - 3) {
            body.body.setLinearVelocity(0f, 5f);
            boss.isInFightingPosition = true;
        }

        if (boss.isInFightingPosition && body.body.getPosition().y + texture.heightInMeters() / 2 >= RenderingSystem.getScreenSizeInMeters().y) {
            body.body.setLinearVelocity(0f, -5f);
        }

        if (boss.isInFightingPosition && body.body.getPosition().y - texture.heightInMeters() / 2 <= 0) {
            body.body.setLinearVelocity(0f, 5f);
        }
    }
}
