package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;

public class SpaceShipComponent implements Component {
    public int life = 1;

    public int damage = 1;


    public SpaceShipComponent() {
    }

    public SpaceShipComponent(int life, int damage) {
        this.life = life;
        this.damage = damage;
    }
}
