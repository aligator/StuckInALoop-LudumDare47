package com.github.aligator.stuckinaloop.components;

import com.badlogic.gdx.physics.box2d.World;

public interface IDisposeableComponent {
    void dispose(World world);
}
