package com.github.aligator.stuckinaloop.handlers;

import com.badlogic.gdx.math.Vector2;

public interface IInputListener {
    void move(Vector2 direction);

    void shoot(boolean active);
}
