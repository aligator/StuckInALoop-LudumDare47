package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent implements Component {

    public final Vector3 position = new Vector3();
    public float rotation = 0.0f;
    public boolean isHidden = false;
}
