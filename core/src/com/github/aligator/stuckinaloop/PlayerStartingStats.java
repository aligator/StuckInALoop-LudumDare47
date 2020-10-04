package com.github.aligator.stuckinaloop;

import com.github.aligator.stuckinaloop.components.PowerUpComponent;

public class PlayerStartingStats {
    public int life = 1;

    public int damage = 1;

    public float firePauseTime = 1;

    public int restartCounter = 0;

    public boolean random = false;

    public static final int MAX_LIFE = 15;
    public static final float MAX_FIRE_PAUSE_TIME = 0.1f;
    public static final int MAX_DAMAGE = 3;

    public boolean canCollect(PowerUpComponent.Type powerUpType) {
        switch (powerUpType) {
            case Life:
                if (life >= MAX_LIFE) {
                    return false;
                }
                break;
            case FireRate:
                if (firePauseTime <= MAX_FIRE_PAUSE_TIME) {
                    return false;
                }
                break;
            case Damage:
                if (damage >= MAX_DAMAGE) {
                    return false;
                }
                break;
        }

        return true;
    }

    @Override
    public String toString() {
        return "PlayerStartingStats{" +
                "life=" + life +
                ", damage=" + damage +
                ", firePauseTime=" + firePauseTime +
                '}';
    }
}
