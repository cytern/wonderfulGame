package com.dam.wonder.model.util;

import com.dam.wonder.model.config.running.GameRunningData;
import javafx.geometry.Point2D;

public class MathUtil {
    private static final GameRunningData gameRunningData;
    static {
        gameRunningData = ApplicationContextUtil.getBean(GameRunningData.class);
    }
    /**
     * 是否在游戏棋盘中
     */
    public static boolean isInGame (Point2D position) {
        //位置不能为负 且不能大于界面大小
        if (position.getY()<0|| position.getX()<0 || position.getX()>gameRunningData.getWindowWidth() || position.getY()> gameRunningData.getWindowHeight()) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 加速度计算 基本的变加速运动
     */
    public static Double runSpeedAdd(Double speed) {
        return null;
    }
    public static Double runSpeedDown(Double speed) {
        return null;
    }


}
