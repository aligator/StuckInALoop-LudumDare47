package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class ShootingComponent implements Component {
    public boolean isShooting = false;
    public float firePauseTime = 1f;

    public ShootingComponent() {
    }

    public ShootingComponent(boolean isShooting, float firePauseTime) {
        this.isShooting = isShooting;
        this.firePauseTime = firePauseTime;
    }
}
