package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class BulletComponent implements Component {
    public boolean isFromPlayer = false;

    public BulletComponent(boolean isFromPlayer) {
        this.isFromPlayer = isFromPlayer;
    }
}
