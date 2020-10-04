package com.github.aligator.stuckinaloop;

public class PlayerStartingStats {
    public int life = 1;

    public int damage = 1;

    public float firePauseTime = 1;

    @Override
    public String toString() {
        return "PlayerStartingStats{" +
                "life=" + life +
                ", damage=" + damage +
                ", firePauseTime=" + firePauseTime +
                '}';
    }
}
