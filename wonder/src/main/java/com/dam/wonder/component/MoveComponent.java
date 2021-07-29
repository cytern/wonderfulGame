package com.dam.wonder.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MoveComponent extends Component {
    private double speedX = 0d;
    private double speedY = 0d;
    private double maxSpeed = 4d;
    private double aTime = 1d;
    private boolean speedXAdd;
    private boolean speedYAdd;
    @Override
    public void onUpdate(double tpf) {
//        log.info("当前状态下  x速度为=[{}]， Y速度为=[{}]  x加速状态为=[{}] Y加速状态为 =[{}]",speedX,speedY,speedXAdd,speedYAdd);
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
            if (!speedXAdd) {
                slowDownSpeed(true);
            }
            if (!speedYAdd) {
                slowDownSpeed(false);
            }
    }

    public void up() {
        changeSpeed(true,false);
    }
    public void left() {
        changeSpeed(false,true);
    }
    public void right() {
       changeSpeed(true,true);
    }
    public void down(){
       changeSpeed(false,false);
    }
    public void stop() {
        speedX = 0d;
        speedY = 0d;
    }
    public void stopX() {
       speedXAdd = false;
    }
    public void stopY() {
       speedYAdd = false;
    }

    /**
     * 改变移动速度 主动改变
     * @param upOrDown
     * @param xOrY
     */
    private  void changeSpeed(boolean upOrDown,boolean xOrY) {
        if (xOrY) {
            speedXAdd = true;
            if (upOrDown) {
                if (speedX < maxSpeed) {
                    speedX = speedX + (float)maxSpeed/(10*aTime) + 0.01;
                }
            }else {
                if (speedX > -maxSpeed) {
                    speedX = speedX - (float)maxSpeed/(10f*aTime) - 0.01;
                }
            }
        }else {
            speedYAdd = true;
            if (upOrDown) {
                if (speedY < maxSpeed) {
                    speedY = speedY + (float)maxSpeed/(10f*aTime) + 0.01;
                }
            }else {
                if (speedY > -maxSpeed) {
                    speedY = speedY - (float)maxSpeed/(10f*aTime) - 0.01;
                }
            }
        }
    }

    /**
     * 速度减少 被动减速
     * @param xOrY
     */
    private void slowDownSpeed(boolean xOrY) {
        if (xOrY) {
            if (speedX > 0.5) {
                speedX = speedX - (float)speedX/10 -0.01;}
            else if (speedX< -0.5){
                speedX = speedX - (float)speedX/10 +0.01;
            }else {
                speedX = 0d;
            }
        }else {
            if (speedY > 0.5) {
                speedY = speedY - (float)speedY/10 -0.01;}
            else if (speedY< -0.5){
                speedY = speedY - (float)speedY/10 +0.01;
            }else {
                speedY = 0d;
            }
        }
    }



    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getaTime() {
        return aTime;
    }

    public void setaTime(double aTime) {
        this.aTime = aTime;
    }
}
