package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.TextureComponent;
import com.github.aligator.stuckinaloop.components.TransformComponent;
import com.github.aligator.stuckinaloop.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
    public MovementSystem() {
        super(Family.all(TransformComponent.class, VelocityComponent.class, TextureComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mapper.transform.get(entity);
        VelocityComponent velocity = Mapper.velocity.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);

        float x = transform.position.x + velocity.force.x * deltaTime;
        float y = transform.position.y + velocity.force.y * deltaTime;

        if (velocity.minBounds != null) {
            if (x < velocity.minBounds.x + texture.region.getRegionWidth() / 2) {
                x = velocity.minBounds.x + texture.region.getRegionWidth() / 2;
            }

            if (y < velocity.minBounds.y + texture.region.getRegionHeight() / 2) {
                y = velocity.minBounds.y + texture.region.getRegionHeight() / 2;
            }
        }

        if (velocity.maxBounds != null) {
            if (x > velocity.maxBounds.x - texture.region.getRegionWidth() / 2) {
                x = velocity.maxBounds.x - texture.region.getRegionWidth() / 2;
            }

            if (y > velocity.maxBounds.y - texture.region.getRegionHeight() / 2) {
                y = velocity.maxBounds.y - texture.region.getRegionHeight() / 2;
            }
        }

        transform.position.set(x, y, 0);
    }
}
