package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.components.BodyComponent;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PlayerComponent;
import com.github.aligator.stuckinaloop.entities.Bullet;

public class ShootingSystem extends IteratingSystem {

    private final World world;
    private float lastShotTime = 0;
    private float firerate = 1;


    public ShootingSystem(World world) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent player = Mapper.player.get(entity);
        BodyComponent body = Mapper.body.get(entity);

        lastShotTime += deltaTime;

        if (player.isShooting && lastShotTime > firerate) {
            lastShotTime = 0;
            System.out.println(body.body.getPosition());
            Entity bullet = Bullet.create(world, 9999999, body.body.getPosition());

            getEngine().addEntity(bullet);
        }
    }
}
