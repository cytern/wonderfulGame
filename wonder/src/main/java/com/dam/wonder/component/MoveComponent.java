package com.dam.wonder.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;

public class MoveComponent extends Component {
    private double speedX = 0d;
    private double speedY = 0d;
    private double maxSpeed = 4d;
    @Override
    public void onUpdate(double tpf) {
            if (speedX != 0d) {
                    Vec2 dir = Vec2.fromAngle(entity.getRotation() - 360)
                            .mulLocal(speedX);
                    entity.translate(dir);
            }
            if (speedY != 0d) {
                    Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                            .mulLocal(speedY);
                    entity.translate(dir);
            }
    }

    public void up() {
        speedY = maxSpeed;
    }
    public void left() {
        speedX = -maxSpeed;
    }
    public void right() {
        speedX = maxSpeed;
    }
    public void down(){
        speedY = -maxSpeed;
    }
    public void stop() {
        speedX = 0d;
        speedY = 0d;
    }


}
