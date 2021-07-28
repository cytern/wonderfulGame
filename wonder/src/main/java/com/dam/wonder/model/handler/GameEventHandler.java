package com.dam.wonder.model.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GameEventHandler {
    /**
     * 事件添加
     */
    public abstract void onAdd();

    /**
     * 事件更新或进行
     */
    public abstract void onUpdate();

    /**
     * 事件结束
     */
    public abstract void onEnded();

    public void startEvent() {
        //游戏暂停

        log.debug("进入事件状态  尝试暂停游戏");

        onAdd();
        onUpdate();
        onEnded();
    }
}
