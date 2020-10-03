package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class BulletComponent implements Component {
    public boolean isFromPlayer;
    public int damage = 1;

    public BulletComponent(boolean isFromPlayer, int damage) {
        this.isFromPlayer = isFromPlayer;
    }
}
