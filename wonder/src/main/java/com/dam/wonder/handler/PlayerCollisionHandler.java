package com.dam.wonder.handler;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.dam.wonder.constant.EntityType;
import javafx.scene.input.KeyCode;

public class PlayerCollisionHandler extends CollisionHandler {
    /**
     * 基类需要提供有参构造。制定两个实体类型
     */
    public PlayerCollisionHandler() {
        super(EntityType.PLANE, EntityType.NPC);
    }

    /**
     * 初次碰撞时触发
     */
    @Override
    protected void onCollisionBegin(Entity player, Entity btn) {
        //模拟点击按键p
        FXGL.getInput().mockKeyPress(KeyCode.P);
    }
}
