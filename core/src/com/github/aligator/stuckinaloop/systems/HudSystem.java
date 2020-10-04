package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.PlayerStartingStats;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PlayerComponent;
import com.github.aligator.stuckinaloop.components.SpaceShipComponent;

public class HudSystem extends EntitySystem implements EntityListener {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Entity player;
    private PlayerStartingStats startingStats;

    public HudSystem(SpriteBatch batch, PlayerStartingStats startingStats) {
        this.batch = batch;
        this.startingStats = startingStats;

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
    }

    @Override
    public void addedToEngine(Engine engine) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get());
        if (entities.size() > 0) {
            player = entities.first();
            return;
        }

        player = null;
    }

    @Override
    public void removedFromEngine(Engine engine) {
        player = null;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (player == null) {
            return;
        }

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        SpaceShipComponent spaceShip = Mapper.spaceShip.get(player);
        PlayerComponent playerComp = Mapper.player.get(player);

        Assets.font24.draw(batch, "Current lives: " + spaceShip.life, 10, cam.viewportHeight);
        Assets.font24.draw(batch, "Killed enemies: " + playerComp.kills + " / " + EnemySpawningSystem.ENEMY_COUNT, 10, cam.viewportHeight - Assets.font24.getLineHeight() * 1);
        Assets.font24.draw(batch, "Experience", cam.viewportWidth - Assets.font24.getXHeight() * 12, cam.viewportHeight);
        Assets.font24.draw(batch, "for next live:", cam.viewportWidth - Assets.font24.getXHeight() * 12, cam.viewportHeight - Assets.font24.getLineHeight() * 1);
        Assets.font24.draw(batch, "Live: " + startingStats.life, cam.viewportWidth - Assets.font24.getXHeight() * 12, cam.viewportHeight - Assets.font24.getLineHeight() * 3);
        Assets.font24.draw(batch, "Damage " + startingStats.damage, cam.viewportWidth - Assets.font24.getXHeight() * 12, cam.viewportHeight - Assets.font24.getLineHeight() * 4);
        Assets.font24.draw(batch, "Fire rate " + Math.round((1.0f - startingStats.firePauseTime) * 10) + 1, cam.viewportWidth - Assets.font24.getXHeight() * 12, cam.viewportHeight - Assets.font24.getLineHeight() * 5);

        batch.draw(Assets.lifePowerUp,
                cam.viewportWidth - Assets.font24.getXHeight() * 12 - Assets.lifePowerUp.getRegionWidth() * 0.2f - 5,
                cam.viewportHeight - Assets.font24.getLineHeight() * 4 + 3,
                0f, 0f,
                Assets.lifePowerUp.getRegionWidth(),
                Assets.lifePowerUp.getRegionHeight(),
                0.2f, 0.2f, 0f
        );
        batch.draw(Assets.damagePowerUp,
                cam.viewportWidth - Assets.font24.getXHeight() * 12 - Assets.lifePowerUp.getRegionWidth() * 0.2f - 5,
                cam.viewportHeight - Assets.font24.getLineHeight() * 5 + 3,
                0f, 0f,
                Assets.lifePowerUp.getRegionWidth(),
                Assets.lifePowerUp.getRegionHeight(),
                0.2f, 0.2f, 0f
        );
        batch.draw(Assets.fireRatePowerUp,
                cam.viewportWidth - Assets.font24.getXHeight() * 12 - Assets.lifePowerUp.getRegionWidth() * 0.2f - 5,
                cam.viewportHeight - Assets.font24.getLineHeight() * 6 + 3,
                0f, 0f,
                Assets.lifePowerUp.getRegionWidth(),
                Assets.lifePowerUp.getRegionHeight(),
                0.2f, 0.2f, 0f
        );
        batch.end();
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
