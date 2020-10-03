package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.TextureComponent;

public class RenderingSystem extends EntitySystem implements EntityListener {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    ImmutableArray<Entity> entities;


    public RenderingSystem(SpriteBatch batch) {
        this.batch = batch;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = getEngine().getEntitiesFor(Family.all(BodyComponent.class, TextureComponent.class).get());
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

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        for (Entity entity : entities) {
            TextureComponent tex = Mapper.texture.get(entity);
            BodyComponent t = Mapper.body.get(entity);

            if (tex.region == null) {
                continue;
            }

            float width = tex.region.getRegionWidth();
            float height = tex.region.getRegionHeight();

            float originX = width / 2f;
            float originY = height / 2f;

            batch.draw(tex.region,
                    t.body.getPosition().x - originX, t.body.getPosition().y - originY,
                    originX, originY,
                    width, height,
                    1, 1,
                    t.body.getTransform().getRotation());
        }

        batch.end();
    }

    public OrthographicCamera getCamera() {
        return cam;
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
