package com.dam.wonder.view.game.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.dam.wonder.model.util.MathUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
public class MoveComponent extends Component {
    /**
     * 最高速度
     */
    private double maxSpeed = 1d;
    /**
     * 最低速度
     */
    private double lowerSpeed = 1;
    /**
     * 加速时间
     */
    private double accelerationTime = 2;
    /**
     * 面向
     */
    private int face = 1;
    /**
     * 现在的速度
     */
    private double nowSpeed = 4;
    /**
     * x 速度
     */
    private double speedX = 0d;
    /**
     * y 速度
     */
    private double speedY = 0d;


    public MoveComponent() {

    }

    /**
     * Called after the component is added to entity.
     */
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(entity.getCenter());
    }

    /**
     * Called each frame when not paused.
     *
     * @param tpf time per frame
     */
    @Override
    public void onUpdate(double tpf) {
        if (speedX != 0d) {
            if (true) {
                Vec2 dir = Vec2.fromAngle(entity.getRotation() - 360)
                        .mulLocal(speedX);
                entity.translate(dir);
            }
        }
        if (speedY != 0d) {
            if (true) {
                Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                        .mulLocal(speedY);
                entity.translate(dir);
            }
        }
        speedX = MathUtil.runSpeedDown(speedX,1d);
        speedY = MathUtil.runSpeedDown(speedY,1d);
    }

    /**
     * 上
     */
    public void up(){
        log.debug("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
                speedY = MathUtil.runSpeedAdd(true,accelerationTime,maxSpeed,speedY);

    }

    /**
     * 下
     */
    public void down() {
        log.debug("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
            speedY = MathUtil.runSpeedAdd(false,accelerationTime,maxSpeed,speedY);
    }

    /**
     * 左
     */
    public void left() {
        log.debug("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
        speedX = MathUtil.runSpeedAdd(false,accelerationTime,maxSpeed,speedX);
    }


    /**
     * 右
     */
    public void right() {
        log.debug("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
        speedX = MathUtil.runSpeedAdd(true,accelerationTime,maxSpeed,speedX);
    }


}
