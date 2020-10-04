package com.github.aligator.stuckinaloop;

import com.github.aligator.stuckinaloop.components.PowerUpComponent;

public class PlayerStartingStats {
    public int life = 1;

    public int damage = 1;

    public float firePauseTime = 1;

    public int restartCounter = 0;

    public boolean canCollect(PowerUpComponent.Type powerUpType) {
        switch (powerUpType) {
            case Life:
                if (life >= 3) {
                    return false;
                }
                break;
            case FireRate:
                if (firePauseTime <= 0.1f) {
                    return false;
                }
                break;
            case Damage:
                if (damage >= 3) {
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
