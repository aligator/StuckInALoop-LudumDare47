package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class BodyComponent implements Component {

    public Body body;
    public final Vector2 scale = new Vector2(1.0f, 1.0f);
}
