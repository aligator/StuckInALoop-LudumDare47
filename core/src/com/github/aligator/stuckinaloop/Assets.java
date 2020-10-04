package com.github.aligator.stuckinaloop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {

    public static AssetManager assetManager;
    public static TextureRegion splashScreen;
    public static TextureRegion player;
    public static TextureRegion explosion;
    public static TextureRegion enemyGreen;
    public static TextureRegion enemyYellow;
    public static TextureRegion enemyRed;
    public static TextureRegion enemyBoss;
    public static TextureRegion enemyBulletGreen;
    public static TextureRegion enemyBulletYellow;
    public static TextureRegion enemyBulletRed;
    public static TextureRegion playerBulletGreen;
    public static TextureRegion playerBulletYellow;
    public static TextureRegion playerBulletRed;

    public static TextureRegion lifePowerUp;
    public static TextureRegion damagePowerUp;
    public static TextureRegion fireRatePowerUp;

    public static Sound shotSound;
    public static Sound explosionSound;

    public static BitmapFont font24;
    public static BitmapFont font48;
    //private static Class<TextureAtlas> TEXTURE_ATLAS = TextureAtlas.class;
    //private static Class<BitmapFont> BITMAP_FONT = BitmapFont.class;

    public static AssetManager load() {

        loadSplash();
        loadPlayer();
        loadEnemy();
        loadBullets();
        loadPowerUps();
        loadExplosion();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("truetypefont/DroidSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        parameter.borderWidth = 1;
        parameter.color = Color.YELLOW;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        font24 = generator.generateFont(parameter); // font size 24 pixels

        parameter.size = 48;
        font48 = generator.generateFont(parameter); // font size 24 pixels
        generator.dispose();

        assetManager = new AssetManager();

        shotSound = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));

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

        enemyBoss = new TextureRegion(new Texture("enemy_boss.png"));
        enemyBoss.flip(true, false);
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

    private static void loadExplosion() {
        explosion = new TextureRegion(new Texture("explosion.png"));
    }
}
