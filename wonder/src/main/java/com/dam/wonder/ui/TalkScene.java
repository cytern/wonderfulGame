package com.dam.wonder.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FontFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;

/**
 * 对话窗口ui
 */
public class TalkScene extends SubScene {
    private boolean show;
    private FontFactory levelFont;
    private double width;
    private double height;
    private HBox hBox;
    private StackPane stackPane;
    private List<String> list;
   private TalkScene() {
        show = false;
        levelFont = FXGL.getAssetLoader().loadFont("chinese.ttf");
        width = FXGL.getAppWidth();
        height = FXGL.getAppHeight()/4d;
       Rectangle bg = new Rectangle(width, height, Color.LIGHTCYAN);
       bg.setStroke(Color.BLUE);
       bg.setStrokeWidth(1.75);
       bg.setEffect(new DropShadow(28, Color.color(0,0,0, 0.9)));
       Text text = FXGL.getUIFactoryService().newText("给我爬", Color.BLACK,22);
       StackPane stackPane = new StackPane(bg, text);
       stackPane.setTranslateX(0);
       stackPane.setTranslateY(height*3);
       this.stackPane = stackPane;
       getContentRoot().getChildren().add(stackPane);
        getInput().addAction(new UserAction("remove") {
            @Override
            protected void onActionBegin() {
                if (list.size()>0) {

                }
                FXGL.getSceneService().popSubScene();
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
   public void show (List<String> list) {
        show = true;
        FXGL.getSceneService().pushSubScene(this);
        this.list = list;
   }


}
