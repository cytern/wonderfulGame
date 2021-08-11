package com.dam.wonder.component;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveComponent extends Component {
    private double speedX = 0d;
    private double speedY = 0d;
    private double maxSpeed = 4d;
    private double aTime = 1d;
    private boolean speedXAdd;
    private boolean speedYAdd;
    private int face = 1;
    private Rectangle faceUp = new Rectangle(50,50);
    private Rectangle faceDown  = new Rectangle(50,50);;
    private Rectangle faceLeft  = new Rectangle(50,50);;
    private Rectangle faceRight  = new Rectangle(50,50);;
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
                    }else {
                        tempFace = 2;
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
            }
            if (!speedXAdd) {
                slowDownSpeed(true);
            }
            if (!speedYAdd) {
                slowDownSpeed(false);
            }
           if (tempFace != face) {
               entity.getViewComponent().clearChildren();
               if (tempFace == 1) {
                   entity.getViewComponent().addChild(faceLeft);
               }else if (tempFace == 2){
                   entity.getViewComponent().addChild(faceRight);
               }else if (tempFace == 3) {
                   entity.getViewComponent().addChild(faceUp);
               }else {
                   entity.getViewComponent().addChild(faceDown);
               }
               face = tempFace;
           }
    }

    public MoveComponent () {
        faceUp.setFill(new ImagePattern(new Image("assets/textures/player-up.png")));
        faceDown.setFill(new ImagePattern(new Image("assets/textures/player-down.png")));
        faceLeft.setFill(new ImagePattern(new Image("assets/textures/player-left.png")));
        faceRight.setFill(new ImagePattern(new Image("assets/textures/player-right.png")));
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
        log.info("实体当前状态为 位置=[{}],速度Y=[{}],速度X =[{}]",entity.getPosition(),this.speedY,this.speedX);
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
