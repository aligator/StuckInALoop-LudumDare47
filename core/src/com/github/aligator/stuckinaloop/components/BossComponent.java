package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class BossComponent implements Component {

    public boolean isInFightingPosition = false;
    public int startingHealth;

    public BossComponent(int startingHealth) {
        this.startingHealth = startingHealth;
    }
}
