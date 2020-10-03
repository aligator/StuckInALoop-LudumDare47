package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    //private static final String FONT = "fonts/courier-new-bold-32.fnt";
    public static AssetManager assetManager;
    public static TextureRegion splashScreen;
    public static TextureRegion player;
    //private static Class<TextureAtlas> TEXTURE_ATLAS = TextureAtlas.class;
    //private static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    public static AssetManager load() {

        loadSplash();
        loadPlayer();

        assetManager = new AssetManager();

        return assetManager;
    }

    private static void loadSplash() {
        splashScreen = new TextureRegion(new Texture("badlogic.jpg"));
    }

    private static void loadPlayer() {
        player = new TextureRegion(new Texture("spaceship.png"));
    }
}
