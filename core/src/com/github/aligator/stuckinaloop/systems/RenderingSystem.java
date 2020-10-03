package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.TextureComponent;

public class RenderingSystem extends EntitySystem implements EntityListener {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    ImmutableArray<Entity> entities;

    static final float PPM = 16.0f;
    public static final float PIXELS_TO_METRES = 1.0f / PPM;
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth() / PPM;//37.5f;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight() / PPM;//.0f;
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

    public RenderingSystem(SpriteBatch batch) {
        this.batch = batch;

        cam = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        cam.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);
    }

    public static Vector2 getScreenSizeInMeters() {
        meterDimensions.set(Gdx.graphics.getWidth() * PIXELS_TO_METRES,
                Gdx.graphics.getHeight() * PIXELS_TO_METRES);
        return meterDimensions;
    }

    public static Vector2 getScreenSizeInPixels() {
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    public static float PixelsToMeters(float pixelValue) {
        return pixelValue * PIXELS_TO_METRES;
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
                    PixelsToMeters(t.scale.x), PixelsToMeters(t.scale.y),
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
