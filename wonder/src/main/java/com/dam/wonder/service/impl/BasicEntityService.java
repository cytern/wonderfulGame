package com.dam.wonder.service.impl;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.dam.wonder.service.EntityService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicEntityService implements EntityService {
    /**
     * 向世界添加实体 生成实体
     *
     * @param entity
     */
    @Override
    public void spawnEntity(Entity entity) {
        try {
            FXGL.getGameWorld().addEntity(entity);
        } catch (Exception e) {
            log.error("添加实体失败 失败原因为",e);
        }
    }
}
