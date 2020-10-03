package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.aligator.stuckinaloop.systems.RenderingSystem;

public class TextureComponent implements Component {
    public TextureRegion region = null;

    public float widthInMeters() {
        return RenderingSystem.PixelsToMeters(region.getRegionWidth());
    }

    public float heightInMeters() {
        return RenderingSystem.PixelsToMeters(region.getRegionHeight());
    }
}
