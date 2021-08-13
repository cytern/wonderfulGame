package com.dam.wonder.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FontType;
import com.dam.wonder.pojo.Talk;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * 对话窗口ui
 */
public class TalkScene extends SubScene {
    private final List<Talk> list = new ArrayList<>();
    private final Text text;
    private final Rectangle rectangle;
    private StackPane stackPane;
    private final ImageView playerDrawAsk;
    private final ImageView playerDrawAnswer;

    /**
     * 构造方法
     */
    private TalkScene() {
       double width = FXGL.getAppWidth();
       double height = FXGL.getAppHeight() / 4d;
       //初始化ui的框体图片
       Image image = new Image("assets/ui/pics/talk_bg.png");
       ImageView imageView = new ImageView(image);
       //保持长宽比
       imageView.setFitWidth(width);
       imageView.setFitHeight(height);
      imageView.setTranslateX(0);
      imageView.setTranslateY(height *3 + 30);
      //初始化两立绘的位置
       playerDrawAnswer =   new ImageView();
       playerDrawAsk  =   new ImageView();
       playerDrawAnswer.setPreserveRatio(true);
       playerDrawAsk.setPreserveRatio(true);
       playerDrawAnswer.setFitWidth(width/3d);
       playerDrawAsk.setFitWidth(width/3d);
       playerDrawAsk.setTranslateX(20);
       playerDrawAnswer.setTranslateX((width*2.6)/4);
       playerDrawAsk.setTranslateY(height*2.5 );
       playerDrawAnswer.setTranslateY(height*2.5 );

      text = FXGL.getUIFactoryService().newText("", Color.PINK, FontType.GAME,22);
       text.visibleProperty().set(true);
       text.setFill(new LinearGradient(0, 0, 1, 2, true, CycleMethod.REPEAT, new Stop(0, Color.AQUA), new Stop(0.5f, Color.RED)));
       text.setStrokeWidth(1);
       text.setStroke(Color.PINK);
       FXGL.animationBuilder(this)
               .repeatInfinitely()
               .duration(Duration.seconds(2))
               .autoReverse(true)
               .scale(playerDrawAnswer)
               .from(new Point2D(1, 1))
               .to(new Point2D(1.05, 1.05))
               .buildAndPlay();
        FXGL.animationBuilder(this)
                .repeatInfinitely()
                .duration(Duration.seconds(2))
                .autoReverse(true)
                .scale(playerDrawAsk)
                .from(new Point2D(1, 1))
                .to(new Point2D(1.05, 1.05))
                .buildAndPlay();
       text.setTranslateX(20);
       text.setTranslateY(height *3 +20);
       rectangle = new Rectangle(width,height);
       rectangle.setTranslateX(0);
       rectangle.setTranslateY(height*3);
       rectangle.setOpacity(0);
       getContentRoot().getChildren().add(imageView);
       //添加一个input事件 鼠标左建进一步对话
        getInput().addAction(new UserAction("remove") {
            @Override
            protected void onActionBegin() {
                getContentRoot().getChildren().remove(stackPane);
                getContentRoot().getChildren().remove(playerDrawAsk);
                getContentRoot().getChildren().remove(playerDrawAnswer);
                nextTalk();
            }
        }, MouseButton.PRIMARY);
   }
   private static TalkScene instance  = new TalkScene();
   public static synchronized TalkScene getInstance() {
       if (instance == null) {
           instance = new TalkScene();
       }
       return instance;
   }
   public synchronized void  show (List<Talk> talkList) {
       FXGL.getSceneService().pushSubScene(this);
       ArrayList<Talk> arrayList = new ArrayList<>();
       arrayList.addAll(talkList);
       arrayList.forEach(t -> {
            int length = t.getTalk().length();
            if (length>20) {
                for (int i = 0; i < length/20; i++) {
                    String substring = t.getTalk().substring(0, Math.min(t.getTalk().length(), 20));
                    Talk talk = new Talk();
                    talk.setTalk(substring);
                    talk.setAsk(t.isAsk());
                    talk.setShowDrawUrl(t.getShowDrawUrl());
                    list.add(talk);
                    if (t.getTalk().length()>20) {
                        t.setTalk(t.getTalk().substring(20,t.getTalk().length() -20));
                    }else {
                        list.add(t);
                        break;
                    }
                }
            }else {
                list.add(t);
            }
        });
       nextTalk();

   }

    /**
     * 下一句话
     */
   public void nextTalk() {
       if (list.size()<1) {
           getInput().clearAll();
           FXGL.getSceneService().popSubScene();
       }else {
           Talk s = list.get(0);
           list.remove(s);
           showTalk(s);
       }
   }

    /**
     * 展示文字
     */
   private void showTalk(Talk talk) {
       text.setText(talk.getTalk());
       StackPane stackPane = new StackPane( rectangle, text);
       this.stackPane =stackPane;
       if (talk.isAsk()) {
           playerDrawAsk.setImage(new Image(talk.getShowDrawUrl()));
           getContentRoot().getChildren().add(playerDrawAsk);
       }else {
           playerDrawAnswer.setImage(new Image(talk.getShowDrawUrl()));
           getContentRoot().getChildren().add(playerDrawAnswer);
       }
       getContentRoot().getChildren().add(stackPane);

   }


}
