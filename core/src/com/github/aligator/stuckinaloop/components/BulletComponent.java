package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class BulletComponent implements Component {
    public boolean isFromPlayer;
    public int damage;

    public BulletComponent(boolean isFromPlayer, int damage) {
        this.isFromPlayer = isFromPlayer;
        this.damage = damage;
    }
}
