package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class ShootingComponent implements Component {
    public boolean isShooting = false;
    public float firePauseTime = 1f;
    public float lastShotTime = -1;

    /**
     * burstFirePauseTime is the firePauseTime max needed to enable burst mode
     */
    public float burstFirePauseTime = 0.3f;
    public float burstCoolDown = 3f;
    public float currentBurstCoolDown = burstCoolDown;
    public float maxShotsInBurstTime = 5;
    public float burstShootCount = 0;

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
