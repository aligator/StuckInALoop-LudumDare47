package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.entities.Enemy;

public class EnemySpawningSystem extends EntitySystem {

    private final World world;

    private float fullTime = 0;
    private float lastStep = 0;

    public EnemySpawningSystem(World world) {
        this.world = world;
    }

    @Override
    public void update(float deltaTime) {
        fullTime += deltaTime;


        if (fullTime >= 3 && lastStep < 3) {
            lastStep = 3;
            getEngine().addEntity(Enemy.create(world, -10f, 0.91f, 2, 1, 1));
            getEngine().addEntity(Enemy.create(world, -9f, 0.5f, 1.5f, 1, 1));
        } else if (fullTime >= 7 && lastStep < 7) {
            lastStep = 7;
            getEngine().addEntity(Enemy.create(world, -8f, 0f, 2, 2, 2));
            getEngine().addEntity(Enemy.create(world, -9f, 1f, 1.5f, 1, 1));
        } else if (fullTime >= 9 && lastStep < 9) {
            lastStep = 9;
            getEngine().addEntity(Enemy.create(world, -4f, 0.7f, 0.6f, 3, 1));
            getEngine().addEntity(Enemy.create(world, -9f, 0.5f, 1.5f, 1, 2));
        } else if (fullTime >= 12 && lastStep < 12) {
            lastStep = 12;
            getEngine().addEntity(Enemy.create(world, -6f, 0.3f, 2, 2, 1));
            getEngine().addEntity(Enemy.create(world, -9f, 0.5f, 1.5f, 1, 2));
        } else if (fullTime >= 15 && lastStep < 15) {
            lastStep = 15;
            getEngine().addEntity(Enemy.create(world, -12f, 0.9f, 2, 3, 3));
            getEngine().addEntity(Enemy.create(world, -8f, 0.5f, 1.5f, 2, 1));
        } else if (fullTime >= 19 && lastStep < 19) {
            lastStep = 19;
            getEngine().addEntity(Enemy.create(world, -11f, 0.2f, 2, 3, 1));
            getEngine().addEntity(Enemy.create(world, -5f, 0.5f, 1.5f, 1, 3));
        } else if (fullTime >= 23 && lastStep < 23) {
            lastStep = 23;
            getEngine().addEntity(Enemy.create(world, -10f, 1f, 2, 1, 1));
            getEngine().addEntity(Enemy.create(world, -4f, 0.9f, 1.5f, 3, 2));
            getEngine().addEntity(Enemy.create(world, -8f, 0f, 1.5f, 1, 3));
            getEngine().addEntity(Enemy.create(world, -9f, 0.3f, 1.5f, 2, 1));
        } else if (fullTime >= 24 && lastStep < 24) {
            lastStep = 24;
            getEngine().addEntity(Enemy.create(world, -9f, 0.7f, 1.6f, 1, 3));
            getEngine().addEntity(Enemy.create(world, -10f, 0.2f, 1.5f, 3, 3));
        } else if (fullTime >= 26 && lastStep < 26) {
            lastStep = 26;
            getEngine().addEntity(Enemy.create(world, -3f, 0.6f, 2, 1, 1));
            getEngine().addEntity(Enemy.create(world, -9f, 0.5f, 1.5f, 2, 3));
        } else if (fullTime >= 28 && lastStep < 28) {
            lastStep = 28;
            getEngine().addEntity(Enemy.create(world, -7f, 0.6f, 1.3f, 1, 2));
            getEngine().addEntity(Enemy.create(world, -9f, 1f, 1.8f, 1, 1));
        } else if (fullTime >= 30 && lastStep < 30) {
            lastStep = 30;
            getEngine().addEntity(Enemy.create(world, -10f, 0.6f, 2, 2, 3));
            getEngine().addEntity(Enemy.create(world, -5f, 0.5f, 1.5f, 3, 2));
        } else if (fullTime >= 32 && lastStep < 32) {
            lastStep = 32;
            getEngine().addEntity(Enemy.create(world, -8f, 0.6f, 2, 2, 1));
            getEngine().addEntity(Enemy.create(world, -9f, 0.5f, 1.5f, 1, 1));
        } else if (fullTime >= 35 && lastStep < 35) {
            lastStep = 35;
            getEngine().addEntity(Enemy.create(world, -6f, 0.3f, 2, 3, 3));
            getEngine().addEntity(Enemy.create(world, -9f, 1f, 1.5f, 1, 3));
        } else if (fullTime >= 36 && lastStep < 36) {
            lastStep = 36;
            getEngine().addEntity(Enemy.create(world, -4f, 0.4f, 2, 3, 3));
            getEngine().addEntity(Enemy.create(world, -5f, 0.2f, 1.5f, 3, 3));
        }
    }
}
