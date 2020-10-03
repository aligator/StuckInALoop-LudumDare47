package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.TextureComponent;
import com.github.aligator.stuckinaloop.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {
    public MovementSystem() {
        super(Family.all(BodyComponent.class, VelocityComponent.class, TextureComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent body = Mapper.body.get(entity);
        VelocityComponent velocity = Mapper.velocity.get(entity);
        TextureComponent texture = Mapper.texture.get(entity);

        float x = body.body.getPosition().x + velocity.force.x * deltaTime;
        float y = body.body.getPosition().y + velocity.force.y * deltaTime;

        if (velocity.minBounds != null) {
            if (x < velocity.minBounds.x + texture.widthInMeters() / 2) {
                x = velocity.minBounds.x + texture.widthInMeters() / 2;
            }

            if (y < velocity.minBounds.y + texture.heightInMeters() / 2) {
                y = velocity.minBounds.y + texture.heightInMeters() / 2;
            }
        }

        if (velocity.maxBounds != null) {
            if (x > velocity.maxBounds.x - texture.widthInMeters() / 2) {
                x = velocity.maxBounds.x - texture.widthInMeters() / 2;
            }

            if (y > velocity.maxBounds.y - texture.heightInMeters() / 2) {
                y = velocity.maxBounds.y - texture.heightInMeters() / 2;
            }
        }
        body.body.setTransform(new Vector2(x, y), 0);
    }
}
