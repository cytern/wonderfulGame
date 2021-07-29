package com.dam.wonder.factory;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.dam.wonder.component.MoveComponent;
import com.dam.wonder.constant.EntityType;


/**
 * 实体静态工厂
 */
public class CustomerEntityFactory {
    public static Entity createEntity(EntityType type){
        switch (type) {
            case PLANE -> {
               Entity entity = FXGL.entityBuilder().view("player.png").with(new MoveComponent()).build();
               entity.setType(EntityType.PLANE);
               return entity;
            }
            default -> {
                return null;
            }

    }
}
}
