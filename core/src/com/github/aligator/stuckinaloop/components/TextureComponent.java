package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class TextureComponent implements Component {
    public TextureRegion region = null;
    public final Vector2 scale = new Vector2(1.0f, 1.0f);

    public float widthInMeters() {
        return RenderingSystem.PixelsToMeters(region.getRegionWidth() * scale.x);
    }

    public float heightInMeters() {
        return RenderingSystem.PixelsToMeters(region.getRegionHeight() * scale.y);
    }
}
