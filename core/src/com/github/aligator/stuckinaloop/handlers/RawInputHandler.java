package com.github.aligator.stuckinaloop.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class RawInputHandler implements InputProcessor {
    private IInputListener listener;

    private boolean forward = false;
    private boolean backward = false;
    private boolean up = false;
    private boolean down = false;


    public RawInputHandler(IInputListener listener) {
        this.listener = listener;
    }

    private void doMove() {
        Vector2 direction = new Vector2();

        if (backward && !forward) {
            direction.x = -1;
        }
        if (forward && !backward) {
            direction.x = 1;
        }

        if (up && !down) {
            direction.y = 1;
        }
        if (down && !up) {
            direction.y = -1;
        }

        listener.move(direction);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W) {
            up = true;
        }

        if (keycode == Input.Keys.S) {
            down = true;
        }

        if (keycode == Input.Keys.D) {
            forward = true;
        }

        if (keycode == Input.Keys.A) {
            backward = true;
        }

        doMove();

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            up = false;
        }

        if (keycode == Input.Keys.S) {
            down = false;
        }

        if (keycode == Input.Keys.D) {
            forward = false;
        }

        if (keycode == Input.Keys.A) {
            backward = false;
        }

        doMove();

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
