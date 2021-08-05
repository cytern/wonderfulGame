package com.dam.wonder.ui;

import com.almasb.fxgl.scene.SubScene;

/**
 * 对话窗口ui
 */
public class TalkScene extends SubScene {
    private boolean show = false;
   private TalkScene() {

   }
   private static TalkScene instance  = new TalkScene();
   public static synchronized TalkScene getInstance() {
       if (instance == null) {
           instance = new TalkScene();
       }
       return instance;
   }
   public void showUi(){

   }

}
