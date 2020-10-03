package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.DiscardingComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.TextureComponent;

/**
 * DiscardingSystem removes entities out of screen (if they have the DiscardingComponent).
 */
public class DiscardingSystem extends IteratingSystem {

    public DiscardingSystem() {
        super(Family.all(DiscardingComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (Mapper.body.has(entity)) {
            BodyComponent body = Mapper.body.get(entity);
            float margin = 1;

            if (Mapper.texture.has(entity)) {
                TextureComponent texture = Mapper.texture.get(entity);

                if (texture.heightInMeters() > texture.widthInMeters()) {
                    margin = texture.heightInMeters() * 2;
                } else {
                    margin = texture.widthInMeters() * 2;
                }
            }

            if (body.body.getPosition().x + margin < 0 ||
                    body.body.getPosition().y + margin < 0 ||
                    body.body.getPosition().x - margin > RenderingSystem.getScreenSizeInMeters().x ||
                    body.body.getPosition().y - margin > RenderingSystem.getScreenSizeInMeters().y) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
