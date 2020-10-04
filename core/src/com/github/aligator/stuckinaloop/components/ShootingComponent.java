package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

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

    public Array<Canon> cannons = new Array<>();

    public ShootingComponent(Array<Canon> cannons) {
        this.cannons = cannons;
    }

    public ShootingComponent(Array<Canon> cannons, boolean isShooting, float firePauseTime) {
        this(cannons);

        if (isShooting) {
            // avoid instant shot
            lastShotTime = 0;
        }
        this.isShooting = isShooting;
        this.firePauseTime = firePauseTime;
    }

    public static class Canon {
        public Vector2 positionOffset;

        public Canon(Vector2 positionOffset) {
            this.positionOffset = positionOffset;
        }
    }
}
