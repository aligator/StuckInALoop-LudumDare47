package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class ShootingComponent implements Component {
    public boolean isShooting = false;
    public float firePauseTime = 1f;
    public float lastShotTime = -1;

    public ShootingComponent() {
    }

    public ShootingComponent(boolean isShooting, float firePauseTime) {
        if (isShooting) {
            // avoid instant shot
            lastShotTime = 0;
        }
        this.isShooting = isShooting;
        this.firePauseTime = firePauseTime;
    }
}
