package com.github.aligator.stuckinaloop.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class RawInputHandler implements InputProcessor {
    private IInputListener listener;

    public RawInputHandler(IInputListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean keyDown(int keycode) {
        Vector2 direction = new Vector2();

        if (keycode == Input.Keys.W) {
            direction.y = 1;
        } else if (keycode == Input.Keys.S) {
            direction.y = -1;
        }

        if (keycode == Input.Keys.D) {
            direction.x = 1;
        } else if (keycode == Input.Keys.A) {
            direction.x = -1;
        }

        listener.move(direction);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        listener.move(new Vector2());

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
