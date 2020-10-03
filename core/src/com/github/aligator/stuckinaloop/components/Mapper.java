package com.github.aligator.stuckinaloop.components;

import com.badlogic.ashley.core.ComponentMapper;

public class Mapper {
    public static final ComponentMapper<PlayerComponent> player = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<EnemyComponent> enemy = ComponentMapper.getFor(EnemyComponent.class);
    public static final ComponentMapper<SpaceShipComponent> spaceShip = ComponentMapper.getFor(SpaceShipComponent.class);
    public static final ComponentMapper<BulletComponent> bullet = ComponentMapper.getFor(BulletComponent.class);
    public static final ComponentMapper<PowerUpComponent> powerUp = ComponentMapper.getFor(PowerUpComponent.class);
    public static final ComponentMapper<ShootingComponent> shooting = ComponentMapper.getFor(ShootingComponent.class);
    public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<BodyComponent> body = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<CollisionComponent> collision = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<DiscardingComponent> discarding = ComponentMapper.getFor(DiscardingComponent.class);
}
