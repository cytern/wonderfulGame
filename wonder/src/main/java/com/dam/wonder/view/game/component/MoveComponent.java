package com.dam.wonder.view.game.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.dam.wonder.model.config.constant.Constant;
import com.dam.wonder.model.config.running.GameRunningData;
import com.dam.wonder.model.util.MathUtil;
import lombok.extern.slf4j.Slf4j;

@org.springframework.stereotype.Component
@Slf4j
public class MoveComponent extends Component {

    private final GameRunningData gameRunningData;
    private double degree = 0.0;

    public MoveComponent(GameRunningData gameRunningData) {
        this.gameRunningData = gameRunningData;
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
    }

    /**
     * 上
     */
    public void up(int speed){
        log.info("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
        if (MathUtil.isInGame(entity.getPosition())) {
            //二分专向
            if (entity.getRotation() != Constant.degree.DEGREE_UP) {

            }else {
                Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                        .mulLocal(speed);
                entity.translate(dir);
            }

        }

    }

    /**
     * 下
     */
    public void down(int speed) {
        log.info("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
        entity.rotateBy(5);
        if (MathUtil.isInGame(entity.getPosition())) {
            Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                    .mulLocal(speed);
            entity.translate(dir);
        }
    }

    /**
     * 左
     */
    public void left(int speed) {
        log.info("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
        if (MathUtil.isInGame(entity.getPosition())) {
            Vec2 dir = Vec2.fromAngle(entity.getRotation() - 270)
                    .mulLocal(speed);
            entity.translate(dir);
        }
    }


    /**
     * 右
     */
    public void right(int speed) {
        log.info("正在执行移动 实例数据为 [{}] , 角度为 [{}]",entity,entity.getRotation());
        if (MathUtil.isInGame(entity.getPosition())) {
            Vec2 dir = Vec2.fromAngle(entity.getRotation() - 270)
                    .mulLocal(speed);
            entity.translate(dir);
        }
    }


}
