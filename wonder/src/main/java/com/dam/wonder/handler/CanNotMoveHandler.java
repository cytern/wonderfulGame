package com.dam.wonder.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.dam.wonder.constant.EntityType;

/**
 * 不允许玩家越过墙体的碰撞监听
 */
public class CanNotMoveHandler extends CollisionHandler {
    /**
     * 制定碰撞的两个实体类型
     */
    public CanNotMoveHandler() {
        super(EntityType.PLANE, EntityType.WALL);
    }


    @Override
    protected void onCollisionBegin(Entity a, Entity b) {
        super.onCollisionBegin(a, b);
    }


    @Override
    protected void onCollisionEnd(Entity a, Entity b) {
        super.onCollisionEnd(a, b);
    }
}
