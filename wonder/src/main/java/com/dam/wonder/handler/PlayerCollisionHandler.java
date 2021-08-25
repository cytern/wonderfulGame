package com.dam.wonder.handler;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.dam.wonder.component.WhyComponent;
import com.dam.wonder.constant.EntityType;
import com.dam.wonder.factory.TalkFactory;
import com.dam.wonder.pojo.Talk;
import com.dam.wonder.ui.TalkScene;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class PlayerCollisionHandler extends CollisionHandler {
     private final EventHandler<MouseEvent> eventHandler;
    /**
     * 基类需要提供有参构造。制定两个实体类型
     */
    public PlayerCollisionHandler() {
        super(EntityType.PLANE, EntityType.NPC);
        eventHandler = event -> {
            //鼠标右键
            if (event.getButton() == MouseButton.SECONDARY) {
                List<Talk> talkList = TalkFactory.buildTalkList();
                TalkScene instance = TalkScene.getInstance();
                instance.show(talkList);
            }
        };
    }

    /**
     * 初次碰撞时触发
     */
    @Override
    protected void onCollisionBegin(Entity player, Entity npc) {

        //模拟点击按键p
        log.info("碰撞事件开始了");
        npc.getComponent(WhyComponent.class).play();
        npc.getViewComponent().addOnClickHandler(eventHandler);
    }

    @Override
    protected void onCollisionEnd(Entity player, Entity npc) {
        log.info("碰撞事件结束了");
        npc.getComponent(WhyComponent.class).stop();
        npc.getViewComponent().removeOnClickHandler(eventHandler);
    }
}
