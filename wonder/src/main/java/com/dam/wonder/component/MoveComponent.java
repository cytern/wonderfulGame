package com.dam.wonder.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveComponent extends Component {
    private double speedX = 0d;
    private double speedY = 0d;

    /**
     * Called after the component is added to entity.
     */
    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
        entity.setScaleY(3);
        entity.setScaleX(3);
    }

    private double maxSpeed = 4d;
    private double aTime = 1d;
    private boolean speedXAdd;
    private boolean speedYAdd;
    private int face = 1;
    private final AnimationChannel up;
    private final AnimationChannel upHold;
    private final AnimationChannel down;
    private final AnimationChannel downHold;
    private final AnimationChannel right;
    private final AnimationChannel rightHold;
    private final AnimationChannel left;
    private final AnimationChannel leftHold;
    private final AnimatedTexture texture;
    @Override
    public void onUpdate(double tpf) {
//        log.info("当前状态下  x速度为=[{}]， Y速度为=[{}]  x加速状态为=[{}] Y加速状态为 =[{}]",speedX,speedY,speedXAdd,speedYAdd);
           int tempFace = face;
            if (speedX != 0d) {
                    Vec2 dir = Vec2.fromAngle(entity.getRotation() - 360)
                            .mulLocal(speedX);
                    entity.translate(dir);
                    if (speedX>0) {
                        tempFace = 1;
                    }else if (speedX<0){
                        tempFace = 2;
                    }
            }else {
                //转变面向
                if (tempFace == 1) {
                    tempFace = 5;
                }else if (tempFace == 2) {
                    tempFace = 6;
                }
            }
            if (speedY != 0d) {
                    Vec2 dir = Vec2.fromAngle(entity.getRotation() - 90)
                            .mulLocal(speedY);
                    entity.translate(dir);
                    if (speedY > 0) {
                        tempFace = 3;
                    }else {
                        tempFace = 4;
                    }
            }else {
                if (tempFace == 3) {
                    tempFace = 7;
                }else if (tempFace == 4) {
                    tempFace = 8;
                }
            }
            if (!speedXAdd) {
                slowDownSpeed(true);
            }
            if (!speedYAdd) {
                slowDownSpeed(false);
            }
           if (tempFace != face) {
               if (tempFace == 1) {
                   this.texture.loopAnimationChannel(left);
               }else if (tempFace == 2){
                   this.texture.loopAnimationChannel(right);
               }else if (tempFace == 3) {
                   this.texture.loopAnimationChannel(up);
               }else if (tempFace == 4){
                   this.texture.loopAnimationChannel(down);
               }else if (tempFace == 5) {
                   this.texture.loopAnimationChannel(leftHold);
               }else if (tempFace == 6) {
                   this.texture.loopAnimationChannel(rightHold);
               }else if (tempFace == 7){
                   this.texture.loopAnimationChannel(upHold);
               }else if (tempFace == 8) {
                   this.texture.loopAnimationChannel(downHold);
               }
               face = tempFace;
           }
    }

    public MoveComponent () {
        Image image = new Image("assets/textures/player.png");
        down = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 0, 3);
        downHold = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 1, 1);
        right = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 4, 7);
        rightHold = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 5, 5);
        leftHold = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 9, 9);
        left = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 8, 11);
        up = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 12, 15);
        upHold = new AnimationChannel(image, 4, 32, 38, Duration.seconds(1), 13, 13);
        texture = new AnimatedTexture(up);
        texture.loop();
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
//        log.info("实体当前状态为 位置=[{}],速度Y=[{}],速度X =[{}]",entity.getPosition(),this.speedY,this.speedX);
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
