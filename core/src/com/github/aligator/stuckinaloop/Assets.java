package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    //private static final String FONT = "fonts/courier-new-bold-32.fnt";
    public static AssetManager assetManager;
    public static TextureRegion splashScreen;
    public static TextureRegion player;
    public static TextureRegion enemyGreen;
    public static TextureRegion enemyYellow;
    public static TextureRegion enemyRed;
    public static TextureRegion enemyBulletGreen;
    public static TextureRegion enemyBulletYellow;
    public static TextureRegion enemyBulletRed;
    public static TextureRegion playerBulletGreen;
    public static TextureRegion playerBulletYellow;
    public static TextureRegion playerBulletRed;

    public static TextureRegion lifePowerUp;
    public static TextureRegion damagePowerUp;
    public static TextureRegion fireRatePowerUp;

    //private static Class<TextureAtlas> TEXTURE_ATLAS = TextureAtlas.class;
    //private static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    public static AssetManager load() {

        loadSplash();
        loadPlayer();
        loadEnemy();
        loadBullets();
        loadPowerUps();

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
        enemyGreen = new TextureRegion(new Texture("enemy_green.png"));
        enemyGreen.flip(true, false);

        enemyYellow = new TextureRegion(new Texture("enemy_yellow.png"));
        enemyYellow.flip(true, false);

        enemyRed = new TextureRegion(new Texture("enemy_red.png"));
        enemyRed.flip(true, false);
    }

    private static void loadBullets() {
        enemyBulletGreen = new TextureRegion(new Texture("bullet_green.png"));
        enemyBulletGreen.flip(true, false);

        enemyBulletYellow = new TextureRegion(new Texture("bullet_yellow.png"));
        enemyBulletYellow.flip(true, false);

        enemyBulletRed = new TextureRegion(new Texture("bullet_red.png"));
        enemyBulletRed.flip(true, false);

        playerBulletGreen = new TextureRegion(new Texture("bullet_green.png"));
        playerBulletYellow = new TextureRegion(new Texture("bullet_yellow.png"));
        playerBulletRed = new TextureRegion(new Texture("bullet_red.png"));
    }

    private static void loadPowerUps() {
        lifePowerUp = new TextureRegion(new Texture("life.png"));
        damagePowerUp = new TextureRegion(new Texture("damage.png"));
        fireRatePowerUp = new TextureRegion(new Texture("fire_rate.png"));
    }
}
