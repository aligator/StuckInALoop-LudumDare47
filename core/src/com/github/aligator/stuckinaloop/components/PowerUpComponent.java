package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class PowerUpComponent implements Component {
    public Type type;

    public PowerUpComponent(Type type) {
        this.type = type;
    }

    public enum Type {
        Life, Damage, FireRate
    }
}
