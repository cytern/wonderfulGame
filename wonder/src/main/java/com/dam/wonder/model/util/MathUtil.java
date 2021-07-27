package com.dam.wonder.model.util;

import com.dam.wonder.model.config.running.GameRunningData;
import javafx.geometry.Point2D;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public static Double runSpeedAdd(boolean trade,Double aTime,Double maxSpeed,Double nowSpeed) {
        if (trade) {
            if (nowSpeed > maxSpeed ) {
                return nowSpeed;
            }else {
                return nowSpeed + maxSpeed/(float)10*aTime;
            }
        }else {
            maxSpeed = - maxSpeed;
            if (nowSpeed < maxSpeed) {
                return  nowSpeed;
            }else {
                return nowSpeed + maxSpeed/(float)10*aTime;
            }
        }
    }

    /**
     * 速度归零算法
     */
    public static Double runSpeedDown(Double nowSpeed,Double aTime) {
        if (nowSpeed.equals( 0d) ) {
            return 0d;
        }else if (nowSpeed >0.1){
            return nowSpeed - 0.01 - (float) (nowSpeed*1)/(float) (aTime*10);
        }else if (nowSpeed <-0.1){
            return nowSpeed + 0.01 - (float) (nowSpeed*1)/(float) (aTime*10);
        }else  {
            return 0d;
        }
    }


}
