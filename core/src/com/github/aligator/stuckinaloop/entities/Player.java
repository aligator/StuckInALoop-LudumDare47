package com.github.aligator.stuckinaloop.entities;


import com.badlogic.ashley.core.Entity;
import com.github.aligator.stuckinaloop.Assets;
import com.github.aligator.stuckinaloop.components.PlayerComponent;
import com.github.aligator.stuckinaloop.components.TextureComponent;
import com.github.aligator.stuckinaloop.components.TransformComponent;

public class Player {
    public static Entity create() {
        Entity e = new Entity();

        TextureComponent texture = new TextureComponent();
        texture.region = Assets.splashScreen;
        TransformComponent transform = new TransformComponent();

        e.add(new PlayerComponent());
        e.add(transform);
        e.add(texture);

        return e;
    }
}
