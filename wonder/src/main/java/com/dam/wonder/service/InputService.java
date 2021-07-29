package com.dam.wonder.service;

import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public interface InputService {
    /**
     * 添加input事件
     */
    public void addInputAction(UserAction userAction, KeyCode keyCode);

}
