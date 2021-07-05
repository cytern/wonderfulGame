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
        String GAME_NAME = "奇妙物语";
        Integer TARGET_FPS = 80;
        Integer TARGET_UPS = 30;
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
        /**
         * 基础
         */
        Integer ITEM_BASE = 0;
        /**
         * 建筑
         */
        Integer ITEM_BUILDING = 1;
        /**
         * 人物
         */
        Integer ITEM_HUMAN = 2;
        /**
         * 设备
         */
        Integer ITEM_EQUIPMENT = 3;
        /**
         * 关系
         */
        Integer ITEM_RELATION_SHIP = 4;
        /**
         * 技能
         */
        Integer ITEM_SKILLS = 5;
        /**
         * 地图
         */
        Integer ITEM_MAP = 6;

        Integer ITEM_TALK = 7;
    }

    /**
     * 情绪
     */
    interface Emotion {
        /**
         * 平静的
         */
        Integer EMOTION_CALM = 0;
        /**
         * 开心的
         */
        Integer EMOTION_HAPPY = 1;
        /**
         * 生气的
         */
        Integer EMOTION_ANGRY = 2;
        /**
         * 伤心的
         */
        Integer EMOTION_SAD = 3;
        /**
         * 害羞的
         */
        Integer EMOTION_SHYNESS = 4;
        /**
         * 疑惑地
         */
        Integer EMOTION_DOUBT = 5;
        /**
         * 欣喜若狂的
         */
        Integer EMOTION_ECSTATIC = 6;
        /**
         * 困倦的
         */
        Integer EMOTION_SLEEPY = 7;
        /**
         * 担忧的
         */
        Integer EMOTION_WORRIED = 8;
        /**
         * 性感的
         */
        Integer EMOTION_SEXY = 9;
    }
}
