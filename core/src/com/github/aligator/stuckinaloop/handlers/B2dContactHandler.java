package com.github.aligator.stuckinaloop.handlers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.github.aligator.stuckinaloop.components.CollisionComponent;
import com.github.aligator.stuckinaloop.components.Mapper;

public class B2dContactHandler implements ContactListener {
    public B2dContactHandler() {
    }

    @Override
    public void beginContact(Contact contact) {
        // get fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        // check if either fixture has an Entity object stored in the body's userData
        if (fa.getBody().getUserData() instanceof Entity) {
            Entity ent = (Entity) fa.getBody().getUserData();
            entityCollision(ent, fb);
            return;
        } else if (fb.getBody().getUserData() instanceof Entity) {
            Entity ent = (Entity) fb.getBody().getUserData();
            entityCollision(ent, fa);
            return;
        }
    }

    private void entityCollision(Entity ent, Fixture fb) {
        // check the collided Entity is also an Entity
        if (fb.getBody().getUserData() instanceof Entity) {
            Entity colEnt = (Entity) fb.getBody().getUserData();
            // get the components for this entity
            CollisionComponent colA = Mapper.collision.get(ent);
            CollisionComponent colB = Mapper.collision.get(colEnt);

            // set the CollisionEntity of the component
            if (colA != null) {
                colA.collidedEntity = colEnt;
            }
            if (colB != null) {
                colB.collidedEntity = ent;
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
