package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {

    public final Vector2 force = new Vector2();
    public Vector2 minBounds = null;
    public Vector2 maxBounds = null;
}
