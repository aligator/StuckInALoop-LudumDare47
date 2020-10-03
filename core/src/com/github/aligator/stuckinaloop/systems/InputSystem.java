package com.github.aligator.stuckinaloop.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.github.aligator.stuckinaloop.components.Mapper;
import com.github.aligator.stuckinaloop.components.PlayerComponent;
import com.github.aligator.stuckinaloop.components.VelocityComponent;
import com.github.aligator.stuckinaloop.entities.Player;
import com.github.aligator.stuckinaloop.handlers.IInputListener;

public class InputSystem extends EntitySystem implements IInputListener {
    private Entity player;

    public InputSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(Family.all(PlayerComponent.class, VelocityComponent.class).get());
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
    public void move(Vector2 direction) {
        if (player == null) {
            return;
        }

        VelocityComponent velocity = Mapper.velocity.get(player);
        velocity.force.x = direction.x * Player.MOVE_SPEED;
        velocity.force.y = direction.y * Player.MOVE_SPEED;
    }

    @Override
    public void shoot(boolean active) {
        if (player == null) {
            return;
        }

        PlayerComponent playerComp = Mapper.player.get(player);
        playerComp.isShooting = active;
    }
}
