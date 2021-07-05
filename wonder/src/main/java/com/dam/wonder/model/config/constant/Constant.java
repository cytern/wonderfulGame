package com.dam.wonder.model.config.constant;

import org.lwjgl.system.CallbackI;

/**
 * 常量表
 */
public interface Constant {
    /**
     * 游戏窗口大小
     */
    interface GameWindow {
        Integer HEIGHT = 720;
        Integer WIDTH = 1080;
    }

    /**
     * 游戏组件大小
     */
    interface GameItemSize {
        Integer HEIGHT_VERTICAL = 64;
        Integer WIDTH_VERTICAL = 64;
        Integer HEIGHT_ICON = 16;
        Integer WIDTH_ICON = 15;
    }

    /**
     * 游戏类型
     */
    interface ItemCode {
        Integer ITEM_BASE = 0;
        Integer ITEM_BUILDING = 1;
        Integer ITEM_HUMAN = 2;
        Integer ITEM_EQUIPMENT = 3;
        Integer ITEM_RELATION_SHIP = 4;
        Integer ITEM_SKILLS = 5;
        Integer ITEM_MAP = 6;
    }
}
