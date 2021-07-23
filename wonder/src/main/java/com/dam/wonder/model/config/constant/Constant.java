package com.dam.wonder.model.config.constant;


/**
 * 常量表
 */
public interface Constant {
    /**
     * 游戏窗口大小
     */
    interface GameWindow {
        Double HEIGHT = 720d;
        Double WIDTH = 1080d;
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
        /**
         * 对话
         */
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

    /**
     * 季节
     */
    interface Season {
        /**
         * 春天
         */
        Integer SEASON_SPRING = 1;
        /**
         * 夏天
         */
        Integer SEASON_SUMMER = 2;
        /**
         * 秋天
         */
        Integer SEASON_AUTUMN = 3;
        /**
         * 冬天
         */
        Integer SEASON_WINTER = 4;
        /**
         * 全季节
         */
        Integer SEASON_ALL = 0;
    }

    /**
     * 星期
     */
    interface Week {
        /**
         * 全星期
         */
        Integer WEEK_ALL = 0;
        /**
         * 星期一
         */
        Integer WEEK_MONDAY = 1;
        /**
         * 星期二
         */
        Integer WEEK_TUESDAY = 2;
        /**
         * 星期三
         */
        Integer WEEK_WEDNESDAY = 3;
        /**
         * 星期四
         */
        Integer WEEK_THURSDAY = 4;
        /**
         * 星期五
         */
        Integer WEEK_FRIDAY= 5;
        /**
         * 星期六
         */
        Integer WEEK_SATURDAY = 6;
        /**
         * 星期日
         */
        Integer WEEK_SUNDAY = 7;
        /**
         * 工作日
         */
        Integer WEEK_WORKDAY = 8;


    }

    /**
     * 资产路径
     */
    interface assetsStatusDir {
        String DIR_TALK = "static/item/talk/BirthdayStory.json";
        String DIR_HUMAN = "static/item/human/Human.json";
    }
    interface idCode {
        Integer CODE_MAIN_HUMAN = 0;
    }

    interface degree {
        Double DEGREE_UP = 0.0;
        Double DEGREE_DOWN = 180.0;
        Double DEGREE_RIGHT = 90.0;
        Double DEGREE_LEFT = 270.0;
    }

}
