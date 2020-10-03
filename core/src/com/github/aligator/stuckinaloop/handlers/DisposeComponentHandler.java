package com.github.aligator.stuckinaloop.handlers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.physics.box2d.World;
import com.github.aligator.stuckinaloop.components.IDisposeableComponent;

public class DisposeComponentHandler implements EntityListener {

    private World world;

    public DisposeComponentHandler(World world) {
        this.world = world;
    }

    @Override
    public void entityRemoved(Entity entity) {
        for (Component component : entity.getComponents()) {
            if (component instanceof IDisposeableComponent) {
                ((IDisposeableComponent) component).dispose(world);
            }
        }
    }

    @Override
    public void entityAdded(Entity entity) {

    }
}
