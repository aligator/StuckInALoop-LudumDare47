package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    //private static final String FONT = "fonts/courier-new-bold-32.fnt";
    public static AssetManager assetManager;
    public static TextureRegion splashScreen;
    public static TextureRegion player;
    public static TextureRegion enemy;
    public static TextureRegion enemyBullet;
    public static TextureRegion playerBullet;
    public static TextureRegion bullet;
    //private static Class<TextureAtlas> TEXTURE_ATLAS = TextureAtlas.class;
    //private static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    public static AssetManager load() {

        loadSplash();
        loadPlayer();
        loadEnemy();
        loadBullets();

        assetManager = new AssetManager();

        return assetManager;
    }

    private static void loadSplash() {
        splashScreen = new TextureRegion(new Texture("badlogic.jpg"));
    }

    private static void loadPlayer() {
        player = new TextureRegion(new Texture("spaceship.png"));
    }

    private static void loadEnemy() {
        enemy = new TextureRegion(new Texture("enemy.png"));
        enemy.flip(true, false);
    }

    private static void loadBullets() {
        enemyBullet = new TextureRegion(new Texture("bullet.png"));
        enemyBullet.flip(true, false);

        playerBullet = new TextureRegion(new Texture("bullet.png"));
    }
}
