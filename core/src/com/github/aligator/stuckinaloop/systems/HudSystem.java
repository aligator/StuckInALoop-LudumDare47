package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.PlayerStartingStats;
import com.github.aligator.stuckinaloop.components.*;

public class HudSystem extends EntitySystem implements EntityListener {

    private SpriteBatch batch;
    private OrthographicCamera cam;
    private Entity player;
    private Entity boss;
    private PlayerStartingStats startingStats;

    public HudSystem(SpriteBatch batch, PlayerStartingStats startingStats) {
        this.batch = batch;
        this.startingStats = startingStats;

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, 0);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = null;
        boss = null;

        ImmutableArray<Entity> players = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get());
        if (players.size() > 0) {
            player = players.first();
        }

        ImmutableArray<Entity> bosses = getEngine().getEntitiesFor(Family.all(BossComponent.class).get());
        if (bosses.size() > 0) {
            boss = bosses.first();
        }

    }

    @Override
    public void removedFromEngine(Engine engine) {
        player = null;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();

        // draw boss hud
        if (boss != null) {
            BossComponent bossComp = Mapper.boss.get(boss);
            SpaceShipComponent spaceShip = Mapper.spaceShip.get(boss);
            String text = "Boss health: " + spaceShip.life + " / " + bossComp.startingHealth;
            Assets.font24.draw(batch, text, cam.viewportWidth / 2 - Assets.font24.getXHeight() * (text.length() / 2), cam.viewportHeight);
        }

        if (player == null) {
            return;
        }

        SpaceShipComponent spaceShip = Mapper.spaceShip.get(player);
        PlayerComponent playerComp = Mapper.player.get(player);

        Assets.font24.draw(batch, "Current health: " + spaceShip.life, 10, cam.viewportHeight);
        Assets.font24.draw(batch, "Killed enemies: " + playerComp.kills + " / " + EnemySpawningSystem.ENEMY_COUNT, 10, cam.viewportHeight - Assets.font24.getLineHeight() * 1);
        Assets.font24.draw(batch, "Experience for next live:", cam.viewportWidth - Assets.font24.getXHeight() * 23, cam.viewportHeight);

        if (startingStats.canCollect(PowerUpComponent.Type.Life)) {
            Assets.font24.draw(batch, "Health:    " + startingStats.life + " / " + PlayerStartingStats.MAX_LIFE, cam.viewportWidth - Assets.font24.getXHeight() * 16, cam.viewportHeight - Assets.font24.getLineHeight() * 2);
        } else {
            Assets.font24Green.draw(batch, "Health:    " + startingStats.life + " / " + PlayerStartingStats.MAX_LIFE, cam.viewportWidth - Assets.font24.getXHeight() * 16, cam.viewportHeight - Assets.font24.getLineHeight() * 2);
        }
        if (startingStats.canCollect(PowerUpComponent.Type.Damage)) {
            Assets.font24.draw(batch, "Damage: " + startingStats.damage + " / " + PlayerStartingStats.MAX_DAMAGE, cam.viewportWidth - Assets.font24.getXHeight() * 16, cam.viewportHeight - Assets.font24.getLineHeight() * 3);
        } else {
            Assets.font24Green.draw(batch, "Damage: " + startingStats.damage + " / " + PlayerStartingStats.MAX_DAMAGE, cam.viewportWidth - Assets.font24.getXHeight() * 16, cam.viewportHeight - Assets.font24.getLineHeight() * 3);
        }
        if (startingStats.canCollect(PowerUpComponent.Type.FireRate)) {
            Assets.font24.draw(batch, "Fire rate: " + (Math.round((1.0f - startingStats.firePauseTime) * 10) + 1) + " / " + (Math.round((1.0f - PlayerStartingStats.MAX_FIRE_PAUSE_TIME) * 10) + 1), cam.viewportWidth - Assets.font24.getXHeight() * 16, cam.viewportHeight - Assets.font24.getLineHeight() * 4);
        } else {
            Assets.font24Green.draw(batch, "Fire rate: " + (Math.round((1.0f - startingStats.firePauseTime) * 10) + 1) + " / " + (Math.round((1.0f - PlayerStartingStats.MAX_FIRE_PAUSE_TIME) * 10) + 1), cam.viewportWidth - Assets.font24.getXHeight() * 16, cam.viewportHeight - Assets.font24.getLineHeight() * 4);
        }

        batch.draw(Assets.lifePowerUp,
                cam.viewportWidth - Assets.font24.getXHeight() * 16 - Assets.lifePowerUp.getRegionWidth() * 0.2f - 5,
                cam.viewportHeight - Assets.font24.getLineHeight() * 3 + 3,
                0f, 0f,
                Assets.lifePowerUp.getRegionWidth(),
                Assets.lifePowerUp.getRegionHeight(),
                0.2f, 0.2f, 0f
        );
        batch.draw(Assets.damagePowerUp,
                cam.viewportWidth - Assets.font24.getXHeight() * 16 - Assets.lifePowerUp.getRegionWidth() * 0.2f - 5,
                cam.viewportHeight - Assets.font24.getLineHeight() * 4 + 3,
                0f, 0f,
                Assets.lifePowerUp.getRegionWidth(),
                Assets.lifePowerUp.getRegionHeight(),
                0.2f, 0.2f, 0f
        );
        batch.draw(Assets.fireRatePowerUp,
                cam.viewportWidth - Assets.font24.getXHeight() * 16 - Assets.lifePowerUp.getRegionWidth() * 0.2f - 5,
                cam.viewportHeight - Assets.font24.getLineHeight() * 5 + 3,
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
