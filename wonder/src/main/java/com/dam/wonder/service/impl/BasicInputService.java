package com.dam.wonder.service.impl;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.dam.wonder.service.InputService;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicInputService implements InputService {
    /**
     * 添加input事件
     *
     * @param userAction
     * @param keyCode
     */
    @Override
    public void addInputAction(UserAction userAction, KeyCode keyCode) {
        try {
            FXGL.getInput().addAction(userAction,keyCode);
        } catch (Exception e) {
            log.error("添加行动失败  失败原因为" ,e);
        }
    }
}
